package com.sprSecurity.spring.logging;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

@Aspect
public class LoggingAspect {

	private Logger logger = Logger.getLogger(LoggingAspect.class);

	@After("within(com.sprSecurity.spring..*)")
	public void logAfter(JoinPoint joinPoint) {
		logger.info("End Method : " + joinPoint.getSignature().getName());
		logger.info(" @After is running!");
		logger.info("***************************************************************");
	}

	@Before("within(com.sprSecurity.spring..*)")
	public void logBefore(JoinPoint joinPoint) {
		logger.info("Start Method : " + joinPoint.getSignature().getName());
		logger.info("@Before  is running!");
		logger.info("***************************************************************");
	}
}
