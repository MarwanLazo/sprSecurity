package com.sprSecurity.ejb;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.ejb.Timeout;
import javax.ejb.Timer;
import javax.ejb.TimerService;

import org.apache.log4j.Logger;

/**
 * Session Bean implementation class TimerTestEJBImpl
 */
@Stateless
@LocalBean
public class TimerTestEJBImpl implements TimerTestEJB {

	private Logger			logger	= Logger.getLogger(TimerTestEJBImpl.class);

	@Resource
	private TimerService	timerService;

	@Resource
	private SessionContext	sessionContext;

	@EJB
	private TempTableEJB	tempTable;

	@PostConstruct
	private void init() {
	}

	@Override
	public void createTimer(Long time) {
		logger.info("__________________________start_________________________________");
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		timerService.createTimer(calendar.getTime(), time, "Task Start");
		logger.info("____________________________end_________________________________");
	}

	@Timeout
	public void timeout(Timer timer) throws RuntimeException {

		tempTable.sartJob();
	}

	@Override
	public void cancelTimer() {
		Collection<Timer> runingServices = sessionContext.getTimerService().getTimers();
		for (Object obj : runingServices) {
			Timer timer = (Timer) obj;
			logger.info("Jobs  cancelled are ::" + timer.getInfo().toString());
			timer.cancel();
		}
	}

}
