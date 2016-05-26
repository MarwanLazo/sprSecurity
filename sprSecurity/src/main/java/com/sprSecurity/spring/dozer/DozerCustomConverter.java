package com.sprSecurity.spring.dozer;

import java.io.Serializable;

import org.apache.log4j.Logger;
import org.dozer.ConfigurableCustomConverter;
import org.dozer.DozerBeanMapper;

import com.sprSecurity.spring.data.dao.AbstractDAO;
import com.sprSecurity.spring.dto.AbstractDTO;
import com.sprSecurity.spring.dto.CompositePKDTO;
import com.sprSecurity.spring.hibernate.entity.AbstractEntity;
import com.sprSecurity.spring.hibernate.entity.CompositePKEB;

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
		logger.info("Temp Table Converter sart with paramter: Marwan");
		logger.info("targetFieldValue:" + targetFieldValue);
		logger.info("sourceFieldValue:" + sourceFieldValue);
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
				return service.findEntityById(entity.getPK());
			} else {
				// source field value is NOT entity bean
				object = (Serializable) sourceFieldValue;
			}
			return service.findEntityById(object);

		} else if (AbstractEntity.class.isAssignableFrom(targetClazz)) {
			// TODO comment ! not Implemented now as I am not need It <<== NOW
			// ==>>
			if (AbstractDTO.class.isAssignableFrom(sourceClazz)) {
				// source field value is DTO
				AbstractDTO dto = (AbstractDTO) sourceFieldValue;
				object = dto.getPK();

			} else {
				object = (Serializable) sourceFieldValue;
			}

			AbstractDTO dto = service.findEntityById(object);

			return dozerBeanMapper.map(dto, dto.getEntityClass());
		} else {
			// if
			// (CompositePKEB.class.getClass().isAssignableFrom(((AbstractDTO)
			// sourceFieldValue).getPK().getClass())) {
			// // TODO comment ! not implemented yet
			// } else if
			// (CompositePKDTO.class.getClass().isAssignableFrom(((AbstractDTO)
			// sourceFieldValue).getPK().getClass())) {
			// // TODO comment ! not implemented yet
			// } else {
			return ((AbstractDTO) sourceFieldValue).getPK();
			// }

		}

		// return null;
	}

	@Override
	public void setParameter(String parameter) {
		// TODO Auto-generated method stub

	}

}
