package com.sprSecurity.spring.hibernate.search;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.Search;
import org.hibernate.search.query.dsl.QueryBuilder;

import com.sprSecurity.spring.dozer.TempTableTransformer;
import com.sprSecurity.spring.dto.TempTableDTO;
import com.sprSecurity.spring.hibernate.entity.TempTableEB;


// TODO Comment ! Hibernate Search not completed nor tested  as it needs to re-invest on it  

public class TempTableSearchImpl implements TempTableSearch {

//	@PersistenceContext(unitName = "sprTest")
	private EntityManager					entitymanager;

	@Inject
	private TempTableTransformer	transformer;

	@SuppressWarnings("unchecked")
	@Override
	public List<TempTableDTO> getEntityByFields(Object object,String... fields) throws InterruptedException {

		FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(entitymanager);
		fullTextEntityManager.createIndexer(TempTableEB.class).startAndWait();
		 QueryBuilder qb = fullTextEntityManager.getSearchFactory().buildQueryBuilder().forEntity(TempTableEB.class).get();
		org.apache.lucene.search.Query query = qb.keyword().onFields(fields).matching(object).createQuery();
		javax.persistence.Query persistenceQuery = fullTextEntityManager.createFullTextQuery(query, TempTableEB.class);
		List<TempTableEB> ebs = persistenceQuery.getResultList();
		return transformer.transfromToDTO(ebs);

	}

}
