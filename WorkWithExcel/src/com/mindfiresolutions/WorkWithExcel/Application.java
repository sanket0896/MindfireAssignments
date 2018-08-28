package com.mindfiresolutions.WorkWithExcel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
			fileToOpen = readStringInputFromBuffer(bufferRead,prompt);
			isPathValid = doesFileExist(fileToOpen, ".xls");
		}
		
//		get sort criteria from argument
		prompt = "Enter sort criteria";
		String sortCriteria = readStringInputFromBuffer(bufferRead,prompt);
		
//		get sort criteria from argument
		prompt = "Enter filter prefix";
		String filterPrefix = readStringInputFromBuffer(bufferRead,prompt);
		
//		get .txt file name from argument
		prompt = "Enter Path of file to write";
		isPathValid = false;
		String fileToWrite = "";
		while(!isPathValid) {
			fileToWrite = readStringInputFromBuffer(bufferRead,prompt);
			isPathValid = doesFileExist(fileToWrite, ".txt");
		}
		
//		open the excel file by passing filename as string
		ExcelRowReader rowReader = new ExcelRowReader(fileToOpen);
		
//		get list of students
		List<Student> studentList = rowReader.getStudentList();
		
//		initialise StudentOperator object
		StudentOperator studOp = new StudentOperator(studentList);
		
//		sort the rows of excel file according to given sort criteria
		studOp.sortRows(sortCriteria);
		
//		print all rows to output window
		studOp.printAllRows(studentList);
		
//		print rows filtered by prefix
		studOp.printFilteredRows(filterPrefix);
		
		//write all rows to file
		studOp.writeToFile(fileToWrite);
	}

	/**
	 * Read String from buffer with exception handling and empty input handling
	 * @param bufferRead
	 * @param prompt
	 * @return
	 */
	private static String readStringInputFromBuffer(BufferedReader bufferRead, String prompt) {
		String bufferContent;
		System.out.println(prompt);
		try {
			bufferContent = bufferRead.readLine();
		} catch (IOException e) {
			System.out.println("Error while reading input. Please try again.");
			bufferContent = readStringInputFromBuffer(bufferRead,prompt);
		}
		if (bufferContent.isEmpty()) {
			System.out.println("Input cannot be empty. Please try again.");
			bufferContent = readStringInputFromBuffer(bufferRead,prompt);
		}
		return bufferContent;
	}

	/**
	 * Check if the given file path contains a valid file of the given file extension
	 * @param filePathStr
	 * @param fileExtension
	 * @return
	 */
	private static boolean doesFileExist(String filePathStr, String fileExtension) {
		
		if (!fileExtension.startsWith(".")) {
			fileExtension = "." + fileExtension;
		}
		if (filePathStr.endsWith(fileExtension)) {
			
			Path filePath = Paths.get(filePathStr);
			if (Files.exists(filePath))
				return true;
			else
				System.out.println("File Not Found. PLease try again.");
		}
		else {
			System.out.println("Enter a valid path for .xls file");
		}
		return false;
	}
}