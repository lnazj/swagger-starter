package com.jrkj.swaggerspringbootstarter;

import com.jrkj.swaggerspringbootstarter.condition.SwaggerCondition;
import com.jrkj.swaggerspringbootstarter.config.SwaggerProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * @author 李娜
 * @version 0.0.1
 * @since 0.0.1  2019-12-09
 */
@Configuration
// 自动装配这个properties类，读取yaml自定义内容
@EnableConfigurationProperties(SwaggerProperties.class)
// 校验类
@Conditional(SwaggerCondition.class)
// 当配置文件中 swagger 的值为 true 时，实例化此类。可以不填
@ConditionalOnProperty(prefix = "swagger", value = "true", matchIfMissing = true)
public class SwaggerServiceAutoConfiguration extends WebMvcConfigurationSupport {
    @Autowired
    private SwaggerProperties swaggerProperties;

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage(swaggerProperties.getBasePackage()))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title(swaggerProperties.getTitle())
                .contact(new Contact(swaggerProperties.getContact().getName(), swaggerProperties.getContact().getUrl(), swaggerProperties.getContact().getEmail()))
                .version(swaggerProperties.getVersion())
                .description(swaggerProperties.getDescription())
                .build();
    }

    public void addResourceHandler(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(swaggerProperties.getBasePath()).addResourceLocations("classpath:/static/");
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources");
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars");
        super.addResourceHandlers(registry);

    }

    public void addCorsMapping(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("GET", "POST", "PUT", "OPTIONS", "DELETE", "PATCH")
                .allowCredentials(true).maxAge(3600);
    }
}
