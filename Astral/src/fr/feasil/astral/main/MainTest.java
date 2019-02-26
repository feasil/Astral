package fr.feasil.astral.main;

import fr.feasil.astral.rule.DataLoader;
import fr.feasil.astral.theme.Theme;
import fr.feasil.astral.theme.ruleengine.expression.OperationAnd;
import fr.feasil.astral.theme.ruleengine.expression.OperationEn;
import fr.feasil.astral.theme.ruleengine.expression.OperationNot;
import fr.feasil.astral.theme.ruleengine.expression.Operation;
import fr.feasil.astral.theme.ruleengine.expression.Operations;

public class MainTest {
	
	public static void main(String[] args) {
		
		Operations operations = Operations.INSTANCE;
		// register new operations with the previously created container
		Operation ot = new OperationAnd();
		operations.registerOperation(ot, "and"); operations.registerOperation(ot, "AND");
		operations.registerOperation(ot, "et"); operations.registerOperation(ot, "ET");
		ot = new OperationEn();
		operations.registerOperation(ot, "en"); operations.registerOperation(ot, "EN");
		ot = new OperationNot();
		operations.registerOperation(ot, "not"); operations.registerOperation(ot, "NOT");
		operations.registerOperation(ot, "non"); operations.registerOperation(ot, "NON");
		
		
		DataLoader data = new DataLoader("in/DataTest.xlsx");
		
		Theme theme = new Theme("in/ThemeTest.xlsx");
		System.out.println("--------------------------");
		
		data.getRules().eval(theme);
		
		
				
		// add all rules to a single container
//		Rules rules = new Rules();
		
//		String[][] regles = {{"Lion EN Maison_8", 						"quand la maison 8 est en Lion... (1)"}, 
//							{"maison1 EN sagittaire", 					"quand la maison 1 est en Sagittaire... (2)"}, 
//							{"Jupiter EN Я ET NON Chiron en Capricorne", 	"quand Jupiter est en retrograde et Vénus n'est pas en Capricorne... (3)"}, 
//							{"Vesta en Maison3", 						"quand Vesta est en maison 3... (4)"}};
//		for ( String[] rgl : regles )
//		{
//			try {
//				rules.addRule(new Rule.Builder()
//						.withExpression(ExpressionParser.fromString(rgl[0]))
//						.withDispatcher(new ActionDispatcher() {
//							@Override
//							public void fire() {
//								System.out.println(rgl[1]);
//							}
//						})
//						.build());
//			} catch (IllegalArgumentException e) {
//				System.out.println("ERREUR = " + e.getMessage() + " // Erreur remonté par la règle : " + rgl[0] );
//			}
//		}
		
		// for test purpose define a variable binding ...
		// ... and evaluate the defined rules with the specified bindings
//		boolean triggered = rules.eval(theme);
//		System.out.println("Action triggered: " + triggered);
		
	}
	
	
}
