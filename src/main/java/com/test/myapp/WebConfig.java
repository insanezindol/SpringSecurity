package com.test.myapp;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 기존 servlet-context.xml 대체
 * (뷰 리졸버 prefix/suffix 는 application.properties 의 spring.mvc.view.* 로 설정)
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

	// /resources/** 요청을 ${webappRoot}/resources 디렉토리의 정적 리소스로 처리
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
	}

}
