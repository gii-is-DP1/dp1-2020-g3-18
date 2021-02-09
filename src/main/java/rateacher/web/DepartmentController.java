package rateacher.web;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import rateacher.model.Department;
import rateacher.model.Subject;
import rateacher.model.Teacher;
import rateacher.service.DepartmentService;
import rateacher.service.SubjectService;
import rateacher.service.TeacherService;

@Controller
public class DepartmentController {

	private final DepartmentService departmentService;
	private final SubjectService subjectService;
	private final TeacherService teacherService;
	
	@Autowired
	public DepartmentController(DepartmentService departmentService, SubjectService subjectService, TeacherService teacherService) {
		this.departmentService = departmentService;
		this.subjectService	= subjectService;
		this.teacherService	=teacherService;
	}
	
	@GetMapping(value = { "/departments" })
	public String showDepartmentList(Map<String, Object> model) {

		Collection<Department> departments = this.departmentService.findAll();
		model.put("departments", departments);
		
		return "departments/departmentsList";
		
	}
	
	@GetMapping(value = {"/departments/{departmentId}/relatedSubjects"})
	public String listSubjectsFromDepartmentId(@PathVariable("departmentId") int departmentId, Map<String, Object> model) {
		Department department = this.departmentService.findDepartmentById(departmentId);
		model.put("department", department);

		List<Subject> subjects = subjectService.findSubjectsFromDepartmentId(departmentId);

		model.put("subjects", subjects);
		return "departments/relatedSubjects";
	}
	
	@GetMapping(value = { "/departments/{departmentId}/relatedTeachers" })
	public String listTeachersFromDepartmentId(@PathVariable("departmentId") int departmentId, Map<String, Object> model) {
		Department department = this.departmentService.findDepartmentById(departmentId);
		model.put("department", department);
		List<Teacher> teachers = new ArrayList<Teacher>();
		for(Teacher t : (List<Teacher>) teacherService.findTeachers()) {
			if(t.getDepartments().contains(department)) teachers.add(t);
		}
		model.put("teachers", teachers);
		return "departments/relatedTeachers";
	}
}
