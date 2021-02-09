package rateacher.tests.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rateacher.model.Department;
import rateacher.model.Score;
import rateacher.model.Teacher;
import rateacher.model.User;
import rateacher.service.DepartmentService;
import rateacher.service.StudentService;
import rateacher.service.TeacherService;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class TeacherServiceTest {

	@Autowired
	protected StudentService studentService;

	@Autowired
	protected TeacherService teacherService;

	@Autowired
	protected DepartmentService departmentService;

	@Test
	@DisplayName("Finding teacher by id")
	void testFindTeacherById() {

		Teacher teacher = this.teacherService.findTeacherById(1);
		System.out.println(teacher.getFirstName());
		assertThat(teacher.getFirstName()).startsWith("Julian");
	}

	// Test positivo
	@Test
	@DisplayName("Finding teachers by student id")
	void testFindTeacherByStudentId() {

		Collection<Teacher> ratedTeachers = this.teacherService.findTeacherByStudentId(1);
		Integer size = ratedTeachers.size();

		assertTrue(size != null);
		assertTrue(ratedTeachers != null);
	}

	// Test negativo
	@Test
	@DisplayName("Finding teachers by bad student id")

	void testFindTeachersByBadStudentId() {
		Integer wrongStudentId = 1991;
		assertThrows(AssertionError.class, () -> this.teacherService.findTeacherByStudentId(wrongStudentId));
	}

	//Test positivo
	@Test
	@DisplayName("Finding teacher by firstName")
	void testFindTeacherByFirstName() {

		List<Teacher> teacher = this.teacherService.findTeacherByFirstName("Julian");
		assertThat(teacher.size() == 1);
		assertThat(teacher.get(0).getId() == 1);
	}
	
	//Test negativo
	@Test
	@DisplayName("Finding teacher by wrong firstName")
	void testFindTeacherByWrongFirstName() {

		List<Teacher> teacher = this.teacherService.findTeacherByFirstName("asdfghjk");
		assertThat(teacher.size()==0);
		
	}

	//Test positivo
	@Test
	@DisplayName("Finding Teacher With Score")
	void testShowTeacherWithScore() {

		List<Teacher> teacher = (List<Teacher>) this.teacherService.showTeacherWithScore();
		assertThat(teacher.size() == 1);
		assertThat(teacher.get(0).getId() == 1);
	}
	
	//Test negativo
	@Test
	@DisplayName("Finding Teacher With NO Score")
	void testShowTeacherWithNoScore() {

		Teacher teacher = new Teacher();
		List<Teacher> teachers = new ArrayList<>();
		teachers.add(teacher);
		assertThat((!this.teacherService.showTeacherWithScore().contains(teacher)));
		
	}

	//Test positivo
	@Test
	@DisplayName("Finding Scores by Teacher Id")
	void testfindScoresByTeacherId() {
		int id = 1;
		List<Score> scores = (List<Score>) this.teacherService.findScoresByTeacherId(id);
		assertThat(scores.size() == 1);
		assertThat(scores.get(0).getId() == 1);
	}
	
	//Test negativo
	@Test
	@DisplayName("Finding Scores by bad Teacher Id")
	void testfindScoresByBadTeacherId() {
		int wrongId = 2018;
		List<Score> scores = (List<Score>) this.teacherService.findScoresByTeacherId(wrongId);
		assertThat(scores.size() == 0);
	}

	
	//Test positivo
	@Test
	@DisplayName("Finding Teacher by Department")
	void findTeachersFromDepartment() {
		int id = 5;
		Department d = departmentService.findDepartmentById(id);
		List<Teacher> teachers = (List<Teacher>) teacherService.findTeachers();
		assertThat(teachers.size() == 1);
		List<Teacher> result = new ArrayList<Teacher>();
		for (Teacher t2 : teachers) {
			if (t2.getDepartments().contains(d))
				result.add(t2);
		}
		assertThat(result.size() == 1);
		assertThat(result.get(0).getId() == 1);

	}
	
	//Test negativo
	@Test
	@DisplayName("Finding Teacher by Department")
	void findTeachersByWrongDepartment() {
		int wrongId = 1939;
		Department d = departmentService.findDepartmentById(wrongId);
		List<Teacher> teachers = (List<Teacher>) teacherService.findTeachers();
		assertThat(teachers.size() == 1);
		List<Teacher> result = new ArrayList<Teacher>();
		for (Teacher t2 : teachers) {
			if (t2.getDepartments().contains(d))
				result.add(t2);
		}
		assertThat(result.size() == 0);

	}
	
	//Test negativo
	@Test
	@DisplayName("Finding a Teacher by bad id")
	void testFindTeacherByBadId() {
		int badId = 234234;
		List<Teacher> res = new ArrayList<Teacher>();
		res.add(teacherService.findTeacherById(badId));
		assertThat(res.size()==0);
	}

	//Test Positivo
	@Test
	@DisplayName("Finding teacher by student id")
	void testFindByStudentId() {

		List<Teacher> teacher = (List<Teacher>) this.teacherService.findTeacherByStudentId(1);

		assertThat(teacher.get(0).getFirstName()).startsWith("Julian");
		assertThat(teacher.get(1).getFirstName()).startsWith("Popench");
	}
	
	//Test negativo
	@Test
	@Transactional
	@DisplayName("Showing teachers to rate by bad student id")
	public void testTeacherToRateByBadStudentId() {

		int wrongId = 1971;
		Collection<Teacher> teachers = this.teacherService.teachersToRate(wrongId);
		assertThat(teachers.isEmpty());
		assertThat(teachers == null);
	}

	//Test positivo
	@Test
	@DisplayName("Finding all teachers")
	void testFindAll2() {

		Collection<Teacher> res = this.teacherService.findTeachers();

		assertTrue(!res.isEmpty());

	}

	//Test positivo
	@Test
	@Transactional
	@DisplayName("Showing teachers to rate")
	public void testTeacherToRate() {

		int studentId = 3;
		Collection<Teacher> teachers = this.teacherService.teachersToRate(studentId);
		assertThat(!teachers.isEmpty());
		assertThat(teachers != null);

	}
	
	//Test negativo
	@Test
	@Transactional
	@DisplayName("Showing teachers to rate")
	public void testTeacherToRateByBadId() {

		int wrongId = 2033;
		Collection<Teacher> teachers = this.teacherService.teachersToRate(wrongId);
		assertThat(teachers.isEmpty());

	}
	
	@Test
	@Transactional
	@DisplayName("Saving a teacher works fine")
	public void shouldSaveStudent() {
	
		Collection<Teacher> teachers = this.teacherService.findTeachers();
		int found = teachers.size();

		Teacher teacher = new Teacher();
		teacher.setName("Paco");
		teacher.setFirstName("Paco");
		teacher.setLastName("Perez");
                User user=new User();
                user.setUsername("paco");
                user.setPassword("paco1");
                user.setEnabled(true);
                teacher.setUser(user);                
                
		this.teacherService.save(teacher);
		assertThat(teacher.getId().longValue()).isNotEqualTo(0);

		teachers = this.teacherService.findTeachers();
		assertThat(teachers.size()).isEqualTo(found + 1);
	}
	
	@Test
	@DisplayName("Finding teachers by username")
	void testFindTeacherByUsername() {
		
		String username="pop";
		Teacher t = this.teacherService.findTeacherByUsername(username);
		assertThat(t.getFirstName()=="Popench");
	}
	
	@Test
	@DisplayName("Finding teachers bad by username")
	void testFindTeacherBadByUsername() {
		
		String username="test";
		List<Teacher> t = new ArrayList<Teacher>();
		t.add(this.teacherService.findTeacherByUsername(username));
		assertThat(t.size()==0);
	}

}
