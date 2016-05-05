package com.sprSecurity.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import com.sprSecurity.spring.logging.SpringInterceptor;

@Configuration
// @EnableWebMvc
// @ComponentScan(basePackages = { "com.sprSecurity.spring.controller" })
// @EnableAspectJAutoProxy()
@ImportResource(locations = { "/WEB-INF/sprCongfig/sprConfig.xml" })
public class MainConfiguration extends WebMvcConfigurerAdapter {
	@Bean
	public ViewResolver viewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setViewClass(JstlView.class);
		viewResolver.setPrefix("/WEB-INF/views/");
		viewResolver.setSuffix(".jsp");

		return viewResolver;
	}

	// add Interceptors
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new SpringInterceptor()).addPathPatterns("/*");
	}

	// Data source
	// @Bean
	// public DataSource getDataSource() throws NamingException {
	// JndiTemplate factoryBean = new JndiTemplate();
	// factoryBean.setEnvironment(setEnvironment());
	// DataSource ds = (DataSource) factoryBean.lookup("TEMP_DATA_SOURCE");
	// return ds;
	// }
	//
	// private Properties setEnvironment() {
	// Properties prop = new Properties();
	// prop.put(Context.INITIAL_CONTEXT_FACTORY,
	// "weblogic.jndi.WLInitialContextFactory");
	// prop.put(Context.PROVIDER_URL, "t3://localhost:7007");
	// prop.put(Context.SECURITY_PRINCIPAL, "weblogic");
	// prop.put(Context.SECURITY_CREDENTIALS, "weblogic1");
	// return prop;
	// }
	//
	// @Bean
	// public LocalContainerEntityManagerFactoryBean
	// getLocalContainerEntityManagerFactoryBean() throws NamingException {
	// LocalContainerEntityManagerFactoryBean bean = new
	// LocalContainerEntityManagerFactoryBean();
	// bean.setDataSource(getDataSource());
	// bean.setPersistenceUnitName("sprTest");
	// bean.setJpaVendorAdapter(getHibernateJpaVendorAdapter());
	// bean.setJpaDialect(getHibernateJpaDialect());
	// // this.getClass().getClassLoader().getResourceAsStream(name)
	// bean.setPersistenceXmlLocation("/META-INF/persistence.xml");
	// // /sprSecurity/src/main/resources/META-INF/persistence.xml
	// return bean;
	// }
	//
	// @Bean
	// public JpaTransactionManager getJpaTransactionManager() throws
	// NamingException {
	// JpaTransactionManager manager = new JpaTransactionManager();
	// manager.setDataSource(getDataSource());
	// manager.setJpaDialect(getHibernateJpaDialect());
	// return manager;
	// }
	//
	// @Bean
	// public HibernateJpaVendorAdapter getHibernateJpaVendorAdapter() {
	// HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
	// adapter.setDatabase(Database.MYSQL);
	// adapter.setShowSql(true);
	// adapter.setDatabasePlatform("org.hibernate.dialect.MySQLDialect");
	// return adapter;
	// }
	//
	// @Bean
	// public HibernateJpaDialect getHibernateJpaDialect() {
	// return new HibernateJpaDialect();
	// }
	//
	// @Bean
	// public PersistenceAnnotationBeanPostProcessor
	// getPersistenceAnnotationBeanPostProcessor() {
	// return new PersistenceAnnotationBeanPostProcessor();
	// }
	//
	// @Bean
	// public JtaTransactionManager getJtaTransactionManager() {
	// return new JtaTransactionManager();
	// }

}
