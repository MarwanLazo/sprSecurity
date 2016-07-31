package com.sprSecurity.spring.jasper.dto;

import java.io.Serializable;
import java.util.List;

/**
 * @author Marwan
 *
 */
public class EmployeeDTO implements Serializable, ReportDTO {

	private static final long	serialVersionUID	= 1L;
	private String				subTilte;
	private List<Employee>		employeeList;

	public List<Employee> getEmployeeList() {
		return employeeList;
	}

	public void setEmployeeList(List<Employee> employeeList) {
		this.employeeList = employeeList;
	}

	public String getSubTilte() {
		return subTilte;
	}

	public void setSubTilte(String subTilte) {
		this.subTilte = subTilte;
	}

}
