package org.training.kafka.spring.advanced.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class CheckWordsObject implements ConstraintValidator<CheckWords, Object> {
    private CheckWords checkWords;

    @Override
    public void initialize(final CheckWords constraintAnnotation) {
        checkWords = constraintAnnotation;
    }

    @Override
    public boolean isValid(final Object valueParam,
                           final ConstraintValidatorContext context) {
        String[]     valueLoc          = checkWords.value();
        Class<?>     classLoc          = valueParam.getClass();
        Field[]      declaredFieldsLoc = classLoc.getDeclaredFields();
        List<String> errorList         = new ArrayList<>();

        for (Field declaredFieldLoc : declaredFieldsLoc) {
            Class<?> typeLoc = declaredFieldLoc.getType();
            if (typeLoc == String.class) {
                declaredFieldLoc.setAccessible(true);
                try {
                    String sLoc = (String) declaredFieldLoc.get(valueParam);
                    for (String stringLoc : valueLoc) {
                        if (sLoc.contains(stringLoc)) {
                            errorList.add("Field : "
                                          + declaredFieldLoc.getName()
                                          + " can not contain : "
                                          + stringLoc
                                          + " value : "
                                          + sLoc);
                        }

                    }
                } catch (Exception eParam) {
                    eParam.printStackTrace();
                }
            }
        }
        if (errorList.isEmpty()) {
            return true;
        } else {
            context.disableDefaultConstraintViolation();
            StringBuilder builderLoc = new StringBuilder();
            for (String stringLoc : errorList) {
                builderLoc.append(stringLoc).append("---");
            }
            ConstraintValidatorContext.ConstraintViolationBuilder constraintViolationBuilderLoc = context.buildConstraintViolationWithTemplate(builderLoc.toString());
            constraintViolationBuilderLoc.addConstraintViolation();
            return false;
        }
    }
}
