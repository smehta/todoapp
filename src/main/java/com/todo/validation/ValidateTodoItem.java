package com.todo.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({ TYPE })
@Retention(RUNTIME)
@Constraint(validatedBy = TodoItemValidator.class)
@Documented
public @interface ValidateTodoItem {
    String message() default "invalid or missing fields";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
