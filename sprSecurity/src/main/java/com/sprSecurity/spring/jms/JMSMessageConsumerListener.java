package com.sprSecurity.spring.jms;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;

import org.springframework.stereotype.Service;

@Service("MessageListener")
public class JMSMessageConsumerListener implements MessageListener {

	@Override
	public void onMessage(Message message) {
		try {
			System.out.println("------------<< Done !! >>-------------"+message.getJMSMessageID());
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}

}
