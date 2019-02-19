package fr.feasil.utils.xlsloader;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelFile {
	
	private Map<String, ExcelSheet> onglets = new HashMap<>();
	private String fileName;
	
	private ExcelFile() {
	
	}
	public static ExcelFile load(String fileName) {
		ExcelFile xlsFile = new ExcelFile();
		try
		{
			xlsFile.fileName = fileName;
			FileInputStream file = new FileInputStream(fileName);
			Workbook workbook = WorkbookFactory.create(file);
			for ( Iterator<Sheet> it = workbook.sheetIterator() ; it.hasNext() ; ) {
				Sheet sheet = it.next();
				xlsFile.onglets.put(sheet.getSheetName(), ExcelSheet.load(sheet));
			}
			workbook.close();
			file.close();
		}
		catch ( IOException e ) 
		{
			e.printStackTrace();
		}
		
		return xlsFile;
	}
	
	
	public String getFileName() {
		return fileName;
	}
	
	public Collection<ExcelSheet> getListSheets() {
		return onglets.values();
	}
	
	public ExcelSheet getSheet(String sheetName) {
		return onglets.get(sheetName);
	}
	
}
