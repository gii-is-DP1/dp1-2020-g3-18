package rateacher.repository;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.dao.DataAccessException;
import org.springframework.data.repository.Repository;

import rateacher.model.ProfessionalExperience;

public interface ProfessionalExperienceRepository extends Repository<ProfessionalExperience, Integer> {

	ProfessionalExperience findById(int id) throws DataAccessException;

	Collection<ProfessionalExperience> findAll() throws DataAccessException;

	void save(@Valid ProfessionalExperience professionalExperience) throws DataAccessException;

}
