package com.sprSecurity.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration

// @EnableWebMvc
// @ComponentScan(basePackages = { "com.sprSecurity.spring.controller" })
// @EnableAspectJAutoProxy()
@ImportResource(locations = { "classpath*:sprCongfig/sprConfig.xml" })
public class MainConfiguration extends WebMvcConfigurerAdapter {

	
}
