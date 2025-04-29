package week5.day1.readexcel;

import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Datalibrary {
	
	public static String[][] excelIntegration(String workBookName) throws IOException {
		System.out.println("test");
       //File fileName=new File("./Data/Datasheet.xlsx");
		XSSFWorkbook workbook = new XSSFWorkbook("./Data/"+workBookName+".xlsx");
		XSSFSheet sheet = workbook.getSheetAt(1);
		XSSFRow row = sheet.getRow(0);
		XSSFCell cell = row.getCell(0);
		int lastrow = sheet.getLastRowNum();

		int lastcol = row.getLastCellNum();
		
		String[][] data=new String[lastrow][lastcol];

		for (int i = 1; i <= lastrow; i++) // i starts with 1 to ignore the header row
		{
			for (int j = 0; j < lastcol; j++) {

				String extractedDataFromExcel  = sheet.getRow(i).getCell(j).getStringCellValue();
				data[i-1][j]=extractedDataFromExcel; // i values is (i-1) to get the index value as 0
				System.out.println(data[i-1][j]);

			}
		}
		workbook.close();
		return data;
	}

}
