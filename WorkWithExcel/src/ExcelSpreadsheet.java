import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFSheet;

public class ExcelSpreadsheet {
	
	private HSSFSheet spreadsheet;

	public ExcelSpreadsheet(String fileToOpen) throws FileNotFoundException, IOException {
		ExcelWorkbook workbook = new ExcelWorkbook(fileToOpen);
		this.spreadsheet = workbook.getWorkbook().getSheetAt(0);
	}
	
	public HSSFSheet getSpreadsheet() {
		return spreadsheet;
	}
}
