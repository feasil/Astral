package fr.feasil.utils.xlsloader;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelSheet {
	private String header[];
	private Object body[][];
	private String lastFileName = null;
	private String lastSheetName = null;
	
	private ExcelSheet() {
	
	}
	public static ExcelSheet load(String fileName, String sheetName) {
		ExcelSheet xlsSheet = new ExcelSheet();
		try
		{
			xlsSheet.setLastFileName(fileName);
			xlsSheet.setLastSheetName(sheetName);
			FileInputStream file = new FileInputStream(fileName);
			Workbook workbook = WorkbookFactory.create(file);
			final Sheet sheet = workbook.getSheet(sheetName);
			int top = sheet.getFirstRowNum();
			int bottom = sheet.getLastRowNum();
			Row line = sheet.getRow(top);
			int start = line.getFirstCellNum();
			int end = line.getLastCellNum();    
			int length = end - start;
			while(length == 0)
			{
				top++;
				line = sheet.getRow(top);
				start = line.getFirstCellNum();
				end = line.getLastCellNum();    
				length = end - start;
			}
			int hight = bottom - top;
			xlsSheet.header =  new String[length];
			xlsSheet.body = new Object[hight][length];
			for (int i = 0; i < length; i++)
			{
				xlsSheet.header[i] = line.getCell(start + i).getStringCellValue();    
			}
			
			for (int index = 0; index < hight; index++) 
			{
				line = sheet.getRow(index + top + 1);
				for (int i = 0; i < length; i++)
				{
					Cell cellule = line.getCell(start + i);
					switch (cellule.getCellType())
					{
					case STRING : 
						xlsSheet.body[index][i] = cellule.getStringCellValue();
						break;
					case BOOLEAN : 
						xlsSheet.body[index][i] = cellule.getBooleanCellValue();
						break;
					default :
						xlsSheet.body[index][i] = cellule.getNumericCellValue();
					}
				}
			}
			workbook.close();
			file.close();
		}
		catch ( IOException e ) 
		{
			e.printStackTrace();
		}
		
		return xlsSheet;
	}
	
	
	public String[] getHeader() 
	{
		return this.header;
	}
	
	public Object[][] getBody() 
	{
		return this.body;
	}
	
	public String getLastFileName() {
		return this.lastFileName;
	}
	
	public void setLastFileName(String lastFileName) {
		this.lastFileName = lastFileName;
	}
	
	public String getLastSheetName() {
		return this.lastSheetName;
	}
	
	public void setLastSheetName(String lastSheetName) {
		this.lastSheetName = lastSheetName;
	}
}
