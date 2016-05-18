package com.sprSecurity.spring.soapws;

import java.util.ArrayList;
import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.sprSecurity.spring.data.service.TempTableService;
import com.sprSecurity.spring.dto.TempTableDTO;

@WebService(name = "ws", serviceName = "HelloSoap")
public class HelloSoap extends SpringBeanAutowiringSupport{

	@Autowired
	private TempTableService service;

	@WebMethod(operationName = "helloWorld")
	public List<TempTableDTO> getHelloWorld(String tempName) {
		List<TempTableDTO> list = new ArrayList<TempTableDTO>();
		if (service != null) {
			return service.findAll();
		}
		return list;
	}

	
}
