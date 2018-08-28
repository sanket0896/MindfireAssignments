package com.mindfiresolutions.WorkWithExcel;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

/**
 * Entry point for Java to execute the WorkWithExcel program.
 * @author Sanket
 *
 */
public class Application {

	public static void main(String[] args) {
		
		InputStreamReader inpStreamRead = new InputStreamReader(System.in);
		BufferedReader bufferRead = new BufferedReader(inpStreamRead);
		String prompt;
		Boolean isPathValid;
		
//		get .xls file name from argument
		prompt = "Enter Path of .xls file";
		isPathValid = false;
		String fileToOpen = "";
		while(!isPathValid) {
			fileToOpen = InputValidityChecker.readStringInputFromBuffer(bufferRead,prompt);
			isPathValid = InputValidityChecker.doesFileExist(fileToOpen, ".xls");
		}
		
//		get sort criteria from argument
		prompt = "Enter sort criteria";
		String sortCriteria = InputValidityChecker.readStringInputFromBuffer(bufferRead,prompt);
		
//		get sort criteria from argument
		prompt = "Enter filter prefix";
		String filterPrefix = InputValidityChecker.readStringInputFromBuffer(bufferRead,prompt);
		
//		get .txt file name from argument
		prompt = "Enter Path of .txt file to write";
		isPathValid = false;
		String fileToWrite = "";
		while(!isPathValid) {
			fileToWrite = InputValidityChecker.readStringInputFromBuffer(bufferRead,prompt);
			isPathValid = InputValidityChecker.doesFileExist(fileToWrite, ".txt");
		}
		
//		open the excel file by passing filename as string
		ExcelRowReader rowReader = new ExcelRowReader(fileToOpen);
		
//		get list of students
		List<Student> studentList = rowReader.getStudentList();
		
//		initialise StudentOperator object
		StudentOperator studOp = new StudentOperator(studentList);
		
//		sort the rows of excel file according to given sort criteria
		studentList = studOp.sortRows(sortCriteria);
		
//		print all rows to output window
		studOp.printRows(studentList);
		
//		print rows filtered by prefix
		studOp.printFilteredRows(filterPrefix);
		
		//write all rows to file
		studOp.writeToFile(fileToWrite);
	}
}