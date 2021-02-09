package rateacher.tests.service;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataAccessException;
import org.springframework.security.test.context.support.WithMockUser;

import rateacher.model.Score;
import rateacher.model.Student;
import rateacher.model.Teacher;
import rateacher.repository.ScoreRepository;
import rateacher.service.ScoreService;
import rateacher.service.StudentService;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;

import java.util.ArrayList;
import java.util.Collection;

import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class ScoreServiceTests {
	
	ScoreRepository repo = mock(ScoreRepository.class);
	StudentService studentService = mock(StudentService.class);
	
	ScoreService scoreService = new ScoreService(repo, studentService);
	
	Score score1;
	Score score2;
	
	@BeforeEach
	void initAll() {
		score1 = new Score(7, "buen profesor",new Student(), new Teacher());
	}
	
	//Casos positivos
	@Test
	@DisplayName("Finding all scores")
	void testFindAll() throws DataAccessException{
		//arrange
		Collection<Score> res;

		when(this.repo.findAll()).thenReturn(Lists.list(score1, score2));
		//act
		res = this.scoreService.findAll();
		//assert
		assertTrue(!res.isEmpty());
		assertTrue(res.size()==2);

	}
	
	@Test
	@DisplayName("find by id returns score")
	void testFindbyId() throws DataAccessException {
		//arrange
		int theId = 8;
		score1.setId(theId);
		when(repo.findById(8)).thenReturn(score1);
		//act
		Score res = scoreService.findScoreById(theId);
		//assert
		assertTrue(res.getValu()==score1.getValu());
	}
	
	@Test
	@DisplayName("find scores by teacher id")
	void testFindAllScoresByTeacherId() throws DataAccessException {
		//arrange
		int teacherId = 8;
		Teacher t = new Teacher();
		t.setId(teacherId);
		Score sco1 = new Score();
		sco1.setTeacher(t);
		Score sco2 = new Score();
		sco2.setTeacher(t);
		Collection<Score> scores = new ArrayList<Score>();
		scores.add(sco1);
		scores.add(sco2);
		when(repo.findAllScoreByTeacherId(t.getId())).thenReturn(scores);
		//act
		Collection<Score> res = scoreService.findAllScoresByTeacherId(t.getId());
		//assert
		assertTrue(res.size() == 2);
	}
	
	
	@Test
	@DisplayName("Saving a score works fine")
	public void shouldSaveScore() {
		//arrange
		Score nuevoScore = new Score(9, "se porta regular en la clase, explica ",
				new Student(), new Teacher());         
		nuevoScore.setId(13);
        //act        
		this.scoreService.saveScore(nuevoScore);
		//assert
		Mockito.verify(this.repo, Mockito.times(1)).save(nuevoScore);
		
	}
	
	@Test
	@DisplayName("Saving a score to create works fine")
	@WithMockUser(value="spring")
	public void shouldSave2CreateScore() {
		//arrange
		Integer teacherId = 111;
		Score nuevoScore = new Score(9, "se porta regular en la clase, explica ",
				new Student(), new Teacher()); 
		nuevoScore.getTeacher().setId(teacherId);
		nuevoScore.setId(13);
		
		when(this.studentService.findStudentByUsername(any())).thenReturn(new Student());
        //act        
		this.scoreService.saveScore2Create(nuevoScore, teacherId);
		//assert
		Mockito.verify(this.repo, Mockito.times(1)).save(nuevoScore);
		
	}
	
	@Test
	@DisplayName("delete score")
	void deleteScoreTest() throws DataAccessException {
		//arrange
		//act
		this.scoreService.delete(score1);
		//assert
		Mockito.verify(this.repo, Mockito.times(1)).delete(score1);
	}

	//Casos negativos
	@Test
	@DisplayName("find by id doesn't exists ")
	void testFindbybadId() throws DataAccessException {
		//arrange
		int badId=3484;
		when(repo.findById(badId)).thenReturn(null);
		//act
		Score scor = scoreService.findScoreById(badId);	
		//assert
		assertTrue(scor==null);
	}
	
	
	@Test
	@DisplayName("find bad scores by teacher id")
	void testFindBadAllScoresByTeacherId() throws DataAccessException {
		int badTeacherId = 825;

		when(repo.findAllScoreByTeacherId(badTeacherId)).thenReturn(null);
		Collection<Score> res = scoreService.findAllScoresByTeacherId(badTeacherId);
		assertTrue(res == null);
	}
}


