package org.training.kafka.spring.advanced.conditonal;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.context.annotation.Profile;
import org.springframework.core.type.AnnotatedTypeMetadata;
import org.springframework.util.MultiValueMap;

import java.util.List;

public class CheckConfigConditionalProcess implements Condition {
    @Override
    public boolean matches(final ConditionContext context,
                           final AnnotatedTypeMetadata metadata) {
        MultiValueMap<String, Object> attrs = metadata.getAllAnnotationAttributes(CheckConfig.class.getName());
        if (attrs != null) {
            List<Object> keyLoc = attrs.get("ckey");
            if (keyLoc != null) {
                String       envName  = (String) keyLoc.get(0);
                List<Object> valueLoc = attrs.get("cvalue");
                String       valLoc   = (String) valueLoc.get(0);
                String propertyLoc = context.getEnvironment()
                                            .getProperty(envName);
                if (propertyLoc != null && valLoc.equals(propertyLoc)) {
                    System.out.println("evet yaratıldı");
                    return true;
                }
            }
        }
        return false;
    }
}
