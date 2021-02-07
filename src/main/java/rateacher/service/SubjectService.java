package rateacher.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import rateacher.model.Department;
import rateacher.model.Subject;
import rateacher.repository.DepartmentRepository;
import rateacher.repository.SubjectRepository;


@Service
public class SubjectService {
	
	private SubjectRepository subjectRepository;	
	private DepartmentRepository departmentRepository;
	
	@Autowired
	public SubjectService(SubjectRepository subjectRepository, DepartmentRepository departmentRepository) {
		this.subjectRepository = subjectRepository;
		this.departmentRepository = departmentRepository;
	}
	
	@Transactional(readOnly = true)	
	public Subject findSubjectById(int id) throws DataAccessException {
		Subject subject = subjectRepository.findById(id);
		return subject;
	}
	
//	@Transactional(readOnly = false)
//	public void save(Subject subject) throws DataAccessException{
//		subjectRepository.save(subject);
//	}
	
	@Transactional(readOnly = true)	
	public Collection<Subject> findSubjects() throws DataAccessException {
		return subjectRepository.findAll();
	}
	
	@Transactional
	public Collection<Subject> findAll() throws DataAccessException { //Si esta repetido pero es por costumbre y comodidad.
		return subjectRepository.findAll();
	}
	
//	@Transactional	
//	public List<Subject> findSubjectsFromDepartmentId(int idDepartment) throws DataAccessException {
//		//Tengo un departament id y quiero obtener la lista de subjects que tiene este department
//		Department d = departmentRepository.findById(idDepartment); 
//		List<Subject> result = new ArrayList<Subject>();
//		for(Subject s2 : subjectRepository.findAll()) {
//			if(s2.getDepartments().contains(d)) result.add(s2);
//		}
//		return result;
//	}
//	
	
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
