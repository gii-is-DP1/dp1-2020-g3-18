package rateacher.tests.repository;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import rateacher.model.PersonalExperience;
import rateacher.repository.PersonalExperienceRepository;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class PersonalExperienceRepositoryTest {

	@Autowired
	private PersonalExperienceRepository pExperienceRepository;
	
	@Test
	@DisplayName(value="Find By ID")
	void findByIdTest() {
	//arrange	
	//act
		PersonalExperience pExperience = this.pExperienceRepository.findById(81);
	//assert	
		assertFalse(pExperience.equals(new PersonalExperience()));
		assertTrue(pExperience!=null);
	}	
	

}
