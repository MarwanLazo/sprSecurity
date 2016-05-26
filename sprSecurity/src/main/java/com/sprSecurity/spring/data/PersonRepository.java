package com.sprSecurity.spring.data;

import org.springframework.stereotype.Repository;

import com.sprSecurity.spring.hibernate.entity.PersonEB;
import com.sprSecurity.spring.hibernate.entity.PersonPKEB;

@Repository(value = "PersonRepository")
public interface PersonRepository extends MainRepository<PersonEB, PersonPKEB> {

}
