package rateacher.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import rateacher.model.College;
import rateacher.model.Dean;
import rateacher.model.Teacher;
import rateacher.repository.DeanRepository;
import rateacher.repository.TeacherRepository;

@Service
public class DeanService {
	
	private DeanRepository deanRepository;
	private TeacherRepository teacherRepository;
	private UserService userService;
	private AuthoritiesService authoritiesService;
	
	@Autowired
	public DeanService(DeanRepository deanRepository, TeacherRepository teacherRepository, UserService userService, AuthoritiesService authoritiesService) {
		this.deanRepository = deanRepository;
		this.teacherRepository = teacherRepository;
		this.userService = userService;
		this.authoritiesService = authoritiesService;
	}
	
	@Transactional
	public void saveDean(Dean dean) throws DataAccessException {
		// creating student
		deanRepository.save(dean);
		// creating user
		userService.saveUser(dean.getUser());
		// creating authorities
		authoritiesService.saveAuthorities(dean.getUser().getUsername(), "dean");
	}

	@Transactional(readOnly = true)
	public void saveTeacher(Teacher teacher) throws DataAccessException{
		teacherRepository.save(teacher);
	}
	
	@Transactional(readOnly = true)
	public Collection<Dean> findAll() throws DataAccessException {
		return deanRepository.findAll();
	}
	
	@Transactional(readOnly = true)
	public Collection<College> findAllColleges() throws DataAccessException {
		return deanRepository.findAllColleges();
	}
	
	@Transactional(readOnly = true)
	public Dean findDeanByUsername(String username) throws DataAccessException {
		return deanRepository.findDeanByUsername(username);
	}

}
