package com.sprSecurity.spring.jasper;

import java.io.File;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.export.OutputStreamExporterOutput;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;

public class ReportExporter {
	private final static String	path	= "C:/Users/marwan/Desktop/testDR/sample_report";
	private JasperPrint			jasperPrint;

	public ReportExporter(JasperPrint jasperPrint) throws JRException {
		this.jasperPrint = jasperPrint;
	}

	public void export(ReportType type) throws JRException {

		switch (type) {
			case PDF:
				JasperExportManager.exportReportToPdfFile(jasperPrint, path + type.getFormat());
				break;

			case XLS:
				JRXlsExporter exporter = new JRXlsExporter();
				SimpleExporterInput input = new SimpleExporterInput(jasperPrint);
				exporter.setExporterInput(input);
				OutputStreamExporterOutput exporterOutputXLS = new SimpleOutputStreamExporterOutput(new File(path + type.getFormat()));
				exporter.setExporterOutput(exporterOutputXLS);
				exporter.exportReport();
				break;
			case XLSX:
				JRXlsxExporter xlsxExporter = new JRXlsxExporter();
				SimpleExporterInput exporterInput = new SimpleExporterInput(jasperPrint);
				xlsxExporter.setExporterInput(exporterInput);
				OutputStreamExporterOutput exporterOutputXLSX = new SimpleOutputStreamExporterOutput(new File(path + type.getFormat()));
				xlsxExporter.setExporterOutput(exporterOutputXLSX);
				xlsxExporter.exportReport();
				break;
			case XML:
				JasperExportManager.exportReportToPdfFile(jasperPrint, path + type.getFormat());
				break;
			case HTML:
				JasperExportManager.exportReportToPdfFile(jasperPrint, path + type.getFormat());
				break;
			default:
				break;
		}

	}

}
