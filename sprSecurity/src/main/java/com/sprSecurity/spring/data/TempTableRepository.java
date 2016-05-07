package com.sprSecurity.spring.data;

import org.springframework.stereotype.Repository;

import com.sprSecurity.spring.hibernate.entity.TempTableEB;

@Repository(value = "tempTableRepository")
public interface TempTableRepository extends MainRepository<TempTableEB, String>{
	
}
