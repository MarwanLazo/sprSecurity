package com.sprSecurity.spring.data.service;

import com.sprSecurity.spring.data.dao.PersonDAO;
import com.sprSecurity.spring.dto.PersonDTO;
import com.sprSecurity.spring.dto.PersonPKDTO;

public interface PersonService extends AbstractService<PersonPKDTO, PersonDTO, PersonDAO> {

}
