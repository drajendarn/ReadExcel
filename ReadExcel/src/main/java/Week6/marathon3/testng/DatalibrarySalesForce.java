package Week6.marathon3.testng;

import java.io.IOException;

import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class DatalibrarySalesForce {
	
	public static String[][] excelIntegration(String workBookName) throws IOException {
		
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

				/*String extractedDataFromExcel  = sheet.getRow(i).getCell(j).getStringCellValue();
				data[i-1][j]=extractedDataFromExcel; // i values is (i-1) to get the index value as 0
				System.out.println(data[i-1][j]);

			}
		}*/
		
				XSSFCell cellValue = sheet.getRow(i).getCell(j);

                if (cellValue != null) {
                    // Check the cell type before extracting the value
                    switch (cellValue.getCellType()) {
                        case STRING:
                            data[i - 1][j] = cellValue.getStringCellValue();  // Extract string value
                            break;
                        case NUMERIC:
                            if (DateUtil.isCellDateFormatted(cellValue)) {
                                // Handle date formatted cells
                                data[i - 1][j] = cellValue.getDateCellValue().toString();
                            } else {
                                // Handle numeric cells
                                data[i - 1][j] = String.valueOf(cellValue.getNumericCellValue());
                            }
                            break;
                        case BOOLEAN:
                            data[i - 1][j] = String.valueOf(cellValue.getBooleanCellValue());  // Extract boolean value
                            break;
                        case FORMULA:
                            data[i - 1][j] = cellValue.getCellFormula();  // Extract formula as string
                            break;
                        default:
                            data[i - 1][j] = "";  // Handle empty or unknown cell types
                            break;
                    }
                    System.out.println("Extracted cell values:" + data[i - 1][j]);  // Print the extracted data
                } else {
                    data[i - 1][j] = "";  // If the cell is null, store an empty string
                }
            }
        }
		workbook.close();
		return data;
	}

}
