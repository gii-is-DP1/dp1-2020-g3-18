package rateacher.tests.service;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import rateacher.model.College;
import rateacher.model.Department;
import rateacher.model.PersonalExperience;
import rateacher.model.Subject;
import rateacher.model.Teacher;
import rateacher.service.CollegeService;
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
