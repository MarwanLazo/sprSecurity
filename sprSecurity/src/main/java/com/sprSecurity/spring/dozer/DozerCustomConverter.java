package com.sprSecurity.spring.dozer;

import java.io.Serializable;

import org.apache.log4j.Logger;
import org.dozer.ConfigurableCustomConverter;

import com.sprSecurity.spring.data.dao.AbstractDAO;
import com.sprSecurity.spring.data.dao.CompositePKDTO;
import com.sprSecurity.spring.dto.AbstractDTO;
import com.sprSecurity.spring.hibernate.entity.AbstractEntity;
import com.sprSecurity.spring.hibernate.entity.CompositePKEB;

@SuppressWarnings("rawtypes")
public class DozerCustomConverter<DAO extends AbstractDAO, DTO extends AbstractDTO> implements ConfigurableCustomConverter {

	private Logger	logger	= Logger.getLogger(DozerCustomConverter.class);

	private DAO		service;

	public DozerCustomConverter(DAO service) {
		this.service = service;
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
				if (CompositePKEB.class.isAssignableFrom(entity.getPK().getClass())) {
					// entity bean PK is Composite PK
				} else {
					// entity bean PK is simple PK
					object = entity.getPK();
				}
				return service.findEntityById(object);
			} else {
				// source field value is NOT entity bean
				return service.findEntityById((Serializable) sourceFieldValue);
			}

		} else if (AbstractEntity.class.isAssignableFrom(targetClazz)) {
			// TODO not Implemented now as I am not need It <<== NOW ==>>
			if (AbstractDTO.class.isAssignableFrom(sourceClazz)) {
				// source field value is DTO
				AbstractDTO dto = (AbstractDTO) sourceFieldValue;
				if (CompositePKDTO.class.isAssignableFrom(dto.getPK().getClass())) {
					// DTO PK is Composite PK
					// -- transform CompositePKDTO to CompositePKEB --step "1"--
				} else {
					// DTO PK is simple PK
					object = dto.getPK();
				}
				// -- find Entity Bean By CompositePKEB object and Return
				// TODO -- for now let us return null as it need to create DAO
				// to get Entity Bean and return or get DTO with out
				// --step "1"-- and re-transform to Entity Bean
			}

		} else {
			return ((AbstractDTO) sourceFieldValue).getPK();
		}

		return null;
	}

	@Override
	public void setParameter(String parameter) {
		// TODO Auto-generated method stub

	}

}
