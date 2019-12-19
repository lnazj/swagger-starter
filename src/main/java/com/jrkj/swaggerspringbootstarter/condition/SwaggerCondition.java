package com.jrkj.swaggerspringbootstarter.condition;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;
import org.springframework.util.StringUtils;

/**
 * 校验类
 * @author 李娜
 * @version 0.0.1
 * @since 0.0.1  2019-12-09
 */
public class SwaggerCondition implements Condition {

    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata annotatedTypeMetadata) {
        String title= context.getEnvironment().getProperty("swagger.title");
        String description = context.getEnvironment().getProperty("swagger.description");
        String basePackage = context.getEnvironment().getProperty("swagger.base-package");

        if (StringUtils.isEmpty(title)) {
            throw new RuntimeException("Lack of swagger configuration:swagger.title");
        } else if (StringUtils.isEmpty(description)) {
            throw new RuntimeException("Lack of swagger configuration:swagger.description");
        } else if (StringUtils.isEmpty(basePackage)) {
            throw new RuntimeException("Lack of swagger configuration:swagger.description");
        }else{
            return true;
        }
    }
}
