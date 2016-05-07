package com.sprSecurity.spring.dozer;

import org.springframework.stereotype.Service;

import com.sprSecurity.spring.dto.TempTableDTO;
import com.sprSecurity.spring.hibernate.entity.TempTableEB;

@Service("tempTableTransformer")
public class TempTableTransformerImpl extends AbstractTransformerImpl<TempTableDTO, TempTableEB> implements TempTableTransformer {
	
	public TempTableTransformerImpl() {
		super(TempTableDTO.class, TempTableEB.class);
	}
	
}
