
package rateacher.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import rateacher.model.Department;
import rateacher.model.Score;
import rateacher.model.Teacher;
import rateacher.repository.DepartmentRepository;
import rateacher.repository.TeacherRepository;

@Service
public class TeacherService {

	private TeacherRepository teacherRepository;

	private DepartmentRepository departmentRepository;


	@Autowired
	private UserService userService;

	private AuthoritiesService authoritiesService;

	@Autowired
	public TeacherService(TeacherRepository teacherRepository) { 
		this.teacherRepository = teacherRepository;
	}

	@Transactional(readOnly = true)
	public Teacher findTeacherById(int id) throws DataAccessException {
		Teacher teacher = teacherRepository.findById(id);
		return teacher;
	}
	
	@Transactional(readOnly = false)
	public void save(Teacher teacher) throws DataAccessException {
		teacherRepository.save(teacher);
	}

	@Transactional(readOnly = true)
	public Collection<Teacher> findTeachers() throws DataAccessException {
		return teacherRepository.findAll();
	}

	public Collection<Teacher> showTeacherWithScore() throws DataAccessException {
		return teacherRepository.showTeacherWithScore();
	}

	public Collection<Score> findScoresByTeacherId(int teacherId) throws DataAccessException {
		return teacherRepository.findScoresByTeacherId(teacherId);
	}

	@Transactional(readOnly = true)
	public List<Teacher> findTeacherByFirstName(String firstName) throws DataAccessException {
		return teacherRepository.findByFirstName(firstName);
	}

	
	@Transactional
	public void saveTeacher(Teacher teacher) throws DataAccessException {
		// creating teacher
		teacherRepository.save(teacher);
		// creating user
		userService.saveUser(teacher.getUser());
		// creating authorities
		authoritiesService.saveAuthorities(teacher.getUser().getUsername(), "teacher");
	}
	

	@Transactional	
	public List<Teacher> findTeachersFromDepartment(int departmentId) throws DataAccessException {
		//Tengo un departament mame y quiero obtener la lista de teachers que tienen este department
		Department d = departmentRepository.findById(departmentId); 
		List<Teacher> result = new ArrayList<Teacher>();
		for(Teacher t2 : teacherRepository.findAll()) {
			if(t2.getDepartments().contains(d)) result.add(t2);
		}
		return result;
	}

	//Buscar profesor por id de estudiante
	@Transactional(readOnly = true)
	public Collection<Teacher> findTeacherByStudentId(int studentId) throws DataAccessException {
		Collection<Teacher> teachers = teacherRepository.findByStudentId(studentId);
		assert !teachers.isEmpty();
		return teachers;
	}
	
	public Collection<Teacher> teachersToRate(int studentId)throws DataAccessException{
		return teacherRepository.teachersToRate(studentId);
	}
	
	@Transactional(readOnly = true)
	public Teacher findTeacherByUsername(String username) throws DataAccessException {
		return teacherRepository.findTeacherByUsername(username);
	}
	

}
