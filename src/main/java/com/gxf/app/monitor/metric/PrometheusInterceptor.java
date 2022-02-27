package com.gxf.app.monitor.metric;

import io.prometheus.client.Histogram;
import org.springframework.web.servlet.AsyncHandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PrometheusInterceptor implements AsyncHandlerInterceptor {
    private ThreadLocal<Histogram.Timer> timerThreadLocal = new ThreadLocal<>();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 正在处理的请求量
        PrometheusComponent.getInstance().gauge().labels(request.getRequestURI(), request.getMethod()).inc();
        // 请求耗时计时器
        timerThreadLocal.set(PrometheusComponent.getInstance().histogram()
                .labels(request.getRequestURI(), request.getMethod(), String.valueOf(response.getStatus()))
                .startTimer());
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        String uri = request.getRequestURI();
        String method = request.getMethod();
        int status = response.getStatus();
        // count请求计数+1，标签分别为 请求路径，请求方法，response http code
        PrometheusComponent.getInstance().counter().labels(uri, method, String.valueOf(status)).inc();

        // 请求完毕，正在请求计数器-1
        PrometheusComponent.getInstance().gauge().labels(uri, method).dec();

        // 请求耗时采集结束
        Histogram.Timer timer = timerThreadLocal.get();
        if (timer != null) {
            timer.observeDuration();
            timerThreadLocal.remove();
        }
    }
}
