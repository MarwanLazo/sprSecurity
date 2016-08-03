package com.sprSecurity.spring.jasper.dynamic;

import java.io.Serializable;

import com.sprSecurity.spring.jasper.ReportType;
import com.sprSecurity.spring.jasper.dto.ReportDTO;

public interface Report<DTO extends ReportDTO> extends Serializable {
	void gernerateReport(DTO reports, ReportType type);

}
