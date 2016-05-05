package com.sprSecurity.spring.dto;

import java.io.Serializable;

import com.sprSecurity.spring.enums.Status;

public abstract class AbstractDTO<PK extends Serializable> implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Status			  status;
	
	public abstract PK getPK ();
	
	public abstract String getPKAsString ();
	
	public abstract Class<?> getEntityClass ();
	
	public Status getStatus () {
		return status;
	}
	
	public void setStatus (Status status) {
		this.status = status;
	}
}
