package rateacher.tests.service;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Collection;

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

import rateacher.model.ExternalEvaluation;
import rateacher.repository.ExternalEvaluationRepository;
import rateacher.service.ExternalEvaluationService;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class ExternalEvaluationServiceTest {

	ExternalEvaluationRepository repo = mock(ExternalEvaluationRepository.class);
	
	ExternalEvaluationService externalEvaluationService = new ExternalEvaluationService(repo);
	
	ExternalEvaluation t1;
	ExternalEvaluation t2;
	
	@BeforeEach
	void initAll() {
		t1 = new ExternalEvaluation(1,"test","test");
	}
	
	// Casos positivos
		@Test
		@DisplayName("Finding all externalEvaluations")
		void testFindAll() throws DataAccessException {
			Collection<ExternalEvaluation> res;

			when(this.repo.findAll()).thenReturn(Lists.list(t1, t2));

			res = this.externalEvaluationService.findAll();

			assertTrue(!res.isEmpty());
			assertTrue(res.size() == 2);
		}
		
		@Test
		@DisplayName("find by id returns externalEvaluation")
		void testFindbyId() throws DataAccessException {
			int theId = 8;
			t1.setId(theId);
			when(repo.findById(8)).thenReturn(t1);
			ExternalEvaluation res = externalEvaluationService.findExternalEvaluationById(theId);
			assertTrue(res.getEvaluationReport()==t1.getEvaluationReport());
		}
		
		@Test
		@Transactional
		@DisplayName("Saving a externalEvaluation ")
		public void shouldSaveExternalEvaluation() {
		
			Collection<ExternalEvaluation> externalEvaluations = this.externalEvaluationService.findAll();

			ExternalEvaluation nuevoExternalEvaluation = new ExternalEvaluation(3,"test to Diseño y Pruebas","test");         
			nuevoExternalEvaluation.setId(13);
	                
			this.externalEvaluationService.save(nuevoExternalEvaluation);
			
			Mockito.verify(this.repo, Mockito.times(1)).save(nuevoExternalEvaluation);
			
		}
	
		
		//Casos negativos
		@Test
		@DisplayName("find by id doesn't exists ")
		void testFindbybadId() throws DataAccessException {
			int badId=3484;
			when(repo.findById(badId)).thenReturn(null);
			ExternalEvaluation t = externalEvaluationService.findExternalEvaluationById(badId);	
			assertTrue(t==null);
		}
}
