package com.mindfiresolutions.WorkWithExcel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

/**
 * Creates an Excel Workbook from the given .xls file
 * It is used by {@link ExcelSpreadsheet} to create spreadsheet from the .xls file. 
 * @author Sanket
 *
 */
public class ExcelWorkbook {

	private HSSFWorkbook workbook;
	
	public ExcelWorkbook(String fileToOpen) throws FileNotFoundException,IOException {
		File file = new File(fileToOpen);
		FileInputStream fInpStream = new FileInputStream(file);
		this.workbook = new HSSFWorkbook(fInpStream);
		fInpStream.close();
	}

	public HSSFWorkbook getWorkbook() {
		return workbook;
	}
}