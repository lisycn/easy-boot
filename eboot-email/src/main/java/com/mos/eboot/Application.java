package com.mos.eboot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
@SpringBootApplication
public class Application extends SpringBootServletInitializer {
	private static final Logger LOGGER = LoggerFactory.getLogger(Application.class);
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(Application.class);
    }

    public static void main(String[] args) {
    	LOGGER.info("-----------------eboot-email系统开始启动------------------");
        SpringApplication.run(Application.class, args);
    }
}
