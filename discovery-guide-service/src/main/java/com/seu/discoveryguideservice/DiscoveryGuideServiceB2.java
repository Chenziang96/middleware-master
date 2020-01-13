package com.seu.discoveryguideservice;

/**
 * <p>Title: Nepxion Discovery</p>
 * <p>Description: Nepxion Discovery</p>
 * <p>Copyright: Copyright (c) 2017-2050</p>
 * <p>Company: Nepxion</p>
 * @author Haojun Ren
 * @version 1.0
 */

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class DiscoveryGuideServiceB2 {
    public static void main(String[] args) {
        System.setProperty("nepxion.banner.shown.ansi.mode", "true");
        System.setProperty("spring.profiles.active", "b2");
        System.setProperty("project.name", "microservice-b2");
        System.setProperty("csp.sentinel.dashboard.server", "localhost:8080");
//        System.setProperty("csp.sentinel.api.port", "4002");

        new SpringApplicationBuilder(DiscoveryGuideServiceB2.class).run(args);
    }
}