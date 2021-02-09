package rateacher.service;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import rateacher.model.Student;
import rateacher.model.Subject;
import rateacher.repository.StudentRepository;

@Service
public class StudentService {

	private StudentRepository studentRepository;


	@Autowired
	private UserService userService;

	@Autowired
	private AuthoritiesService authoritiesService;

	@Autowired
	public StudentService(StudentRepository studentRepository) {
		this.studentRepository = studentRepository;

	}

	@Transactional(readOnly = true)
	public Student findStudentById(int id) throws DataAccessException {
		Student student = studentRepository.findById(id);
		assert student != null;
		return student;
	}

	@Transactional(readOnly = true)
	public Collection<Student> findStudents() throws DataAccessException {
		return studentRepository.findAll();
	}

	@Transactional
	public void saveStudent(Student student) throws DataAccessException {
		// creating student
		studentRepository.save(student);
		// creating user
		userService.saveUser(student.getUser());
		// creating authorities
		authoritiesService.saveAuthorities(student.getUser().getUsername(), "student");
	}
	
	@Transactional(readOnly = true)
	public Student findStudentByUsername(String username) throws DataAccessException {
		return studentRepository.findStudentByUsername(username);
	}
	
	@Transactional
	public List<Subject> findMySubjects(int i){
		return studentRepository.findMySubjects(i);
	}
	
	@Transactional
	public List<Subject> findMySubjectsByUsername(String username){
		return studentRepository.findMySubjectsByUsername(username);
	}

	
	public Collection<Student> StudentsRatedATeacher(int teacherId) throws DataAccessException{
		return studentRepository.StudentsRatedATeacher(teacherId);
	}
	

}
