package com.sprSecurity.spring.data.dao;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.sprSecurity.spring.data.PersonRepository;
import com.sprSecurity.spring.dozer.PersonTransformer;
import com.sprSecurity.spring.dto.PersonDTO;
import com.sprSecurity.spring.dto.PersonPKDTO;
import com.sprSecurity.spring.hibernate.entity.PersonEB;

@Service("PersonDAOImpl")
public class PersonDAOImpl extends AbstractDAOImpl<PersonPKDTO, PersonDTO, PersonEB, PersonRepository, PersonTransformer> implements PersonDAO {

	@Inject
	private PersonRepository repository;
	@Inject
	private PersonTransformer transformer;

	@Override
	public PersonTransformer getTransFormer() {
		return transformer;
	}

	@Override
	public PersonRepository getRepository() {
		return repository;
	}

}
