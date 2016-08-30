package org.apache.cxf.jaxws.service;

import javax.jws.WebMethod;
import javax.jws.WebService;

import com.sprSecurity.spring.dto.TempTableDTO;

@WebService
public interface Hello {
	@WebMethod
	public String insertTempTable(TempTableDTO bookVO);

	@WebMethod
	public TempTableDTO getTempTable(String title);

}
