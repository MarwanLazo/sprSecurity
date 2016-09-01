package com.sprSecurity.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;


@EnableWebSecurity
@Configuration
@ImportResource(locations = { "classpath*:sprCongfig/sprConfig.xml" })
public class MainConfiguration {
 
  /*  @Autowired
    private RestAuthenticationEntryPoint restAuthenticationEntryPoint;
 
    @Autowired
    private MySavedRequestAwareAuthenticationSuccessHandler authenticationSuccessHandler;
    
    @Autowired
    private SimpleUrlAuthenticationFailureHandler simpleUrlAuthenticationFailureHandler;
  
 
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().
          withUser("temporary").password("temporary").roles("ADMIN").and().
          withUser("user").password("userPass").roles("USER");
    }
 
    @Override
    protected void configure(HttpSecurity http) throws Exception { 
        http
        .csrf().disable()
        .exceptionHandling()
        .authenticationEntryPoint(restAuthenticationEntryPoint)
        .and()
        .authorizeRequests()
        .antMatchers("/api/foos").authenticated()
        .and()
        .formLogin()
        .successHandler(authenticationSuccessHandler)
        .failureHandler(simpleUrlAuthenticationFailureHandler)
        .and()
        .logout();
    }
 
  */
   
    
   

	


	
}
