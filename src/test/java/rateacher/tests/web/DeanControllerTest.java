package rateacher.tests.web;

import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.ArrayList;
import java.util.Collection;

import org.assertj.core.util.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import rateacher.configuration.SecurityConfiguration;
import rateacher.model.College;
import rateacher.model.Department;
import rateacher.model.PersonalExperience;
import rateacher.model.Score;
import rateacher.model.Student;
import rateacher.model.Subject;
import rateacher.model.Teacher;
import rateacher.model.User;
import rateacher.repository.CollegeRepository;
import rateacher.repository.TeacherRepository;
import rateacher.service.CollegeService;
import rateacher.service.DeanService;
import rateacher.service.StudentService;
import rateacher.service.SubjectService;
import rateacher.service.TeacherService;
import rateacher.web.DeanController;
import rateacher.web.TeacherController;

@WebMvcTest(controllers = DeanController.class,
excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class),
excludeAutoConfiguration = SecurityConfiguration.class)
public class DeanControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private DeanService deanService;
	
	@MockBean
	private TeacherRepository teacherRepository;
	
	@MockBean
	private TeacherService teacherService;
	
	@MockBean
	private CollegeService collegeService;
	
	@MockBean
	private CollegeRepository collegeRepository;
	
	@BeforeEach
	void init() {
		
	}
	
	@Test
	@DisplayName("Test initCreationForm new Teacher")
	@WithMockUser(value="spring")
	void CreateTeacherInitFormTest() {
		//arrange

		try {
			mockMvc.perform(get("/teachers/new"))
			.andExpect(status().isOk())
			.andExpect(view().name("teachers/newTeacherCreationForm"));
		} catch (Exception e) {
			System.err.println("Error testing controller: "+e.getMessage());
			e.printStackTrace();
		}
	}
	
	@Test
	@DisplayName("Test ProcessCreationForm new Teacher")
	@WithMockUser(value = "spring")
	void CreateTeacherProcessFormTest() {
		//arrange
		Teacher teacher1 = new Teacher("nombre", new User(), Lists.list(new College()), new PersonalExperience(),
				Lists.list(new Department()), Lists.list(new Subject()));
		
		when(this.teacherService.findTeacherById(80)).thenReturn(teacher1);

			try {
				//act
				mockMvc.perform(post("/teachers/new", 80, teacher1)
				.with(csrf())
					.param("username", "pico")
					.param("password", "pala"))
				//assert
				.andExpect(status().isOk());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	@Test
	@DisplayName("show college List")
	@WithMockUser(value="spring")
	void ShowCollegeListTest() {
		//arrange		
		Collection<College> colleges = new ArrayList<>();
		College college = new College("colegio", "misiuda", new ArrayList<Teacher>());
		colleges.add(college);
		
		when(this.deanService.findAllColleges()).thenReturn(colleges);
		
		try {
			//act
			mockMvc.perform(get("/deans/colleges"))
			//assert
			.andExpect(status().isOk())
			.andExpect(view().name("deans/collegesList"))
			.andExpect(model().attribute("colleges", hasItem(
                    allOf(
                            hasProperty("name", is(college.getName())),
                            hasProperty("city", is(college.getCity()))
                    )
            )));
		} catch (Exception e) {
			System.err.println("Error testing controller: "+e.getMessage());
			e.printStackTrace();
		}
	}
	
	@Test
	@DisplayName("show teacher by college list")
	@WithMockUser(value="spring")
	void ShowTeacherTest() {
		//arrange		
		Collection<Teacher> teachers = new ArrayList<>();
		Integer collegeId = 99;
		Teacher teacher = new Teacher("nombre", new User(), Lists.list(new College()), new PersonalExperience(),
				Lists.list(new Department()), Lists.list(new Subject()));
		teachers.add(teacher);
		
		when(this.teacherService.findTeachers()).thenReturn(teachers);
		
		try {
			//act
			mockMvc.perform(get("/deans/colleges/{collegeId}/teachers", collegeId))
			//assert
			.andExpect(status().isOk())
			.andExpect(view().name("deans/teachersByCollegeList"))
			.andExpect(model().attribute("collegeId", is(collegeId)))
			.andExpect(model().attribute("teachers", hasItem(
                            hasProperty("name", is(teacher.getName()))
            )));
		} catch (Exception e) {
			System.err.println("Error testing controller: "+e.getMessage());
			e.printStackTrace();
		}
	}
	
	@Test
	@DisplayName("add teacher to college init")
	@WithMockUser(value="spring")
	void AddTeacherToCollegeInitTest() {
		//arrange		
		Integer collegeId = 99;
		Integer teacherId = 90;
		Teacher teacher = new Teacher("nombre", new User(), Lists.list(new College()), new PersonalExperience(),
				Lists.list(new Department()), Lists.list(new Subject()));
		
		when(this.teacherService.findTeacherById(teacherId)).thenReturn(teacher);
		
		try {
			//act
			mockMvc.perform(get("/deans/colleges/{collegeId}/teachers/{teacherId}/add", collegeId, teacherId))
			//assert
			.andExpect(status().isOk())
			.andExpect(view().name("deans/AreYouSureView"))
			.andExpect(model().attribute("collegeId", is(collegeId)))
			.andExpect(model().attribute("teacher", hasProperty("name", is(teacher.getName()))));
		} catch (Exception e) {
			System.err.println("Error testing controller: "+e.getMessage());
			e.printStackTrace();
		}
	}
	

}