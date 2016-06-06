package com.sprSecurity.ejb;

import javax.ejb.Stateless;

import org.apache.log4j.Logger;

@Stateless(mappedName = "TempTableEJB")
public class TempTableEJBImpl implements TempTableEJB {

	private Logger	logger	= Logger.getLogger(TempTableEJBImpl.class);
	private int		lap		= 1;

	@Override
	public void get() {
		System.out.println("Test EJB");
	}

	@Override
	public void sartJob() {
		logger.info("___________________________Start App ___________________________");
		System.out.println("Start Job" + (lap++));
		logger.info("___________________________ End App ___________________________");
	}

}
