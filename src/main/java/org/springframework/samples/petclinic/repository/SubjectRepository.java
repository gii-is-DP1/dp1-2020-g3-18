package org.springframework.samples.petclinic.repository;

import java.util.Collection;

import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.Subject;

public interface SubjectRepository {
	Collection<Subject> findAll() throws DataAccessException;

}
