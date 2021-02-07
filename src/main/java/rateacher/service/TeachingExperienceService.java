package rateacher.service;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import rateacher.model.TeachingExperience;
import rateacher.repository.TeachingExperienceRepository;

@Service
public class TeachingExperienceService {

	private TeachingExperienceRepository teachingExperienceRepository;

	@Autowired
	public TeachingExperienceService(TeachingExperienceRepository teachingExperienceRepository) {
		this.teachingExperienceRepository = teachingExperienceRepository;
	}

	@Transactional(readOnly = true)
	public TeachingExperience findTeachingExperienceById(int id) throws DataAccessException {
		return teachingExperienceRepository.findById(id);
	}

	@Transactional(readOnly = true)
	public void save(@Valid TeachingExperience teachingExperience) throws DataAccessException {
		teachingExperienceRepository.save(teachingExperience);
	}

	public Collection<TeachingExperience> findAll() {
		return this.teachingExperienceRepository.findAll();
	}

}
