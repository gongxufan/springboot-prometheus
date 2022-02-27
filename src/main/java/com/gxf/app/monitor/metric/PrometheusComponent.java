package com.gxf.app.monitor.metric;

import io.prometheus.client.CollectorRegistry;
import io.prometheus.client.Counter;
import io.prometheus.client.Gauge;
import io.prometheus.client.Histogram;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @author xufangong
 * @date 2022/02/22
 */
@Component
public class PrometheusComponent implements ApplicationContextAware {
    private static PrometheusComponent instance;


    /**
     * 请求总数
     */
    private Counter reqCounter;

    /**
     * 正在请求的http数量
     */
    private Gauge duringReqGauge;

    /**
     * 直方图，请求分布情况
     */
    private Histogram reqLatencyHistogram;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        instance = this;
        // 获取SpringBoot容器的CollectorRegistry
        CollectorRegistry collectorRegistry = applicationContext.getBean(CollectorRegistry.class);
        // 定义三个采集指标和标签
        reqCounter = Counter.build().name("example_rest_req_total").labelNames("path", "method", "code")
                .help("总的请求计数").register(collectorRegistry);
        duringReqGauge = Gauge.build()
                .name("example_rest_inprogress_req").labelNames("path", "method")
                .help("正在处理的请求数").register(collectorRegistry);
        reqLatencyHistogram = Histogram.build().labelNames("path", "method", "code")
                .name("example_rest_requests_latency_seconds_histogram").help("请求耗时分布")
                .register(collectorRegistry);
    }

    public static PrometheusComponent getInstance() {
        return instance;
    }

    public Counter counter() {
        return reqCounter;
    }

    public Gauge gauge() {
        return duringReqGauge;
    }

    public Histogram histogram() {
        return reqLatencyHistogram;
    }
}