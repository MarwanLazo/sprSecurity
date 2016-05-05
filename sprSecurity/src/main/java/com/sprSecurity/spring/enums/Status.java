package com.sprSecurity.spring.enums;

public enum Status implements PersistentEnum{
	ACTIVE(0), IN_ACTIVE(1);
	
	private Integer id;
	
	private Status(Integer id) {
		this.id = id;
	}
	
	
	
	public void setId (Integer id) {
		this.id = id;
	}

	@Override
	public int getId () {
		return id;
	}
}
