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
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

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
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.hamcrest.Matchers.allOf;

import rateacher.configuration.SecurityConfiguration;
import rateacher.model.College;
import rateacher.model.Dean;
import rateacher.model.Department;
import rateacher.model.PersonalExperience;
import rateacher.model.Student;
import rateacher.model.Subject;
import rateacher.model.Teacher;
import rateacher.model.TeachingPlan;
import rateacher.model.User;
import rateacher.service.DeanService;
import rateacher.service.StudentService;
import rateacher.service.SubjectService;
import rateacher.service.TeacherService;
import rateacher.service.TeachingPlanService;
import rateacher.web.SubjectController;


@WebMvcTest(controllers = SubjectController.class,
excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class),
excludeAutoConfiguration = SecurityConfiguration.class)
public class SubjectControllerTests {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private SubjectService subjectService;
	
	@MockBean
	private DeanService deanService;
	
	@MockBean
	private StudentService studentService;
	
	@MockBean
	private TeacherService teacherService;
	
	@MockBean
	private TeachingPlanService teachingPlanService;
	
	Subject subjectTest;
	Dean deanTest;
	Student studentTest;
	List<Subject> subjectsListTest;
	Teacher teacherTest;
	TeachingPlan teachingPlanTest;
	
	@BeforeEach
	void init() {
		subjectTest = new Subject("name subject", 3, new Department(), new ArrayList<Teacher>(), new TeachingPlan());
		subjectTest.setId(100);
		studentTest = new Student("name student", "test@test.com", new User(), new ArrayList<Subject>(),
		new ArrayList<Teacher>());
		studentTest.setId(100);
		deanTest = new Dean(new User(), new College());
		deanTest.setId(100);
		teacherTest =new Teacher("name teacher", new User(), new ArrayList<College>(), new PersonalExperience(),
				new ArrayList<Department>(), new ArrayList<Subject>());
		teacherTest.setId(100);
		teachingPlanTest = new TeachingPlan("name teachingPlan");
		teachingPlanTest.setId(100);
	}
	
	@Test
	@DisplayName("Show subjects list")
	@WithMockUser(value="spring")
	void ShowSubjectListTest() {
		//arrange		
		when(this.studentService.findStudentByUsername(any())).thenReturn(studentTest);
		when(this.deanService.findDeanByUsername(any())).thenReturn(deanTest);
		System.out.println(model().toString());
		try {
			//act
			mockMvc.perform(get("/subjects"))
			//assert
			.andExpect(status().isOk())
			.andExpect(view().name("subjects/subjectsList"))
			.andExpect(model().attribute("student", hasProperty("name", is(studentTest.getName()))))
			.andExpect(model().attributeExists("subjects"));
		} catch (Exception e) {
			System.err.println("Error testing controller: "+e.getMessage());
			e.printStackTrace();
		}
	}
	
	@Test
	@DisplayName("Test Creating new Subject")
	@WithMockUser(value = "spring")
	void CreateSubjectProcessTest() {
		//arrange
		when(this.subjectService.findSubjectById(101)).thenReturn(subjectTest);
		when(this.teacherService.findTeacherById(100)).thenReturn(teacherTest);
		when(this.subjectService.findAll()).thenReturn(Lists.list(subjectTest));
			try {
				//act
				mockMvc.perform(post("/subjects/{subjectId}/teachers/{teacherId}/add", 101, 100)
				.with(csrf())
					.param("name", "Asignatura prueba")
					.param("curso", "1"))
				//assert
				.andExpect(status().isOk())
				.andExpect(model().attributeExists("subjects"))
				.andExpect(model().attribute("subjects", hasItem(
						allOf(
								hasProperty("name", is(subjectTest.getName())),
								hasProperty("curso", is(subjectTest.getCurso()))))));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	@Test
	@DisplayName("Test list Teachers of a Subject")
	@WithMockUser(value="spring")
	void TeachersFromASubjectTest() {
		//arrange
		when(this.teacherService.findTeacherById(100)).thenReturn(teacherTest);
		try {
			//act
			mockMvc.perform(get("/subjects/{subjectId}/teachers",100))
			//assert
			.andExpect(status().isOk())
			.andExpect(view().name("subjects/teachersList"))
			.andExpect(model().attribute("subjectId", is(100)))
			.andExpect(model().attributeExists("teachers"));
			
		}catch (Exception e){
			System.err.println("Error testing controller: "+e.getMessage());
			e.printStackTrace();
		}

		
	}
	
	
	@Test
	@DisplayName("Test add teacher to a subject")
	@WithMockUser(value="spring")
	void AddTeacherSubjectTest() {
		//arrange
		when(this.teacherService.findTeacherById(101)).thenReturn(teacherTest);
		try {
			//act
			mockMvc.perform(get("/subjects/{subjectId}/teachers/{teacherId}/add",100,101))
			//assert
			.andExpect(status().isOk())
			.andExpect(view().name("subjects/AreYouSureView"))
			.andExpect(model().attribute("subjectId", is(100)))
			.andExpect(model().attribute("teacher", hasProperty("name", is(teacherTest.getName()))))
			.andExpect(model().attribute("teacher", hasProperty("id", is(100))));
		}catch (Exception e){
			System.err.println("Error testing controller: "+e.getMessage());
			e.printStackTrace();
		}		
	}
	
//	@PostMapping(value = {"/subjects/save"})
//	public String processCreationForm(@Valid Subject subject, BindingResult result, ModelMap modelMap) {
//		String view = "subjects/subjectsList";
//		if (result.hasErrors()) {
//			modelMap.addAttribute("subject", subject);
//			return "subjects/newSubject";
//		}
//		
//		else {
//			subjectService.save(subject);
//			modelMap.addAttribute("message", "Subject successfully saved!");
//			view = showSubjectsList(modelMap);
//		}
//		return view;
//	}
	
//	@Test
//	@DisplayName("Test post save subject")
//	@WithMockUser(value="spring")
//	void SaveSubjectTest() {
//		//arrange
//		when(this.subjectService.).thenReturn(teacherTest);
//		try {
//			//act
//			mockMvc.perform(get("/subjects/{subjectId}/teachers/{teacherId}/add"))
//			//assert
//			.andExpect(status().isOk())
//			.andExpect(view().name("subjects/AreYouSureView"))
//			.andExpect(model().attribute("subjectId", is(100)))
//			.andExpect(model().attribute("teacher", hasProperty("name", is(teacherTest.getName()))))
//			.andExpect(model().attribute("teacher", hasProperty("id", is(100))));
//		}catch (Exception e){
//			System.err.println("Error testing controller: "+e.getMessage());
//			e.printStackTrace();
//		}		
//	}
	
	
	@Test
	@DisplayName("Test delete subject")
	@WithMockUser(value="spring")
	void DeleteSubjectTest() {
		//arrange
		Subject subject = new Subject("subject a borrar", 3, new Department(), new ArrayList<Teacher>(), new TeachingPlan());
		subject.setId(101);
		studentTest.getSubjects().add(subject);
		teacherTest.getSubjects().add(subject);
		when(subjectService.findSubjectById(101)).thenReturn(subject);
		try {
			//act
			mockMvc.perform(get("/subjects/delete/{subjectId}",101))
			//assert
			.andExpect(status().isOk())
			.andExpect(view().name("subjects/subjectsList"))
			.andExpect(model().attribute("message", is("Subject successfully deleted!")));
		}catch (Exception e){
			System.err.println("Error testing controller: "+e.getMessage());
			e.printStackTrace();
		}		
	}
	
//	@GetMapping(value = {"/subjects/{subjectId}/newTeachingPlan"})
//	public String newTeachingPlan(ModelMap model, @PathVariable int subjectId) {
//		TeachingPlan teachingPlan = new TeachingPlan();
//		model.put("teachingPlan", teachingPlan);
//		Subject subject = subjectService.findSubjectById(subjectId);
//		model.put("subjects", subject);
//		return "teachingPlans/newTeachingPlan";
//
//	}
	@Test
	@DisplayName("Test new Teaching plan")
	@WithMockUser(value="spring")
	void NewTeachingPlanTest() {
		//arrange
		when(this.subjectService.findSubjectById(100)).thenReturn(subjectTest);
		try {
			//act
			mockMvc.perform(get("/subjects/{subjectId}/newTeachingPlan",100))
			//assert
			.andExpect(status().isOk())
			.andExpect(view().name("teachingPlans/newTeachingPlan"))
			.andExpect(model().attributeExists("teachingPlan"))
			.andExpect(model().attributeExists("subjects"));
		}catch (Exception e){
			System.err.println("Error testing controller: "+e.getMessage());
			e.printStackTrace();
		}		
	}
	
	
	@Test
	@DisplayName("Test Creating new TeachingPlan")
	@WithMockUser(value = "spring")
	void CreateTeachingPlanTest() {
		//arrange
		when(this.subjectService.findSubjectById(101)).thenReturn(subjectTest);
			try {
				//act
				mockMvc.perform(post("/subjects/{subjectId}/newTeachingPlan", 101)
				.with(csrf())
					.param("name", "TeachingPlan prueba"))
				//assert
				.andExpect(status().isOk())
//				.andExpect(model().attributeExists("subject"))
//				.andExpect(model().attributeExists("subjects"))
				.andExpect(model().attributeExists("teachingPlan"))
				.andExpect(model().attribute("teachingPlan", hasProperty("name", is("TeachingPlan prueba"))));
			} catch (Exception e) {
				System.err.println("Error testing controller: "+e.getMessage());
				e.printStackTrace();
			}
	}
	
	
}
