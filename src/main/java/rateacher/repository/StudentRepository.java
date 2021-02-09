package rateacher.repository;

import java.util.Collection;
import java.util.List;

import javax.validation.Valid;

import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import rateacher.model.Student;
import rateacher.model.Subject;

public interface StudentRepository extends Repository<Student, Integer> {

	@Query("select s from Student s where s.id=?1")
	Student findById(int id) throws DataAccessException;

	Collection<Student> findAll() throws DataAccessException;

	void save(@Valid Student student) throws DataAccessException;


	@Query("select s from Student s where s.user.username = ?1")
	Student findStudentByUsername(String username);

	@Query("select s.subjects from Student s where s.id=?1")
	List<Subject> findMySubjects(int i);
	
	@Query("select s.subjects from Student s where s.user.username=?1")
	List<Subject> findMySubjectsByUsername(String username);

//Esta consulta muestra los profesores de un alumno, pero no se usa por eso la comento
//	@Query("select t from Teacher t where t.id in t.subjects AND t.subjects in"
//			+ "(select s.subjects from Student s where s.id =?1)")
//	Collection<Teacher> myTeachers(int id);


	@Query(nativeQuery = true, value = "SELECT * FROM STUDENTS s where s.id in "
			+ "(select student_id from scores ss where ss.teacher_id in "
			+ "(select t.id from teachers t where t.id=?1))")
	Collection<Student> StudentsRatedATeacher(int teacherId);

}
