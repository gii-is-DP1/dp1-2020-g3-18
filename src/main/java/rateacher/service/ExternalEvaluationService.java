package rateacher.service;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import rateacher.model.ExternalEvaluation;
import rateacher.repository.ExternalEvaluationRepository;

@Service
public class ExternalEvaluationService {

	private ExternalEvaluationRepository externalEvaluationRepository;

	@Autowired
	public ExternalEvaluationService(ExternalEvaluationRepository externalEvaluationRepository) {
		this.externalEvaluationRepository = externalEvaluationRepository;
	}

	@Transactional(readOnly = true)
	public ExternalEvaluation findExternalEvaluationById(int id) throws DataAccessException {
		return externalEvaluationRepository.findById(id);
	}

	@Transactional(readOnly = true)
	public void save(@Valid ExternalEvaluation externalEvaluation) throws DataAccessException {
		externalEvaluationRepository.save(externalEvaluation);
	}

	public Collection<ExternalEvaluation> findAll() {
		return this.externalEvaluationRepository.findAll();
	}
}
