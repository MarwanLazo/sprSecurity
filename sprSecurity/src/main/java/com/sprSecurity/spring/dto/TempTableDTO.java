package com.sprSecurity.spring.dto;

import javax.validation.constraints.NotNull;

import com.sprSecurity.spring.hibernate.entity.TempTableEB;
import com.sprSecurity.spring.oval.UpperCase;
import com.sprSecurity.spring.oval.ValidID;

import net.sf.oval.constraint.MatchPattern;
import net.sf.oval.constraint.NotBlank;

public class TempTableDTO extends AbstractDTO<String> {
	
	private static final long	serialVersionUID = 1L;
	private static final String	EMAIL_PATTERN	 = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
	
	@NotBlank
	@NotNull(message = "Name cannot be null")
	@UpperCase
	@ValidID
	private String				id;
	
	@NotNull
	@MatchPattern(pattern = EMAIL_PATTERN, message = "invalid email Oval")
	// @Pattern(regexp = EMAIL_PATTERN, message = "invalid email JavaX")
	private String				tempEmail;
	
	private TempTableDTO		tempRef;
	
	public TempTableDTO() {
	}
	
	public TempTableDTO(String tempName, String tempEmail) {
		super();
		this.id = tempName;
		this.tempEmail = tempEmail;
	}
	
	
	public String getTempEmail () {
		return tempEmail;
	}
	
	public void setTempEmail (String tempEmail) {
		this.tempEmail = tempEmail;
	}
	
	public TempTableDTO getTempRef () {
		return tempRef;
	}
	
	public void setTempRef (TempTableDTO tempRef) {
		this.tempRef = tempRef;
	}
	
	@Override
	public String toString () {
		return "[" + id + ":" + tempEmail + "]";
	}
	
	@Override
	public boolean equals (Object obj) {
		if (obj == null) return false;
		if (obj instanceof TempTableDTO) {
			TempTableDTO temp = (TempTableDTO) obj;
			if (temp.id != null && temp.tempEmail != null && temp.tempEmail.equals(this.tempEmail) && temp.id.equals(this.id)) return true;
		}
		return false;
	}
	
	@Override
	public String getPK () {
		return id;
	}
	
	@Override
	public String getPKAsString () {
		return id.toString();
	}
	
	@Override
	public Class<TempTableEB> getEntityClass () {
		return TempTableEB.class;
	}

	public String getId () {
		return id;
	}

	public void setId (String id) {
		this.id = id;
	}
	
}
