package rateacher.util;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import rateacher.model.Teacher;

public class TeacherValidator implements Validator{

	@Override
	public void validate(Object target, Errors errors) {
		Teacher teacher = (Teacher) target;

		if(teacher.getFirstName().equals("")) {
			errors.rejectValue("firstName", "null first name", "You cannot leave it blank");
		}
		if(teacher.getLastName().equals("")) {
			errors.rejectValue("lastName", "null last name", "You cannot leave it blank");
		}
		if(teacher.getName().equals("")) {
			errors.rejectValue("name", "blank name", "You cannot leave it blank");
		}else if(!(teacher.getName().equals(teacher.getFirstName() + " " + teacher.getLastName()))){
			errors.rejectValue("name", "wrong name", "Name must be equal to first name plus last name");
		}
		if(teacher.getUser()==null) {
			errors.rejectValue("user.username", "null user", "You cannot leave it blank");
		}else if(teacher.getUser().getUsername().equals("")) {
			errors.rejectValue("user.username", "blank username", "You cannot leave it blank");
		}else if(teacher.getUser().getPassword().equals("")) {
			errors.rejectValue("user.password", "blank password", "You cannot leave it blank");
		}
	}
	
	@Override
	public boolean supports(Class<?> clazz) {
		return Teacher.class.isAssignableFrom(clazz);
	}

}
