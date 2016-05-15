package com.sprSecurity.configuration;

public class WebIntailizer /* implements WebApplicationInitializer */{
	
	/*public void onStartup (ServletContext container) throws ServletException {
		
		AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();
		ctx.register(MainConfiguration.class);
		ctx.setServletContext(container);
		ServletRegistration.Dynamic servlet = container.addServlet("dispatcher", new DispatcherServlet(ctx));
		servlet.setLoadOnStartup(1);
		 servlet.addMapping("/spr/*.html");
		servlet.addMapping("/spr/*");
	}*/
	
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