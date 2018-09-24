package com.mos.eboot;

import org.springframework.util.ResourceUtils;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

public class My extends WebMvcConfigurationSupport{
	@Override
	protected void addResourceHandlers(ResourceHandlerRegistry registry) {
		 registry.addResourceHandler("/**").addResourceLocations(ResourceUtils.CLASSPATH_URL_PREFIX+"/page/");
	     registry.addResourceHandler("/**").addResourceLocations(ResourceUtils.CLASSPATH_URL_PREFIX+"/static/");
		super.addResourceHandlers(registry);
	}
}
