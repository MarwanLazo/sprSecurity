package com.sprSecurity.spring.dto;

import com.sprSecurity.spring.hibernate.entity.PersonPKEB;

public class PersonPKDTO implements CompositePKDTO {
	
	private static final long serialVersionUID = 1L;
	
	private String			  name;
	
	private String			  fullName;
	
	public PersonPKDTO() {
	}
	
	public PersonPKDTO(String name, String fullName) {
		super();
		this.name = name;
		this.fullName = fullName;
	}
	
	public String getName () {
		return name;
	}
	
	public void setName (String name) {
		this.name = name;
	}
	
	public String getFullName () {
		return fullName;
	}
	
	public void setFullName (String fullName) {
		this.fullName = fullName;
	}
	
	@Override
	public int hashCode () {
		int code = 31;
		if (name != null) code += name.length();
		if (fullName != null) code += fullName.length();
		return code;
	}
	
	@Override
	public boolean equals (Object obj) {
		if (!(obj instanceof PersonPKDTO)) return false;
		PersonPKDTO pk = (PersonPKDTO) obj;
		if (pk.fullName == null && pk.name == null && this.fullName == null && this.name == null) return false;
		if (!pk.fullName.equals(this.fullName) && !pk.name.equals(this.name)) return false;
		return true;
	}
	
	@Override
	public String toString () {
		return "[" + name + "," + fullName + "]";
	}
	
	

	@Override
	public Class<?> getPKClass () {
		return PersonPKEB.class;
	}
	
}
