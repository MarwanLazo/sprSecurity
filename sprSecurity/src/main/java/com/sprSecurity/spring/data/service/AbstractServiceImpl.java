package com.sprSecurity.spring.data.service;

import java.io.Serializable;
import java.util.List;

import com.sprSecurity.spring.data.dao.AbstractDAO;
import com.sprSecurity.spring.dto.AbstractDTO;

public abstract class AbstractServiceImpl<PK extends Serializable, DTO extends AbstractDTO<PK>, DAO extends AbstractDAO<PK, DTO>>
        implements AbstractService<PK, DTO, DAO> {
	
	public abstract DAO getDAO ();
	
	@Override
	public DTO createEntity (DTO dto) {
		
		return getDAO().createEntity(dto);
	}
	
	@Override
	public DTO updateEntity (DTO dto) {
		
		return getDAO().updateEntity(dto);
	}
	
	@Override
	public boolean deleteEntity (DTO dto) {
		return getDAO().deleteEntity(dto);
	}
	
	@Override
	public DTO findEntityById (PK pk) {
		return getDAO().findEntityById(pk);
	}
	
	@Override
	public List<DTO> findAll () {
		return getDAO().findAll();
	}
	
}
