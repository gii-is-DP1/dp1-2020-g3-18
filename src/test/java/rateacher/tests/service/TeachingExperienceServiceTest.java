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

import rateacher.model.TeachingExperience;
import rateacher.repository.TeachingExperienceRepository;
import rateacher.service.TeachingExperienceService;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class TeachingExperienceServiceTest {

	TeachingExperienceRepository repo = mock(TeachingExperienceRepository.class);
	
	TeachingExperienceService teachingExperienceService = new TeachingExperienceService(repo);
	
	TeachingExperience t1;
	TeachingExperience t2;
	
	@BeforeEach
	void initAll() {
		t1 = new TeachingExperience("Teaching Experience 1","test");
	}
	
	// Casos positivos
		@Test
		@DisplayName("Finding all teachingExperiences")
		void testFindAll() throws DataAccessException {
			Collection<TeachingExperience> res;

			when(this.repo.findAll()).thenReturn(Lists.list(t1, t2));

			res = this.teachingExperienceService.findAll();

			assertTrue(!res.isEmpty());
			assertTrue(res.size() == 2);
		}
		
		@Test
		@DisplayName("find by id returns teachingExperience")
		void testFindbyId() throws DataAccessException {
			int theId = 8;
			t1.setId(theId);
			when(repo.findById(8)).thenReturn(t1);
			TeachingExperience res = teachingExperienceService.findTeachingExperienceById(theId);
			assertTrue(res.getTitulation()==t1.getTitulation());
		}
		
		@Test
		@Transactional
		@DisplayName("Saving a teachingExperience ")
		public void shouldSaveTeachingExperience() {
		
			Collection<TeachingExperience> teachingExperiences = this.teachingExperienceService.findAll();

			TeachingExperience nuevoTeachingExperience = new TeachingExperience("test to Diseño y Pruebas","test");         
			nuevoTeachingExperience.setId(13);
	                
			this.teachingExperienceService.save(nuevoTeachingExperience);
			
			Mockito.verify(this.repo, Mockito.times(1)).save(nuevoTeachingExperience);
			
		}
	
		
		//Casos negativos
		@Test
		@DisplayName("find by id doesn't exists ")
		void testFindbybadId() throws DataAccessException {
			int badId=3484;
			when(repo.findById(badId)).thenReturn(null);
			TeachingExperience t = teachingExperienceService.findTeachingExperienceById(badId);	
			assertTrue(t==null);
		}
}
