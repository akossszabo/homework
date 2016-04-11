package com.mycompany.ee.constraint;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;


@Constraint(validatedBy = BirthDayValidator.class)
@Target({ElementType.TYPE})
@Retention(RUNTIME)
public @interface BirthDayConstraint {

    String message() default "{BirthDay.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}