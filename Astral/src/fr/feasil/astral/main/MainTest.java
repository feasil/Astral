package fr.feasil.astral.main;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.jeasy.rules.api.Facts;
import org.jeasy.rules.api.Rule;
import org.jeasy.rules.api.Rules;
import org.jeasy.rules.api.RulesEngine;
import org.jeasy.rules.core.DefaultRulesEngine;
import org.jeasy.rules.core.RuleBuilder;
import org.jeasy.rules.mvel.MVELRule;

import fr.feasil.astral.data.Signe;

public class MainTest {
	
	public static void main(String[] args) {
		MainTest m = new MainTest();
		m.load("in/test.xlsx", "Feuil1");
		System.out.println(m.getBody()[10][2]);
		
		
//		Rule weatherRule = new RuleBuilder()
//		        .name("rule1")
//		        .description("règle n°1")
//		        .when(facts -> facts.get("rain").equals(true) 
//		        		&& facts.get("umbrella") != null && facts.get("umbrella").equals(false) 
//		        		&& facts.get("day") != null )
//		        .then(facts -> System.out.println("On est " + facts.get("day") + " et il pleut, prend ton parapluie !"))
//		        .build();
		Rule weatherRule = new MVELRule()
		        .name("rule1")
		        .description("règle n°1")
		        //.when("rain == true && day != null")
		        //.then("System.out.println(\"On est \" + day + \" et il pleut\" + (umbrella?\"\":\", prend ton parapluie\") + \" !\");");
		        .when("rain == true && day != null")
		        .then("System.out.println(\"coucou\");");
		
		// define facts
        Facts facts = new Facts();
        facts.put("signe", Signe.LION);
        facts.put("maison", false);
        facts.put("day", "dimanche");

        // define rules
        //Rule weatherRule = ...
        Rules rules = new Rules();
        rules.register(weatherRule);

        // fire rules on known facts
        RulesEngine rulesEngine = new DefaultRulesEngine();
        rulesEngine.fire(rules, facts);
		
	}
	
	
	private String header[];
    private Object body[][];
    private String lastFileName = null;
    private String lastSheetName = null;
	
	private void load(String fileName, String sheetName) {
		 try
	        {
	            this.setLastFileName(fileName);
	            this.setLastSheetName(sheetName);
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
	            this.header =  new String[length];
	            this.body = new Object[hight][length];
	            for (int i = 0; i < length; i++)
	            {
	                header[i] = line.getCell(start + i).getStringCellValue();    
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
	                            this.body[index][i] = cellule.getStringCellValue();
	                            break;
	                        case BOOLEAN : 
	                            this.body[index][i] = cellule.getBooleanCellValue();
	                            break;
	                        default :
	                            this.body[index][i] = cellule.getNumericCellValue();
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

    private void setLastFileName(String lastFileName) {
        this.lastFileName = lastFileName;
    }

    public String getLastSheetName() {
        return this.lastSheetName;
    }

    private void setLastSheetName(String lastSheetName) {
        this.lastSheetName = lastSheetName;
    }
}
