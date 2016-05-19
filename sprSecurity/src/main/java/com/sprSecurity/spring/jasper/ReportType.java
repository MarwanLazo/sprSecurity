package com.sprSecurity.spring.jasper;

public enum ReportType {
	PDF(".pdf"), XLS(".xls"), XLSX(".xlsx"), HTML(".html"), XML(".xml"),CSV(".csv");

	private String format;

	private ReportType(String format) {
		this.format = format;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String extension) {
		this.format = extension;
	}

}
