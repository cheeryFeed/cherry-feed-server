package com.bazzi.cherryfeed.configuration;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.UiConfiguration;
import springfox.documentation.swagger.web.UiConfigurationBuilder;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration	// 스프링 실행시 설정파일 읽어드리기 위한 어노테이션
@EnableSwagger2	// Swagger2를 사용하겠다는 어노테이션
@SuppressWarnings("unchecked")	// warning밑줄 제거를 위한 태그
public class SwaggerConfig extends WebMvcConfigurationSupport {

    //리소스 핸들러 설정
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
        registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
    }


    // API마다 구분짓기 위한 설정.
    @Bean
    public Docket lucaApi() {
        return getDocket("루카", Predicates.or(
                PathSelectors.regex("/api/v1/users.*")));
    }
    @Bean
    public Docket productApi() {
        return getDocket("유저", Predicates.or(
                PathSelectors.regex("/user.*")));
    }


    @Bean
    public Docket searchApi() {
        return getDocket("예약", Predicates.or(
                PathSelectors.regex("/reservation.*")));
    }


    @Bean
    public Docket commonApi() {
        return getDocket("공통", Predicates.or(
                PathSelectors.regex("/test.*")));

    }

    @Bean
    public Docket allApi() {
        return getDocket("전체", Predicates.or(
                PathSelectors.regex("/*.*")));
    }

    //swagger 설정.
    public Docket getDocket(String groupName, Predicate<String> predicate) {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName(groupName)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.bazzi.cherryfeed")) //이부분 설정 중요한듯 luca
                .paths(predicate)
                .apis(RequestHandlerSelectors.any())
                .build();
    }

    //swagger ui 설정.
    @Bean
    public UiConfiguration uiConfig() {
        return UiConfigurationBuilder.builder()
                .displayRequestDuration(true)
                .validatorUrl("")
                .build();
    }

}