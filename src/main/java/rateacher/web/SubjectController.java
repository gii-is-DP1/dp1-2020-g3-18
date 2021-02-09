package rateacher.web;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import rateacher.model.Dean;
import rateacher.model.Student;
import rateacher.model.Subject;
import rateacher.model.Teacher;
import rateacher.model.TeachingPlan;
import rateacher.service.DeanService;
import rateacher.service.StudentService;
import rateacher.service.SubjectService;
import rateacher.service.TeacherService;
import rateacher.service.TeachingPlanService;
import rateacher.util.SubjectValidator;

@Controller
public class SubjectController {
	private static final String VIEW_SUBJECT_CREATE_FORM ="subjects/newSubject";

	
	private final SubjectService subjectService;
	private final TeacherService teacherService;
	private final StudentService studentService;
	private final DeanService deanService;
	private final TeachingPlanService teachingPlanService;
	
	@Autowired
	public SubjectController(SubjectService subjectService, TeacherService teacherService, StudentService studentService,DeanService deanService, TeachingPlanService teachingPlanService) {
		this.subjectService = subjectService;
		this.teacherService = teacherService;
		this.studentService = studentService;
		this.deanService = deanService;
		this.teachingPlanService = teachingPlanService;
	}
	
	@InitBinder
	public void setAllowedFields(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}
	
	@InitBinder("subject")
	public void initSubjectBinder(WebDataBinder dataBinder) {
		dataBinder.setValidator(new SubjectValidator());
	}

	@GetMapping(value = {"/subjects/new"})
	public String newSubject(ModelMap model) {
		
		Subject subject = new Subject();
		model.put("subjects", subject);
		return VIEW_SUBJECT_CREATE_FORM;

	}
	
	@GetMapping(value = { "/subjects" })
	public String showSubjectsList(Map<String, Object> model) {
		List<Subject> subjects = new ArrayList<Subject>(subjectService.findAll());
		model.put("subjects", subjects);
		String principal = SecurityContextHolder.getContext().getAuthentication().getName();
		Dean dean = this.deanService.findDeanByUsername(principal);
		Student student = this.studentService.findStudentByUsername(principal);
        Boolean condition = (student != null);
        Boolean esDean = (dean != null);
        model.put("esDean", esDean);
        model.put("condition", condition);
        model.put("student", student);
        model.put("dean", dean);
		return "subjects/subjectsList";

	}
	
	@GetMapping(value = { "/subjects/{subjectId}/teachers" })
	public String showTeacherByCollegesList(@PathVariable int subjectId, Map<String, Object> model) {
		Collection<Teacher> teachers = this.teacherService.findTeachers();
		model.put("subjectId", subjectId);
		model.put("teachers", teachers);
		return "subjects/teachersList";
	}
	
	@GetMapping(value = { "/subjects/{subjectId}/teachers/{teacherId}/add" })
	public String addTeacherToCollege(@PathVariable int subjectId, @PathVariable int teacherId, Map<String, Object> model) {
		model.put("subjectId", subjectId);
		Teacher teacherAdded = this.teacherService.findTeacherById(teacherId);
		model.put("teacher", teacherAdded);
		return "subjects/AreYouSureView";
	}
	
	@PostMapping("/subjects/{subjectId}/teachers/{teacherId}/add")
	public String addTeacherToCollegeProcess(@PathVariable int subjectId, @PathVariable int teacherId, Teacher teacherd, Map<String, Object> model, BindingResult result) {
		Subject subject = subjectService.findSubjectById(subjectId);
		List<Integer> teacherIds = new ArrayList<Integer>();
		for(Teacher teacher: subject.getTeachers()) {
			teacherIds.add(teacher.getId());
		}
		Teacher teacherAdded = this.teacherService.findTeacherById(teacherId);
		teacherAdded.setName(teacherAdded.getFirstName());
		teacherAdded.getSubjects().add(subject);	
		this.teacherService.save(teacherAdded);
		Collection<Subject> subjects = this.subjectService.findAll();
		model.put("subjects", subjects);
		return "subjects/subjectsList";
		
	}
  
  @PostMapping(value = {"/subjects/new"})
	public String processCreationForm(@Valid Subject subject, BindingResult result, ModelMap modelMap) {
	  	if (result.hasErrors()) {
			modelMap.addAttribute("subjects", subject);
			return VIEW_SUBJECT_CREATE_FORM;
		}
		
		else {
			subjectService.save(subject);
			modelMap.addAttribute("message", "Subject successfully saved!");
			return "redirect:/subjects";
		}
	}
	
	@GetMapping(path="/subjects/delete/{subjectId}")
	public String deleteSubject(@PathVariable("subjectId") int subjectId, ModelMap modelMap){
		String view = "subjects/subjectsList";
		Optional<Subject> subject = Optional.ofNullable(subjectService.findSubjectById(subjectId));
		if(subject.isPresent()) {
			subjectService.delete(subject.get());
			modelMap.addAttribute("message", "Subject successfully deleted!");
			view = showSubjectsList(modelMap);
		} else {
			modelMap.addAttribute("message", "Subject not found!");
			view = showSubjectsList(modelMap);
		}
		return view;
	}
	@GetMapping(value = {"/subjects/{subjectId}/newTeachingPlan"})
	public String newTeachingPlan(ModelMap model, @PathVariable int subjectId) {
		TeachingPlan teachingPlan = new TeachingPlan();
		model.put("teachingPlan", teachingPlan);
		Subject subject = subjectService.findSubjectById(subjectId);
		model.put("subjects", subject);
		return "teachingPlans/newTeachingPlan";

	}

	
	@PostMapping(value = "subjects/{subjectId}/newTeachingPlan")
	public String processCreationForm(@PathVariable int subjectId, @Valid TeachingPlan teachingPlan, BindingResult result,
			ModelMap model) {
		if (result.hasErrors()) {
			Subject subject = subjectService.findSubjectById(subjectId);
			model.put("subject", subject);
			return "teachingPlans/newTeachingPlan";
		} else {
			teachingPlanService.save(teachingPlan);
			Subject subject = subjectService.findSubjectById(subjectId);
			subject.setTeachingPlan(teachingPlan);
			subjectService.save(subject);
			model.put("teachingPlan", teachingPlan);
			model.put("subject", subject);
			Collection<Subject> subjects = this.subjectService.findAll();
			model.put("subjects", subjects);
			return "subjects/subjectsList";
		}
	}
	
	
	@GetMapping(path="/subjects/deleteTeachingPlan/{subjectId}")
	public String deleteTeachingPlan(@PathVariable("subjectId") int subjectId, ModelMap modelMap){
		String view = "subjects/subjectsList";
		Optional<Subject> subject = Optional.ofNullable(subjectService.findSubjectById(subjectId));
		if(subject.isPresent()) {
			subjectService.deleteTeachingPlan(subject.get());
			modelMap.addAttribute("message", "Teaching Plan successfully deleted!");
			view = showSubjectsList(modelMap);
		} else {
			modelMap.addAttribute("message", "Teachin Plan not found!");
			view = showSubjectsList(modelMap);
		}
		return view;
	}
	
}

