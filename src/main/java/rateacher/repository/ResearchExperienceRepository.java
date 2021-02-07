package rateacher.repository;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.dao.DataAccessException;
import org.springframework.data.repository.Repository;

import rateacher.model.ResearchExperience;

public interface ResearchExperienceRepository extends  Repository<ResearchExperience, Integer> {

	ResearchExperience findById(int id) throws DataAccessException;
	
	Collection<ResearchExperience> findAll() throws DataAccessException;

	void save(@Valid ResearchExperience researchExperience) throws DataAccessException;
	
}
