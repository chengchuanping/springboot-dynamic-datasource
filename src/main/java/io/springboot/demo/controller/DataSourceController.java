package io.springboot.demo.controller;

import java.util.Collections;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zaxxer.hikari.HikariDataSource;

import io.springboot.demo.datasource.DynamicDataSource;
import lombok.Data;

// 数据源Model
@Data
class DataSourceDTO {
	private String key;			// 唯一标识
	private String url;			// JDBC连接地址
	private String username;	// 用户名
	private String password;	// 密码
}


@RestController
@RequestMapping("/api/datasource")
public class DataSourceController {
	
	@Autowired
	private DynamicDataSource dataSource;
	
	/**
	 * 获取系统中的所有数据源
	 * @return
	 */
	@GetMapping
	public Object list () {
		return this.dataSource.getDataSources().entrySet()
				.stream()
				.map(db -> Collections.singletonMap(db.getKey(), ((HikariDataSource) db.getValue()).getJdbcUrl()))
				.toList();
	}
	
	/**
	 * 添加数据源
	 * @param payload
	 * @return
	 */
	@PostMapping(produces = "text/plain; charset=utf-8")
	public Object add (@RequestBody DataSourceDTO payload) {
		
		Map<Object, DataSource> dataSource = this.dataSource.getDataSources();
		
		if (dataSource.containsKey(payload.getKey())) {
			return "已经存在:" + payload.getKey();
		}
		

		HikariDataSource hikariDataSource = new HikariDataSource();
		hikariDataSource.setJdbcUrl(payload.getUrl());
		hikariDataSource.setUsername(payload.getUsername());
		hikariDataSource.setPassword(payload.getPassword());
		
		dataSource.put(payload.getKey(), hikariDataSource);
		
		return "添加成功";
	}
	
	/**
	 * 删除数据源
	 * @param key
	 * @return
	 */
	@DeleteMapping("/{key}")
	public Object delete (@PathVariable("key") String key) {
		DataSource dataSource = this.dataSource.getDataSources().remove(key);
		if (dataSource == null) {
			return "数据源不存在:" + key;
		}
		
		((HikariDataSource) dataSource).close();
		
		return "删除成功";
	}
}
