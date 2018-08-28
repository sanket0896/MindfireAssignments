package com.mindfiresolutions.WorkWithExcel;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * 
 * Writes the values of elements in Student object List to a file in table format.
 * @author Sanket
 *
 */
public class FileWriterClass {
	
	private BufferedWriter buffer;
	private static final String TAB_SPACE = "\t\t";
	
	public FileWriterClass(String fileToWrite) {
		FileWriter writer = null;
		try {
			writer = new FileWriter(fileToWrite);
		} catch (IOException e) {
			System.out.println("Error creating FileWriter instance.");
		}
		this.buffer = new BufferedWriter(writer);
	}

	public BufferedWriter getBuffer() {
		return buffer;
	}

	/**
	 * Takes a List<Student> and write all Student objects to a file
	 * @param studentList
	 */
	public void writeToFile(List<Student> studentList) {
		studentList.forEach(stud -> {
			try {
				this.buffer.write(stud.getName()+TAB_SPACE+stud.getRoll()+TAB_SPACE+stud.getStudClass()+
						TAB_SPACE+stud.getGrade());
				this.buffer.newLine();
			} catch (IOException e) {
				System.out.println("Error writing to file.");
			}
		});	
	}

	/**
	 * Closes the buffer
	 */
	public void closeBuffer() {
		try {
			this.buffer.close();
		} catch (IOException e) {
			System.out.println("Error in closing the buffer.");
		}
	}
	
}