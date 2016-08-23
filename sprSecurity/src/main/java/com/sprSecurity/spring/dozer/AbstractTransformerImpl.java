package com.sprSecurity.spring.dozer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.dozer.CustomConverter;
import org.dozer.DozerBeanMapper;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import com.sprSecurity.spring.data.dao.AbstractDAO;
import com.sprSecurity.spring.dto.AbstractDTO;
import com.sprSecurity.spring.hibernate.entity.AbstractEntity;

@SuppressWarnings("rawtypes")
public abstract class AbstractTransformerImpl<Source extends AbstractDTO<?>, Target extends AbstractEntity<?>, DAO extends AbstractDAO>
		implements AbstractTransformer<Source, Target> {

	private final String	TMP_TABLE_CONVERTER	= "DozerConverter";

	private DozerBeanMapper	dozerBeanMapper;
	private Class<Source>	source;
	private Class<Target>	target;

	@PostConstruct
	private void init() throws IOException {
		dozerBeanMapper = new DozerBeanMapper();
		List<String> mappingFileUrls = new ArrayList<String>();
		Resource[] resources = new PathMatchingResourcePatternResolver().getResources("classpath*:dozer/*");
		for (Resource resource : resources) {
			mappingFileUrls.add("dozer/" + resource.getFile().getName());
		}

		dozerBeanMapper.setMappingFiles(mappingFileUrls);
		Map<String, CustomConverter> customConvertersWithId = new HashMap<String, CustomConverter>();
		customConvertersWithId.put(TMP_TABLE_CONVERTER, new DozerCustomConverter<DAO, Source>(getDAO(), dozerBeanMapper));
		dozerBeanMapper.setCustomConvertersWithId(customConvertersWithId);

	}

	public abstract DAO getDAO();

	public AbstractTransformerImpl(Class<Source> source, Class<Target> target) {
		this.source = source;
		this.target = target;

	}

	@Override
	public Target transfromToEntity(Source source) {
		return dozerBeanMapper.map(source, target);
	}

	@Override
	public Source transfromToDTO(Target target) {
		return dozerBeanMapper.map(target, source);
	}

	@Override
	public List<Source> transfromToDTO(List<Target> targets) {
		List<Source> sources = new ArrayList<Source>();
		for (Target enity : targets) {
			sources.add(transfromToDTO(enity));
		}
		return sources;
	}

	@Override
	public List<Target> transfromListToEntity(List<Source> source) {
		List<Target> targets = new ArrayList<Target>();
		for (Source dto : source) {
			targets.add(transfromToEntity(dto));
		}
		return targets;
	}

	public DozerBeanMapper getDozerBeanMapper() {
		return dozerBeanMapper;
	}

	@Override
	public Target transfromToSameType(Target source, Target target) {
		dozerBeanMapper.map(source, target);
		return target;
	}

}
