package com.sprSecurity.spring.jasper.template;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import com.sprSecurity.spring.jasper.ReportExporter;
import com.sprSecurity.spring.jasper.ReportType;
import com.sprSecurity.spring.jasper.dto.Employee;
import com.sprSecurity.spring.jasper.dto.EmployeeDTO;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanArrayDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

/**
 * @author Marwan
 *
 */

@Service("EmployeeReporet")
public class EmployeeReporetImpl implements EmployeeReporet {

	private static final long	serialVersionUID	= 1L;
	private JasperReport		employeeReport;

	@PostConstruct
	private void init() throws JRException {
		employeeReport = JasperCompileManager.compileReport(this.getClass().getClassLoader().getResourceAsStream("/reports/Employees.jrxml"));
	}

	@Override
	public void gernerateReport(Object obj, ReportType type) {
		try {
			EmployeeDTO dto = (EmployeeDTO) obj;
			if (dto == null) {
				dto = new EmployeeDTO();
				List<Employee> report = new ArrayList<Employee>();
				report.add(new Employee(1, "Osama1", 3001, 12.51f));
				report.add(new Employee(2, "Rady", 3010, 12.52f));
				report.add(new Employee(3, "Oraby", 3100, 12.53f));
				report.add(new Employee(4, "Mostafa", 31000, 12.54f));

				dto.setEmployeeList(report);
				dto.setSubTilte("Commission received by Employee  This report was generated at " + new Date() + " This information is confidential ");
			}

			JRBeanArrayDataSource mainDataSource = new JRBeanArrayDataSource(new Object[] { dto }, false);
			JRBeanCollectionDataSource subDataSource = new JRBeanCollectionDataSource(dto.getEmployeeList(), false);

			Map<String, Object> jasperParameter = new HashMap<String, Object>();
			jasperParameter.put("employee_list", subDataSource);

			JasperPrint jasperPrint = JasperFillManager.fillReport(employeeReport, jasperParameter, mainDataSource);
			ReportExporter.export(type, jasperPrint);
		} catch (JRException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
