package com.sprSecurity.spring.dozer;

import java.io.Serializable;

import org.apache.log4j.Logger;
import org.dozer.ConfigurableCustomConverter;
import org.dozer.DozerBeanMapper;

import com.sprSecurity.spring.data.dao.AbstractDAO;
import com.sprSecurity.spring.dto.AbstractDTO;
import com.sprSecurity.spring.hibernate.entity.AbstractEntity;

@SuppressWarnings("rawtypes")
public class DozerCustomConverter<DAO extends AbstractDAO, DTO extends AbstractDTO> implements ConfigurableCustomConverter {

	private Logger			logger	= Logger.getLogger(DozerCustomConverter.class);

	private DAO				service;

	private DozerBeanMapper	dozerBeanMapper;

	public DozerCustomConverter(DAO service, DozerBeanMapper dozerBeanMapper) {
		this.service = service;
		this.dozerBeanMapper = dozerBeanMapper;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Object convert(Object targetFieldValue, Object sourceFieldValue, Class<?> targetClazz, Class<?> sourceClazz) {
		logger.info("targetClazz:" + targetClazz);
		logger.info("sourceClazz:" + sourceClazz);

		if (targetFieldValue == null && sourceFieldValue == null) {
			return null;
		}
		Serializable object = null;

		if (AbstractDTO.class.isAssignableFrom(targetClazz)) {
			if (AbstractEntity.class.isAssignableFrom(sourceClazz)) {
				// source field value is entity bean
				AbstractEntity entity = (AbstractEntity) sourceFieldValue;
				return dozerBeanMapper.map(entity, entity.getDTOClass());
			} else {
				// source field value is NOT entity bean
				object = (Serializable) sourceFieldValue;
				return service.findEntityById(object);
			}

		} else if (AbstractEntity.class.isAssignableFrom(targetClazz)) {
			// TODO comment ! not Implemented now as I am not need It <<== NOW
			// ==>>
			if (AbstractDTO.class.isAssignableFrom(sourceClazz)) {
				// source field value is DTO
				AbstractDTO dto = (AbstractDTO) sourceFieldValue;
				object = dto.getPK();
				return service.method(dto.getEntityClass(), object);
			} else {
				AbstractDTO dto = service.findEntityById((Serializable) sourceFieldValue);
				return dozerBeanMapper.map(dto, dto.getEntityClass());
			}

		} else {

			return ((AbstractDTO) sourceFieldValue).getPK();

		}

	}

	@Override
	public void setParameter(String parameter) {
		// TODO Auto-generated method stub

	}

}
