package com.sprSecurity.spring.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.sprSecurity.spring.data.service.PersonService;
import com.sprSecurity.spring.data.service.TempTableService;
import com.sprSecurity.spring.dto.PersonDTO;
import com.sprSecurity.spring.dto.PersonPKDTO;
import com.sprSecurity.spring.dto.TempTableDTO;
import com.sprSecurity.spring.enums.Status;
import com.sprSecurity.spring.jasper.ReportType;
import com.sprSecurity.spring.jasper.dynamic.EmployeeReport;
import com.sprSecurity.spring.jms.JMSMessageObject;
import com.sprSecurity.spring.jms.JMSMessageSender;
import com.sprSecurity.spring.jms.QueueEnum;

@WebServlet("/HelloServlet")
public class HelloServlet extends HttpServlet {
	private static final long	serialVersionUID	= 1L;
	@Inject
	private TempTableService	service;

	@Inject
	private PersonService		personService;
	@Inject
	private EmployeeReport		report;
	@Inject
	private JMSMessageSender	messageSender;

	private Logger				logger				= Logger.getLogger(HelloServlet.class);

	public HelloServlet() {
		super();
	}

	@Override
	public void init() throws ServletException {
		super.init();
		logger.info("intgerate Spring context with servlet context");
		SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, this.getServletContext());
		logger.info("intgerate Done !! Successfully");
	}

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		try {

			PersonDTO p = new PersonDTO();
			p.setId(new PersonPKDTO("Ali_2", "Ali Sami_2"));
			p.setJob("Eng.");
			p.setAge(32);
			out.println(personService.updateEntity(p));
			out.println("================================");
			TempTableDTO dto = new TempTableDTO();
			dto.setTempTableName("Adam");
			dto.setTempEmail("adam@gmail.com");
			dto.setStatus(Status.ACTIVE);
			dto.setPerson(p);
			dto.setCreateTime(new Date());
			dto = service.updateEntity(dto);
			dto.setTempRef(service.findEntityById("Adam_1"));
			logger.info("sevlet method 'Sarvice' create or update  temp table");
			dto = service.createEntity(dto);
			logger.info("creation Done !! successfully	");
			out.println(dto);
			report.gernerateReport(null, ReportType.CSV);
			messageSender.sendMessage(new JMSMessageObject(), QueueEnum.QUEUE_TEST);
			out.println(service.findAll());
		} finally {
			out.close();
		}
	}

}
