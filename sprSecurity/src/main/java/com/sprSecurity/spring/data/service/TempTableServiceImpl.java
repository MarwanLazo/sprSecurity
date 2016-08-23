package com.sprSecurity.spring.data.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.sprSecurity.spring.data.dao.TempTableDAO;
import com.sprSecurity.spring.dto.TempTableDTO;

@Service("tempTableService")
public class TempTableServiceImpl extends AbstractServiceImpl<String, TempTableDTO, TempTableDAO> implements TempTableService {
	
	@Inject
	private TempTableDAO dao;
	
	@Override
	public TempTableDAO getDAO () {
		
		return dao;
	}

	@Override
	public List<TempTableDTO> geTempTable(String tempEmail) {
		return dao.geTempTable(tempEmail);
	}
	
}
