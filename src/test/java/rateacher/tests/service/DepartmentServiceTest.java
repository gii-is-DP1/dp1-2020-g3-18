package rateacher.tests.service;

import static org.junit.Assert.assertTrue;

import java.util.Collection;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import rateacher.model.Department;
import rateacher.service.DepartmentService;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))

public class DepartmentServiceTest {
	
	@Autowired
	protected DepartmentService departmentService;
	
	@Test
	@DisplayName("Find Department By ID")
	public void testFindDepartmentById() {
		Department department = this.departmentService.findDepartmentById(1);
		Assertions.assertThat(department!=null);
		
	}	
	
	//Negative test
	@Test
	@DisplayName("Find Department By Bad ID")
	public void testFindDepartmentByBadId() {
		int badId = 1356;
		Department department = this.departmentService.findDepartmentById(badId);
		Assertions.assertThat(department==null);
		
	}
	
	@Test
	@DisplayName("Find All Departments")
	public void testFindAllColleges() {
		Collection<Department> departments = this.departmentService.findAll();
		assertTrue(!departments.isEmpty());	
	}
	
	@Test
	@DisplayName("Save Department")
	public void testSaveDepartment() {
		Collection<Department> departments = this.departmentService.findAll();
		int tam = departments.size();
		Department department = new Department();
		department.setName("Departamento de Matemática Aplicada");
		this.departmentService.saveDepartment(department);
		Assertions.assertThat(department.getId().longValue()).isNotEqualTo(0);
		departments = this.departmentService.findAll();
		Assertions.assertThat(departments.size()).isEqualTo(tam+1);
	}	
	

}
