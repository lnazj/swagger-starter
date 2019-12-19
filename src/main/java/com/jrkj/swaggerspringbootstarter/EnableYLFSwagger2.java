package com.jrkj.swaggerspringbootstarter;
import org.springframework.context.annotation.Import;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.lang.annotation.*;

/**
 * @author 李娜
 * @version 0.0.1
 * @since 0.0.1  2019-12-09
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Import({SwaggerServiceAutoConfiguration.class})
@EnableSwagger2
public @interface EnableYLFSwagger2 {
}
