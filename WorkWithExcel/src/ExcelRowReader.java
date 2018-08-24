import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

public class ExcelRowReader {

	private ExcelSpreadsheet spreadsheet;
	private static HSSFRow row;
	private List<Student> studentList;
	
	public ExcelRowReader(String fileToOpen) throws FileNotFoundException, IOException {
		this.spreadsheet = new ExcelSpreadsheet(fileToOpen);
		this.studentList = new ArrayList<Student>();
		convertSheetToList();
	}
	
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

	private boolean isRowFirst() {
		if (this.spreadsheet.getSpreadsheet().getRow(0) == ExcelRowReader.row) {
			return true;
		}
		return false;
	}
	
	public void sortRows(String sortCriteria) {
		
		int sortCriteriaColumnIndex = -1;
		Iterator<Row> rowIterator = this.spreadsheet.getSpreadsheet().iterator();
		
		if(rowIterator.hasNext()) {
			
			row = (HSSFRow) rowIterator.next();
			Iterator<Cell> cellIterator = row.cellIterator();
			
			while(cellIterator.hasNext()) {
				Cell cell = cellIterator.next();
				if(cell.getStringCellValue() == sortCriteria) {
					sortCriteriaColumnIndex = cell.getColumnIndex();
					break;
				}
			}
		}
		
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
			break;
		}
				
	}

	private void sortRowsByGrade() {
		Collections.sort(studentList, new SortByGrade());
	}

	private void sortRowsByClass() {
		Collections.sort(studentList, new SortByClass());
	}

	private void sortRowsByRoll() {
		Collections.sort(studentList, new SortByRoll());
	}

	private void sortRowsByName() {
		Collections.sort(studentList, new SortByName());
	}
	
	public void printAllRows() {
		System.out.println("Name\t\tRoll\t\tClass\t\tGrade\t\t");
		Iterator<Student> iter = studentList.iterator();
		while(iter.hasNext()) {
			Student stud = iter.next();
			System.out.println(stud.getName()+"\t\t"+stud.getRoll()+"\t\t"+
					stud.getStudClass()+"\t\t"+stud.getGrade());
		}
	}

	public void writeToFile(String fileToWrite) throws IOException {
		FileWriterClass fWrite = new FileWriterClass(fileToWrite);
		fWrite.writeToFile(studentList);
		fWrite.closeBuffer();
	}
}

class SortByName implements Comparator<Student>{

	@Override
	public int compare(Student o1, Student o2) {
		return o1.getName().compareTo(o2.getName());
	}
	
}

class SortByRoll implements Comparator<Student>{

	@Override
	public int compare(Student o1, Student o2) {
		return o1.getRoll() - o2.getRoll();
	}
	
}

class SortByClass implements Comparator<Student>{

	@Override
	public int compare(Student o1, Student o2) {
		return o1.getStudClass() - o2.getStudClass();
	}
	
}

class SortByGrade implements Comparator<Student>{

	@Override
	public int compare(Student o1, Student o2) {
		return o1.getGrade() - o2.getGrade();
	}
	
}