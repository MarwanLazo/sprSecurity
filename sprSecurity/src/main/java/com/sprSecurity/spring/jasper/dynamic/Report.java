package com.sprSecurity.spring.jasper.dynamic;

import java.util.List;

import com.sprSecurity.spring.jasper.dto.ReportDTO;

public interface Report<DTO extends ReportDTO> {
	void gernerateReport(List<DTO> reports);
}
