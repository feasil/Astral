package fr.feasil.astral.rule;

import java.util.ArrayList;
import java.util.List;

import fr.feasil.astral.theme.ruleengine.ActionDispatcher;
import fr.feasil.astral.theme.ruleengine.ExpressionParser;
import fr.feasil.astral.theme.ruleengine.Rule;
import fr.feasil.astral.theme.ruleengine.Rules;
import fr.feasil.utils.xlsloader.ExcelFile;
import fr.feasil.utils.xlsloader.ExcelSheet;

public class DataLoader {
	private final static String DATA_SHEET = "Data";
	
	private ExcelFile file;
	
	private Rules rules = new Rules();
	
	
	public DataLoader(String fileName) {
		this.file = ExcelFile.load(fileName);
		loadData();
	}
	
	public Rules getRules() {
		return rules;
	}
	
	
	private void loadData() {
		ExcelSheet sheet = file.getSheet(DATA_SHEET);
		List<String> headers = new ArrayList<>();
		headers.add(null);
		for ( int i = 1 ; i < sheet.getHeader().length ; i++ )
			headers.add(sheet.getHeader()[i].replaceAll(" ", ""));
		
		for ( Object[] ligne : sheet.getBody() )
		{
			String rowName = ligne[0].toString().replaceAll(" ", "");
			for ( int i = 1 ; i < ligne.length ; i++ ) {
				if ( ligne[i] != null )
				{
					String expression = rowName + " EN " + headers.get(i);
					String content = ligne[i].toString();
					rules.addRule(new Rule.Builder()
							.withExpression(ExpressionParser.fromString(expression))
							.withDispatcher(new ActionDispatcher() {
								@Override
								public void fire() {
									//TODO trouver une solution pour ça !
									System.out.println(content);
								}
							})
							.build());
				}
			}
		}
	}
}
