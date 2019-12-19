package com.jrkj.swaggerspringbootstarter.config;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import	java.io.DataInput;
import	java.awt.Composite;

/**
 * @author 李娜
 * @version 0.0.1
 * @since 0.0.1  2019-12-09
 */
@Data
@Component
@ConfigurationProperties(prefix = "swagger")
public class SwaggerProperties {
    /**
     * 标题，必填
     */
    private String title;

    /**
     * 维护人
     */
    private Contact contact = new Contact();

    /**
     * 版本
     */
    private String version;
    private String description;

    /**
     * swagger扫描的基础包
     */
    private String basePackage = "";

    /**
     * 需要处理的基础url规则
     */
    private String basePath = "/**";
    private String excludePath = "";

    /**
     * 许可证
     */
    private String license ;
    private String licenseUrl;

    @Data
    public static class Contact {
        private String name;
        private String email;
        private String url;
    }


}
