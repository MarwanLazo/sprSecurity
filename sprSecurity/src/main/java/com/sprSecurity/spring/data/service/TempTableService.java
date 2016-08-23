package com.sprSecurity.spring.data.service;

import java.util.List;

import com.sprSecurity.spring.data.dao.TempTableDAO;
import com.sprSecurity.spring.dto.TempTableDTO;

public interface TempTableService extends AbstractService<String, TempTableDTO, TempTableDAO> {
	List<TempTableDTO> geTempTable(String tempEmail);
}
