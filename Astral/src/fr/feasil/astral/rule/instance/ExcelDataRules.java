package fr.feasil.astral.rule.instance;

import java.util.ArrayList;
import java.util.List;

import fr.feasil.astral.rule.DataRules;
import fr.feasil.astral.theme.ruleengine.ActionDispatcher;
import fr.feasil.astral.theme.ruleengine.ExpressionParser;
import fr.feasil.astral.theme.ruleengine.expression.Operation;
import fr.feasil.astral.theme.ruleengine.expression.Operations;
import fr.feasil.utils.xlsloader.ExcelFile;
import fr.feasil.utils.xlsloader.ExcelSheet;

public class ExcelDataRules extends DataRules {
	
	private ExcelFile file;
	
	public ExcelDataRules(ActionDispatcher dispatcher, String fileName) {
		super(dispatcher);
		this.file = ExcelFile.load(fileName);
		loadData();
	}
	
	
	private void loadData() {
		for (ExcelSheet sheet : file.getListSheets() ) {
			Operation op = null;
			if (  sheet.getHeader().length > 0 && sheet.getHeader()[0] != null )
				op = Operations.INSTANCE.getOperation(sheet.getHeader()[0]);
			if ( op == null)
				op = Operations.INSTANCE.getOperation("EN");
			String operationSymbol = " " + op.getSymbol() + " ";
			
			List<String> headers = new ArrayList<>();
			headers.add(null);
			for ( int i = 1 ; i < sheet.getHeader().length ; i++ )
				headers.add(sheet.getHeader()[i].replaceAll(" ", ""));
			
			for ( Object[] ligne : sheet.getBody() )
			{
				String rowName = ligne[0].toString().replaceAll(" ", "");
				for ( int i = 1 ; i < ligne.length ; i++ )
					if ( ligne[i] != null )
						addRule(ExpressionParser.fromString(rowName + operationSymbol + headers.get(i)), ligne[i].toString());
			}
		}
	}
}
