package io.springboot.demo.datasource;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.stereotype.Component;

import com.zaxxer.hikari.HikariDataSource;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class DynamicDataSource extends AbstractRoutingDataSource implements DisposableBean {

	// 系統中维护的数据源
	private final Map<Object, DataSource> dataSources = new HashMap<>();
	
	/**
	 * 根据KEY获取到数据源
	 */
	protected DataSource determineTargetDataSource() {
		DataSource dataSource = this.dataSources.get(this.determineCurrentLookupKey());
		if (dataSource == null) {
			// TODO 数据源不存在
		}
		return dataSource;
	}
	
	/**
	 * 获取当前的KEY
	 */
	@Override
	protected Object determineCurrentLookupKey() {
		return DataSourceKeyHolder.get();
	}
	
	public Map<Object, DataSource> getDataSources() {
		return dataSources;
	}

	@Override
	public void afterPropertiesSet() {
	}

	@Override
	public void destroy() throws Exception {
		this.dataSources.entrySet().forEach(db -> {
			log.info("释放 {} 数据源资源", db.getKey());
			((HikariDataSource) db.getValue()).close();
		});
	}
}
