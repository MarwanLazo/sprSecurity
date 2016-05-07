package com.sprSecurity.spring.data;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import com.sprSecurity.spring.hibernate.entity.AbstractEntity;

@NoRepositoryBean
public interface MainRepository<Entity extends AbstractEntity<id>, id extends Serializable> extends JpaRepository<Entity, id> {
	
}
