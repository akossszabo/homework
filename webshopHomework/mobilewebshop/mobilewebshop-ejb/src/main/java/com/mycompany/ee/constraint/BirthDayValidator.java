package com.mycompany.ee.constraint;

import com.mycompany.ee.dto.UserDTO;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;


public class BirthDayValidator implements ConstraintValidator<BirthDayConstraint, UserDTO>{

    @Override
    public void initialize(BirthDayConstraint constraintAnnotation) {
    }

    @Override
    public boolean isValid(UserDTO user, ConstraintValidatorContext context) {
        if(user.getDateOfBirth()!= null){
            if(user.getDateOfBirth().isBefore(user.getRegistrationDate())){
                return true;
            }
        }
        return false;
    }
    
}
