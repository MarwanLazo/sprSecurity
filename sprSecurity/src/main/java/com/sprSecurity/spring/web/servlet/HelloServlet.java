package com.sprSecurity.spring.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.sprSecurity.spring.data.service.TempTableService;
import com.sprSecurity.spring.dto.TempTableDTO;
import com.sprSecurity.spring.enums.Status;


@WebServlet("/HelloServlet")
public class HelloServlet extends HttpServlet {
	private static final long	serialVersionUID	= 1L;
	@Inject
	private TempTableService	service;

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
			TempTableDTO dto = new TempTableDTO();
			dto.setTempTableName(request.getParameter("name"));
			dto.setTempEmail(request.getParameter("email"));
			dto.setStatus(Status.IN_ACTIVE);
			dto.setTempRef(service.findEntityById("Adam"));
			logger.info("sevlet method 'Sarvice' create or update  temp table");
			dto = service.createEntity(dto);
			logger.info("creation Done !! successfully	");
			out.println(dto);
			out.println(service.findAll());
		} finally {
 			out.close();
		}
	}

}
