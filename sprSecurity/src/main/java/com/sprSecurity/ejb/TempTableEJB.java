package com.sprSecurity.ejb;

import javax.ejb.Local;
import javax.ejb.Remote;

@Remote
@Local
public interface TempTableEJB {
	void get();
}