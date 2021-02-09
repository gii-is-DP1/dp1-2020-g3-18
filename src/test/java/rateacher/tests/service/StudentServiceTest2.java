package rateacher.tests.service;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


import org.assertj.core.util.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataAccessException;

import rateacher.model.Score;
import rateacher.model.Student;
import rateacher.model.Subject;
import rateacher.model.Teacher;
import rateacher.model.User;
import rateacher.repository.StudentRepository;
import rateacher.service.StudentService;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class StudentServiceTest2 {

	StudentRepository repo = mock(StudentRepository.class);

	StudentService studentService = new StudentService(repo);

	Student s1;
	Student s2;

	@BeforeEach
	void initAll() {
		s1 = new Student();
		s1.setName("Jorge");
		s1.setLastName("Fernández");
		s1.setUser(new User());

	}

	// Casos positivos
	@Test
	@DisplayName("Finding all students")
	void testFindAll() throws DataAccessException {
		Collection<Student> res;

		when(this.repo.findAll()).thenReturn(Lists.list(s1, s2));

		res = this.studentService.findStudents();

		assertTrue(!res.isEmpty());
		assertTrue(res.size() == 2);
	}

	@Test
	@DisplayName("Finding by Id")
	void testFindById() throws DataAccessException {
		s1.setId(111);

		when(this.repo.findById(111)).thenReturn(s1);

		Student res = this.studentService.findStudentById(111);

		assertTrue(res.getName() == "Jorge");
	}

	@Test
	@DisplayName("Finding by Username")
	void testFindByUsername() throws DataAccessException {
		User u = new User();
		u.setUsername("test");
		s1.setUser(u);

		when(this.repo.findStudentByUsername("test")).thenReturn(s1);

		Student res = this.studentService.findStudentByUsername("test");

		assertTrue(res.getName() == "Jorge");
	}

	@Test
	@DisplayName("Finding my subjects")
	void testfindMySubjects() throws DataAccessException {
		Subject subj1 = new Subject();
		subj1.setId(100);
		Subject subj2 = new Subject();
		subj2.setId(200);
		List<Subject> subjets = new ArrayList<Subject>();
		subjets.add(subj1);
		subjets.add(subj2);
		s1.setSubjects(subjets);
		s1.setId(111);

		when(this.repo.findMySubjects(111)).thenReturn(subjets);

		List<Subject> res = this.studentService.findMySubjects(111);
		assertTrue(!res.isEmpty());
		assertTrue(res.size() == 2);
	}

	@Test
	@DisplayName("Finding student rated a teachers")
	void testfindStudentsRatedATeacher() throws DataAccessException {
		Teacher t1 = new Teacher();
		t1.setId(1000);

		Score sco1 = new Score();
		sco1.setId(10);
		Score sco2 = new Score();
		sco2.setId(20);
		List<Score> scores = new ArrayList<Score>();
		scores.add(sco1);
		scores.add(sco2);
		sco1.setTeacher(t1);
		sco2.setTeacher(t1);
		sco1.setStudent(s1);
		sco2.setStudent(s1);
		List<Student> students = new ArrayList<Student>();
		students.add(s1);

		when(this.repo.StudentsRatedATeacher(1000)).thenReturn(students);

		List<Student> res = (List<Student>) this.studentService.StudentsRatedATeacher(1000);
		assertTrue(!res.isEmpty());
		assertTrue(res.size() == 1);
	}

	// Casos negativos
	@Test
	@DisplayName("find by id doesn't exists ")
	void testFindbybadId() throws DataAccessException {
		int badId = 3484;
		when(repo.findById(badId)).thenReturn(null);
		assertThrows(AssertionError.class,()->this.studentService.findStudentById(badId));
	}
	
	@Test
	@DisplayName("Finding bad by Username")
	void testFindBadByUsername() throws DataAccessException {
		String badUsername = "aaaa";
		when(this.repo.findStudentByUsername(badUsername)).thenReturn(null);
		Student res = studentService.findStudentByUsername(badUsername);
		assertTrue(res == null);
	}
	
	@Test
	@DisplayName("Finding bad my subjects")
	void testfindBadMySubjects() throws DataAccessException {

		int badId = 123;
		when(this.repo.findMySubjects(badId)).thenReturn(null);

		List<Subject> res = this.studentService.findMySubjects(badId);
		assertTrue(res == null);
	}
	
	@Test
	@DisplayName("Finding bad student rated a teachers")
	void testfindBadStudentsRatedATeacher() throws DataAccessException {
		int badId = 1234;

		when(this.repo.StudentsRatedATeacher(badId)).thenReturn(null);

		List<Student> res = (List<Student>) this.studentService.StudentsRatedATeacher(badId);
		assertTrue(res == null);

	}
}
