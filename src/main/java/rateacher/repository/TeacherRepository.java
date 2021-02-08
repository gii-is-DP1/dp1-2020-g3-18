package rateacher.repository;

import java.util.Collection;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import org.springframework.data.repository.query.Param;

import rateacher.model.Score;
import rateacher.model.Student;
import rateacher.model.Subject;
import rateacher.model.Teacher;

public interface TeacherRepository extends Repository<Teacher, Integer> {

	Teacher findById(int id) throws DataAccessException;

	Collection<Teacher> findAll() throws DataAccessException;

	void save(Teacher teacher) throws DataAccessException;

	public List<Teacher> findByFirstName(String firstName);

	@Query("select t from Teacher t  where t.id in (select teacher from Score s where s.teacher is not null)")
	Collection<Teacher> showTeacherWithScore();

	@Query("select s from Score s where s.teacher.id = ?1")
	Collection<Score> findScoresByTeacherId(int id);

	@Query("select t from Teacher t where t.id in (select s.teacher from Score s where s.teacher is not null  AND s.student in (select e.id from Student e where e.id = ?1))")
	Collection<Teacher> findByStudentId(int studentId);

	@Query("select t from Teacher t where t.user.username = ?1")
	Teacher findTeacherByUsername(String username);

	@Query(nativeQuery = true, value = "select * from teachers t where t.id in"
			+ "( select q.teacher_id from teachers_subjects q where q.subject_id in "
			+ "(select s.subject_id from students_subjects s where s.student_id =?1 ))")
	Collection<Teacher> teachersToRate(int studentId);

}
