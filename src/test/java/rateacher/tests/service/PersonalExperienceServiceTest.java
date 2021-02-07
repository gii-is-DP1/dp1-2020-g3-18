package rateacher.tests.service;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Collection;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import rateacher.model.PersonalExperience;
import rateacher.service.PersonalExperienceService;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))

public class PersonalExperienceServiceTest {
	
	@Autowired
	protected PersonalExperienceService pExperienceService;
	
	//Positive test
	@Test
	@DisplayName("Find Personal Experience By ID")
	public void testFindPersonalExperienceById() {
		PersonalExperience pExperience = this.pExperienceService.findById(80);
		Assertions.assertThat(pExperience!=null);
		
	}
	
	@Test
	@DisplayName("Find Personal Experience By Bad ID")
	public void testFindPersonalExperienceByBadId() {
		int badId = 223323533;
		assertThrows(AssertionError.class,()->this.pExperienceService.findById(badId));
		
	}
	
	@Test
	@DisplayName("Find all Personal Experiences")
	public void testFindAllPersonalExperiences() {

		Collection<PersonalExperience> res = this.pExperienceService.findAllPersonalExperiences();

		assertTrue(!res.isEmpty());
		

	}
	@Test
	@DisplayName("Save Personal Experience")
	public void testSavePersonalExperience() {
		Collection<PersonalExperience> res = this.pExperienceService.findAllPersonalExperiences();
		int tam = res.size();
		PersonalExperience pExperience = new PersonalExperience();
		pExperience.setName("Personal Experience");
		this.pExperienceService.save(pExperience);
		Assertions.assertThat(pExperience.getId().longValue()).isNotEqualTo(0);
		res = this.pExperienceService.findAllPersonalExperiences();
		Assertions.assertThat(res.size()).isEqualTo(tam+1);
	}	
	

}
