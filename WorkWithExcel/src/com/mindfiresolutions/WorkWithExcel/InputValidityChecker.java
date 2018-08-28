package com.mindfiresolutions.WorkWithExcel;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Checks the validity of input.
 * Contains the following functions: <br/>
 * 
 * <br/>{@link InputValidityChecker#readStringInputFromBuffer(BufferedReader, String)}
 * <br/>{@link InputValidityChecker#doesFileExist(String, String)}
 * @author Sanket
 *
 */
public class InputValidityChecker {
	
	/**
	 * Read String from buffer with exception handling and empty input handling
	 * @param bufferRead
	 * @param prompt
	 * @return String
	 */
	public static String readStringInputFromBuffer(BufferedReader bufferRead, String prompt) {
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
	 * @return boolean
	 */
	public static boolean doesFileExist(String filePathStr, String fileExtension) {
		
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
			System.out.println("Invalid Path. Please try again.");
		}
		return false;
	}
}
