package com.example.demo.validtor;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = NameValidConfig.class)
public @interface NameValid {

    String message() default "长度必须大于2";

    int value();

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
