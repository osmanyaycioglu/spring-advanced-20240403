package org.training.kafka.spring.advanced.validation;

import jakarta.validation.Constraint;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CheckWordsString implements ConstraintValidator<CheckWords,String> {
    private CheckWords checkWords;

    @Override
    public void initialize(final CheckWords constraintAnnotation) {
        checkWords = constraintAnnotation;
    }

    @Override
    public boolean isValid(final String valueParam,
                           final ConstraintValidatorContext context) {
        String[] valueLoc = checkWords.value();
        for (String stringLoc : valueLoc) {
            if (valueParam.contains(stringLoc)){
                return false;
            }
        }
        return true;
    }
}
