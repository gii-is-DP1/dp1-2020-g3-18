package rateacher.tests.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.validation.ConstraintViolationException;

import org.assertj.core.util.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import rateacher.model.Department;
import rateacher.model.Student;
import rateacher.model.Subject;
import rateacher.model.Teacher;
import rateacher.model.TeachingPlan;
import rateacher.model.User;
import rateacher.repository.DepartmentRepository;
import rateacher.repository.StudentRepository;
import rateacher.repository.SubjectRepository;
import rateacher.repository.TeachingPlanRepository;
import rateacher.service.DepartmentService;
import rateacher.service.StudentService;
import rateacher.service.SubjectService;
import rateacher.service.TeachingPlanService;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class SubjectServiceTest2 {

	SubjectRepository subjectRepo = mock(SubjectRepository.class);

	SubjectService subjectService = new SubjectService(subjectRepo, null);
	
	TeachingPlanRepository teachingPlanRepo = mock(TeachingPlanRepository.class);

	TeachingPlanService teachingPlanService = new TeachingPlanService(teachingPlanRepo, subjectService);

	Subject s1;
	Subject s2;
	Department d1;
	TeachingPlan t1;

	@BeforeEach
	void initAll() {
		s1 = new Subject();
		s1.setName("Restauracion violenta de sistemas online");
		s1.setCurso(3);
		
		s2 = new Subject();
		s2.setName("Pruebas y luego Disenyo");
		s2.setCurso(2);
				
		d1 = new Department();
		d1.setName("Violencia ingenieril");
		
		t1 = new TeachingPlan();
		t1.setName("Teaching Plan 1");
		
	}
	
		//Casos positivos
		@Test
		@DisplayName("Finding all subjects")
		void testFindAll() throws DataAccessException {
			Collection<Subject> res;

			when(this.subjectRepo.findAll()).thenReturn(Lists.list(s1, s2));

			res = this.subjectService.findAll();

			assertTrue(!res.isEmpty());
			assertTrue(res.size() == 2);
		}
		@Test
		@DisplayName("Finding by Id")
		void testFindById() throws DataAccessException {
			s1.setId(567);

			when(this.subjectRepo.findById(567)).thenReturn(s1);

			Subject res = this.subjectService.findSubjectById(567);

			assertTrue(res.getName() == "Restauracion violenta de sistemas online");
		}
		
		@Test
		@DisplayName("Saving a subject")
		void testSaveSubject() throws DataAccessException {
			
			Subject res = s1;
			s1.setName("Nuevo nombre");
			this.subjectService.save(s1);

			assertTrue(res.getName() == "Nuevo nombre");
		}
		
		@Test
		@DisplayName("Deleting a subject")
		void testDeleteSubject() throws DataAccessException {
			
			s1.setId(890);
			Subject subjectToDelete = this.subjectRepo.findById(890);
			this.subjectService.delete(subjectToDelete);
			Subject res = this.subjectRepo.findById(890);

			assertTrue(res == null);
		}
		
		@Test
		@DisplayName("Deleting a teaching plan")
		void testDeleteTeachingPlan() throws DataAccessException {
			t1.setId(456);
			s1.setId(123);
			when(this.teachingPlanRepo.findById(456)).thenReturn(t1);
			TeachingPlan teachingPlanToDelete = this.teachingPlanRepo.findById(456);
			s1.setTeachingPlan(teachingPlanToDelete);
			assertTrue(s1.getTeachingPlan().getName()== "Teaching Plan 1");
			this.teachingPlanService.delete(teachingPlanToDelete);
			assertTrue(s1.getTeachingPlan()==null);
		}
		
	//casos negativos	
		@Test
		@DisplayName("Finding by bad id")
		void testFindBadByUsername() throws DataAccessException {
			Integer badId = 666;
			when(this.subjectRepo.findById(badId)).thenReturn(null);
			Subject res = subjectService.findSubjectById(badId);
			assertTrue(res == null);
		}
		
//		@Test
//		@DisplayName("Saving subject without a name")
//		public void SavingSubjectNoNameTest() {
//			s1.setName(null);
//	                
//			this.subjectService.save(s1);
//			assertThrows(ConstraintViolationException.class,()->this.subjectService.save(s1));
//		}
}

