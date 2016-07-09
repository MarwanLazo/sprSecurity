package com.sprSecurity.spring.jasper.dynamic;

import java.awt.Color;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.sprSecurity.spring.jasper.ReportExporter;
import com.sprSecurity.spring.jasper.ReportType;
import com.sprSecurity.spring.jasper.ReportUtils;
import com.sprSecurity.spring.jasper.dto.Employee;

import ar.com.fdvs.dj.domain.DynamicReport;
import ar.com.fdvs.dj.domain.Style;
import ar.com.fdvs.dj.domain.builders.ColumnBuilderException;
import ar.com.fdvs.dj.domain.builders.DynamicReportBuilder;
import ar.com.fdvs.dj.domain.constants.Font;
import ar.com.fdvs.dj.domain.constants.Page;
import ar.com.fdvs.dj.domain.entities.columns.AbstractColumn;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;

@Service("employeeReport")
public class EmployeeReportImpl extends ReportUtils implements EmployeeReport {

	@Override
	public void gernerateReport(List<Employee> reports,ReportType type) {
		System.out.print("Test App");
		System.out.print("\nTest App");
		if (reports == null || reports.isEmpty()) {

			reports = new ArrayList<Employee>();
			reports.add(new Employee(1, "Osama1", 3001, 12.51f));
			reports.add(new Employee(2, "Rady", 3010, 12.52f));
			reports.add(new Employee(3, "Oraby", 3100, 12.53f));
			reports.add(new Employee(4, "Mostafa", 31000, 12.54f));

			try {
				JasperPrint jp = getReport(reports);
				// JasperViewer jasperViewer = new JasperViewer(jp);
				
				ReportExporter.export(ReportType.PDF,jp);
				ReportExporter.export(ReportType.XLS,jp);
				ReportExporter.export(ReportType.XLSX,jp);
				ReportExporter.export(ReportType.XML,jp);
				ReportExporter.export(ReportType.HTML,jp);
				

				// jasperViewer.setVisible(true);
			} catch (ColumnBuilderException e) {
				e.printStackTrace();
			} catch (JRException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@Override
	protected DynamicReport getReport() throws ColumnBuilderException, ClassNotFoundException {
		Style headerStyle = createHeaderStyle();
		Style detailTextStyle = createDetailTextStyle();
		Style detailNumStyle = createDetailNumberStyle();

		DynamicReportBuilder report = new DynamicReportBuilder();

		report.setSubtitle("Commission received by Employee \\n This report was generated at \\n" + new Date() + "\\n This information is confidential \\n")

				.setSubtitleStyle(createSubTitleStyle());

		AbstractColumn columnEmpNo = createColumn("empNo", Integer.class, "Employee Number", 30, headerStyle, detailTextStyle);
		AbstractColumn columnName = createColumn("name", String.class, "Name", 30, headerStyle, detailTextStyle);
		AbstractColumn columnSalary = createColumn("salary", Integer.class, "Salary", 30, headerStyle, detailNumStyle);
		AbstractColumn columnCommission = createColumn("commission", Float.class, "Commission", 30, headerStyle, detailNumStyle);
		report.addColumn(columnEmpNo).addColumn(columnName).addColumn(columnSalary).addColumn(columnCommission);

		report.setTitle("Employee Report");
		report.setTitleStyle(createTitleStyle());
		report.setPageSizeAndOrientation(Page.Page_A4_Portrait());
		report.setUseFullPageWidth(true);
		report.addWatermark("Marwan APP", new Font(40, Font._FONT_GEORGIA, true), Color.GRAY, 30);
		return report.build();
	}

}
