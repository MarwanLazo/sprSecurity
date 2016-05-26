package com.sprSecurity.spring.hibernate.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class PersonPKEB implements CompositePKEB {

	private static final long	serialVersionUID	= 1L;

	@Column(name = "f_name")
	private String				name;
	@Column(name = "l_name")
	private String				fullName;

	public PersonPKEB() {
	}

	public PersonPKEB(String name, String fullName) {
		super();
		this.name = name;
		this.fullName = fullName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	@Override
	public int hashCode() {
		int code = 31;
		if (name != null)
			code += name.length();
		if (fullName != null)
			code += fullName.length();
		return code;
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof PersonPKEB))
			return false;
		PersonPKEB pk = (PersonPKEB) obj;
		if (pk.fullName == null && pk.name == null && this.fullName == null && this.name == null)
			return false;
		if (!pk.fullName.equals(this.fullName) && !pk.name.equals(this.name))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "[" + name + "," + fullName + "]";
	}

}
