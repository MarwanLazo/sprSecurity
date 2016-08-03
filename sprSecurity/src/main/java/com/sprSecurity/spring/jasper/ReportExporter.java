package com.sprSecurity.spring.jasper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Arrays;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.HtmlExporter;
import net.sf.jasperreports.engine.export.JRCsvExporter;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.export.OutputStreamExporterOutput;
import net.sf.jasperreports.export.SimpleCsvMetadataExporterConfiguration;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleHtmlExporterOutput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimplePdfExporterConfiguration;
import net.sf.jasperreports.export.SimpleWriterExporterOutput;
import net.sf.jasperreports.export.SimpleXlsReportConfiguration;
import net.sf.jasperreports.export.SimpleXlsxReportConfiguration;

public class ReportExporter {
	private final static String PATH = "C:/Users/marwan/Desktop/testDR/";

	public static void export(ReportType type, JasperPrint jasperPrint, String fileName) throws JRException, IOException {

		String filePath = PATH + fileName + type.getFormat();

		switch (type) {
			case PDF:
				exportToPDF(jasperPrint, filePath);
				break;

			case XLS:
				exportToXLS(jasperPrint, filePath);
				break;
			case XLSX:
				exportToXLSX(jasperPrint, filePath);
				break;
			case XML:
				exportToXML(jasperPrint, filePath, true);
				break;
			case HTML:
				exportToHTML(jasperPrint, filePath);
				break;

			case CSV:
				exportToCSV(jasperPrint, filePath);
				break;
			default:
				break;
		}

	}

	private static void exportToPDF(JasperPrint jasperPrint, String path) throws JRException, IOException {

		JRPdfExporter exporter = new JRPdfExporter();
		File outputFile = new File(path);
		File parentFile = outputFile.getParentFile();
		if (parentFile != null)
			parentFile.mkdirs();
		FileOutputStream fos = new FileOutputStream(outputFile);
		exporter.setExporterInput(SimpleExporterInput.getInstance(Arrays.asList(jasperPrint)));
		OutputStreamExporterOutput exporterOutput = new SimpleOutputStreamExporterOutput(fos);
		exporter.setExporterOutput(exporterOutput);
		SimplePdfExporterConfiguration exporterConfiguration = new SimplePdfExporterConfiguration();
		String pdfJavaScript = exporterConfiguration.getPdfJavaScript();
		if (pdfJavaScript == null)
			pdfJavaScript = "this.zoom = 100;";
		else {
			pdfJavaScript += "this.zoom = 100;";
		}
		exporterConfiguration.setPdfJavaScript(pdfJavaScript);
		exporter.setConfiguration(exporterConfiguration);
		exporter.exportReport();
		fos.close();
	}

	private static void exportToXLS(JasperPrint jasperPrint, String path) throws IOException, JRException {
		JRXlsExporter exporter = new JRXlsExporter();
		File outputFile = new File(path);
		File parentFile = outputFile.getParentFile();
		if (parentFile != null)
			parentFile.mkdirs();
		FileOutputStream fos = new FileOutputStream(outputFile);
		exporter.setExporterInput(SimpleExporterInput.getInstance(Arrays.asList(jasperPrint)));
		OutputStreamExporterOutput exporterOutput = new SimpleOutputStreamExporterOutput(fos);
		exporter.setExporterOutput(exporterOutput);
		SimpleXlsReportConfiguration configuration = new SimpleXlsReportConfiguration();
		configuration.setWhitePageBackground(Boolean.FALSE);
		configuration.setDetectCellType(Boolean.TRUE);
		configuration.setIgnoreGraphics(Boolean.FALSE);
		exporter.setConfiguration(configuration);
		exporter.exportReport();
	}

	private static void exportToXLSX(JasperPrint jasperPrint, String path) throws IOException, JRException {
		JRXlsxExporter exporter = new JRXlsxExporter();
		File outputFile = new File(path);
		File parentFile = outputFile.getParentFile();
		if (parentFile != null)
			parentFile.mkdirs();
		FileOutputStream fos = new FileOutputStream(outputFile);
		exporter.setExporterInput(SimpleExporterInput.getInstance(Arrays.asList(jasperPrint)));
		OutputStreamExporterOutput exporterOutput = new SimpleOutputStreamExporterOutput(fos);
		exporter.setExporterOutput(exporterOutput);
		SimpleXlsxReportConfiguration configuration = new SimpleXlsxReportConfiguration();
		configuration.setWhitePageBackground(Boolean.FALSE);
		configuration.setDetectCellType(Boolean.TRUE);
		configuration.setIgnoreGraphics(Boolean.FALSE);
		exporter.setConfiguration(configuration);
		exporter.exportReport();
	}

	private static void exportToXML(JasperPrint jasperPrint, String path, boolean isEmbeddingImages) throws IOException, JRException {
		JasperExportManager.exportReportToXmlFile(jasperPrint, path, isEmbeddingImages);
	}

	private static void exportToCSV(JasperPrint jasperPrint, String path) throws IOException, JRException {
		File outputFile = new File(path);
		File parentFile = outputFile.getParentFile();
		if (parentFile != null)
			parentFile.mkdirs();
		JRCsvExporter cvsExporter = new JRCsvExporter();
		cvsExporter.setExporterOutput(new SimpleWriterExporterOutput(new OutputStreamWriter(new FileOutputStream(new File(path)))));
		cvsExporter.setExporterInput(new SimpleExporterInput(jasperPrint));
		SimpleCsvMetadataExporterConfiguration configuration = new SimpleCsvMetadataExporterConfiguration();
		configuration.setFieldDelimiter(",");
		cvsExporter.setConfiguration(configuration);
		cvsExporter.exportReport();
	}

	private static void exportToHTML(JasperPrint jasperPrint, String path) throws IOException, JRException {
		HtmlExporter exporter = new HtmlExporter();
		File outputFile = new File(path);
		File parentFile = outputFile.getParentFile();
		if (parentFile != null)
			parentFile.mkdirs();
		FileOutputStream fos = new FileOutputStream(outputFile);
		exporter.setExporterInput(SimpleExporterInput.getInstance(Arrays.asList(jasperPrint)));
		SimpleHtmlExporterOutput exporterOutput = new SimpleHtmlExporterOutput(fos);
		exporter.setExporterOutput(exporterOutput);
		exporter.exportReport();
	}

}
