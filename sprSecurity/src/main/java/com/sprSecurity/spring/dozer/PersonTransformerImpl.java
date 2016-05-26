package com.sprSecurity.spring.dozer;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.sprSecurity.spring.data.dao.PersonDAO;
import com.sprSecurity.spring.dto.PersonDTO;
import com.sprSecurity.spring.hibernate.entity.PersonEB;
@Service("PersonTransformerImpl")
public class PersonTransformerImpl extends AbstractTransformerImpl<PersonDTO, PersonEB, PersonDAO> implements PersonTransformer {

	@Inject
	private PersonDAO dao;

	public PersonTransformerImpl() {
		super(PersonDTO.class, PersonEB.class);
	}

	@Override
	public PersonDAO getDAO() {
		return dao;
	}

}
