package com.sprSecurity.spring.hibernate.usertype;

import com.sprSecurity.spring.enums.Status;

public class StatusUserType extends PersistentEnumUserType<Status> {

	@Override
	public Class<Status> returnedClass() {
		return Status.class;
	}
}
