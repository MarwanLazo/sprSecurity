package com.sprSecurity.spring.dto;

import java.io.Serializable;

import com.sprSecurity.spring.hibernate.entity.CompositePKEB;

public interface CompositePKDTO extends Serializable {
	Class<? extends CompositePKEB> getPKCLass();
}
