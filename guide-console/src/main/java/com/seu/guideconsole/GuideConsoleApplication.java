package com.seu.guideconsole;

/**
 * <p>Title: seu</p>
 * <p>Description: seu demo</p>
 * <p>Copyright: Copyright (c) 2017-2050</p>
 * <p>Company: seu</p>
 *
 * @author cza
 * @version 1.0
 */

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class GuideConsoleApplication {

    public static void main(String[] args) {
        System.setProperty("nepxion.banner.shown.ansi.mode", "true");
        SpringApplication.run(GuideConsoleApplication.class, args);
    }
}
