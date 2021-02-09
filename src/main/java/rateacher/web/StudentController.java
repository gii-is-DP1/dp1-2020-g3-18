package rateacher.web;

import java.util.List;
import java.util.Collection;
import java.util.Map;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;

import rateacher.model.Student;
import rateacher.model.Subject;
import rateacher.model.Teacher;
import rateacher.service.StudentService;
import rateacher.service.TeacherService;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller

public class StudentController {


	private static final String VIEWS_STUDENT_CREATE_OR_UPDATE_FORM = "students/createOrUpdateStudentForm";

	private final StudentService studentService;
	private final TeacherService teacherService;


	@Autowired
	public StudentController(StudentService studentService, TeacherService teacherService) {

		this.studentService = studentService;
		this.teacherService = teacherService;

	}

	@InitBinder
	public void setAllowedFields(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}


	@GetMapping(value = { "/students" })
	public String showStudentsList(Map<String, Object> model) {

		Collection<Student> students = this.studentService.findStudents();
		model.put("students", students);
		return "students/studentsList";

	}
	
	
	@GetMapping(value = "/students/new")
	public String initCreationForm(Map<String, Object> model) {
		Student student = new Student();
		model.put("student", student);
		return VIEWS_STUDENT_CREATE_OR_UPDATE_FORM;
	}

	@PostMapping(value = "/students/new")
	public String processCreationForm(@Valid Student student, BindingResult result) {
		if (result.hasErrors()) {
			return VIEWS_STUDENT_CREATE_OR_UPDATE_FORM;
		}
		else {
			//creating student, user and authorities
			this.studentService.saveStudent(student);
			
			return "redirect:/students/" + student.getId();
		}
	}
	

	@GetMapping(value = "/subjects/mySubjects/{studentId}")
	public ModelAndView listMySubjects(@PathVariable("studentId") int studentId) {
		ModelAndView mav = new ModelAndView("students/mySubjects");
		List<Subject> subjects = studentService.findMySubjects(studentId);
		String principal = SecurityContextHolder.getContext().getAuthentication().getName();
        Student student = this.studentService.findStudentByUsername(principal);
        if(studentId != student.getId()) {
        	mav = new ModelAndView("redirect:/exception");
        	return mav; 
        }
        mav.addObject("student", student);
		mav.addObject("subjects", subjects);
		mav.addObject("studentId", studentId);
		return mav;
	}
	
	@GetMapping("students/{studentId}/showRatedTeachers")
	public ModelAndView showMyRatedTeachers(@PathVariable("studentId") int studentId) {
		ModelAndView mav = new ModelAndView("teachers/myRatedTeachersList");
		Student student = studentService.findStudentById(studentId);
		Collection<Teacher> teachers = teacherService.findTeacherByStudentId(student.getId());
		String principal = SecurityContextHolder.getContext().getAuthentication().getName();
        Student studentSession = this.studentService.findStudentByUsername(principal);
        if(studentSession.getId() != studentId) {
        	mav = new ModelAndView("redirect:/exception");
        	return mav;
        }
		//"teachers" hace referencia a como se va a llamar en el .jsp
		mav.addObject("teachers",teachers);
		return mav;
	}
	
	@GetMapping("students/{studentId}/teacherToRate")
	public ModelAndView teacherToRate(@PathVariable("studentId") int studentId) {
		ModelAndView mav = new ModelAndView("teachers/teacherToRate");
		String principal = SecurityContextHolder.getContext().getAuthentication().getName();
        Student studentSession = this.studentService.findStudentByUsername(principal);
		if(studentSession.getId() != studentId) {
        	mav = new ModelAndView("redirect:/exception");
        	return mav;
        }
		Collection<Teacher> teachers = teacherService.teachersToRate(studentId);
		mav.addObject("teachers",teachers);
		return mav;
	}
	


}
