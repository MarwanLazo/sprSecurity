package org.apache.cxf.jaxws.service;

import javax.inject.Inject;
import javax.jws.WebService;

import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.sprSecurity.spring.data.service.TempTableService;
import com.sprSecurity.spring.dto.TempTableDTO;

@WebService(endpointInterface = "org.apache.cxf.jaxws.service.Hello", serviceName = "HelloService")
public class HelloImpl extends SpringBeanAutowiringSupport implements Hello {

	@Inject
	private TempTableService service;

	@Override
	public String insertTempTable(TempTableDTO tenp) {
		return "Saved Successfully" + tenp;
	}

	@Override
	public TempTableDTO getTempTable(String name) {
		return service.findEntityById(name);
	}

}
