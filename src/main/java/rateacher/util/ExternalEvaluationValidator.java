package rateacher.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import rateacher.model.ExternalEvaluation;

public class ExternalEvaluationValidator implements Validator {

	@Autowired
	public ExternalEvaluationValidator() {
	}

	@Override
	public void validate(Object target, Errors errors) {
		ExternalEvaluation externalEvaluation = (ExternalEvaluation) target;
		if (externalEvaluation.getNote()==null || externalEvaluation.getNote() < 0) {
			errors.rejectValue("note", "bad range", "Value must be between 0 and 5");
		}else if (externalEvaluation.getNote() > 5 || externalEvaluation.getNote() < 0) {
			errors.rejectValue("note", "bad range", "Value must be between 0 and 5");
		}
		if (externalEvaluation.getEvaluationReport().equals("")) {
			errors.rejectValue("evaluationReport", "blank evaluationReport", "must not be empty");
		}
		if (externalEvaluation.getComment().equals("")) {
			errors.rejectValue("comment", "blank comment", "must not be empty");
		}
		
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return ExternalEvaluation.class.isAssignableFrom(clazz);
	}

}
