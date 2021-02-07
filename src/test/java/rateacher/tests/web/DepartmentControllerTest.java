package rateacher.tests.web;

import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.assertj.core.util.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import rateacher.configuration.SecurityConfiguration;
import rateacher.model.College;
import rateacher.model.Department;
import rateacher.model.PersonalExperience;
import rateacher.model.Score;
import rateacher.model.Student;
import rateacher.model.Subject;
import rateacher.model.Teacher;
import rateacher.model.TeachingPlan;
import rateacher.model.User;
import rateacher.repository.CollegeRepository;
import rateacher.repository.TeacherRepository;
import rateacher.service.CollegeService;
import rateacher.service.DeanService;
import rateacher.service.DepartmentService;
import rateacher.service.ScoreService;
import rateacher.service.StudentService;
import rateacher.service.SubjectService;
import rateacher.service.TeacherService;
import rateacher.web.DepartmentController;
import rateacher.web.StudentController;

@WebMvcTest(controllers = DepartmentController.class,
excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class),
excludeAutoConfiguration = SecurityConfiguration.class)
public class DepartmentControllerTest{
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private DepartmentService departmentService;
	
	@MockBean
	private TeacherService teacherService;
	
	@MockBean
	private StudentService studentService;
	
	@MockBean
	private SubjectService subjectService;
	

	Teacher teacher;
	Department department;
	Subject subject;
	
	@BeforeEach
	void init() { 
		teacher = new Teacher("nombre", new User(), Lists.list(new College()), new PersonalExperience(),
				Lists.list(new Department()), Lists.list(new Subject()));
		teacher.setId(80);
		department = new Department("Departamento de Matemática Aplicada");
		subject = new Subject("Introducción a la Matemática Discreta", 1, new Department(), new ArrayList<>(), new TeachingPlan());

	}
	
	@Test
	@DisplayName("Show Department List")
	@WithMockUser(value="spring")
	void ShowDepartmentListTest() {
		//arrange		
		when(this.departmentService.findAll()).thenReturn(Lists.list(department));
		try {
			//act
			mockMvc.perform(get("/departments"))
			//assert
			.andExpect(status().isOk())
			.andExpect(view().name("departments/departmentsList"))
			.andExpect(model().attribute("departments", hasItem(
                           hasProperty("name", is(department.getName()))
            )));
		} catch (Exception e) {
			System.err.println("Error testing controller: "+e.getMessage());
			e.printStackTrace();
		}
	}
	
	@Test
	@DisplayName("List Subjects From Department ID")
	@WithMockUser(value="spring")
	void listSubjectsFromDepartmentIdTest() {
		//arrange
		department.setId(1111);
		when(this.departmentService.findDepartmentById(department.getId())).thenReturn(department);
		when(this.subjectService.findSubjectsFromDepartmentId(department.getId())).thenReturn(Lists.list(subject));
		try {
			//act
			mockMvc.perform(get("/departments/{departmentId}/relatedSubjects", department.getId()))
			//assert
			.andExpect(status().isOk())
			.andExpect(view().name("departments/relatedSubjects"))			
			.andExpect(model().attribute("department", hasProperty("name", is(department.getName()))))
			.andExpect(model().attribute("subjects", hasItem(hasProperty("name", is(subject.getName())))));
			
		} catch (Exception e) {
			System.err.println("Error testing controller: "+e.getMessage());
			e.printStackTrace();
		}
	}
	

}
