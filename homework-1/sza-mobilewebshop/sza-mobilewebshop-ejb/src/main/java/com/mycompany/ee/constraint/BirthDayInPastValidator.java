package com.mycompany.ee.constraint;

import java.time.LocalDate;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;


public class BirthDayInPastValidator implements ConstraintValidator<BirthDayInPastConstraint, LocalDate>{

    @Override
    public void initialize(BirthDayInPastConstraint constraintAnnotation) {
        //Init BirthayInPastValidator
    }
    
   @Override
   public boolean isValid(LocalDate date, ConstraintValidatorContext context) {
       if(date!= null){
           return date.isBefore(LocalDate.now());
       }
       return true;
   }
}