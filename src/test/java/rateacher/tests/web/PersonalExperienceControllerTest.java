package rateacher.tests.web;

import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

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
import rateacher.model.College;
import rateacher.model.Department;
import rateacher.model.ExternalEvaluation;
import rateacher.model.PersonalExperience;
import rateacher.model.ProfessionalExperience;
import rateacher.model.ResearchExperience;
import rateacher.model.Subject;
import rateacher.model.Teacher;
import rateacher.model.TeachingExperience;
import rateacher.model.User;
import rateacher.service.ExternalEvaluationService;
import rateacher.service.PersonalExperienceService;
import rateacher.service.ProfessionalExperienceService;
import rateacher.service.ResearchExperienceService;
import rateacher.service.TeacherService;
import rateacher.service.TeachingExperienceService;
import rateacher.web.PersonalExperienceController;

@WebMvcTest(controllers = PersonalExperienceController.class,
excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class),
excludeAutoConfiguration = SecurityConfiguration.class)
public class PersonalExperienceControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private PersonalExperienceService personalExperienceService;
	
	@MockBean
	private TeacherService teacherService;
	
	@MockBean
	private ResearchExperienceService researchExperienceService;
	
	@MockBean
	private TeachingExperienceService teachingExperienceService;
	
	@MockBean
	private ProfessionalExperienceService professionalExperienceService;
	
	@MockBean
	private ExternalEvaluationService externalEvaluationService;
	
	PersonalExperience personalExperienceTest;
	Teacher teacherTest;
	ResearchExperience researchExperienceTest;
	TeachingExperience teachingExperienceTest;
	ProfessionalExperience professionalExperienceTest;
	ExternalEvaluation externalEvaluationTest;

	@BeforeEach
	void init() {
		
		personalExperienceTest = new PersonalExperience("name", new ResearchExperience(), new TeachingExperience(),
				new ProfessionalExperience(), new ExternalEvaluation());
		personalExperienceTest.setId(166);
		teacherTest = new Teacher("nombre", new User(), Lists.list(new College()), new PersonalExperience(),
				Lists.list(new Department()), Lists.list(new Subject()));
		teacherTest.setId(160);
	}
	
	@Test
	@DisplayName("Show new personal Experience")
	@WithMockUser(value="spring")
	void ShowNewPersonalExperienceTest() {
		//arrange		
		when(this.teacherService.findTeacherById(160)).thenReturn(teacherTest);
		try {
			//act
			mockMvc.perform(get("/teachers/{teacherId}/newPersonalExperience",160))
			//assert
			.andExpect(status().isOk())
			.andExpect(view().name("personalExperience/createPersonalExperienceForm"));
	
		} catch (Exception e) {
			System.err.println("Error testing controller: "+e.getMessage());
			e.printStackTrace();
		}
	}
	@Test
	@DisplayName("Test Creating new Personal Experience")
	@WithMockUser(value = "spring")
	void CreatePersonalExperienceProcessTest() {
		//arrange
		PersonalExperience perEx = new PersonalExperience();
		when(this.teacherService.findTeacherById(160)).thenReturn(teacherTest);
			try {
				//act
				mockMvc.perform(post("/teachers/{teacherId}/newPersonalExperience", 160, perEx)
				.with(csrf())
					.param("name", "personal experience"))
				//assert
				.andExpect(status().isOk())
				.andExpect(view().name("teachers/teachersList"));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	@Test
	@DisplayName("Show personal Experience")
	@WithMockUser(value="spring")
	void ShowPersonalExperience() {
		//arrange		
		when(this.personalExperienceService.findById(166)).thenReturn(personalExperienceTest);
		try {
			//act
			mockMvc.perform(get("/personalExperience/{personalExperienceId}",166))
			//assert
			.andExpect(status().isOk())
			.andExpect(view().name("personalExperience/personalExperience"));
	
		} catch (Exception e) {
			System.err.println("Error testing controller: "+e.getMessage());
			e.printStackTrace();
		}
	}
	
	@Test
	@DisplayName("Show new research Experience")
	@WithMockUser(value="spring")
	void ShowNewResearchExperience() {
		//arrange		
		ResearchExperience re = new ResearchExperience();
		when(this.personalExperienceService.findById(166)).thenReturn(personalExperienceTest);
		try {
			//act
			mockMvc.perform(get("/personalExperience/{personalExperienceId}/newResearchExperience", 166, re))
					//assert
					.andExpect(status().isOk())
					.andExpect(view().name("personalExperience/researchExperience"));
	
		} catch (Exception e) {
			System.err.println("Error testing controller: "+e.getMessage());
			e.printStackTrace();
		}
	}
	
	@Test
	@DisplayName("Test Creating new Research Experience")
	@WithMockUser(value = "spring")
	void CreateResearchExperienceProcessTest() {
		//arrange
		ResearchExperience re = new ResearchExperience();
		when(this.personalExperienceService.findById(166)).thenReturn(personalExperienceTest);
			try {
				//act
				mockMvc.perform(post("/personalExperience/{personalExperienceId}/newResearchExperience", 166, re)
				.with(csrf())
					.param("research", "research experience"))
				//assert
				.andExpect(status().isOk())
				.andExpect(view().name("personalExperience/personalExperience"));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	@Test
	@DisplayName("Show new teaching Experience")
	@WithMockUser(value="spring")
	void ShowNewTeachingExperience() {
		//arrange		
		TeachingExperience te = new TeachingExperience();
		when(this.personalExperienceService.findById(166)).thenReturn(personalExperienceTest);
		try {
			//act
			mockMvc.perform(get("/personalExperience/{personalExperienceId}/newTeachingExperience", 166, te))
					//assert
					.andExpect(status().isOk())
					.andExpect(view().name("personalExperience/teachingExperience"));
	
		} catch (Exception e) {
			System.err.println("Error testing controller: "+e.getMessage());
			e.printStackTrace();
		}
	}
	
	@Test
	@DisplayName("Test Creating new Teaching Experience")
	@WithMockUser(value = "spring")
	void CreateTeachingExperienceProcessTest() {
		//arrange
		TeachingExperience te = new TeachingExperience();
		when(this.personalExperienceService.findById(166)).thenReturn(personalExperienceTest);
			try {
				//act
				mockMvc.perform(post("/personalExperience/{personalExperienceId}/newTeachingExperience", 166, te)
				.with(csrf())
					.param("titulation", "titulation experience")
					.param("comment", " comment titulation experience"))
				//assert
				.andExpect(status().isOk())
				.andExpect(view().name("personalExperience/personalExperience"));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	@Test
	@DisplayName("Show new professional Experience")
	@WithMockUser(value="spring")
	void ShowNewProfessionalExperience() {
		//arrange		
		ProfessionalExperience pro = new ProfessionalExperience();
		when(this.personalExperienceService.findById(166)).thenReturn(personalExperienceTest);
		try {
			//act
			mockMvc.perform(get("/personalExperience/{personalExperienceId}/newProfessionalExperience", 166, pro))
					//assert
					.andExpect(status().isOk())
					.andExpect(view().name("personalExperience/professionalExperience"));
	
		} catch (Exception e) {
			System.err.println("Error testing controller: "+e.getMessage());
			e.printStackTrace();
		}
	}
	
	@Test
	@DisplayName("Test Creating new professional Experience")
	@WithMockUser(value = "spring")
	void CreateProfessionalExperienceProcessTest() {
		//arrange
		ProfessionalExperience pro = new ProfessionalExperience();
		when(this.personalExperienceService.findById(166)).thenReturn(personalExperienceTest);
			try {
				//act
				mockMvc.perform(post("/personalExperience/{personalExperienceId}/newProfessionalExperience", 166, pro)
				.with(csrf())
					.param("university", "university experience")
					.param("comment", " comment professional experience"))
				//assert
				.andExpect(status().isOk())
				.andExpect(view().name("personalExperience/personalExperience"));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	@Test
	@DisplayName("Show new external evaluation")
	@WithMockUser(value="spring")
	void ShowNewExternalEvaluation() {
		//arrange		
		ExternalEvaluation ex = new ExternalEvaluation();
		when(this.personalExperienceService.findById(166)).thenReturn(personalExperienceTest);
		try {
			//act
			mockMvc.perform(get("/personalExperience/{personalExperienceId}/newExternalEvaluation", 166, ex))
					//assert
					.andExpect(status().isOk())
					.andExpect(view().name("personalExperience/externalEvaluation"));
	
		} catch (Exception e) {
			System.err.println("Error testing controller: "+e.getMessage());
			e.printStackTrace();
		}
	}
	
	@Test
	@DisplayName("Test Creating new external evaluation")
	@WithMockUser(value = "spring")
	void CreateExternalEvaluationProcessTest() {
		//arrange
		ExternalEvaluation ex = new ExternalEvaluation();
		when(this.personalExperienceService.findById(166)).thenReturn(personalExperienceTest);
			try {
				//act
				mockMvc.perform(post("/personalExperience/{personalExperienceId}/newExternalEvaluation", 166, ex)
				.with(csrf())
					.param("note", "3")
					.param("evaluationReport", " evaluationReport external evaluation")
					.param("comment", " comment external evaluation"))
				//assert
				.andExpect(status().isOk())
				.andExpect(view().name("personalExperience/personalExperience"));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	// CASOS NEGATIVOS
	
	@Test
	@DisplayName("Show bad new personal Experience")
	@WithMockUser(value="spring")
	void ShowBadNewPersonalExperienceTest() {
		//arrange	
		Integer teacherId = 91;
		when(this.teacherService.findTeacherById(160)).thenReturn(null);
		try {
			//act
			mockMvc.perform(get("/teachers/{teacherId}/newPersonalExperience",teacherId))
			//assert
			.andExpect(status().isOk())
			.andExpect(view().name("personalExperience/createPersonalExperienceForm"))
			.andExpect(model().attributeDoesNotExist("teacher"));
			

	
		} catch (Exception e) {
			System.err.println("Error testing controller: "+e.getMessage());
			e.printStackTrace();
		}
	}
	
	@Test
	@DisplayName("Test Creating bad new Personal Experience")
	@WithMockUser(value = "spring")
	void CreateBadPersonalExperienceProcessTest() {
		//arrange
		PersonalExperience perEx = new PersonalExperience();
		when(this.teacherService.findTeacherById(160)).thenReturn(teacherTest);
			try {
				//act
				mockMvc.perform(post("/teachers/{teacherId}/newPersonalExperience", 160, perEx)
				.with(csrf())
					.param("name", ""))
				//assert
				.andExpect(status().isOk())
				.andExpect(view().name("personalExperience/createPersonalExperienceForm"));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	@Test
	@DisplayName("Show bad personal Experience")
	@WithMockUser(value="spring")
	void ShowBadPersonalExperience() {
		//arrange	
		Integer perExpId = 91;
		when(this.personalExperienceService.findById(166)).thenReturn(null);
		try {
			//act
			mockMvc.perform(get("/personalExperience/{personalExperienceId}",perExpId))
			//assert
			.andExpect(status().isOk())
			.andExpect(view().name("personalExperience/personalExperience"))
			.andExpect(model().attributeDoesNotExist("personalExperience"));
	
		} catch (Exception e) {
			System.err.println("Error testing controller: "+e.getMessage());
			e.printStackTrace();
		}
	}
	
	@Test
	@DisplayName("Show bad new research Experience")
	@WithMockUser(value="spring")
	void ShowBadNewResearchExperience() {
		//arrange
		Integer perExpId = 91;
		ResearchExperience re = new ResearchExperience();
		when(this.personalExperienceService.findById(166)).thenReturn(null);
		try {
			//act
			mockMvc.perform(get("/personalExperience/{personalExperienceId}/newResearchExperience", perExpId, re))
					//assert
					.andExpect(status().isOk())
					.andExpect(view().name("personalExperience/researchExperience"))
					.andExpect(model().attributeDoesNotExist("personalExperience"));
	
		} catch (Exception e) {
			System.err.println("Error testing controller: "+e.getMessage());
			e.printStackTrace();
		}
	}
	
	@Test
	@DisplayName("Test Creating bad new Research Experience")
	@WithMockUser(value = "spring")
	void CreateBadResearchExperienceProcessTest() {
		//arrange
		ResearchExperience re = new ResearchExperience();
		when(this.personalExperienceService.findById(166)).thenReturn(personalExperienceTest);
			try {
				//act
				mockMvc.perform(post("/personalExperience/{personalExperienceId}/newResearchExperience", 166, re)
				.with(csrf())
					.param("research", ""))
				//assert
				.andExpect(status().isOk())
				.andExpect(view().name("personalExperience/researchExperience"));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	@Test
	@DisplayName("Show bad new teaching Experience")
	@WithMockUser(value="spring")
	void ShowBadNewTeachingExperience() {
		//arrange	
		Integer perExpId = 91;
		TeachingExperience te = new TeachingExperience();
		when(this.personalExperienceService.findById(166)).thenReturn(null);
		try {
			//act
			mockMvc.perform(get("/personalExperience/{personalExperienceId}/newTeachingExperience", perExpId, te))
					//assert
					.andExpect(status().isOk())
					.andExpect(view().name("personalExperience/teachingExperience"))
					.andExpect(model().attributeDoesNotExist("personalExperience"));
	
		} catch (Exception e) {
			System.err.println("Error testing controller: "+e.getMessage());
			e.printStackTrace();
		}
	}
	
	@Test
	@DisplayName("Test Creating bad new Teaching Experience")
	@WithMockUser(value = "spring")
	void CreateBadTeachingExperienceProcessTest() {
		//arrange
		TeachingExperience te = new TeachingExperience();
		when(this.personalExperienceService.findById(166)).thenReturn(personalExperienceTest);
			try {
				//act
				mockMvc.perform(post("/personalExperience/{personalExperienceId}/newTeachingExperience", 166, te)
				.with(csrf())
					.param("titulation", "")
					.param("comment", " comment titulation experience"))
				//assert
				.andExpect(status().isOk())
				.andExpect(view().name("personalExperience/teachingExperience"));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	@Test
	@DisplayName("Show bad new professional Experience")
	@WithMockUser(value="spring")
	void ShowBadNewProfessionalExperience() {
		//arrange
		Integer perExpId = 91;
		ProfessionalExperience pro = new ProfessionalExperience();
		when(this.personalExperienceService.findById(166)).thenReturn(null);
		try {
			//act
			mockMvc.perform(get("/personalExperience/{personalExperienceId}/newProfessionalExperience", perExpId, pro))
					//assert
					.andExpect(status().isOk())
					.andExpect(view().name("personalExperience/professionalExperience"))
					.andExpect(model().attributeDoesNotExist("personalExperience"));
	
		} catch (Exception e) {
			System.err.println("Error testing controller: "+e.getMessage());
			e.printStackTrace();
		}
	}
	
	@Test
	@DisplayName("Test Creating bad new professional Experience")
	@WithMockUser(value = "spring")
	void CreateBadProfessionalExperienceProcessTest() {
		//arrange
		ProfessionalExperience pro = new ProfessionalExperience();
		when(this.personalExperienceService.findById(166)).thenReturn(personalExperienceTest);
			try {
				//act
				mockMvc.perform(post("/personalExperience/{personalExperienceId}/newProfessionalExperience", 166, pro)
				.with(csrf())
					.param("university", "")
					.param("comment", " comment professional experience"))
				//assert
				.andExpect(status().isOk())
				.andExpect(view().name("personalExperience/profesionalExperience"));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	@Test
	@DisplayName("Show bad new external evaluation")
	@WithMockUser(value="spring")
	void ShowBadNewExternalEvaluation() {
		//arrange	
		Integer perExpId = 91;
		ExternalEvaluation ex = new ExternalEvaluation();
		when(this.personalExperienceService.findById(166)).thenReturn(personalExperienceTest);
		try {
			//act
			mockMvc.perform(get("/personalExperience/{personalExperienceId}/newExternalEvaluation", perExpId, ex))
					//assert
					.andExpect(status().isOk())
					.andExpect(view().name("personalExperience/externalEvaluation"))
					.andExpect(model().attributeDoesNotExist("personalExperience"));
	
		} catch (Exception e) {
			System.err.println("Error testing controller: "+e.getMessage());
			e.printStackTrace();
		}
	}
	
	@Test
	@DisplayName("Test Creating bad new external evaluation")
	@WithMockUser(value = "spring")
	void CreateBadExternalEvaluationProcessTest() {
		//arrange
		ExternalEvaluation ex = new ExternalEvaluation();
		when(this.personalExperienceService.findById(166)).thenReturn(personalExperienceTest);
			try {
				//act
				mockMvc.perform(post("/personalExperience/{personalExperienceId}/newExternalEvaluation", 166, ex)
				.with(csrf())
					.param("note", "3")
					.param("evaluationReport", "")
					.param("comment", " comment external evaluation"))
				//assert
				.andExpect(status().isOk())
				.andExpect(view().name("personalExperience/externalEvaluation"));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
}
