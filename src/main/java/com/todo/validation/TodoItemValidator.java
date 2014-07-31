//////////////////////////////////////////////////////////////////////////////
//
// COPYRIGHT
//    Copyright (c) 2013 iRise. ALL RIGHTS RESERVED
//
// TRADE SECRET NOTICE
//    This software contains trade secret information belonging to iRise.
//    Permission to view, copy, or disclose is prohibited without the
//    express written consent of iRise.
//
// WARRANTY DISCLAIMER
//    THE IRISE CORPORATION MAKES NO REPRESENTATION ABOUT THE SUITABILITY
//    OR ACCURACY OF THIS SOFTWARE OR DATA FOR ANY PURPOSE, AND MAKES NO
//    WARRANTIES, EITHER EXPRESS OR IMPLIED, INCLUDING MERCHANTABILITY AND
//    FITNESS FOR A PARTICULAR PURPOSE OR THAT THE USE OF THIS SOFTWARE OR
//    DATA WILL NOT INFRINGE ANY THIRD PARTY PATENTS, COPYRIGHTS, TRADEMARKS,
//    OR OTHER RIGHTS. THE SOFTWARE AND DATA ARE PROVIDED "AS IS".
//
// SPECIFICATIONS ARE SUBJECT TO CHANGE WITHOUT NOTICE
//
//////////////////////////////////////////////////////////////////////////////

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
