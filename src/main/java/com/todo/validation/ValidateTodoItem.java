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
