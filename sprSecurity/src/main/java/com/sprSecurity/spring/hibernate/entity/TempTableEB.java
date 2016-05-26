package com.sprSecurity.spring.hibernate.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Type;
import org.hibernate.envers.Audited;
import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.Store;

import com.sprSecurity.spring.dto.TempTableDTO;
import com.sprSecurity.spring.enums.Status;

@Entity
@Table(name = "temp_table")
@Indexed
@Audited
// @Cacheable
// @Cache(usage = CacheConcurrencyStrategy.TRANSACTIONAL)
public class TempTableEB extends AbstractEntity<String> {
	private static final long	serialVersionUID	= 1L;
	private static final String	SATUS_USER_TYPE		= "com.sprSecurity.spring.hibernate.usertype.StatusUserType";
	private static final String	FORMULA_QUERY		= "SELECT p FROM PersonEB p WHERE p.id.name='Ali' and p.id.fullName='Ali Sami'";

	@Id
	@Column(name = "TEMP_NAME")
	@Field(analyze = Analyze.YES, index = Index.YES, store = Store.NO)
	@NotNull
	private String				id;

	@Field(analyze = Analyze.YES, index = Index.YES, store = Store.NO)
	@Column(name = "TEMP_EMAIL")
	private String				tempEmail;
	@Field(analyze = Analyze.YES, index = Index.YES, store = Store.NO)
	@Column(name = "TEMP_REF")
	private String				tempRef;

	@Column(name = "active")
	@Type(type = SATUS_USER_TYPE)
	private Status				status;

	@Column(name = "create_time")
	private Date				createTime;
	//
	// @ManyToOne(fetch = FetchType.LAZY)
	// @JoinColumns({ @JoinColumn(name = "f_name", referencedColumnName =
	// "f_name"), @JoinColumn(name = "l_name", referencedColumnName = "l_name")
	// })
	//
	 @JoinColumns({
	        @JoinColumn(name = "l_name", referencedColumnName = "l_name"),
	        @JoinColumn(name = "f_name", referencedColumnName = "f_name")})
	    @ManyToOne
	private PersonEB			person;

	public TempTableEB() {
		super();
	}

	public TempTableEB(String tempName, String tempEmail) {
		this.id = tempName;
		this.tempEmail = tempEmail;
	}

	public String getId() {
		return this.id;
	}

	public void setId(String tempName) {
		this.id = tempName;
	}

	public String getTempEmail() {
		return this.tempEmail;
	}

	public void setTempEmail(String tempEmail) {
		this.tempEmail = tempEmail;
	}

	@Override
	public String toString() {
		return "[" + id + ":" + tempEmail + "]";
	}

	public String getTempRef() {
		return tempRef;
	}

	public void setTempRef(String tempRef) {
		this.tempRef = tempRef;
	}

	@Override
	public String getPK() {
		return getId();
	}

	@Override
	public String getPKAsString() {
		return getId().toString();
	}

	@Override
	public Class<TempTableDTO> getDTOClass() {
		return TempTableDTO.class;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public PersonEB getPerson() {
		return person;
	}

	public void setPerson(PersonEB person) {
		this.person = person;
	}

}
