package com.sprSecurity.spring.dozer;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.sprSecurity.spring.data.dao.TempTableDAO;
import com.sprSecurity.spring.dto.TempTableDTO;
import com.sprSecurity.spring.hibernate.entity.TempTableEB;

@Service("tempTableTransformer")
public class TempTableTransformerImpl extends AbstractTransformerImpl<TempTableDTO, TempTableEB, TempTableDAO> implements TempTableTransformer {

	@Inject
	private TempTableDAO dao;

	public TempTableTransformerImpl() {
		super(TempTableDTO.class, TempTableEB.class);
	}

	@PostConstruct
	private void init() {

	}

	@Override
	public TempTableDAO getDAO() {
		return dao;
	}

}
