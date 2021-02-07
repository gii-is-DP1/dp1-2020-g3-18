package rateacher.service;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import rateacher.model.ProfessionalExperience;
import rateacher.repository.ProfessionalExperienceRepository;

@Service
public class ProfessionalExperienceService {

	private ProfessionalExperienceRepository professionalExperienceRepository;

	@Autowired
	public ProfessionalExperienceService(ProfessionalExperienceRepository professionalExperienceRepository) {
		this.professionalExperienceRepository = professionalExperienceRepository;
	}

	@Transactional(readOnly = true)
	public ProfessionalExperience findProfessionalExperienceById(int id) throws DataAccessException {
		return professionalExperienceRepository.findById(id);
	}

	@Transactional(readOnly = true)
	public void save(@Valid ProfessionalExperience professionalExperience) throws DataAccessException {
		professionalExperienceRepository.save(professionalExperience);
	}

	public Collection<ProfessionalExperience> findAll() {
		return this.professionalExperienceRepository.findAll();
	}
}
