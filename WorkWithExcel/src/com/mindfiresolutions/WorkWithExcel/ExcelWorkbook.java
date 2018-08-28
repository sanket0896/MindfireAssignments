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
	
	public ExcelWorkbook(String fileToOpen){
		File file = new File(fileToOpen);
		try {
			FileInputStream fInpStream = new FileInputStream(file);
			this.workbook = new HSSFWorkbook(fInpStream);
			fInpStream.close();
		} catch (FileNotFoundException e) {
			System.out.println("Error: "+fileToOpen+" was not found.");
		} catch (IOException e) {
			System.out.println("Error: There was a problem opening the file.");
		}
	}

	public HSSFWorkbook getWorkbook() {
		return workbook;
	}
}