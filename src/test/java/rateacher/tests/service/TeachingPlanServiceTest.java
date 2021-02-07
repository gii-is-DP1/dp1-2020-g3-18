package rateacher.tests.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.validation.ConstraintViolationException;

import org.assertj.core.util.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataAccessException;
import org.springframework.transaction.annotation.Transactional;

import rateacher.model.TeachingPlan;
import rateacher.model.TeachingPlan;
import rateacher.model.Student;
import rateacher.model.Subject;
import rateacher.model.Teacher;
import rateacher.model.TeachingPlan;
import rateacher.repository.TeachingPlanRepository;
import rateacher.service.TeachingPlanService;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class TeachingPlanServiceTest {

	
	TeachingPlanRepository repo = mock(TeachingPlanRepository.class);
	
	TeachingPlanService teachingPlanService = new TeachingPlanService(repo, null);
	
	TeachingPlan t1;
	TeachingPlan t2;
	
	@BeforeEach
	void initAll() {
		t1 = new TeachingPlan("Teaching Plan 1");
	}
	
	// Casos positivos
		@Test
		@DisplayName("Finding all teachingPlans")
		void testFindAll() throws DataAccessException {
			Collection<TeachingPlan> res;

			when(this.repo.findAll()).thenReturn(Lists.list(t1, t2));

			res = this.teachingPlanService.findTeachingPlans();

			assertTrue(!res.isEmpty());
			assertTrue(res.size() == 2);
		}
		
		@Test
		@DisplayName("find by id returns teachingPlan")
		void testFindbyId() throws DataAccessException {
			int theId = 8;
			t1.setId(theId);
			when(repo.findById(8)).thenReturn(t1);
			TeachingPlan res = teachingPlanService.findTeachingPlanById(theId);
			assertTrue(res.getName()==t1.getName());
		}
		
		@Test
		@Transactional
		@DisplayName("Saving a teachingPlan ")
		public void shouldSaveTeachingPlan() {
		
			Collection<TeachingPlan> teachingPlans = this.teachingPlanService.findTeachingPlans();

			TeachingPlan nuevoTeachingPlan = new TeachingPlan("Teaching plan to Diseño y Pruebas ");         
			nuevoTeachingPlan.setId(13);
	                
			this.teachingPlanService.save(nuevoTeachingPlan);
			
			Mockito.verify(this.repo, Mockito.times(1)).save(nuevoTeachingPlan);
			
		}
	
		
		//Casos negativos
		@Test
		@DisplayName("find by id doesn't exists ")
		void testFindbybadId() throws DataAccessException {
			int badId=3484;
			when(repo.findById(badId)).thenReturn(null);
			TeachingPlan t = teachingPlanService.findTeachingPlanById(badId);	
			assertTrue(t==null);
		}
		
}
