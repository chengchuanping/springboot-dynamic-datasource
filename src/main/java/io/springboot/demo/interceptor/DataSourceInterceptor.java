package io.springboot.demo.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import io.springboot.demo.datasource.DataSourceKeyHolder;
import io.springboot.demo.datasource.DynamicDataSource;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DataSourceInterceptor implements HandlerInterceptor {
	
	@Autowired 
	private DynamicDataSource dataSource;
	
	/**
	 * 从 Request 中解析出要使用的DataSourceKey
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)throws Exception {
		
		// 从Header 解析
		String dataSourceKey = request.getHeader("x-db-key");
		
		// 从查询参数解析
		if (!StringUtils.hasText(dataSourceKey)) {
			dataSourceKey = request.getParameter("dbKey");
		}
		
		log.info("DB标识: {}", dataSourceKey);
		
		if (!StringUtils.hasText(dataSourceKey)) {
			response.setContentType("text/plain");
			response.setCharacterEncoding("utf-8");
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			response.getWriter().println("缺少DB标识");
			return false;
		}
		
		if(!this.dataSource.getDataSources().containsKey(dataSourceKey)) {
			response.setContentType("text/plain");
			response.setCharacterEncoding("utf-8");
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			response.getWriter().println("数据源:" + dataSourceKey + ", 不存在");
			return false;
		}
		
		
		DataSourceKeyHolder.set(dataSourceKey);
		
		return true;
	}
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
		DataSourceKeyHolder.clear();
		HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
	}
}
