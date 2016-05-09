package com.sprSecurity.spring.data.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.log4j.Logger;

import com.sprSecurity.spring.data.MainRepository;
import com.sprSecurity.spring.dozer.AbstractTransformer;
import com.sprSecurity.spring.dto.AbstractDTO;
import com.sprSecurity.spring.hibernate.entity.AbstractEntity;

public abstract class AbstractDAOImpl<PK extends Serializable, DTO extends AbstractDTO<PK>, Entity extends AbstractEntity<PK>, Repository extends MainRepository<Entity, PK>, TransFormer extends AbstractTransformer<DTO, Entity>>
		implements AbstractDAO<PK, DTO> {

	private Logger					logger	= Logger.getLogger(AbstractDAOImpl.class);

	@PersistenceContext
	private transient EntityManager	em;

	public abstract TransFormer getTransFormer();

	public abstract Repository getRepository();

	@Override
	public DTO createEntity(DTO dto) {
		logger.info("Create Entity Start");
		Entity eb = getTransFormer().transfromToEntity(dto);
		eb = getRepository().save(eb);
		return getTransFormer().transfromToDTO(eb);
	}

	@Override
	public DTO updateEntity(DTO dto) {
		logger.info("Update Entity Start");
		return createEntity(dto);
	}

	@Override
	public boolean deleteEntity(DTO dto) {
		logger.info("delete Entity Start");
		Entity eb = getTransFormer().transfromToEntity(dto);
		if (findEntityById(eb) != null) {
			getRepository().delete(eb);
			logger.info("delete Entity end successfully");
			return true;
		}
		return false;
	}

	@Override
	public DTO findEntityById(PK pk) {
		logger.info("Find  Entity if exist");
		if (pk != null) {
			Entity eb = getRepository().findOne(pk);
			logger.info("Entity founded");
			return getTransFormer().transfromToDTO(eb);
		} else {
			logger.info("No Entity founded with Id >> " + pk);
			return null;
		}

	}

	@Override
	public List<DTO> findAll() {
		logger.info("Find All Entities");
		return getTransFormer().transfromListToDTO(getRepository().findAll());
	}

	@Override
	public EntityManager getEntityManager() {
		return em;
	}

	private Entity findEntityById(Entity entity) {
		logger.info("Find  Entity if exist");
		if (entity.getPK() != null && entity.getPK().getClass().isAssignableFrom(Serializable.class)) {
			return getRepository().findOne(entity.getPK());
		} else {
			// do some senario to get entity by its composite PK
			return null;
		}
	}

}
