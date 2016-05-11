package com.sprSecurity.spring.jms;

public interface JMSMessageSender {
	boolean sendMessage(JMSMessageObject jmsMsg, QueueEnum queueEnum);
}
