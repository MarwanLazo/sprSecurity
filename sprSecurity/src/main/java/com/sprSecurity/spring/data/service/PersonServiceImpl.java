package com.sprSecurity.spring.data.service;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.sprSecurity.spring.data.dao.PersonDAO;
import com.sprSecurity.spring.dto.PersonDTO;
import com.sprSecurity.spring.dto.PersonPKDTO;

@Service("PersonServiceImpl")
public class PersonServiceImpl extends AbstractServiceImpl<PersonPKDTO, PersonDTO, PersonDAO> implements PersonService {

	@Inject
	private PersonDAO dao;

	@Override
	public PersonDAO getDAO() {
		return dao;
	}

}
