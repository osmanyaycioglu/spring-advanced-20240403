package org.training.kafka.spring.advanced.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE,ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = { CheckWordsString.class, CheckWordsObject.class})
public @interface CheckWords {

    String[] value();

    String message() default "{jakarta.validation.constraints.CheckWords.message}";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
