package com.todo.validation;

import com.todo.resource.json.TodoItemRequest;
import org.apache.commons.lang.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class TodoItemValidator implements ConstraintValidator<ValidateTodoItem, TodoItemRequest> {

    @Override
    public void initialize(ValidateTodoItem constraintAnnotation) {
    }

    protected void createErrorMessage(ConstraintValidatorContext constraintValidatorContext, String message) {
        constraintValidatorContext.disableDefaultConstraintViolation();
        constraintValidatorContext.buildConstraintViolationWithTemplate(message).addConstraintViolation();
    }

    @Override
    public boolean isValid(TodoItemRequest todoItemRequest, ConstraintValidatorContext context) {
        if (StringUtils.isBlank(todoItemRequest.getTitle())) {
           createErrorMessage(context, "title must not be empty");
            return false;
        }

        if (todoItemRequest.getTitle().length() > 40) {
            createErrorMessage(context, "title length cannot be more than 40 characters");
            return false;
        }

        if (StringUtils.isNotBlank(todoItemRequest.getBody()) && todoItemRequest.getBody().length() > 250) {
            createErrorMessage(context, "Body length cannot be more than 250 characters");
            return false;
        }

        return true;

    }


}
