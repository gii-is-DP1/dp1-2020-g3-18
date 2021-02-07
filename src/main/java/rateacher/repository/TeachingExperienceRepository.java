package rateacher.repository;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.dao.DataAccessException;
import org.springframework.data.repository.Repository;

import rateacher.model.TeachingExperience;

public interface TeachingExperienceRepository extends Repository<TeachingExperience, Integer> {

	TeachingExperience findById(int id) throws DataAccessException;

	Collection<TeachingExperience> findAll() throws DataAccessException;

	void save(@Valid TeachingExperience teachingExperience) throws DataAccessException;
}
