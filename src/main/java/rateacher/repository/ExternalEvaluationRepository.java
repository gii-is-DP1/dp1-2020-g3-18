package rateacher.repository;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.dao.DataAccessException;
import org.springframework.data.repository.Repository;

import rateacher.model.ExternalEvaluation;

public interface ExternalEvaluationRepository extends Repository<ExternalEvaluation, Integer> {

	ExternalEvaluation findById(int id) throws DataAccessException;

	Collection<ExternalEvaluation> findAll() throws DataAccessException;

	void save(@Valid ExternalEvaluation externalEvaluation) throws DataAccessException;
}
