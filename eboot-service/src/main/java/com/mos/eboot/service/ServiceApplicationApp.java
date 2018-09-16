package com.mos.eboot.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author 小尘哥
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableSwagger2
public class ServiceApplicationApp {

    public static void main(String[] args) {
        SpringApplication.run(ServiceApplicationApp.class,args);
    }
}
