package com.mindfiresolutions.WorkWithExcel;

import org.apache.poi.hssf.usermodel.HSSFSheet;

/**
 * Creates an Excel Spreadsheet from the given .xls file
 * @author Sanket
 *
 */
public class ExcelSpreadsheet {
	
	private HSSFSheet spreadsheet;

	public ExcelSpreadsheet(String fileToOpen) {
		ExcelWorkbook workbook = new ExcelWorkbook(fileToOpen);
		this.spreadsheet = workbook.getWorkbook().getSheetAt(0);
	}
	
	public HSSFSheet getSpreadsheet() {
		return spreadsheet;
	}
}