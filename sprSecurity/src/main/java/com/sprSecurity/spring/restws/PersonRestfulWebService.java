package com.sprSecurity.spring.restws;

import java.util.List;

import javax.inject.Inject;

import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sprSecurity.spring.data.service.PersonService;
import com.sprSecurity.spring.dto.PersonDTO;

@RestController
public class PersonRestfulWebService {
	private Logger			logger	= Logger.getLogger(PersonRestfulWebService.class);
	@Inject
	private PersonService	personService;

	@RequestMapping(value = "/person", method = RequestMethod.GET)
	public ResponseEntity<List<PersonDTO>> getAllPerson() {
		List<PersonDTO> temps = personService.findAll();
		logger.info("Load All Temp tables :" + temps.size());
		if (temps.isEmpty()) {
			return new ResponseEntity<List<PersonDTO>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<PersonDTO>>(temps, HttpStatus.OK);
	}

}
