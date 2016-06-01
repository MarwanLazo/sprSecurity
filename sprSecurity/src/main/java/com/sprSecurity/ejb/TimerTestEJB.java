package com.sprSecurity.ejb;

import javax.ejb.Local;

@Local
public interface TimerTestEJB {

	void createTimer(Long timer);

	void cancelTimer();

}
