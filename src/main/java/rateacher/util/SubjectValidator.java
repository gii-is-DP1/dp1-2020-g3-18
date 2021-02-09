package rateacher.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import rateacher.model.Subject;

public class SubjectValidator implements Validator{
	
	
	@Autowired
	public SubjectValidator() {
	}	

	@Override
	public void validate(Object target, Errors errors) {
		Subject subject = (Subject) target;
		
		if(subject.getName()==null) {
			errors.rejectValue("name", "null name", "You cannot leave it blank");
		}
		if(subject.getCurso()==null) {
			errors.rejectValue("curso", "null curso", "You cannot leave it blank");
		} else if(subject.getCurso()<1 || subject.getCurso()>4) {
			errors.rejectValue("curso", "null curso", "Value must be between 1 and 4");
		}	
	}
	
	@Override
	public boolean supports(Class<?> clazz) {
		return Subject.class.isAssignableFrom(clazz);
	}

}
