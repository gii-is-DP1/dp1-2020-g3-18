package rateacher.service;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import rateacher.model.Subject;
import rateacher.repository.SubjectRepository;


@Service
public class SubjectService {
	
	private SubjectRepository subjectRepository;	
	
	@Autowired
	public SubjectService(SubjectRepository subjectRepository) {
		this.subjectRepository = subjectRepository;
	}
	
	@Transactional(readOnly = true)	
	public Subject findSubjectById(int id) throws DataAccessException {
		Subject subject = subjectRepository.findById(id);
		return subject;
	}
	
	@Transactional(readOnly = true)	
	public Collection<Subject> findSubjects() throws DataAccessException {
		return subjectRepository.findAll();
	}
	
	@Transactional
	public Collection<Subject> findAll() throws DataAccessException { //Si esta repetido pero es por costumbre y comodidad.
		return subjectRepository.findAll();
	}
	
	@Transactional	
	public List<Subject> findSubjectsFromDepartmentId(int idDepartment) throws DataAccessException {
		return this.subjectRepository.findSubjectsFromDepartmentId(idDepartment);
	}
  
    @Transactional
	public void save(Subject subject)throws DataAccessException {
		subjectRepository.save(subject);
	}

	public void delete(Subject subject) {
		subjectRepository.delete(subject);
	}
	public void deleteTeachingPlan(Subject subject) {
		subject.setTeachingPlan(null);
	}
	
}
