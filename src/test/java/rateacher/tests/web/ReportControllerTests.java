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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.hamcrest.Matchers.allOf;

import rateacher.configuration.SecurityConfiguration;
import rateacher.model.Student;
import rateacher.model.Teacher;
import rateacher.model.Score;
import rateacher.model.Report;
import rateacher.model.User;

import rateacher.service.ScoreService;
import rateacher.service.StudentService;
import rateacher.service.SubjectService;
import rateacher.service.TeacherService;
import rateacher.service.ReportService;

import rateacher.web.ReportController;


@WebMvcTest(controllers = ReportController.class,
excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class),
excludeAutoConfiguration = SecurityConfiguration.class)
public class ReportControllerTests {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private ScoreService scoreService;
	
	@MockBean
	private ReportService reportService;
		
	Student studentTest;
	Teacher teacherTest;
	Score scoreTest;
	Report reportTest;
	
	@BeforeEach
	void init() {
		
		scoreTest = new Score(4, "me parece un docente muy decente", new Student(), new Teacher());
		scoreTest.setId(998);
		reportTest = new Report("Es demasiado amable", scoreTest);
		reportTest.setId(999);
	}
	
	@Test
	@DisplayName("Show reports list")
	@WithMockUser(value="spring")
	void ShowSubjectListTest() {
		//arrange		
		when(this.scoreService.findScoreById(998)).thenReturn(scoreTest);
		System.out.println(model().toString());
		try {
			//act
			mockMvc.perform(get("/reports"))
			//assert
			.andExpect(status().isOk())
			.andExpect(view().name("reports/reportsList"))
			.andExpect(model().attribute("score", hasProperty("comment", is(scoreTest.getComment()))));
		} catch (Exception e) {
			System.err.println("Error testing controller: "+e.getMessage());
			e.printStackTrace();
		}
	}
	
	@Test
	@DisplayName("Test Creating new Report")
	@WithMockUser(value = "spring")
	void CreateSubjectProcessTest() {
		//arrange
		when(this.reportService.findReportById(999)).thenReturn(reportTest);
		when(this.scoreService.findScoreById(998)).thenReturn(scoreTest);
			try {
				//act
				mockMvc.perform(post("/reports/{scoreId}/new", 999, 998)
				.with(csrf())
					.param("reason", "No me convence su comentario"))
				//assert
				.andExpect(status().isOk())
				.andExpect(model().attributeExists("score"))
				.andExpect(model().attribute("score", hasItem(
						allOf(
								hasProperty("valu", is(scoreTest.getValu())),
								hasProperty("comment", is(scoreTest.getComment()))))));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
}
