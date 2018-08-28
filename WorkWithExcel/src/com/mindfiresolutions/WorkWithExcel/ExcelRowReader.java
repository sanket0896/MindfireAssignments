package com.mindfiresolutions.WorkWithExcel;

import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFRow;

/**
 * Reads the rows of the given .xls file and converts each row to {@link Student} object.<br/>
 * It creates a list of {@link Student}.
 * @author Sanket
 *
 */
public class ExcelRowReader {

	private ExcelSpreadsheet spreadsheet;
	private HSSFRow row;
	private List<Student> studentList;
	
	public List<Student> getStudentList() {
		return studentList;
	}

	public ExcelRowReader(String fileToOpen) {
		this.spreadsheet = new ExcelSpreadsheet(fileToOpen);
		this.studentList = new ArrayList<Student>();
		convertSheetToList();
	}
	
	/**
	 * Populates the studentList with {@link Student} objects
	 */
	private void convertSheetToList() {
		this.spreadsheet.getSpreadsheet().forEach(row -> {
			this.row = (HSSFRow) row;
			if(!isRowFirst(this.row)) {
				Student student = convertRowToStudentObject();
				this.studentList.add(student);
			}
		});
	}
	
	/**
	 * Gets details from each cell of a row and put it into {@link Student} object.
	 * @return Student
	 */
	private Student convertRowToStudentObject() {
		Student student = new Student();
		this.row.forEach(cell -> {
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
		});
		return student;
	}

	/**
	 * Checks if it is the first row of the sheet
	 * @return boolean
	 */
	private boolean isRowFirst(HSSFRow row) {
		if (this.spreadsheet.getSpreadsheet().getRow(0) == row) {
			return true;
		}
		return false;
	}

}