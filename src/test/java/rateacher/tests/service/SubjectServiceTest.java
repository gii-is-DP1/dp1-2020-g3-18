package rateacher.tests.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Collection;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import rateacher.model.Department;
import rateacher.model.Subject;
import rateacher.service.StudentService;
import rateacher.service.SubjectService;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class SubjectServiceTest {
	
	@Autowired
	protected SubjectService subjectService;
	
	@Autowired
	protected StudentService studentService;
	
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
			assertTrue(res.size() == 0); // está así porque no tenemos relacionados aún ninguno
		}
		
	//casos negativos	
		@Test
		@DisplayName("Finding a subject by bad id")
		void testFindSubjectByBadId(){
			int badId = 234234;
			assertThrows(AssertionError.class,()->this.subjectService.findSubjectById(badId)); // lo mismo
		}
		
		@Test
		@DisplayName("Finding bad subject by departmentId")
		void testFindBadFindSubjectsFromDepartmentId(){
			int badId = 22222;
			assertThrows(AssertionError.class, () -> this.subjectService.findSubjectsFromDepartmentId(badId));//problemas
		}
		

}
