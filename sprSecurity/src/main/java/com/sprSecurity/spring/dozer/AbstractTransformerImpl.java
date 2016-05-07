package com.sprSecurity.spring.dozer;

import java.util.ArrayList;
import java.util.List;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.sprSecurity.spring.dto.AbstractDTO;
import com.sprSecurity.spring.hibernate.entity.AbstractEntity;

public abstract class AbstractTransformerImpl<Source extends AbstractDTO<?>, Target extends AbstractEntity<?>> implements AbstractTransformer<Source, Target> {
	
	@Autowired
	private Mapper		  dozerBeanMapper;
	
	private Class<Source> source;
	private Class<Target> target;
	
	public AbstractTransformerImpl(Class<Source> source, Class<Target> target) {
		this.source = source;
		this.target = target;
	}
	
	@Override
	public Target transfromToEntity (Source source) {
		return dozerBeanMapper.map(source, target);
	}
	
	@Override
	public Source transfromToDTO (Target target) {
		return dozerBeanMapper.map(target, source);
	}
	
	@Override
	public List<Source> transfromListToDTO (List<Target> targets) {
		List<Source> sources = new ArrayList<Source>();
		for (Target enity : targets) {
			sources.add(transfromToDTO(enity));
		}
		return sources;
	}
	
	@Override
	public List<Target> transfromListToEntity (List<Source> source) {
		List<Target> targets = new ArrayList<Target>();
		for (Source dto : source) {
			targets.add(transfromToEntity(dto));
		}
		return targets;
	}
	
}
