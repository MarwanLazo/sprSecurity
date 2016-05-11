package com.sprSecurity.spring.hibernate.search;

import java.util.List;

import com.sprSecurity.spring.dto.TempTableDTO;

public interface TempTableSearch {

	List<TempTableDTO> getEntityByFields(Object object ,String... fields) throws InterruptedException;
}
