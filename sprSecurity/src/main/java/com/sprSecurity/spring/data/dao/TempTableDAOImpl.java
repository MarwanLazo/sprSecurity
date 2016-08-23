package com.sprSecurity.spring.data.dao;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.mysema.query.types.Predicate;
import com.sprSecurity.spring.data.TempTableRepository;
import com.sprSecurity.spring.dozer.TempTableTransformer;
import com.sprSecurity.spring.dto.TempTableDTO;
import com.sprSecurity.spring.hibernate.entity.QTempTableEB;
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

	public List<TempTableDTO> geTempTable(String tempEmail) {
		Predicate predicate = QTempTableEB.tempTableEB.tempEmail.like(tempEmail);
		List<TempTableDTO> output = new ArrayList<TempTableDTO>();
		Iterable<TempTableEB> data = repository.findAll(predicate);
		for (TempTableEB tempTable : data) {
			output.add(transformer.transfromToDTO(tempTable));
		}
		return output;
	}

}
