package com.sprSecurity.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
@ImportResource(locations = { "classpath*:sprCongfig/sprConfig.xml" })
public class MainConfiguration {
}

// @Bean
// public ViewResolver viewResolver() {
// InternalResourceViewResolver viewResolver = new
// InternalResourceViewResolver();
// viewResolver.setViewClass(JstlView.class);
// viewResolver.setPrefix("/WEB-INF/views/");
// viewResolver.setSuffix(".jsp");
//
// return viewResolver;
// }
