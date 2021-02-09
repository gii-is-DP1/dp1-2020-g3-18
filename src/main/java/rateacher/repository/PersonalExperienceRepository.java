package rateacher.repository;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import rateacher.model.PersonalExperience;

public interface PersonalExperienceRepository extends Repository<PersonalExperience, Integer>{
	
	@Query("select s from PersonalExperience s where s.id=?1")
	PersonalExperience findById(int id) throws DataAccessException;

	Collection<PersonalExperience> findAll() throws DataAccessException;

	void save(@Valid PersonalExperience personalExperience) throws DataAccessException;

}
