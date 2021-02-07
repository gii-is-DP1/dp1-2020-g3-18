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
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import rateacher.model.Department;
import rateacher.model.Subject;
import rateacher.model.Teacher;
import rateacher.model.TeachingPlan;
import rateacher.service.StudentService;
import rateacher.service.SubjectService;
import rateacher.service.TeachingPlanService;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class SubjectServiceTest {
	
	@Autowired
	protected SubjectService subjectService;
	
	@Autowired
	protected StudentService studentService;
	
	@Autowired
	protected TeachingPlanService teachingPlanService;
	
	//Casos positivos
		@Test
		@DisplayName("Finding all subjects")
		void testFindAll() {

			Collection<Subject> res = this.subjectService.findSubjects();

			assertTrue(!res.isEmpty());
			

		}
		@Test
		@DisplayName("Finding subject by id")
		void testFindStudentById(){
			
			Subject subject = this.subjectService.findSubjectById(111);
			System.out.println(subject.getName());
			assertThat(subject.getName()).startsWith("Diseño y pruebas");
		}
		
		@Test
		@DisplayName("Finding subject by departmentId")
		void testFindFindSubjectsFromDepartmentId(){
			int idDepartment = 1;

			List<Subject> res = this.subjectService.findSubjectsFromDepartmentId(idDepartment);
			assertTrue(res.size() != 0); // está así porque no tenemos relacionados aún ninguno
		}
		
		@Test
		@DisplayName("Saving a subject")
		public void savingSubject() {
		
			Collection<Subject> subjects = this.subjectService.findAll();
			int found = subjects.size();

			Subject subject = new Subject();
			subject.setName("Restauración violenta de sistemas online");
			subject.setCurso(3);
				Department d1 = new Department();
				Collection<Department> departments = new ArrayList<Department>();
				d1.setName("Violencia ingenieril");
				departments.add(d1);
	              Teacher t1 = new Teacher();
	              t1.setFirstName("Antouniou");
	              t1.setLastName("Perks");
	              t1.setDepartments(departments);
				  Collection<Teacher> teachers = new ArrayList<Teacher>();
	        subject.setTeachers(teachers);
	                
			this.subjectService.save(subject);
			assertThat(subject.getId().longValue()).isNotEqualTo(0);

			subjects = this.subjectService.findSubjects();
			assertThat(subjects.size()).isEqualTo(found + 1);
		}
		
		@Test
		@DisplayName("Deleting a subject")
		public void deletingSubject() {
			
			Collection<Subject> subjects = this.subjectService.findAll();
			int found = subjects.size();
		
			Subject subject = new Subject();
			subject.setName("Restauración violenta de sistemas online");
			subject.setCurso(3);
				Department d1 = new Department();
				Collection<Department> departments = new ArrayList<Department>();
				d1.setName("Violencia ingenieril");
				departments.add(d1);
	              Teacher t1 = new Teacher();
	              t1.setFirstName("Antouniou");
	              t1.setLastName("Perks");
	              t1.setDepartments(departments);
				  Collection<Teacher> teachers = new ArrayList<Teacher>();
	        subject.setTeachers(teachers);
	        this.subjectService.save(subject);
			assertThat(subject.getId().longValue()).isNotEqualTo(0);

			subjects = this.subjectService.findSubjects();
			assertThat(subjects.size()).isEqualTo(found + 1);
			
			this.subjectService.delete(subject);
			Collection<Subject> subjectsAfter = this.subjectService.findAll();
			assertThat(subjectsAfter.size()).isEqualTo(found);

		}
		
		@Test
		@DisplayName("Deleting a teaching plan")
		void testDeleteTeachingPlan() throws DataAccessException {
			Subject subject = new Subject();
			subject.setName("Restauración violenta de sistemas online");
			subject.setCurso(3);
				Department d1 = new Department();
				Collection<Department> departments = new ArrayList<Department>();
				d1.setName("Violencia ingenieril");
				departments.add(d1);
	              Teacher t1 = new Teacher();
	              t1.setFirstName("Antouniou");
	              t1.setLastName("Perks");
	              t1.setDepartments(departments);
				  Collection<Teacher> teachers = new ArrayList<Teacher>();
	        subject.setTeachers(teachers);
	        	TeachingPlan teachingPlan = new TeachingPlan();
	        	teachingPlan.setName("Teaching Plan");
	        	teachingPlan.setId(3);
	        subject.setTeachingPlan(teachingPlan);
		
	        assertThat(subject.getTeachingPlan().getId() == 3);
	        
	        this.teachingPlanService.delete(teachingPlan);
	        assertThat(subject.getTeachingPlan()==null);
		}
		
	//casos negativos	
		@Test
		@DisplayName("Finding a subject by bad id")
		void testFindSubjectByBadId(){
			int badId = 234234;
			assertThat(this.subjectService.findSubjectById(badId)==null); // lo mismo
		}
		
		@Test
		@DisplayName("Finding bad subject by departmentId")
		void testFindBadFindSubjectsFromDepartmentId(){
			int badId = 22222;
			assertThat(this.subjectService.findSubjectsFromDepartmentId(badId)==null);//problemas
		}
		
		@Test
		@DisplayName("Saving subject without a name")
		public void SavingSubjectNoNameTest() {
			Subject subject = new Subject();
			subject.setName(null);
			subject.setCurso(3);
	                
			assertThrows(ConstraintViolationException.class,()->this.subjectService.save(subject));
		}
}

