package com.sprSecurity.ejb;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;

@Singleton
@Startup
public class StartUPEJB {
	
	@EJB
	private TimerTestEJB ejb;

	@PostConstruct
	public void applicationStartup() {
//		ejb.createTimer(10000L);
	}
}
