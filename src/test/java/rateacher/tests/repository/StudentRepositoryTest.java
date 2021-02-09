package rateacher.tests.repository;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import rateacher.model.Student;
import rateacher.model.Subject;
import rateacher.repository.StudentRepository;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class StudentRepositoryTest {

	@Autowired
	private StudentRepository studentRepository;
	
	@Test
	@DisplayName(value="Find By ID")
	void findByIdTest() {
	//arrange	
	//act
		Student student = this.studentRepository.findById(1);
	//assert	
		assertFalse(student.equals(new Student()));
		assertTrue(student!=null);
		assertTrue(student.getFirstName().equals("Tomás Francisco"));
	}	
	
	@Test
	@DisplayName(value="Find Student By Username")
	void findStudentByUsername() {
	//arrange	
	//act
		Student student = this.studentRepository.findStudentByUsername("tomas");
	//assert	
		assertTrue(student.getFirstName().equals("Tomás Francisco"));
	}
	
	@Test
	@DisplayName(value="Find My Subjects")
	void findMySubjects() {
	//arrange	
	//act
		List<Subject> subject = new ArrayList<>(studentRepository.findMySubjects(1));
	//assert	
		assertFalse(subject.isEmpty());
		assertTrue(subject.size()==2);
		assertTrue(subject.get(0).getName().equals("Circuitos Electronicos Digitales"));
	}
	
	@Test
	@DisplayName(value="Find My Subjects By Username")
	void findMySubjectsByUsername() {
	//arrange	
	//act
		List<Subject> subject = new ArrayList<>(studentRepository.findMySubjectsByUsername("tomas"));
	//assert	
		assertFalse(subject.isEmpty());
		assertTrue(subject.size()==2);
		assertTrue(subject.get(0).getName().equals("Circuitos Electronicos Digitales"));
	}
	
	@Test
	@DisplayName(value="Students Rated a Teacher")
	void studentsRatedATeacher() {
	//arrange	
	//act
		List<Student> students = (List<Student>) this.studentRepository.StudentsRatedATeacher(1);
	//assert	
		assertFalse(students.isEmpty());
		
		assertTrue(students.size()==1);
		assertTrue(students.get(0).getFirstName().equals("Tomás Francisco"));
	}

}
