package rateacher.tests.validators;


import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Locale;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;


import org.junit.jupiter.api.Test;

import org.springframework.context.i18n.LocaleContextHolder;

import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;



import rateacher.model.Report;

public class ReportValidatorTest {
	
	
	private Validator createValidator() {
		LocalValidatorFactoryBean localValidatorFactoryBean = new LocalValidatorFactoryBean();
		localValidatorFactoryBean.afterPropertiesSet();
		return localValidatorFactoryBean;
	}
	

	@Test
	void ShouldNotValidateWhenReasonEmpty() {
		System.out.println("---------------------------------------------");
		System.out.println("Should not validate when there is no reason");
		System.out.println("---------------------------------------------");
		LocaleContextHolder.setLocale(Locale.ENGLISH);

		Report report = new Report();
		report.setReason("");

		Validator validator = createValidator();		
		Set<ConstraintViolation<Report>> constraintViolations = validator.validate(report);
		assertTrue(constraintViolations.size() == 1);
		ConstraintViolation<Report> violation = constraintViolations.iterator().next();
		assertTrue(violation.getPropertyPath().toString().equals("reason"));
		assertTrue(violation.getMessage().equals("must not be blank"));
	}
	

}
