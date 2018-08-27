import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

public class ExcelRowReader {

	private ExcelSpreadsheet spreadsheet;
	private static HSSFRow row;
	private List<Student> studentList;
	
	private class SortByName implements Comparator<Student>{

		@Override
		public int compare(Student o1, Student o2) {
			return o1.getName().compareTo(o2.getName());
		}
	}

	private class SortByRoll implements Comparator<Student>{

		@Override
		public int compare(Student o1, Student o2) {
			return o1.getRoll() - o2.getRoll();
		}
	}

	private class SortByClass implements Comparator<Student>{

		@Override
		public int compare(Student o1, Student o2) {
			return o1.getStudClass() - o2.getStudClass();
		}
	}

	private class SortByGrade implements Comparator<Student>{

		@Override
		public int compare(Student o1, Student o2) {
			return o1.getGrade() - o2.getGrade();
		}
	}
	
	public ExcelRowReader(String fileToOpen) throws FileNotFoundException, IOException {
		this.spreadsheet = new ExcelSpreadsheet(fileToOpen);
		this.studentList = new ArrayList<Student>();
		convertSheetToList();
	}
	
//	Populates the studentList with Student objects
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
	
//	Gets details of student from each column of a row and put it into Student object.
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

//	Checks if it is the first row of the sheet
	private boolean isRowFirst() {
		if (this.spreadsheet.getSpreadsheet().getRow(0) == ExcelRowReader.row) {
			return true;
		}
		return false;
	}
	
//	Sort rows of the excel sheet according to the given sort criteria
	public void sortRows(String sortCriteria) {
		
		sortCriteria = sortCriteria.toLowerCase();
		switch(sortCriteria) {
		case "name":
			sortRowsByName();
			break;
		case "roll":
			sortRowsByRoll();
			break;
		case "class":
			sortRowsByClass();
			break;
		case "grade":
			sortRowsByGrade();
			break;
		default:
			System.out.println("Invalid Sort Criteria");
			break;
		}
				
	}

//	Helper function to sort rows of excel sheet by grade
	private void sortRowsByGrade() {
		Collections.sort(studentList, new SortByGrade());
	}

//	Helper function to sort rows of excel sheet by class
	private void sortRowsByClass() {
		Collections.sort(studentList, new SortByClass());
	}

//	Helper function to sort rows of excel sheet by roll
	private void sortRowsByRoll() {
		Collections.sort(studentList, new SortByRoll());
	}

//	Helper function to sort rows of excel sheet by name
	private void sortRowsByName() {
		Collections.sort(studentList, new SortByName());
	}
	
//	Prints all elements of studentList to the output window in a table format
	public void printAllRows() {
		System.out.println("Name\t\tRoll\t\tClass\t\tGrade\t\t");
		Iterator<Student> iter = studentList.iterator();
		while(iter.hasNext()) {
			Student stud = iter.next();
			System.out.println(stud.getName()+"\t\t"+stud.getRoll()+"\t\t"+
					stud.getStudClass()+"\t\t"+stud.getGrade());
		}
	}

//	Writes all elements of studentList to the output file in a table format 
	public void writeToFile(String fileToWrite) throws IOException {
		FileWriterClass fWrite = new FileWriterClass(fileToWrite);
		fWrite.writeToFile(studentList);
		fWrite.closeBuffer();
	}
}