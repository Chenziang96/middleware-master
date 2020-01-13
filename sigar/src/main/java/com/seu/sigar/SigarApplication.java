package com.seu.sigar;

import com.nepxion.discovery.plugin.strategy.adapter.DiscoveryEnabledStrategy;
import com.nepxion.discovery.plugin.strategy.adapter.StrategyTracerAdapter;
import com.nepxion.discovery.plugin.strategy.service.adapter.FeignStrategyInterceptorAdapter;
import com.nepxion.discovery.plugin.strategy.service.adapter.RestTemplateStrategyInterceptorAdapter;
import com.nepxion.discovery.plugin.strategy.service.sentinel.adapter.ServiceSentinelRequestOriginAdapter;
import com.seu.sigar.impl.*;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SigarApplication {
    private static final String NAME = "SigarSysImpl";
    public static void main(String[] args) {
        System.setProperty("nepxion.banner.shown.ansi.mode", "true");
        System.setProperty("spring.profiles.active", "a1");
        System.setProperty("project.name", "SigarSysImpl");
        System.setProperty("csp.sentinel.dashboard.server", "localhost:8075");
        System.setProperty("csp.sentinel.api.port", "3001");
        new SpringApplicationBuilder(SigarApplication.class).run(args);
    }

    @Bean
    public DiscoveryEnabledStrategy discoveryEnabledStrategy() {
        return new MyDiscoveryEnabledStrategy();
    }

    // 自定义Feign拦截器中的Header传递
    @Bean
    public FeignStrategyInterceptorAdapter feignStrategyInterceptorAdapter() {
        return new MyFeignStrategyInterceptorAdapter();
    }

    // 自定义RestTemplate拦截器中的Header传递
    @Bean
    public RestTemplateStrategyInterceptorAdapter restTemplateStrategyInterceptorAdapter() {
        return new MyRestTemplateStrategyInterceptorAdapter();
    }

    // 自定义调用链上下文参数
    @Bean
    public StrategyTracerAdapter strategyTracerAdapter() {
        return new MyStrategyTracerAdapter();
    }

    // 自定义组合式熔断
    @Bean
    public ServiceSentinelRequestOriginAdapter ServiceSentinelRequestOriginAdapter() {
        return new MyServiceSentinelRequestOriginAdapter();
    }
}
