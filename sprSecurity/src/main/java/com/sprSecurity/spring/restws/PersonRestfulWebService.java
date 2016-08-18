package com.sprSecurity.spring.restws;

import java.util.List;

import javax.inject.Inject;

import org.apache.log4j.Logger;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.sprSecurity.spring.data.service.PersonService;
import com.sprSecurity.spring.dto.PersonDTO;
import com.sprSecurity.spring.dto.PersonPKDTO;
import com.sprSecurity.spring.dto.TempTableDTO;

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

	@RequestMapping(value = "/addPerson/", method = RequestMethod.POST)
	public ResponseEntity<Void> createPerson(@RequestBody PersonDTO person, UriComponentsBuilder ucBuilder) {
		logger.info("Creating person " + person.getPK());
		personService.createEntity(person);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/person/id/{name}/{fullName}").buildAndExpand(person.getPK().getName(), person.getPK().getFullName()).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/person/{name}/{fullName}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<PersonDTO> getPerson(@PathVariable("name") String name, @PathVariable("fullName") String fullName) {
		logger.info("Fetching Person with id [" + name + "," + fullName + "]");
		PersonDTO dto = personService.findEntityById(new PersonPKDTO(name, fullName));
		if (dto == null) {
			logger.info("User with id [" + name + "," + fullName + "] not found");
			return new ResponseEntity<PersonDTO>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<PersonDTO>(dto, HttpStatus.OK);
	}

	@RequestMapping(value = "/deleteperson/{name}/{fullName}", method = RequestMethod.DELETE)
	public ResponseEntity<TempTableDTO> deletePerson(@PathVariable("name") String name, @PathVariable("fullName") String fullName) {
		logger.info("Fetching & Deleting temp Table with id [" + name + "," + fullName + "]");

		PersonDTO person = personService.findEntityById(new PersonPKDTO(name, fullName));
		if (person == null) {
			logger.info("Unable to delete. person with id [" + name + "," + fullName + "] not found");
			return new ResponseEntity<TempTableDTO>(HttpStatus.NOT_FOUND);
		}

		personService.deleteEntity(person);
		logger.info(" person with id " + person.getPKAsString() + " Deleted Successfully");
		return new ResponseEntity<TempTableDTO>(HttpStatus.NO_CONTENT);
	}

}
