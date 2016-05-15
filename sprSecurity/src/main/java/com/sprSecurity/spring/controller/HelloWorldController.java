package com.sprSecurity.spring.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.sprSecurity.spring.data.service.TempTableService;
import com.sprSecurity.spring.dto.TempTableDTO;

@Controller
@RequestMapping(value = "/")
public class HelloWorldController {
	@Inject
	private TempTableService service;

//	@RequestMapping(method = RequestMethod.GET)
//	public String sayHello(ModelMap model) {
//		model.addAttribute("greeting", "Hello World from Spring 4 MVC");
//		return "welcome";
//	}
//
//	@RequestMapping(value = "/hello", method = RequestMethod.GET)
//	public String sayHelloAgain(ModelMap model) {
//
//		TempTableDTO dto = new TempTableDTO();
//		dto.setStatus(Status.ACTIVE);
//		dto.setTempTableName("Adam");
//		dto.setTempEmail("adam@gmail.com");
//		service.createEntity(dto);
//		model.addAttribute("greeting", "Hello World Again, from Spring 4 MVC");
//		return "welcome";
//	}


	@RequestMapping("/hello")
	public ModelAndView helloWorld() {
		List<TempTableDTO> temp = service.findAll();
		ModelAndView view = new ModelAndView("welcome", "message", temp);
	
		return view;
	}

}
