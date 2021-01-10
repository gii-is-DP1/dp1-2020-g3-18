package rateacher.repository;

import java.util.Collection;
import java.util.List;

import javax.validation.Valid;

import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import rateacher.model.Subject;

public interface SubjectRepository extends Repository<Subject, Integer> {

	Subject findById(int id) throws DataAccessException;

	Collection<Subject> findAll() throws DataAccessException;

	void save(@Valid Subject subject) throws DataAccessException;

	
	@Query("select s from Subject s where s.department.id = ?1")

	List<Subject> findSubjectsFromDepartmentId(int idDepartment);

}
