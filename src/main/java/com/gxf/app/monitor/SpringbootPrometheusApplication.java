package com.gxf.app.monitor;

import com.gxf.app.monitor.metric.PrometheusInterceptor;
import io.micrometer.core.instrument.MeterRegistry;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.autoconfigure.metrics.MeterRegistryCustomizer;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;

@SpringBootApplication
@MapperScan("com.gxf.app.monitor.mapper")
public class SpringbootPrometheusApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootPrometheusApplication.class, args);
	}

	// 注册应用
	@Bean
	MeterRegistryCustomizer<MeterRegistry> configurer(@Value("${spring.application.name}") String applicationName) {
		return (registry) -> registry.config().commonTags("application", applicationName);
	}

}
