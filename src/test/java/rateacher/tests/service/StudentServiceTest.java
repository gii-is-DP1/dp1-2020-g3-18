package rateacher.tests.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.validation.ConstraintViolationException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import rateacher.model.Department;
import rateacher.model.Student;
import rateacher.model.Subject;
import rateacher.model.Teacher;
import rateacher.model.TeachingPlan;
import rateacher.model.User;
import rateacher.service.StudentService;
import rateacher.service.TeacherService;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class StudentServiceTest {

	
	@Autowired
	protected StudentService studentService; //= new StudentService(repo);
	protected TeacherService teacherService;

	
	//Casos positivos
	@Test
	@DisplayName("Finding all students")
	void testFindAll() {

		Collection<Student> res = this.studentService.findStudents();

		assertTrue(!res.isEmpty());

	}
	@Test
	@DisplayName("Finding student by id")
	void testFindStudentById(){
		
		Student student = this.studentService.findStudentById(2);
		System.out.println(student.getFirstName());
		assertThat(student.getFirstName()).startsWith("Francisco");
	}
	
	//Positive test
	@Test
	@Transactional
	@DisplayName("Saving a student works fine")
	public void shouldSaveStudent() {
	
		Collection<Student> students = this.studentService.findStudents();
		int found = students.size();

		Student student = new Student();
		student.setName("Paco");
		student.setFirstName("Paco");
		student.setLastName("Perez");
                User user=new User();
                user.setUsername("paco");
                user.setPassword("paco1");
                user.setEnabled(true);
                student.setUser(user);                
                
		this.studentService.saveStudent(student);
		assertThat(student.getId().longValue()).isNotEqualTo(0);

		students = this.studentService.findStudents();
		assertThat(students.size()).isEqualTo(found + 1);
	}

	
	// Negative test
	@Test
	@DisplayName("Finding a student by bad id")
	void testFindStudentByBadId(){
		int badId = 234234;
		assertThrows(AssertionError.class,()->this.studentService.findStudentById(badId));
	}
	
	@Test
	@Transactional
	@DisplayName("Saving student without name")
	public void SavingStudentBadTest() {
	

		Student student = new Student();
		student.setName(null);//valor null obligatorio
		student.setFirstName("Paco");
		student.setLastName("Perez");
                User user=new User();
                user.setUsername("paco");
                user.setPassword("paco1");
                user.setEnabled(true);
                student.setUser(user);    
        
                
		assertThrows(ConstraintViolationException.class,()->this.studentService.saveStudent(student));
	}
	
	//Possitive test
	@Test
	@Transactional
	@DisplayName("Finding my subjects by username")
	public void testFindMySubjectsByUsername() {
		
                
		List<Subject> ls = this.studentService.findMySubjectsByUsername("tomas");
		List<Teacher> lt = new ArrayList<>();
		Department department1 = new Department("DTE");
		TeachingPlan tp1 = new TeachingPlan("Our teaching plan");
		Teacher teacher1 = new Teacher();
		Teacher teacher2 = new Teacher();
		lt.add(teacher1);
		lt.add(teacher2);
		Subject s1 = new Subject("Arquitectura de Computadores", 2, department1, lt, tp1);
		ls.add(s1);
		
		assertTrue(!ls.isEmpty());
		assertTrue(ls.contains(s1));
	}

	//Negative test
	@Test
	@Transactional
	@DisplayName("Finding my subjects by bad username")
	public void testFindMySubjectsByBadUsername() {
		String badUserName="klsdfhglskdf";
		List<Subject> ls = this.studentService.findMySubjectsByUsername(badUserName);
		
		assertTrue(ls.isEmpty());
	}
	


}
