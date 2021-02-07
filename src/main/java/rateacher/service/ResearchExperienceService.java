package rateacher.service;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import rateacher.model.ResearchExperience;
import rateacher.repository.ResearchExperienceRepository;

@Service
public class ResearchExperienceService {

	private ResearchExperienceRepository researchExperienceRepository;

	@Autowired
	public ResearchExperienceService(ResearchExperienceRepository researchExperienceRepository) {
		this.researchExperienceRepository = researchExperienceRepository;
	}

	@Transactional(readOnly = true)
	public ResearchExperience findResearchExperienceById(int id) throws DataAccessException {
		return researchExperienceRepository.findById(id);
	}

	@Transactional(readOnly = true)
	public void save(@Valid ResearchExperience researchExperience) throws DataAccessException {
		researchExperienceRepository.save(researchExperience);
	}

	public Collection<ResearchExperience> findAll() {
		return this.researchExperienceRepository.findAll();
	}

}
