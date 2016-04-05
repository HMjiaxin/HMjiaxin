package cn.hmjiaxin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@SpringBootApplication
@Configuration
// 配置控制
@EnableAutoConfiguration
// 启用自动配置
@ComponentScan
// 组件扫描
public class ApplicationMain extends SpringBootServletInitializer {

	public static void main(String[] args) throws Exception {
		// 启动Spring Boot项目的唯一入口
		SpringApplication.run(ApplicationMain.class, args);

	}

	@Override
	protected SpringApplicationBuilder configure(
			SpringApplicationBuilder builder) {
		return builder.sources(ApplicationMain.class);
	}
}
