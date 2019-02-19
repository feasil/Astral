package fr.feasil.utils.xlsloader;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

public class ExcelSheet {
	private String header[];
	private Object body[][];
	private String sheetName = null;
	
	private ExcelSheet() {
	
	}
	protected static ExcelSheet load(final Sheet sheet) {
		ExcelSheet xlsSheet = new ExcelSheet();
		
		xlsSheet.sheetName = sheet.getSheetName();
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
	
	public String getSheetName() {
		return this.sheetName;
	}
	
}
