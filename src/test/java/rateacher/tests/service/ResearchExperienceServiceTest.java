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

import rateacher.model.ResearchExperience;
import rateacher.repository.ResearchExperienceRepository;
import rateacher.service.ResearchExperienceService;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class ResearchExperienceServiceTest {

	ResearchExperienceRepository repo = mock(ResearchExperienceRepository.class);
	
	ResearchExperienceService researchExperienceService = new ResearchExperienceService(repo);
	
	ResearchExperience t1;
	ResearchExperience t2;
	
	@BeforeEach
	void initAll() {
		t1 = new ResearchExperience("Research Experience 1");
	}
	
	// Casos positivos
		@Test
		@DisplayName("Finding all researchExperiences")
		void testFindAll() throws DataAccessException {
			Collection<ResearchExperience> res;

			when(this.repo.findAll()).thenReturn(Lists.list(t1, t2));

			res = this.researchExperienceService.findAll();

			assertTrue(!res.isEmpty());
			assertTrue(res.size() == 2);
		}
		
		@Test
		@DisplayName("find by id returns researchExperience")
		void testFindbyId() throws DataAccessException {
			int theId = 8;
			t1.setId(theId);
			when(repo.findById(8)).thenReturn(t1);
			ResearchExperience res = researchExperienceService.findResearchExperienceById(theId);
			assertTrue(res.getResearch()==t1.getResearch());
		}
		
		@Test
		@Transactional
		@DisplayName("Saving a researchExperience ")
		public void shouldSaveResearchExperience() {
		
			ResearchExperience nuevoResearchExperience = new ResearchExperience("Test to Diseño y Pruebas ");         
			nuevoResearchExperience.setId(13);
	                
			this.researchExperienceService.save(nuevoResearchExperience);
			
			Mockito.verify(this.repo, Mockito.times(1)).save(nuevoResearchExperience);
			
		}
	
		
		//Casos negativos
		@Test
		@DisplayName("find by id doesn't exists ")
		void testFindbybadId() throws DataAccessException {
			int badId=3484;
			when(repo.findById(badId)).thenReturn(null);
			ResearchExperience t = researchExperienceService.findResearchExperienceById(badId);	
			assertTrue(t==null);
		}
}
