package com.sprSecurity.spring.dto;

import java.io.Serializable;

public interface CompositePKDTO extends Serializable {
	
	Class<?> getPKClass ();
	
}
