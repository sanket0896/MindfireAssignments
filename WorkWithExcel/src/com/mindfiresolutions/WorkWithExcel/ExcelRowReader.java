package com.mindfiresolutions.WorkWithExcel;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

/**
 * Reads the rows of the given .xls file and converts each row to {@link Student} object.<br/>
 * It creates a list of {@link Student}.
 * @author Sanket
 *
 */
public class ExcelRowReader {

	private ExcelSpreadsheet spreadsheet;
	private static HSSFRow row;
	private List<Student> studentList;
	
	public List<Student> getStudentList() {
		return studentList;
	}

	public ExcelRowReader(String fileToOpen) throws FileNotFoundException, IOException {
		this.spreadsheet = new ExcelSpreadsheet(fileToOpen);
		this.studentList = new ArrayList<Student>();
		convertSheetToList();
	}
	
	/**
	 * Populates the studentList with {@link Student} objects
	 */
	private void convertSheetToList() {
		Iterator<Row> rowIterator = this.spreadsheet.getSpreadsheet().iterator();
		while (rowIterator.hasNext()) {
			row = (HSSFRow) rowIterator.next();
			if(isRowFirst())
				continue;
			Student student = convertRowToStudentObject();
			this.studentList.add(student);
		}
	}
	
	/**
	 * Gets details from each cell of a row and put it into {@link Student} object.
	 * @return
	 */
	private Student convertRowToStudentObject() {
		Iterator<Cell> cellIteartor = row.cellIterator();
		Student student = new Student();
		while(cellIteartor.hasNext()) {
			Cell cell = cellIteartor.next();
			switch(cell.getColumnIndex()) {
			case 0:
				student.setName(cell.getStringCellValue());
				break;
			case 1:
				student.setRoll((int)cell.getNumericCellValue());
				break;
			case 2:
				student.setStudClass((int)cell.getNumericCellValue());
				break;
			case 3:
				student.setGrade((int)cell.getNumericCellValue());
				break;
			default:
				break;
			}
		}
		return student;
	}

	/**
	 * Checks if it is the first row of the sheet
	 * @return boolean
	 */
	private boolean isRowFirst() {
		if (this.spreadsheet.getSpreadsheet().getRow(0) == ExcelRowReader.row) {
			return true;
		}
		return false;
	}

}