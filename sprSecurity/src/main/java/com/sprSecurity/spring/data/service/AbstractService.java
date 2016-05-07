package com.sprSecurity.spring.data.service;

import java.io.Serializable;
import java.util.List;

import com.sprSecurity.spring.data.dao.AbstractDAO;
import com.sprSecurity.spring.dto.AbstractDTO;

public interface AbstractService<PK extends Serializable, DTO extends AbstractDTO<PK>, DAO extends AbstractDAO<PK, DTO>> {
	
	DTO createEntity (DTO dto);
	
	DTO updateEntity (DTO dto);
	
	boolean deleteEntity (DTO dto);
	
	DTO findEntityById (PK pk);
	
	List<DTO> findAll ();
}
