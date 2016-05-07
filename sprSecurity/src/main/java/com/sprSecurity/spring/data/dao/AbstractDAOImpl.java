package com.sprSecurity.spring.data.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.sprSecurity.spring.data.MainRepository;
import com.sprSecurity.spring.dozer.AbstractTransformer;
import com.sprSecurity.spring.dto.AbstractDTO;
import com.sprSecurity.spring.hibernate.entity.AbstractEntity;

public abstract class AbstractDAOImpl<PK extends Serializable, DTO extends AbstractDTO<PK>, Entity extends AbstractEntity<PK>, Repository extends MainRepository<Entity, PK>, TransFormer extends AbstractTransformer<DTO, Entity>>
        implements AbstractDAO<PK, DTO> {
	
	@PersistenceContext
	private transient EntityManager em;
	
	public abstract TransFormer getTransFormer ();
	
	public abstract Repository getRepository ();
	
	@Override
	public DTO createEntity (DTO dto) {
		Entity eb = getTransFormer().transfromToEntity(dto);
		eb = getRepository().save(eb);
		return getTransFormer().transfromToDTO(eb);
	}
	
	@Override
	public DTO updateEntity (DTO dto) {
		return createEntity(dto);
	}
	
	@Override
	public boolean deleteEntity (DTO dto) {
		Entity eb = getTransFormer().transfromToEntity(dto);
		if (findEntityById(eb) != null) {
			getRepository().delete(eb);
			return true;
		}
		return false;
	}
	
	@Override
	public DTO findEntityById (PK pk) {
		if (pk != null && pk.getClass().isAssignableFrom(Serializable.class)) {
			Entity eb = getRepository().findOne(pk);
			return getTransFormer().transfromToDTO(eb);
		} else {
			return null;
		}
		
	}
	
	@Override
	public List<DTO> findAll () {
		return getTransFormer().transfromListToDTO(getRepository().findAll());
	}
	
	@Override
	public EntityManager getEntityManager () {
		return em;
	}
	
	private Entity findEntityById (Entity entity) {
		if (entity.getPK() != null && entity.getPK().getClass().isAssignableFrom(Serializable.class)) {
			return getRepository().findOne(entity.getPK());
		} else {
			// do some senario to get entity by its composite PK
			return null;
		}
	}
	
}
