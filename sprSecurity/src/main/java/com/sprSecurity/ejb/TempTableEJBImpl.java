package com.sprSecurity.ejb;

import java.util.Date;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.apache.log4j.Logger;

@Stateless(mappedName = "TempTableEJB")
public class TempTableEJBImpl implements TempTableEJB {

	/**
	 * mail.smtp=localhost 
	 * mail.host=localhost
	 * mail.transport.protocol=smtp
	 * mail.from=temp@domain.com
	 * 
	 */

	private Logger				logger		= Logger.getLogger(TempTableEJBImpl.class);
	private int					lap			= 1;

	private static final String	JNDI_MAIL	= "MailSession_test";
	@Resource(mappedName = JNDI_MAIL)
	private Session				session;

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

	@Override
	public void sendMail(String subject, String body, String sendTo) {

		Message msg = new MimeMessage(session);
		try {
			msg.setFrom();
			msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(sendTo, false));
			msg.setSubject(subject);
			msg.setSentDate(new Date());
			// Content is stored in a MIME multi-part message
			// with one body part
			MimeBodyPart mbp = new MimeBodyPart();
			mbp.setText(body);
			Multipart mp = new MimeMultipart();
			mp.addBodyPart(mbp);
			msg.setContent(mp);
			Transport.send(msg);
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
