package com.mindfiresolutions.WorkWithExcel;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

/**
 * 
 * Writes the values of elements in Student object List to a file in table format.
 * @author Sanket
 *
 */
public class FileWriterClass {
	
	private BufferedWriter buffer;
	public static final String TAB = "\t\t";
	
	public FileWriterClass(String fileToWrite) throws IOException {
		FileWriter writer = new FileWriter(fileToWrite);
		this.buffer = new BufferedWriter(writer);
	}

	public BufferedWriter getBuffer() {
		return buffer;
	}

	/**
	 * Takes a List<Student> and write all Student objects to a file
	 * @param studentList
	 * @throws IOException
	 */
	public void writeToFile(List<Student> studentList) throws IOException {
		Iterator<Student> iter = studentList.iterator();
		while(iter.hasNext()) {
			Student stud = iter.next();
			this.buffer.write(stud.getName()+TAB+stud.getRoll()+TAB+stud.getStudClass()+
					TAB+stud.getGrade());
			this.buffer.newLine();
		}		
	}

	/**
	 * Closes the buffer
	 * @throws IOException
	 */
	public void closeBuffer() throws IOException {
		this.buffer.close();
	}
	
}