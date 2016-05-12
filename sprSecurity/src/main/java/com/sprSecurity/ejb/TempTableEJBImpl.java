package com.sprSecurity.ejb;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;

@Stateless(mappedName = "TempTableEJB")
public class TempTableEJBImpl implements TempTableEJB {

	// @PersistenceContext(unitName="sprTest")
	// private EntityManager entityManager;

	@PostConstruct
	private void init() {

	}

	@Override
	public void get() {
		System.out.println("Test EJB");
		// System.out.println(entityManager);
	}

}
