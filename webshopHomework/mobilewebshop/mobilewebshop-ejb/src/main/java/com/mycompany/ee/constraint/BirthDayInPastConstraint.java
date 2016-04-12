package com.mycompany.ee.constraint;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;


@Constraint(validatedBy = BirthDayInPastValidator.class)
@Target({ElementType.TYPE,ElementType.FIELD})
@Retention(RUNTIME)
public @interface BirthDayInPastConstraint {

    String message() default "{User's birthday should be in the past}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}