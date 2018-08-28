package com.mindfiresolutions.WorkWithExcel;

import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 
 * Provides operations on a list of {@link Student}<br/>
 * 
 * <br/>{@link StudentOperator#sortRows(String)}
 * <br/>{@link StudentOperator#printAllRows()}
 * <br/>{@link StudentOperator#writeToFile(String)}
 * @author Sanket
 *
 */
public class StudentOperator {

	private List<Student> studentList;	

	public StudentOperator(List<Student> studentList) {
		this.studentList = studentList;
	}

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

	/**
	 * Sort {@link Student} List according to the given sort criteria
	 * @param sortCriteria
	 */
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

	/**
	 * Helper function to sort {@link Student} List by grade
	 */
	private void sortRowsByGrade() {
		Collections.sort(studentList, new SortByGrade());
	}

	/**
	 * Helper function to sort {@link Student} List by class
	 */
	private void sortRowsByClass() {
		Collections.sort(studentList, new SortByClass());
	}

	/**
	 * Helper function to sort {@link Student} List by roll
	 */
	private void sortRowsByRoll() {
		Collections.sort(studentList, new SortByRoll());
	}

	/**
	 * Helper function to sort {@link Student} List by name
	 */
	private void sortRowsByName() {
		Collections.sort(studentList, new SortByName());
	}
	
	/**
	 * Prints all elements of {@link Student} List to the output window in a table format
	 */
	public void printAllRows(List<Student> studentList) {
		System.out.println("Name\t\tRoll\t\tClass\t\tGrade\t\t");
		Iterator<Student> iter = studentList.iterator();
		while(iter.hasNext()) {
			Student stud = iter.next();
			System.out.println(stud.getName()+"\t\t"+stud.getRoll()+"\t\t"+
					stud.getStudClass()+"\t\t"+stud.getGrade());
		}
	}

	/**
	 * Writes all elements of {@link Student} List to the output file in a table format 
	 * @param fileToWrite
	 * @throws IOException
	 */
	public void writeToFile(String fileToWrite) throws IOException {
		FileWriterClass fWrite = new FileWriterClass(fileToWrite);
		fWrite.writeToFile(studentList);
		fWrite.closeBuffer();
	}
	
	/**
	 * Prints a list of all Students whose name starts with the given prefix
	 * @param prefix
	 */
	public void printFilteredRows(String prefix) {
		List<Student> filteredStudentList = studentList.stream()
				.filter(student -> student.getName().toLowerCase().startsWith(prefix.toLowerCase()))
				.collect(Collectors.toList());
		this.printAllRows(filteredStudentList);
	}

}
