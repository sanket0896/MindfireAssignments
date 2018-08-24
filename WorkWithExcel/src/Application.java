import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

//Entry point for Java to execute the program
public class Application {

	public static void main(String[] args) throws FileNotFoundException, IOException {
		
		//get file name from argument
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		System.out.println("Enter Path of .xls file");
		String fileToOpen = br.readLine();
		System.out.println("Enter sort criteria");
		String sortCriteria = br.readLine();
		System.out.println("Enter Path of file to write");
		String fileToWrite = br.readLine();
		
		//open the excel file by passing filename as string
		ExcelRowReader rowReader = new ExcelRowReader(fileToOpen);
		rowReader.sortRows(sortCriteria);
		rowReader.printAllRows();
		
		//write to file
		rowReader.writeToFile(fileToWrite);
	}

}
