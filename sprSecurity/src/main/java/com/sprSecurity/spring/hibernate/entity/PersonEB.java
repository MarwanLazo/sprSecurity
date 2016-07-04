package com.sprSecurity.spring.hibernate.entity;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.envers.Audited;

import com.sprSecurity.spring.dto.PersonDTO;

@Entity
@Table(name = "person")
@Audited
// @Where(clause="")
public class PersonEB extends AbstractEntity<PersonPKEB> {

	private static final long	serialVersionUID	= 1L;

	@EmbeddedId
	private PersonPKEB			id;

	@Column(name = "age")
	private Integer				age;

	@Column(name = "job")
	private String				job;

//	@Version
//	private Integer				version				= 0;

	public PersonEB() {

	}

	public PersonEB(PersonPKEB id, Integer age, String job) {
		super();
		this.id = id;
		this.age = age;
		this.job = job;
	}

	public PersonEB(PersonPKEB personPKEB) {
		this.id = personPKEB;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public PersonPKEB getId() {
		return id;
	}

	public void setId(PersonPKEB id) {
		this.id = id;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	@Override
	public int hashCode() {
		if (getId() == null)
			return 31;
		return getId().hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof PersonEB))
			return false;
		PersonEB eb = (PersonEB) obj;
		if (eb.id == null && this.id == null)
			return false;
		if (!eb.id.equals(this.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		if (getId() == null)
			return "";
		return getId().toString();
	}

	@Override
	public PersonPKEB getPK() {
		return getId();
	}

	@Override
	public String getPKAsString() {
		return getId().toString();
	}

	@Override
	public Class<PersonDTO> getDTOClass() {
		return PersonDTO.class;
	}

	// public Integer getVersion() {
	// return version;
	// }
	//
	// public void setVersion(Integer version) {
	// this.version = version;
	// }

}
