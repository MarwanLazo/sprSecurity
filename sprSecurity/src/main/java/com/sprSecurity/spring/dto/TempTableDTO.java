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
	private String				tempTableName;
	
	@NotNull
	@MatchPattern(pattern = EMAIL_PATTERN, message = "invalid email Oval")
	private String				tempEmail;
	
	private TempTableDTO		tempRef;
	
	public TempTableDTO() {
	}
	
	public TempTableDTO(String tempName, String tempEmail) {
		super();
		this.tempTableName = tempName;
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
		return "[" + tempTableName + ":" + tempEmail + "]";
	}
	
	@Override
	public boolean equals (Object obj) {
		if (obj == null) return false;
		if (obj instanceof TempTableDTO) {
			TempTableDTO temp = (TempTableDTO) obj;
			if (temp.tempTableName != null && temp.tempEmail != null && temp.tempEmail.equals(this.tempEmail) && temp.tempTableName.equals(this.tempTableName)) return true;
		}
		return false;
	}
	
	@Override
	public String getPK () {
		return getTempTableName();
	}
	
	@Override
	public String getPKAsString () {
		return getTempTableName().toString();
	}
	
	@Override
	public Class<TempTableEB> getEntityClass () {
		return TempTableEB.class;
	}

	public String getTempTableName() {
		return tempTableName;
	}

	public void setTempTableName(String tempTableName) {
		this.tempTableName = tempTableName;
	}
	
}
