package com.sprSecurity.spring.dto;

import com.sprSecurity.spring.hibernate.entity.PersonEB;

public class PersonDTO extends AbstractDTO<PersonPKDTO> {

	private static final long	serialVersionUID	= 1L;

	private PersonPKDTO			id;

	private Integer				age;

	private String				job;

	public PersonDTO() {

	}

	public PersonDTO(PersonPKDTO id, Integer age, String job) {
		super();
		this.id = id;
		this.age = age;
		this.job = job;
	}



	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public PersonPKDTO getId() {
		return id;
	}

	public void setId(PersonPKDTO id) {
		this.id = id;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	@Override
	public PersonPKDTO getPK() {
		return getId();
	}

	@Override
	public String getPKAsString() {
		return getId().toString();
	}

	@Override
	public Class<?> getEntityClass() {
		return PersonEB.class;
	}

	@Override
	public String toString() {
		if (getId() == null)
			return "";
		return getId().toString();
	}

	@Override
	public int hashCode() {
		if (getId() == null)
			return 31;
		return getId().hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof PersonDTO))
			return false;
		PersonDTO eb = (PersonDTO) obj;
		if (eb.id == null && this.id == null)
			return false;
		if (!eb.id.equals(this.id))
			return false;
		return true;
	}

}
