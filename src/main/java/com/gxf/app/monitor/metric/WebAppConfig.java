package com.gxf.app.monitor.metric;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//注册拦截器
@Configuration
public class WebAppConfig implements WebMvcConfigurer
{

    @Bean
    public MetricInterceptor metricInterceptor() {
        return new MetricInterceptor();
    }
    @Bean
    public PrometheusInterceptor prometheusInterceptor() {
        return new PrometheusInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry)
    {
        //注册自己的拦截器并设置拦截的请求路径
        registry.addInterceptor(prometheusInterceptor()).addPathPatterns("/**");
        registry.addInterceptor(metricInterceptor()).addPathPatterns("/**");

    }
}