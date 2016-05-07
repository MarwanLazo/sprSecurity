package com.sprSecurity.spring.data.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;

import com.sprSecurity.spring.dto.AbstractDTO;

public interface AbstractDAO<PK extends Serializable, DTO extends AbstractDTO<PK>> {
	
	DTO createEntity (DTO dto);
	
	DTO updateEntity (DTO dto);
	
	boolean deleteEntity (DTO dto);
	
	DTO findEntityById (PK pk);
	
	List<DTO> findAll ();
	
	EntityManager getEntityManager ();
}
