package com.sprSecurity.spring.jasper;

import java.awt.Color;
import java.util.List;

import ar.com.fdvs.dj.core.DynamicJasperHelper;
import ar.com.fdvs.dj.core.layout.ClassicLayoutManager;
import ar.com.fdvs.dj.domain.DynamicReport;
import ar.com.fdvs.dj.domain.Style;
import ar.com.fdvs.dj.domain.builders.ColumnBuilder;
import ar.com.fdvs.dj.domain.builders.ColumnBuilderException;
import ar.com.fdvs.dj.domain.builders.StyleBuilder;
import ar.com.fdvs.dj.domain.constants.Border;
import ar.com.fdvs.dj.domain.constants.Font;
import ar.com.fdvs.dj.domain.constants.HorizontalAlign;
import ar.com.fdvs.dj.domain.constants.Stretching;
import ar.com.fdvs.dj.domain.constants.Transparency;
import ar.com.fdvs.dj.domain.constants.VerticalAlign;
import ar.com.fdvs.dj.domain.entities.columns.AbstractColumn;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

public abstract class ReportUtils {

	public JasperPrint getReport(List<?> list) throws ColumnBuilderException, JRException, ClassNotFoundException {
		DynamicReport dynaReport = getReport();
		JasperPrint jp = DynamicJasperHelper.generateJasperPrint(dynaReport, new ClassicLayoutManager(), new JRBeanCollectionDataSource(list));
		return jp;
	}

	protected abstract DynamicReport getReport() throws ColumnBuilderException, ClassNotFoundException;

	protected Style createSubTitleStyle() {
		StyleBuilder subTitleStyle = new StyleBuilder(true);
		subTitleStyle.setStretchWithOverflow(true);

		subTitleStyle.setVerticalAlign(VerticalAlign.TOP);
		subTitleStyle.setHorizontalAlign(HorizontalAlign.LEFT);
		subTitleStyle.setFont(new Font(Font.MEDIUM, Font._FONT_GEORGIA, true));
		subTitleStyle.setTextColor(Color.DARK_GRAY);
		Style subStyle = subTitleStyle.build();
		subStyle.setStreching(Stretching.NO_STRETCH);
		return subStyle;
	}

	protected Style createTitleStyle() {
		StyleBuilder titleStyle = new StyleBuilder(true);
		titleStyle.setHorizontalAlign(HorizontalAlign.LEFT);
		titleStyle.setFont(new Font(20, Font._FONT_GEORGIA, true));
		titleStyle.setTextColor(Color.BLACK);
		return titleStyle.build();
	}

	protected Style createHeaderStyle() {
		StyleBuilder sb = new StyleBuilder(true);
		sb.setFont(Font.VERDANA_MEDIUM_BOLD);
		sb.setBorder(Border.THIN());
		sb.setBorderBottom(Border.PEN_2_POINT());
		sb.setBorderColor(Color.BLACK);
		sb.setBackgroundColor(Color.GRAY);
		sb.setTextColor(Color.BLACK);
		sb.setHorizontalAlign(HorizontalAlign.CENTER);
		sb.setVerticalAlign(VerticalAlign.MIDDLE);
		sb.setTransparency(Transparency.OPAQUE);
		return sb.build();
	}

	protected Style createDetailTextStyle() {
		StyleBuilder sb = new StyleBuilder(true);
		sb.setFont(Font.VERDANA_MEDIUM);
		sb.setBorder(Border.THIN());
		sb.setBorderColor(Color.BLACK);
		sb.setTextColor(Color.BLACK);
		sb.setHorizontalAlign(HorizontalAlign.LEFT);
		sb.setVerticalAlign(VerticalAlign.MIDDLE);
		sb.setPaddingLeft(5);
		return sb.build();
	}

	protected Style createDetailNumberStyle() {
		StyleBuilder sb = new StyleBuilder(true);
		sb.setFont(Font.VERDANA_MEDIUM);
		sb.setBorder(Border.THIN());
		sb.setBorderColor(Color.BLACK);
		sb.setTextColor(Color.BLACK);
		sb.setHorizontalAlign(HorizontalAlign.RIGHT);
		sb.setVerticalAlign(VerticalAlign.MIDDLE);
		sb.setPaddingRight(5);
		return sb.build();
	}

	protected AbstractColumn createColumn(String property, Class<?> type, String title, int width, Style headerStyle, Style detailStyle) throws ColumnBuilderException {
		AbstractColumn columnState = ColumnBuilder.getNew().setColumnProperty(property, type.getName()).setTitle(title).setWidth(Integer.valueOf(width)).setStyle(detailStyle)
				.setHeaderStyle(headerStyle).build();
		return columnState;
	}

}
