package rateacher.repository;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import rateacher.model.Score;

public interface ScoreRepository extends Repository<Score, Integer>{

	Score findById(int id) throws DataAccessException;
	
	Collection<Score> findAll() throws DataAccessException;

	@Query("select s from Score s where s.teacher.id = ?1")
	Collection<Score> findAllScoreByTeacherId(int id) throws DataAccessException;	
	
	void save(@Valid Score score) throws DataAccessException;
	
	void delete(@Valid Score score);

}
