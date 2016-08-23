package com.sprSecurity.spring.data.dao;

import java.util.List;

import com.sprSecurity.spring.dto.TempTableDTO;

public interface TempTableDAO extends AbstractDAO<String, TempTableDTO> {

	List<TempTableDTO> geTempTable(String tempEmail);
}
