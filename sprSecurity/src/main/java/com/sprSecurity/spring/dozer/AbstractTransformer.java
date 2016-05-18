package com.sprSecurity.spring.dozer;

import java.util.List;

import com.sprSecurity.spring.dto.AbstractDTO;
import com.sprSecurity.spring.hibernate.entity.AbstractEntity;

public interface AbstractTransformer<Source extends AbstractDTO<?>, Target extends AbstractEntity<?>> {

	

	Target transfromToEntity(Source source);

	Source transfromToDTO(Target target);

	List<Source> transfromListToDTO(List<Target> targets);

	List<Target> transfromListToEntity(List<Source> source);

}
