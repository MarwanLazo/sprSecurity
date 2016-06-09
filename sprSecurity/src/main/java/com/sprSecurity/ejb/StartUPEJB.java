package com.sprSecurity.ejb;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;

@Singleton
@Startup
public class StartUPEJB {

	@EJB
	private TimerTestEJB	ejb;

/*	@EJB
	private EmailSenderEJB	sender;
*/
	@PostConstruct
	public void applicationStartup() {
		// ejb.createTimer(10000L);

		
//			sender.sendMail("Test Sending", "The Sent Body", "eng.marawan1988@yahoo.om");
		
		
	}
}
