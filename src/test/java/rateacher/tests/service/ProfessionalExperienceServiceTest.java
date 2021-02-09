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

import rateacher.model.ProfessionalExperience;
import rateacher.repository.ProfessionalExperienceRepository;
import rateacher.service.ProfessionalExperienceService;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class ProfessionalExperienceServiceTest {
	
	ProfessionalExperienceRepository repo = mock(ProfessionalExperienceRepository.class);
	
	ProfessionalExperienceService professionalExperienceService = new ProfessionalExperienceService(repo);
	
	ProfessionalExperience t1;
	ProfessionalExperience t2;
	
	@BeforeEach
	void initAll() {
		t1 = new ProfessionalExperience("Professional Experience 1","test");
	}
	
	// Casos positivos
		@Test
		@DisplayName("Finding all professionalExperiences")
		void testFindAll() throws DataAccessException {
			Collection<ProfessionalExperience> res;

			when(this.repo.findAll()).thenReturn(Lists.list(t1, t2));

			res = this.professionalExperienceService.findAll();

			assertTrue(!res.isEmpty());
			assertTrue(res.size() == 2);
		}
		
		@Test
		@DisplayName("find by id returns professionalExperience")
		void testFindbyId() throws DataAccessException {
			int theId = 8;
			t1.setId(theId);
			when(repo.findById(8)).thenReturn(t1);
			ProfessionalExperience res = professionalExperienceService.findProfessionalExperienceById(theId);
			assertTrue(res.getUniversity()==t1.getUniversity());
		}
		
		@Test
		@Transactional
		@DisplayName("Saving a professionalExperience ")
		public void shouldSaveProfessionalExperience() {
		

			ProfessionalExperience nuevoProfessionalExperience = new ProfessionalExperience("test to Diseño y Pruebas","test");         
			nuevoProfessionalExperience.setId(13);
	                
			this.professionalExperienceService.save(nuevoProfessionalExperience);
			
			Mockito.verify(this.repo, Mockito.times(1)).save(nuevoProfessionalExperience);
			
		}
	
		
		//Casos negativos
		@Test
		@DisplayName("find by id doesn't exists ")
		void testFindbybadId() throws DataAccessException {
			int badId=3484;
			when(repo.findById(badId)).thenReturn(null);
			ProfessionalExperience t = professionalExperienceService.findProfessionalExperienceById(badId);	
			assertTrue(t==null);
		}
}
