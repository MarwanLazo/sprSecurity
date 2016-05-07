package com.sprSecurity.spring.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sprSecurity.spring.data.service.TempTableService;
import com.sprSecurity.spring.dto.TempTableDTO;
import com.sprSecurity.spring.enums.Status;

@Controller
@RequestMapping(value = "/")
public class HelloWorldController {
	@Inject
	private TempTableService service;
	
	@RequestMapping(method = RequestMethod.GET)
	public String sayHello (ModelMap model) {
		model.addAttribute("greeting", "Hello World from Spring 4 MVC");
		return "welcome";
	}
	
	@RequestMapping(value = "/hello", method = RequestMethod.GET)
	public String sayHelloAgain (ModelMap model) {
		
		TempTableDTO dto = new TempTableDTO();
		dto.setStatus(Status.ACTIVE);
		dto.setId("Adam");
		dto.setTempEmail("adam@gmail.com");
		service.createEntity(dto);
		model.addAttribute("greeting", "Hello World Again, from Spring 4 MVC");
		return "welcome";
	}
}
