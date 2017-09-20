package com.fedex.lhso.ils.entity.constraints;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.fedex.lhso.ils.entity.annotations.UniqueUsername;

public class UniqueUsernameValidator implements ConstraintValidator<UniqueUsername, String> {

	
	@Override
	public void initialize(UniqueUsername constraintAnnotation) {
		
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
/*		if(userRepository == null){
			return true;
		}
		
		User user = userRepository.findByName(value);
		
		if(user != null){
			return false;
		}
*/		return true;
	}

}
