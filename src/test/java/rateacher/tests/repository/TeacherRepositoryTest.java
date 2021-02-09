package rateacher.tests.repository;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import rateacher.model.Score;
import rateacher.model.Teacher;
import rateacher.repository.TeacherRepository;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class TeacherRepositoryTest {

	
	@Autowired
	private TeacherRepository teacherRepository;

	@Test
	@DisplayName(value="Show teacher with score query")
	void showTeacherWithScoreTest() {
	//arrange	
	//act
		List<Teacher> teachers = new ArrayList<>(teacherRepository.showTeacherWithScore());//id de julian
	//assert	
		assertFalse(teachers.isEmpty());
		assertTrue(teachers.get(0).getFirstName().equals("Julian"));
		assertTrue(teachers.size()==3);
	}
	
	@Test
	@DisplayName(value="Find Scores by Teacher query")
	void findScoresByTeacherIdTest() {
	//arrange
	//act
		List<Score> scores = new ArrayList<>(teacherRepository.findScoresByTeacherId(1));//id de julian
	//assert	
		assertFalse(scores.isEmpty());
		assertTrue(scores.size()==1);
		assertTrue(scores.get(0).getComment().equals("Las clases de este profesor me parecen un autentico toston, creo que no debería deshaogarse tanto con nosotros"));
	}
	
	@Test
	@DisplayName(value="Find by Student id query")
	void findByStudentIdTest() {
	//arrange
	//act
		List<Teacher> teachers = new ArrayList<>(teacherRepository.findByStudentId(1));//id de julian
	//assert	
		assertFalse(teachers.isEmpty());
		assertTrue(teachers.size()==3);
		assertTrue(teachers.get(0).getFirstName().equals("Julian"));
	}
	
	@Test
	@DisplayName(value="Find Teacher by username query")
	void findTeacherByUsernameTest() {
	//arrange
	//act
		Teacher teacher = teacherRepository.findTeacherByUsername("pop");//id de julian
	//assert	
		assertTrue(teacher.getFirstName().equals("Popench"));
	}
	
	@Test
	@DisplayName(value="Teachers to rate query")
	void teachersToRateTest() {
	//arrange
	//act
		List<Teacher> teachers = new ArrayList<>(teacherRepository.teachersToRate(1));//id de julian
	//assert	
		assertFalse(teachers.isEmpty());
		assertTrue(teachers.size()==2);
		assertTrue(teachers.get(0).getFirstName().equals("Macarena"));
		
	}
	
}
