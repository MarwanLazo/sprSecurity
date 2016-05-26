package com.sprSecurity.spring.data.dao;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.sprSecurity.spring.data.TempTableRepository;
import com.sprSecurity.spring.dozer.TempTableTransformer;
import com.sprSecurity.spring.dto.TempTableDTO;
import com.sprSecurity.spring.hibernate.entity.TempTableEB;

@Service("TempTableDAO")
public class TempTableDAOImpl extends AbstractDAOImpl<String, TempTableDTO, TempTableEB, TempTableRepository, TempTableTransformer> implements TempTableDAO {

	@Inject
	private TempTableTransformer	transformer;

	@Inject
	private TempTableRepository		repository;

	@Override
	public TempTableTransformer getTransFormer() {
		return transformer;
	}

	@Override
	public TempTableRepository getRepository() {

		return repository;
	}

}
