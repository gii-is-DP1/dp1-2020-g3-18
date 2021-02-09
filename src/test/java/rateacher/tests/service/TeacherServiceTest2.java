package rateacher.tests.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.assertj.core.util.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import rateacher.model.Department;
import rateacher.model.Score;
import rateacher.model.Student;
import rateacher.model.Teacher;
import rateacher.model.User;
import rateacher.repository.AuthoritiesRepository;
import rateacher.repository.DepartmentRepository;
import rateacher.repository.ScoreRepository;
import rateacher.repository.StudentRepository;
import rateacher.repository.TeacherRepository;
import rateacher.repository.UserRepository;
import rateacher.service.AuthoritiesService;
import rateacher.service.DepartmentService;
import rateacher.service.ScoreService;
import rateacher.service.StudentService;
import rateacher.service.TeacherService;
import rateacher.service.UserService;



public class TeacherServiceTest2 {
	
	UserRepository userRepo = mock(UserRepository.class);
	
	UserService userService = new UserService(userRepo);
	
	AuthoritiesRepository authoritiesRepo = mock(AuthoritiesRepository.class);
	
	 AuthoritiesService authoritiesService = new AuthoritiesService(authoritiesRepo, userService);
	
	TeacherRepository teacherRepo = mock(TeacherRepository.class);
	
	TeacherService teacherService = new TeacherService(teacherRepo);
	
	StudentRepository studentRepo = mock(StudentRepository.class);
	
	StudentService studentService = new StudentService(studentRepo, teacherRepo);
	
	DepartmentRepository departmentRepo = mock(DepartmentRepository.class);
	
	DepartmentService departmentService = new DepartmentService(departmentRepo);
	
	ScoreRepository scoreRepo = mock(ScoreRepository.class);
	
	ScoreService scoreService = new ScoreService(scoreRepo, studentService);
	
	
	Teacher t1;
	Teacher t2;
	Student s1;

	
	// Casos positivos
	@BeforeEach
	void initAll() {
		t1 = new Teacher();
		t1.setName("Perico");
		t1.setLastName("Pedrolo");
		t1.setUser(new User());
		
		
		Collection<Department> departments = new ArrayList<Department>();
		
		Department department1 = new Department();
		department1.setName("Math");
		departments.add(department1);
		
		t1.setDepartments(departments);
		
		s1 = new Student();
		s1.setLastName("Garcia");
		s1.setFirstName("Antonio");
		s1.setUser(new User());
		s1.setTeachers(new ArrayList<Teacher>());

		
	}
	
	@Test
	@DisplayName("Finding all teachers")
	void testFindAll() throws DataAccessException{

		Collection<Teacher> res;

		when(this.teacherRepo.findAll()).thenReturn(Lists.list(t1, t2));

		res = this.teacherService.findTeachers();

		assertTrue(!res.isEmpty());
		assertTrue(res.size()==2);

	}

	@Test
	@DisplayName("Find by id returns teacher")
	void testFindbyId() throws DataAccessException {
		int id = 8;
		t1.setId(id);
		when(teacherRepo.findById(id)).thenReturn(t1);
		
		ArrayList<Department> deps = (ArrayList<Department>) t1.getDepartments();
		Department d1 = deps.get(0);
		
		Teacher res = teacherService.findTeacherById(id);
		
		assertTrue(res.getName()==t1.getName());
		assertTrue(d1.getName().equals("Math"));
	}
	
	@Test
	@DisplayName("Finding a Teacher by bad id")
	void testFindTeacherByBadId(){
		int badId = 234234;
		assertTrue(this.teacherService.findTeacherById(badId)==null);
	}
	

	// Test positivo
	@Test
	@DisplayName("Finding teachers by student id")
	void testFindTeacherByStudentId() { 
		s1.setId(100);
		s1.setName("asas");
		s1.getTeachers().add(t1);
		when(this.teacherRepo.findByStudentId(100)).thenReturn((List<Teacher>) (s1.getTeachers()));

		
		assertTrue(s1.getTeachers().size() == 1);
	}
	
	// Test negativo
	@Test
	@DisplayName("Finding a teacher that a student doesn`t have")

	void testFindTeacherNotInStudentCollection() {
		s1.setId(100);
		when(this.teacherRepo.findByStudentId(100)).thenReturn((List<Teacher>) (s1.getTeachers()));
				
		assertFalse(s1.getTeachers().contains(t1));
		
	}
	

	//Test positivo
	@Test
	@DisplayName("Find teacher by first name")
	void testFindTeacherByFirstName() {
		t1.setFirstName("prueba");
		List<Teacher> list = new ArrayList<Teacher>();
		list.add(t1);
		when(this.teacherRepo.findByFirstName("prueba")).thenReturn(list);
		
		assertTrue(list.size()==1);
		assertTrue(list.get(0) == t1);
	}
	
	//Test negativo
	@Test
	@DisplayName("Find teacher by first name that not exist")
	void testFindTeacherByFirstNameNoExist() {
		List<Teacher> list = new ArrayList<Teacher>();
		when(this.teacherRepo.findByFirstName("nadaaaa")).thenReturn(list);
		
		assertTrue(list.isEmpty());
	
	}
		
	//Test positivo
	@Test
	@DisplayName("Find teacher with score")
	void findTeacherWithScore() {
		Score s = new Score();
		s.setTeacher(t1);
		List<Teacher> teachers = new ArrayList<Teacher>();
		teachers.add(t1);

		when(this.teacherService.showTeacherWithScore()).thenReturn(teachers);
		
		assertTrue(teachers.size()==1);
	}
	
	//Test negativo
	@Test
	@DisplayName("Find teacher with no score")
	void findTeacherWithNoScore() {

		List<Teacher> teachers = new ArrayList<Teacher>();

		when(this.teacherService.showTeacherWithScore()).thenReturn(teachers);
		
		assertTrue(teachers.isEmpty());
	}
	
	//Test positivo
	@Test
	@DisplayName("Find teacher from Department")
	void testFindTeacherFromDepartment() {

		Department d = new Department();
		d.setId(100);
		t1.getDepartments().add(d);
		List<Teacher> t = new ArrayList<Teacher>();
		t.add(t1);
		when(this.departmentRepo.findById(100)).thenReturn(d);
		when(this.teacherRepo.findAll()).thenReturn(t);
		
		assertTrue(t.size()==1);

	}
	
	//Test negativo
	@Test
	@DisplayName("Find teachers that are not from a Department (Empty)")
	void testFindTeacherNoFromDepartment() {
		
		Department d = new Department();
		d.setId(100);
		List<Teacher> t = new ArrayList<Teacher>();
		when(this.departmentRepo.findById(100)).thenReturn(d);
		when(this.teacherRepo.findAll()).thenReturn(t);
		
		assertTrue(t.size()==0);
		assertFalse(t.contains(t1));
	}
	

	
	
	
}
