package io.springboot.demo.configuration;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import io.springboot.demo.interceptor.DataSourceInterceptor;

@Component
public class WebMvcConfiguration implements WebMvcConfigurer {
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(this.dataSourceInterceptor())
			.addPathPatterns("/api/user/**");
			;
	}
	
	public DataSourceInterceptor dataSourceInterceptor () {
		return new DataSourceInterceptor();
	}
}
