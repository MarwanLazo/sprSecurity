package com.sprSecurity.spring.jasper;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRCsvExporter;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.export.OutputStreamExporterOutput;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimpleWriterExporterOutput;

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

			case CSV:

				JRCsvExporter cvsExporter = new JRCsvExporter();
				try {

					/**
					 * net.sf.jasperreports.export.csv.exclude.origin.keep.first
					 * .band.1=pageHeader
					 * net.sf.jasperreports.export.csv.exclude.origin.band.2=
					 * pageFooter
					 * net.sf.jasperreports.export.csv.exclude.origin.keep.first
					 * .band.3=columnHeader
					 * net.sf.jasperreports.export.csv.exclude.origin.keep.first
					 * .report.3=*
					 * net.sf.jasperreports.export.csv.exclude.origin.band.4=
					 * columnFooter
					 */

					jasperPrint.getPropertiesMap().setProperty("net.sf.jasperreports.export.csv.exclude.origin.keep.first.band.1", "pageHeader");
					jasperPrint.getPropertiesMap().setProperty("net.sf.jasperreports.export.csv.exclude.origin.band.2", "pageFooter");
					jasperPrint.getPropertiesMap().setProperty("net.sf.jasperreports.export.csv.exclude.origin.keep.first.band.3", "columnHeader");
					jasperPrint.getPropertiesMap().setProperty("net.sf.jasperreports.export.csv.exclude.origin.keep.first.band.3", "*");
					jasperPrint.getPropertiesMap().setProperty("net.sf.jasperreports.export.csv.exclude.origin.band.4", "columnFooter");

					cvsExporter.setExporterOutput(new SimpleWriterExporterOutput(new OutputStreamWriter(new FileOutputStream(new File(path + type.getFormat())))));
					cvsExporter.setExporterInput(new SimpleExporterInput(jasperPrint));
					cvsExporter.exportReport();
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}

				break;
			default:
				break;
		}

	}

}
