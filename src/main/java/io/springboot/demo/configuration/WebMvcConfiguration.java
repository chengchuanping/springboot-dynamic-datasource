package io.springboot.demo.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import io.springboot.demo.interceptor.DataSourceInterceptor;

@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer {
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(this.dataSourceInterceptor())
			.addPathPatterns("/api/user/**");
			;
	}
	
	@Bean
	public DataSourceInterceptor dataSourceInterceptor () {
		return new DataSourceInterceptor();
	}
}
