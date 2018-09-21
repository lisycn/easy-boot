package com.mos.eboot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.mos.eboot.im.ImServerStart;

@EnableScheduling
@EnableEurekaClient
@EnableFeignClients
@SpringBootApplication
public class  Application{
	private static final Logger LOGGER = LoggerFactory.getLogger(Application.class);
    public static void main(String[] args) {
    	LOGGER.info("-----------------eboot-websocket系统开始启动------------------");
        SpringApplication.run(Application.class, args);
        ImServerStart s=new ImServerStart();
    	s.start();
    }

}
