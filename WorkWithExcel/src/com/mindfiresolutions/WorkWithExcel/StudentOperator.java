package com.mindfiresolutions.WorkWithExcel;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 
 * Provides operations on a list of {@link Student}<br/>
 * 
 * <br/>{@link StudentOperator#sortRows(String)}
 * <br/>{@link StudentOperator#printRows()}
 * <br/>{@link StudentOperator#writeToFile(String)}
 * @author Sanket
 *
 */
public class StudentOperator {

	private List<Student> studentList;
	private static final String TAB_SPACE = "\t\t";
	private static final String NEW_LINE = "\n\n";

	public StudentOperator(List<Student> studentList) {
		this.studentList = studentList;
	}

	private class SortByName implements Comparator<Student>{

		@Override
		public int compare(Student o1, Student o2) {
			return o1.getName().toLowerCase().compareTo(o2.getName().toLowerCase());
		}
	}	

	/**
	 * Sort {@link Student} List according to the given sort criteria
	 * @param sortCriteria
	 * @return List<Student>
	 */
	public List<Student> sortRows(String sortCriteria) {
		
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
		return this.studentList;
	}

	/**
	 * Helper function to sort {@link Student} List by grade
	 */
	private void sortRowsByGrade() {
		this.studentList = this.studentList.stream().sorted(Comparator.comparing(Student::getGrade)).collect(Collectors.toList());
	}

	/**
	 * Helper function to sort {@link Student} List by class
	 */
	private void sortRowsByClass() {
		this.studentList = this.studentList.stream().sorted(Comparator.comparing(Student::getStudClass)).collect(Collectors.toList());
	}

	/**
	 * Helper function to sort {@link Student} List by roll
	 */
	private void sortRowsByRoll() {
		this.studentList = this.studentList.stream().sorted(Comparator.comparing(Student::getRoll)).collect(Collectors.toList());
	}

	/**
	 * Helper function to sort {@link Student} List by name
	 */
	private void sortRowsByName() {
		this.studentList = this.studentList.stream().sorted(new SortByName()).collect(Collectors.toList());
	}
	
	/**
	 * Prints all elements of {@link Student} List to the output window in a table format
	 */
	public void printRows(List<Student> studentList) {
		System.out.println(NEW_LINE+"Name"+TAB_SPACE+"Roll"+TAB_SPACE+"Class"+TAB_SPACE+"Grade");
		studentList.forEach(stud -> System.out.println(stud.getName() + TAB_SPACE + stud.getRoll() + TAB_SPACE + 
				stud.getStudClass() + TAB_SPACE + stud.getGrade()));
	}

	/**
	 * Writes all elements of {@link Student} List to the output file in a table format 
	 * @param fileToWrite
	 */
	public void writeToFile(String fileToWrite) {
			FileWriterClass fWrite = new FileWriterClass(fileToWrite);
			fWrite.writeToFile(studentList);
			fWrite.closeBuffer();
	}

	/**
	 * 
	 * @param prefix
	 * @return List<Student>
	 */
	private List<Student> filterRows(String prefix) {
		List<Student> filteredStudentList = studentList.stream()
				.filter(student -> student.getName().toLowerCase().startsWith(prefix.toLowerCase()))
				.collect(Collectors.toList());
		return filteredStudentList;
	}
	
	/**
	 * Prints a list of all Students whose name starts with the given prefix
	 * @param prefix
	 */
	public void printFilteredRows(String prefix) {
		List<Student> filteredStudentList = filterRows(prefix);
		this.printRows(filteredStudentList);
	}

}
