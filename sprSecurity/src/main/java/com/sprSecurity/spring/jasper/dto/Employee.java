package com.sprSecurity.spring.jasper.dto;

public class Employee implements ReportDTO{
	private Integer	empNo;
	private String	name;
	private Integer	salary;
	private float	commission;

	public Employee(Integer empNo, String name, Integer salary, float commission) {
		super();
		this.empNo = empNo;
		this.name = name;
		this.salary = salary;
		this.commission = commission;
	}

	public Integer getEmpNo() {
		return empNo;
	}

	public void setEmpNo(Integer empNo) {
		this.empNo = empNo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getSalary() {
		return salary;
	}

	public void setSalary(Integer salary) {
		this.salary = salary;
	}

	public float getCommission() {
		return commission;
	}

	public void setCommission(float commission) {
		this.commission = commission;
	}
}
