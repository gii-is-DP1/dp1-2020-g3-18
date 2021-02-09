package rateacher.tests.repository;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import rateacher.model.Score;
import rateacher.repository.ScoreRepository;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class ScoreRepositoryTest {

	@Autowired
	private ScoreRepository scoreRepository;

	@Test
	void findAllScoreByTeacherTest() {
	//arrange
	//act
		List<Score> scores = new ArrayList<>(scoreRepository.findAllScoreByTeacherId(1));//id de julian
	//assert	
		assertFalse(scores.isEmpty());
		assertTrue(scores.get(0).getComment().equals("Las clases de este profesor me parecen un autentico toston, creo que no debería deshaogarse tanto con nosotros"));
	}
}
