package rateacher.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import rateacher.model.Score;

public class ScoreValidator implements Validator{
	
	@Autowired
	public ScoreValidator() {
	}
	
	@Override
	public void validate(Object target, Errors errors) {
		Score score = (Score) target;

		if(score.getValu()==null) {
			errors.rejectValue("valu", "null score", "You cannot leave it blank");
		}else if(score.getValu()>5 || score.getValu()<0) {
			errors.rejectValue("valu", "bad range", "Value must be between 0 and 5");
		}
		if(score.getComment().equals("")) {
			errors.rejectValue("comment", "blank comment", "You cannot leave it blank");
		} 
	}
	
	@Override
	public boolean supports(Class<?> clazz) {
		return Score.class.isAssignableFrom(clazz);
	}

}
