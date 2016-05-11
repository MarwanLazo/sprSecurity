package com.sprSecurity.spring.jms;

import java.io.Serializable;

import javax.inject.Inject;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.ObjectMessage;
import javax.jms.Session;

import org.apache.log4j.Logger;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

@Service("JMSMessageSender")
public class JMSMessageSenderImp implements MessageCreator, JMSMessageSender {

	@Inject
	private JmsTemplate			jmsTemplate;
	private Serializable		jmsMsg;
	private final static String	QUEUE_NAME	= "queue_TEST";
	private Logger				logger		= Logger.getLogger(JMSMessageSenderImp.class);

	@Override
	public boolean sendMessage(final JMSMessageObject jmsMsg, QueueEnum queueEnum) {
		boolean isMsgSend = false;

		this.jmsMsg = jmsMsg;
		logger.info("Message :>> " + this.jmsMsg + " Will be sent ");
		jmsTemplate.send(QUEUE_NAME, this);
		logger.info("Message :>> " + this.jmsMsg + " sent Successfully ");
		return isMsgSend;
	}

	@Override
	public Message createMessage(Session session) throws JMSException {
		ObjectMessage object = session.createObjectMessage();
		object.setObject(jmsMsg);
		logger.info(jmsMsg + " =============>>>>");
		return object;
	}

}
