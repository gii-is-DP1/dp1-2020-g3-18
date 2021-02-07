package rateacher.tests.validators;
import static org.junit.Assert.assertTrue;
import java.util.Locale;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import org.junit.jupiter.api.Test;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import rateacher.model.ExternalEvaluation;



public class ExternalEvaluationValidatorTest {

	private Validator createValidator() {
		LocalValidatorFactoryBean localValidatorFactoryBean = new LocalValidatorFactoryBean();
		localValidatorFactoryBean.afterPropertiesSet();
		return localValidatorFactoryBean;
	}
	

	
	@Test
	void ShouldNotValidateWhenNoteOutOfRange() {
		System.out.println("---------------------------------------------");
		System.out.println("Should not validate when note out of range");
		System.out.println("---------------------------------------------");
		LocaleContextHolder.setLocale(Locale.ENGLISH);
		ExternalEvaluation externalEvaluation = new ExternalEvaluation();
		externalEvaluation.setNote(20);
		externalEvaluation.setEvaluationReport("evaluationReport");
		externalEvaluation.setComment("Este es un comentario de prueba para el test");
		Validator validator =  createValidator();
		Set<ConstraintViolation<ExternalEvaluation>> constraintViolations = validator.validate(externalEvaluation);
		assertTrue(constraintViolations.size()==1);
		ConstraintViolation<ExternalEvaluation> violation = constraintViolations.iterator().next();
		assertTrue(violation.getPropertyPath().toString().equals("note"));
		assertTrue(violation.getMessage().equals("must be between 1 and 5"));
	
	}
	
	@Test
	void ShouldNotValidateWhenEvaluationReportEmpty() {
		System.out.println("---------------------------------------------");
		System.out.println("Should not validate when evaluation report is empty");
		System.out.println("---------------------------------------------");
		LocaleContextHolder.setLocale(Locale.ENGLISH);
		ExternalEvaluation externalEvaluation = new ExternalEvaluation();
		externalEvaluation.setNote(3);
		externalEvaluation.setEvaluationReport("");
		externalEvaluation.setComment("comment");
		Validator validator = createValidator();
		Set<ConstraintViolation<ExternalEvaluation>> constraintViolations = validator.validate(externalEvaluation);
		assertTrue(constraintViolations.size()==1);
		ConstraintViolation<ExternalEvaluation> violation = constraintViolations.iterator().next();
		assertTrue(violation.getPropertyPath().toString().equals("evaluationReport"));
		//assertTrue(violation.getMessage().equals("must not be empty"));
		
	}

	
	@Test
	void ShouldNotValidateWhenCommentEmpty() {
		System.out.println("---------------------------------------------");
		System.out.println("Should not validate when comment is empty");
		System.out.println("---------------------------------------------");
		LocaleContextHolder.setLocale(Locale.ENGLISH);
		ExternalEvaluation externalEvaluation1 = new ExternalEvaluation();
		externalEvaluation1.setNote(3);
		externalEvaluation1.setEvaluationReport("evaluationReport");
		externalEvaluation1.setComment("");
		Validator validator = createValidator();
		Set<ConstraintViolation<ExternalEvaluation>> constraintViolations1 = validator.validate(externalEvaluation1);
		assertTrue(constraintViolations1.size()==1);
		ConstraintViolation<ExternalEvaluation> violation1 = constraintViolations1.iterator().next();
		assertTrue(violation1.getPropertyPath().toString().equals("comment"));
		//assertTrue(violation1.getMessage().equals("must not be empty"));
		
	}
	

	


	
	
}
