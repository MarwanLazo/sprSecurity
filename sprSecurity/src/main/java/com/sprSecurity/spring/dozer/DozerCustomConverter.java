package com.sprSecurity.spring.dozer;

import org.apache.log4j.Logger;
import org.dozer.CustomConverter;

import com.sprSecurity.spring.data.dao.AbstractDAO;
import com.sprSecurity.spring.dto.AbstractDTO;

@SuppressWarnings("rawtypes")
public class DozerCustomConverter<DAO extends AbstractDAO, DTO extends AbstractDTO> implements CustomConverter {

	private Logger	logger	= Logger.getLogger(DozerCustomConverter.class);

	private DAO		service;

	public DozerCustomConverter(DAO service) {
		this.service = service;
	}

	@Override
	public Object convert(Object targetFieldValue, Object sourceFieldValue, Class<?> targetClazz, Class<?> sourceClazz) {
		logger.info("Temp Table Converter sart with paramter: Marwan");
		logger.info("targetFieldValue:" + targetFieldValue);
		logger.info("sourceFieldValue:" + sourceFieldValue);
		logger.info("targetClazz:" + targetClazz);
		logger.info("sourceClazz:" + sourceClazz);

		@SuppressWarnings("unchecked")
		DTO dto = (DTO) service.findEntityById((String) sourceFieldValue);
		logger.info("sourceFieldValue ==>>" + sourceFieldValue);
		if (dto != null)
			logger.info(dto.getClass().getName() + "==>>" + dto);

		return dto;
	}

}
