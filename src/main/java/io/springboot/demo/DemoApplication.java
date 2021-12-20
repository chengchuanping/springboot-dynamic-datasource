package io.springboot.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * 
 * @author KevinBlandy
 *
 */
@SpringBootApplication(exclude = {
	DataSourceAutoConfiguration.class			// 禁止数据源自动配置
})
@EnableJpaRepositories(basePackages = { "io.springboot.demo.repository" })
@EntityScan(basePackages = { "io.springboot.demo.entity" })
public class DemoApplication {
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
}
