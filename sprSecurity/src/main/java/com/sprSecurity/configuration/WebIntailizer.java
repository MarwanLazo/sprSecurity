package com.sprSecurity.configuration;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class WebIntailizer implements WebApplicationInitializer {
	
	public void onStartup (ServletContext container) throws ServletException {
		
		AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();
		ctx.register(MainConfiguration.class);
		ctx.setServletContext(container);
		ServletRegistration.Dynamic servlet = container.addServlet("dispatcher", new DispatcherServlet(ctx));
		servlet.setLoadOnStartup(1);
		servlet.addMapping("/spr/*.html");
		servlet.addMapping("/spr/*");
	}
	
}

/*
 * public class WebIntailizer extends
 * AbstractAnnotationConfigDispatcherServletInitializer {
 * 
 * @Override protected Class<?>[] getRootConfigClasses() { return new Class[] {
 * MainConfiguration.class }; }
 * 
 * @Override protected Class<?>[] getServletConfigClasses() { return null; }
 * 
 * @Override protected String[] getServletMappings() { return new String[] { "/"
 * }; } }
 */