package com.sprSecurity.spring.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.sprSecurity.spring.data.service.TempTableService;
import com.sprSecurity.spring.dto.TempTableDTO;

@Controller
@RequestMapping(value = "/")
public class HelloWorldController {
	@Inject
	private TempTableService service;

	@RequestMapping("/hello")
	public ModelAndView helloWorld() {
		List<TempTableDTO> temp = service.findAll();
		ModelAndView view = new ModelAndView("welcome", "message", temp);

		return view;
	}

	@RequestMapping(value = { "/", "/welcome**" }, method = RequestMethod.GET)
	public ModelAndView welcomePage() {

		ModelAndView model = new ModelAndView();
		model.addObject("title", "Spring Security Hello World");
		model.addObject("message", "This is welcome page!");
		model.setViewName("hello");
		return model;

	}

	@RequestMapping(value = "/admin**", method = RequestMethod.GET)
	public ModelAndView adminPage() {

		ModelAndView model = new ModelAndView();
		model.addObject("title", "Spring Security Hello World");
		model.addObject("message", "This is protected page - Admin Page!");
		model.setViewName("admin");

		return model;

	}

	@RequestMapping(value = "/dba**", method = RequestMethod.GET)
	public ModelAndView dbaPage() {

		ModelAndView model = new ModelAndView();
		model.addObject("title", "Spring Security Hello World");
		model.addObject("message", "This is protected page - Database Page!");
		model.setViewName("admin");

		return model;

	}

}
