package rateacher.tests.web;

import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.mockito.ArgumentMatchers.any;


import java.util.ArrayList;

import javax.security.auth.Subject;

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

import rateacher.configuration.SecurityConfiguration;
import rateacher.model.Student;
import rateacher.model.Teacher;
import rateacher.model.User;
import rateacher.service.ScoreService;
import rateacher.service.StudentService;
import rateacher.service.TeacherService;
import rateacher.web.StudentController;

@WebMvcTest(controllers = StudentController.class,
excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class),
excludeAutoConfiguration = SecurityConfiguration.class)
public class StudentControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private StudentService studentService;
	
	@MockBean
	private TeacherService teacherService;
	
	@MockBean
	private ScoreService scoreService;
	
	Student student;
	Student student2;
	@BeforeEach
	void init() {
		student = new Student("josalberto", "jusele@telefonica.net", new User(), Lists.list(), Lists.list());
		student2 = new Student("manuel", "mmmm@hotmail.com", new User(), Lists.list(), Lists.list());
	}
	
	@Test
	@DisplayName("Show student list")
	@WithMockUser(value="spring")
	void showStudentsListTest() {
		//arrange
		when(this.studentService.findStudents()).thenReturn(Lists.list(student2, student));
		try {
			//act
			mockMvc.perform(get("/students"))
			//assert
			.andExpect(status().isOk())
			.andExpect(view().name("students/studentsList"))
			.andExpect(model().attribute("students", hasItem(
                    allOf(
                            hasProperty("name", is(student.getName())),
                            hasProperty("email", is(student.getEmail()))
                    )
            )));
		} catch (Exception e) {
			System.err.println("Error testing controller: "+e.getMessage());
			e.printStackTrace();
		}
	}
	
	@Test
	@DisplayName("Init creation form")
	@WithMockUser(value="spring")
	void initCreationFormTest() {
		//arrange
		try {
			//act
			mockMvc.perform(get("/students/new"))
			//assert
			.andExpect(status().isOk())
			.andExpect(view().name("students/createOrUpdateStudentForm"))
			.andExpect(model().attributeExists("student"));
		} catch (Exception e) {
			System.err.println("Error testing controller: "+e.getMessage());
			e.printStackTrace();
		}
	}
	
	@Test
	@DisplayName("Process creation form")
	@WithMockUser(value="spring")
	void processCreationFormTest() {
		//arrange
		try {
			//act
			mockMvc.perform(post("/students/new", student)
				.param("firstName", "Will")
				.param("lastName", "Smith")
				.param("name", "Will Smith")
				.param("email", "will@smith.com")
				.param("username", "willy")
				.param("password", "smilly")
				.with(csrf()))
			//assert
			.andExpect(status().is3xxRedirection())
			.andExpect(view().name("redirect:/students/" + "null"));
		} catch (Exception e) {
			System.err.println("Error testing controller: "+e.getMessage());
			e.printStackTrace();
		}
	}
	
	@Test
	@DisplayName("List my subjects")
	@WithMockUser(value="spring")
	void listMySubjectsTest() {
		//arrange
		student.setId(111);
		when(this.studentService.findStudentByUsername(any())).thenReturn(student);
		try {
			//act
			mockMvc.perform(get("/subjects/mySubjects/{studentId}", student.getId()))
			//assert
			.andExpect(status().isOk())
			.andExpect(view().name("students/mySubjects"))
			.andExpect(model().attribute("student", hasProperty("name", is(student.getName()))));
		} catch (Exception e) {
			System.err.println("Error testing controller: "+e.getMessage());
			e.printStackTrace();
		}
	}
	
	@Test
	@DisplayName("Show my rated teachers")
	@WithMockUser(value="spring")
	void showMyRatedTeachersTest() {
		//arrange
		student.setId(111);
		Teacher teacher = new Teacher("profesor", null, null, null, null, null);
		when(studentService.findStudentById(student.getId())).thenReturn(student);
		when(teacherService.findTeacherByStudentId(student.getId())).thenReturn(Lists.list(teacher));
		try {
			//act
			mockMvc.perform(get("/students/{studentId}/showRatedTeachers", student.getId()))
			//assert
			.andExpect(status().isOk())
			.andExpect(view().name("teachers/myRatedTeachersList"))
			.andExpect(model().attribute("teachers", hasItem(
                    hasProperty("name", is(teacher.getName()))
					)));
		} catch (Exception e) {
			System.err.println("Error testing controller: "+e.getMessage());
			e.printStackTrace();
		}
	}
	
	@Test
	@DisplayName("Teacher to rate")
	@WithMockUser(value="spring")
	void teacherToRateTest() {
		//arrange
		student.setId(123);
		Teacher teacher = new Teacher("profesor", null, null, null, null, null);
		when(teacherService.teachersToRate(student.getId())).thenReturn(Lists.list(teacher));
		try {
			//act
			mockMvc.perform(get("/students/{studentId}/teacherToRate", student.getId()))
			//assert
			.andExpect(status().isOk())
			.andExpect(view().name("teachers/teacherToRate"))
			.andExpect(model().attribute("teachers", hasItem(
                    hasProperty("name", is(teacher.getName()))
					)));
		} catch (Exception e) {
			System.err.println("Error testing controller: "+e.getMessage());
			e.printStackTrace();
		}
	}
	//negative
	
	@Test
	@DisplayName("Process creation form null name")
	@WithMockUser(value="spring")
	void processCreationFormNegativeTest() {
		//arrange
		try {
			//act
			mockMvc.perform(post("/students/new", student)
				.param("firstName", "Will")
				.param("lastName", "Smith")
				.param("email", "will@smith.com")
				.param("username", "willy")
				.param("password", "smilly")
				.with(csrf()))
			//assert
			.andExpect(status().isOk())
			.andExpect(view().name("students/createOrUpdateStudentForm"));
		} catch (Exception e) {
			System.err.println("Error testing controller: "+e.getMessage());
			e.printStackTrace();
		}
	}
	
	@Test
	@DisplayName("List my subjects bad studentId")
	@WithMockUser(value="spring")
	void listMySubjectsNegativeTest() {
		//arrange
		student.setId(111);
		when(this.studentService.findStudentByUsername(any())).thenReturn(null);
		try {
			//act
			mockMvc.perform(get("/subjects/mySubjects/{studentId}", student.getId()))
			//assert
			.andExpect(status().isOk())
			.andExpect(view().name("students/mySubjects"))
			.andExpect(model().attribute("subjects", is(new ArrayList<>())));
		} catch (Exception e) {
			System.err.println("Error testing controller: "+e.getMessage());
			e.printStackTrace();
		}
	}
	
	@Test
	@DisplayName("Show my rated teachers bad id")
	@WithMockUser(value="spring")
	void showMyRatedTeachersNegativeTest() {
		//arrange
		student.setId(111);
		Teacher teacher = new Teacher("profesor", null, null, null, null, null);
		when(studentService.findStudentById(student.getId())).thenReturn(null);
		when(teacherService.findTeacherByStudentId(student.getId())).thenReturn(Lists.list(teacher));
		try {
			//act
			mockMvc.perform(get("/students/{studentId}/showRatedTeachers", student.getId()))
			//assert
			.andExpect(status().isOk())
			.andExpect(view().name("exception"));
		} catch (Exception e) {
			System.err.println("Error testing controller: "+e.getMessage());
			e.printStackTrace();
		}
	}
	
	@Test
	@DisplayName("Teacher to rate bad student id")
	@WithMockUser(value="spring")
	void teacherToRateNegativeTest() {
		//arrange
		student.setId(123);
		when(teacherService.teachersToRate(student.getId())).thenReturn(Lists.list());
		try {
			//act
			mockMvc.perform(get("/students/{studentId}/teacherToRate", student.getId()))
			//assert
			.andExpect(status().isOk())
			.andExpect(view().name("teachers/teacherToRate"))
			.andExpect(model().attribute("teachers", is(new ArrayList<Teacher>())));
		} catch (Exception e) {
			System.err.println("Error testing controller: "+e.getMessage());
			e.printStackTrace();
		}
	}
}
