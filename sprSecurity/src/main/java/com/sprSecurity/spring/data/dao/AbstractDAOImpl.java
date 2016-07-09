package com.sprSecurity.spring.data.dao;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.sprSecurity.ejb.TempTableEJB;
import com.sprSecurity.spring.data.MainRepository;
import com.sprSecurity.spring.dozer.AbstractTransformer;
import com.sprSecurity.spring.dto.AbstractDTO;
import com.sprSecurity.spring.dto.CompositePKDTO;
import com.sprSecurity.spring.hibernate.entity.AbstractEntity;
import com.sprSecurity.spring.hibernate.entity.CompositePKEB;

import net.sf.oval.ConstraintViolation;
import net.sf.oval.Validator;
import net.sf.oval.configuration.annotation.AnnotationsConfigurer;
import net.sf.oval.configuration.annotation.BeanValidationAnnotationsConfigurer;

public abstract class AbstractDAOImpl<PK extends Serializable, DTO extends AbstractDTO<PK>, Entity extends AbstractEntity<? extends Serializable>, Repository extends MainRepository<Entity, ? extends Serializable>, TransFormer extends AbstractTransformer<DTO, Entity>>
		implements AbstractDAO<PK, DTO> {

	private Logger logger = Logger.getLogger(AbstractDAOImpl.class);
	// java:global._auto_generated_ear_.sprSecurity.TempTableEJBImpl
	@EJB(mappedName = "TempTableEJB#com.sprSecurity.ejb.TempTableEJB")
	private TempTableEJB tableEJB;

	@PersistenceContext
	private transient EntityManager em;

	public abstract TransFormer getTransFormer();

	public abstract Repository getRepository();

	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public DTO createEntity(DTO dto) {
		if (!validate(dto))
			return null;
		logger.info("Create Entity Start");
		Entity eb = getTransFormer().transfromToEntity(dto);
		eb = getRepository().save(eb);
		return getTransFormer().transfromToDTO(eb);
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	@Override
	public DTO updateEntity(DTO dto) {
		if (!validate(dto))
			return null;
		logger.info("Update Entity Start");
		Entity eb = getTransFormer().transfromToEntity(dto);
		// eb = em.merge(eb);
		eb = getRepository().save(eb);
		return getTransFormer().transfromToDTO(eb);
	}

	@Override
	public boolean deleteEntity(DTO dto) {
		if (!validate(dto))
			return false;
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
		logger.info("======================>> " + tableEJB);
		tableEJB.get();
		logger.info("Find  Entity if exist");
		if (pk != null) {
			Serializable pkk = (Serializable) getPkEB(pk);
			Entity eb = (Entity) getRepository().findEntityById(pkk);
			if (eb == null) {
				logger.info("No Entity founded with Id >> " + pk);
				return null;
			}

			logger.info("Entity founded");
			return getTransFormer().transfromToDTO(eb);
		} else {
			logger.info("No Entity founded with Id >> " + pk);
			return null;
		}

	}

	@Override
	public Object getPkEB(Object pk) {
		if (pk instanceof CompositePKEB) {
			return pk;
		}
		if (pk instanceof CompositePKDTO) {
			return getTransFormer().getDozerBeanMapper().map(pk, ((CompositePKDTO) pk).getPKClass());
		}
		return pk;
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
			Entity eb = (Entity) getRepository().findEntityById(entity.getPK());
			logger.info(" Entity Loaded Successfully with ID" + eb);
			return eb;
		} else {
			logger.info("No Entity With Id: " + entity.getPKAsString());
			return null;
		}
	}

	@Override
	public Object method(Class<?> entityClassName, Object obj) {
		Object pk = getPkEB(obj);
		return em.find(entityClassName, pk);
	}

	private boolean validate(DTO dto) {
		// TODO
		AnnotationsConfigurer annotationsConfigurer;
		BeanValidationAnnotationsConfigurer beanValidationAnnotationsConfigurer;
		Validator validator;
		annotationsConfigurer = new AnnotationsConfigurer();
		beanValidationAnnotationsConfigurer = new BeanValidationAnnotationsConfigurer();
		validator = new Validator(annotationsConfigurer, beanValidationAnnotationsConfigurer);

		List<ConstraintViolation> errors = validator.validate(dto);
		if (!errors.isEmpty()) {
			for (ConstraintViolation constraintViolation : errors) {
				logger.error(constraintViolation.getMessage());
			}
			return false;
		}
		return true;
	}

}
