package rateacher.tests.validators;

import static org.junit.Assert.assertTrue;
import java.util.Locale;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import org.junit.jupiter.api.Test;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import rateacher.model.Subject;

public class SubjectValidatorTest {

	private Validator createValidator() {
		LocalValidatorFactoryBean localValidatorFactoryBean = new LocalValidatorFactoryBean();
		localValidatorFactoryBean.afterPropertiesSet();
		return localValidatorFactoryBean;
	}
	

	
	@Test
	void ShouldNotValidateWhenNameEmpty() {
		System.out.println("---------------------------------------------");
		System.out.println("Should not validate when name is empty");
		System.out.println("---------------------------------------------");
		LocaleContextHolder.setLocale(Locale.ENGLISH);
		Subject subject = new Subject();
		subject.setName("");
		subject.setCurso(1);
		Validator validator =  createValidator();
		Set<ConstraintViolation<Subject>> constraintViolations = validator.validate(subject);
		assertTrue(constraintViolations.size()==1);
		ConstraintViolation<Subject> violation = constraintViolations.iterator().next();
		assertTrue(violation.getPropertyPath().toString().equals("name"));
	
	}
	
	@Test
	void ShouldNotValidateWhenCursoEmpty() {
		System.out.println("---------------------------------------------");
		System.out.println("Should not validate when curso is empty");
		System.out.println("---------------------------------------------");
		LocaleContextHolder.setLocale(Locale.ENGLISH);
		Subject subject1 = new Subject();
		subject1.setName("Calculo Infinitesimal y Numerico");
		subject1.setCurso(null);
		Validator validator = createValidator();
		Set<ConstraintViolation<Subject>> constraintViolations1 = validator.validate(subject1);
		assertTrue(constraintViolations1.size()==1);
		ConstraintViolation<Subject> violation1 = constraintViolations1.iterator().next();
		assertTrue(violation1.getPropertyPath().toString().equals("curso"));
	}
	
	@Test
	void ShouldNotValidateWhenCursoOutOfRange() {
		System.out.println("---------------------------------------------");
		System.out.println("Should not validate when curso is out of range");
		System.out.println("---------------------------------------------");
		LocaleContextHolder.setLocale(Locale.ENGLISH);
		Subject subject1 = new Subject();
		subject1.setName("Calculo Infinitesimal y Numerico");
		subject1.setCurso(7);
		Validator validator = createValidator();
		Set<ConstraintViolation<Subject>> constraintViolations1 = validator.validate(subject1);
		assertTrue(constraintViolations1.size()==1);
		ConstraintViolation<Subject> violation1 = constraintViolations1.iterator().next();
		assertTrue(violation1.getPropertyPath().toString().equals("curso"));
		
	}
	

	


	
	
}
