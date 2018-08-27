import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

//Entry point for Java to execute the program
public class Application {

	public static void main(String[] args) throws FileNotFoundException, IOException {
		
//		get file name from argument
		InputStreamReader inpStreamRead = new InputStreamReader(System.in);
		BufferedReader bufferRead = new BufferedReader(inpStreamRead);
		System.out.println("Enter Path of .xls file");
		String fileToOpen = bufferRead.readLine();
		System.out.println("Enter sort criteria");
		String sortCriteria = bufferRead.readLine();
		System.out.println("Enter Path of file to write");
		String fileToWrite = bufferRead.readLine();
		
//		open the excel file by passing filename as string
		ExcelRowReader rowReader = new ExcelRowReader(fileToOpen);
		
//		sort the rows of excel file according to given sort criteria
		rowReader.sortRows(sortCriteria);
		
//		print all rows to output window
		rowReader.printAllRows();
		
		//write all rows to file
		rowReader.writeToFile(fileToWrite);
	}

}