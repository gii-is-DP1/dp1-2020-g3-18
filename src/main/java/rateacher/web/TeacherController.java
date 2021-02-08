
package rateacher.web;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;


import rateacher.model.Subject;
import rateacher.model.Subjects;
import rateacher.service.SubjectService;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import rateacher.model.PersonalExperience;
import rateacher.model.Score;
import rateacher.model.Student;
import rateacher.model.Teacher;
import rateacher.model.Teachers;
import rateacher.service.ScoreService;
import rateacher.service.StudentService;
import rateacher.service.TeacherService;
import rateacher.util.DuplicatedStudentScoreException;
import rateacher.util.ScoreValidator;

import org.springframework.beans.BeanUtils;



@Controller
public class TeacherController {

	private final TeacherService teacherService;
	private final StudentService studentService;
	private final ScoreService scoreService;
	private final SubjectService subjectService;

	@Autowired
	public TeacherController(TeacherService teacherService, StudentService studentService, ScoreService scoreService, SubjectService subjectService) {
		this.teacherService = teacherService;
		this.studentService = studentService;
		this.scoreService = scoreService;
		this.subjectService = subjectService;
	}

	@InitBinder
	public void setAllowedFields(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}

	@InitBinder("score")
	public void initTeacherBinder(WebDataBinder dataBinder) {
		dataBinder.setValidator(new ScoreValidator(scoreService, studentService, teacherService));
	}
	
	@GetMapping(value = { "/teachers" })
	public String showTeacherList(Map<String, Object> model) {
		String principal = SecurityContextHolder.getContext().getAuthentication().getName();
		Student student = this.studentService.findStudentByUsername(principal);
		Teacher teacher = this.teacherService.findTeacherByUsername(principal); 
		model.put("student", student);
		Collection<Teacher> teachers = this.teacherService.findTeachers();
		model.put("teachers", teachers);
		Boolean condition = (teacher != null);
		model.put("condition", condition);
		if(teacher != null)
		model.put("teacherr", teacher);
		return "teachers/teachersList";

	}

	@GetMapping(value = { "/teachers.xml" })
	public @ResponseBody Teachers showResourcesTeacherList() {

		Teachers teachers = new Teachers();
		teachers.getTeachersList().addAll(this.teacherService.findTeachers());
		return teachers;
	}

	@GetMapping(value = "teachers/{teacherId}")
	public ModelAndView showTeacher(@PathVariable("teacherId") int teacherId, Map<String, Object> model) {
		ModelAndView mav = new ModelAndView("teachers/teacherDetails");
		Teacher teacher = this.teacherService.findTeacherById(teacherId);
		mav.addObject("teacher", this.teacherService.findTeacherById(teacherId));
		String principal = SecurityContextHolder.getContext().getAuthentication().getName();
		PersonalExperience p = teacher.getPersonalExperience();
		model.put("personalExperience", p);
		Student student = this.studentService.findStudentByUsername(principal);
	        if(student!=null) {
	        	Collection<Subject> subjectsStudent = student.getSubjects(); 
	        	Collection<Subject> subjectsTeacher = teacher.getSubjects();
	            Integer match = 1;
	             for (Subject s: subjectsStudent) {
	             	if (subjectsTeacher.contains(s)) {
	             		match = match-match;
	             	}
	             }
	             Boolean studentAuth = ((match==0) && (student != null));
	             model.put("studentAuth", studentAuth);
	        }
		return mav;
	}

	@GetMapping(value = { "/teachersWithScore" })
	public String showTeacherWithScore(Map<String, Object> model) {

		Collection<Teacher> teachers = this.teacherService.showTeacherWithScore();
		model.put("teachers", teachers);
		return "teachers/teachersWithScore";
	}

	@GetMapping(value = "/findTeachers")
	public String initFindForm(Map<String, Object> model) {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		Teacher t = teacherService.findTeacherByUsername(username);
		if(t != null) {
			model.put( "teacher", t);
			Boolean condition = true;
			model.put("condition", condition);
		}
		model.put("teachers", new Teacher());
		return "teachers/findTeachers";
	}

	@GetMapping(value = "/teachersFound")
	public String processFindForm(Teacher teacher, BindingResult result, Map<String, Object> model) {

		if (teacher.getFirstName() == null) {
			teacher.setFirstName(""); // empty string signifies broadest possible search
		}
		// find teachers by first name
		List<Teacher> results = this.teacherService.findTeacherByFirstName(teacher.getFirstName());
		if (results.isEmpty()) {
			result.rejectValue("firstName", "notFound", "not found");
			model.put("teachers", new Teacher());
			return "teachers/findTeachers";
		} else if (results.size() == 1) {
			// 1 teacher found
			teacher = results.iterator().next();
			return "redirect:/teachers/" + teacher.getId();
		} else {
			// multiple teachers found
			model.put("selections", results);

			String principal = SecurityContextHolder.getContext().getAuthentication().getName();
			Teacher teacherr = this.teacherService.findTeacherByUsername(principal); 
			if(teacherr != null) model.put("teacher", teacherr);
			return "teachers/teachersList";
		}
	}

	@GetMapping(value = { "teachers/{teacherId}/scores" })
	public String showTeacherScoreList(@PathVariable("teacherId") int teacherId, Map<String, Object> model) {
		Collection<Score> scores = this.teacherService.findScoresByTeacherId(teacherId);
		model.put("scores", scores);
		String principal = SecurityContextHolder.getContext().getAuthentication().getName();
        Teacher teacher = this.teacherService.findTeacherByUsername(principal);
        Boolean teacherAuth = (teacher != null);
        model.put("teacherAuth", teacherAuth);
		return "scores/scoresList";
	}


	@GetMapping(value = { "teachers/{teacherId}/scores/new" })
	public String initCreationForm(@PathVariable int teacherId, ModelMap model) { // para crear el modelo que va a la
																					// vista.
		Score score = new Score();
		Teacher teacher = this.teacherService.findTeacherById(teacherId);
		score.setTeacher(teacher);
		model.put("score", score);
		model.put("teacher", teacher);
		return "scores/createForm";
	}

	@PostMapping(value = "teachers/{teacherId}/scores/new")
	public String processCreationForm(@PathVariable int teacherId, @Valid Score score, BindingResult result,
			ModelMap model) {
		if (result.hasErrors()) {
			model.put("score", score);
			Teacher teacher = teacherService.findTeacherById(teacherId);
			model.put("teacher", teacher);
			return "scores/createForm";
		} else {
			String principal = SecurityContextHolder.getContext().getAuthentication().getName();
			Student student = this.studentService.findStudentByUsername(principal);
			score.setStudent(student);
			Teacher teacher = this.teacherService.findTeacherById(teacherId);
			score.setTeacher(teacher);
			this.scoreService.saveScore2Create(score, teacherId);
			return "redirect:/teachers/{teacherId}/scores";
		}
	}

	@GetMapping(value = "teachers/{teacherId}/scores/{scoreId}/edit")
	public String initEditForm(@PathVariable int teacherId, @PathVariable int scoreId, ModelMap model) {
		Score score = this.scoreService.findScoreById(scoreId);
		model.put("score", score);
		Teacher teacher = this.teacherService.findTeacherById(teacherId);
		model.put("teacher", teacher);
		return "scores/createForm";
	}

	@PostMapping(value = "teachers/{teacherId}/scores/{scoreId}/edit")
	public String processEditForm(@PathVariable int teacherId, @PathVariable int scoreId, @Valid Score score,
			BindingResult result, ModelMap model) {
		if (result.hasErrors()) {
			Teacher teacher = this.teacherService.findTeacherById(teacherId);
			model.put("teacher", teacher);
			model.put("score", score);
			return "scores/createForm";
		} else {
			Score uno = this.scoreService.findScoreById(scoreId);
			BeanUtils.copyProperties(score, uno, "id", "teacher", "student");
			this.scoreService.saveScore(score);
			return "redirect:/teachers/{teacherId}/scores";
		}
	}
	
	@GetMapping(value = {"teachers/{subjectId}/subjectsTeached"})
	public ModelAndView listMySubjects(@PathVariable("subjectId") int subjectId) {
		ModelAndView mav = new ModelAndView("teachers/subjectListTeached");
		List<Teacher> teachers = new ArrayList<Teacher>();
		Subject s = this.subjectService.findSubjectById(subjectId);
		for(Teacher t: this.teacherService.findTeachers()) {
			if(t.getSubjects().contains(s)) {
				teachers.add(t);
			}
		}
		mav.addObject("teachers", teachers);
		return mav;
	}

	@GetMapping(value ="teachers/{teacherId}/studentsRated")
	public String showStudentsRatedATeacher(@PathVariable("teacherId") int teacherId, Map<String, Object> model) {
		Collection<Student> students = this.studentService.StudentsRatedATeacher(teacherId);
		model.put("students", students);
		return "students/studentRatedATeacher";
	}
  	@GetMapping(path="/teachers/{teacherId}/scores/delete/{scoreId}")
	public String deleteScore(@PathVariable("teacherId") int teacherId, @PathVariable("scoreId") int scoreId, ModelMap modelMap){
		String view = "scores/scoresList";
		Optional<Score> score = Optional.ofNullable(scoreService.findScoreById(scoreId));
		if(score.isPresent()) {
			scoreService.delete(score.get());
			modelMap.addAttribute("message", "Score successfully deleted!");
			view = showTeacherScoreList(teacherId, modelMap);
		} else {
			modelMap.addAttribute("message", "Score not found!");
			view = showTeacherScoreList(teacherId, modelMap);
		}
		return view;
	}

}
