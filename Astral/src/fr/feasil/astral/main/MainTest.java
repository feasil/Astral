package fr.feasil.astral.main;

import fr.feasil.astral.rule.DataRules;
import fr.feasil.astral.rule.instance.ExcelDataRules;
import fr.feasil.astral.theme.Theme;
import fr.feasil.astral.theme.instance.ExcelTheme;
import fr.feasil.astral.theme.instance.TextTheme;
import fr.feasil.astral.theme.ruleengine.expression.OperationAnd;
import fr.feasil.astral.theme.ruleengine.expression.OperationEn;
import fr.feasil.astral.theme.ruleengine.expression.OperationNot;
import fr.feasil.astral.theme.ruleengine.ActionDispatcher;
import fr.feasil.astral.theme.ruleengine.Expression;
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
		
		
		DataRules data = new ExcelDataRules(new ActionDispatcher() {
			@Override
			public void fire(Expression expression, Object argument) {
				System.out.println(argument);
			}
		}, "in/DataTest.xlsx");
		
		Theme themeExcel = new ExcelTheme("in/ThemeTest.xlsx");
		Theme themeText = new TextTheme("Positions des planètes\r\n" + 
				"Soleil 	13°45' 		Poissons\r\n" + 
				"Lune 	21°37' 		Verseau\r\n" + 
				"Mercure 	29°33' 		Poissons\r\n" + 
				"Vénus 	3°29' 		Verseau\r\n" + 
				"Mars 	12°15' 		Taureau\r\n" + 
				"Jupiter 	22°17' 		Sagittaire\r\n" + 
				"Saturne 	18°03' 		Capricorne\r\n" + 
				"Uranus 	29°55' 		Bélier\r\n" + 
				"Neptune 	16°04' 		Poissons\r\n" + 
				"Pluton 	22°32' 		Capricorne\r\n" + 
				"Chiron 	0°47' 		Bélier\r\n" + 
				"Cérès 	10°27' 		Sagittaire\r\n" + 
				"Pallas 	28°41' 	Я 	Balance\r\n" + 
				"Junon 	8°20' 		Gémeaux\r\n" + 
				"Vesta 	15°26' 		Poissons\r\n" + 
				"Noeud Nord 	25°58' 	Я 	Cancer\r\n" + 
				"Lilith 	20°10' 		Verseau\r\n" + 
				"Fortune 	25°28' 		Cancer\r\n" + 
				"AS 	17°35' 		Lion\r\n" + 
				"MC 	3°41' 		Taureau\r\n" + 
				"Planètes en maisons*\r\n" + 
				"Soleil 	Maison 8\r\n" + 
				"Lune 	Maison 7\r\n" + 
				"Mercure 	Maison 8\r\n" + 
				"Vénus 	Maison 6\r\n" + 
				"Mars 	Maison 10\r\n" + 
				"Jupiter 	Maison 5\r\n" + 
				"Saturne 	Maison 6\r\n" + 
				"Uranus 	Maison 9\r\n" + 
				"Neptune 	Maison 8\r\n" + 
				"Pluton 	Maison 6\r\n" + 
				"Chiron 	Maison 9\r\n" + 
				"Cérès 	Maison 4\r\n" + 
				"Pallas 	Maison 3\r\n" + 
				"Junon 	Maison 10\r\n" + 
				"Vesta 	Maison 8\r\n" + 
				"Noeud Nord 	Maison 12\r\n" + 
				"Lilith 	Maison 7\r\n" + 
				"Fortune 	Maison 12\r\n" + 
				"\r\n" + 
				"* Comme il est d'usage, nous considérons qu'une planète à moins de 1 degré de la maison suivante lui appartient, et nous prenons 2 degrés pour le cas de l'AS et du MC.\r\n" + 
				"Positions des maisons\r\n" + 
				"Maison 1 	17°35' 	Lion\r\n" + 
				"Maison 2 	6°42' 	Vierge\r\n" + 
				"Maison 3 	1°21' 	Balance\r\n" + 
				"Maison 4 	3°41' 	Scorpion\r\n" + 
				"Maison 5 	12°14' 	Sagittaire\r\n" + 
				"Maison 6 	18°27' 	Capricorne\r\n" + 
				"Maison 7 	17°35' 	Verseau\r\n" + 
				"Maison 8 	6°42' 	Poissons\r\n" + 
				"Maison 9 	1°21' 	Bélier\r\n" + 
				"Maison 10 	3°41' 	Taureau\r\n" + 
				"Maison 11 	12°14' 	Gémeaux\r\n" + 
				"Maison 12 	18°27' 	Cancer\r\n" + 
				"Liste des aspects\r\n" + 
				"			Orbe 	\r\n" + 
				"Soleil 	Conjonction 	Neptune 	Orbe 	2°19'\r\n" + 
				"Saturne 	Conjonction 	Pluton 	Orbe 	4°28'\r\n" + 
				"Vénus 	Carré 	Uranus 	Orbe 	3°33'\r\n" + 
				"Jupiter 	Carré 	Neptune 	Orbe 	6°12'\r\n" + 
				"Mercure 	Carré 	Jupiter 	Orbe 	7°16'\r\n" + 
				"Uranus 	Carré 	Pluton 	Orbe 	7°23'\r\n" + 
				"Mars 	Trigone 	Saturne 	Orbe 	5°48'\r\n" + 
				"Jupiter 	Trigone 	Uranus 	Orbe 	7°38'\r\n" + 
				"Lune 	Sextile 	Jupiter 	Orbe 	0°39'\r\n" + 
				"Soleil 	Sextile 	Mars 	Orbe 	1°30'\r\n" + 
				"Saturne 	Sextile 	Neptune 	Orbe 	1°58'\r\n" + 
				"Mars 	Sextile 	Neptune 	Orbe 	3°49'\r\n" + 
				"Mercure 	Sextile 	Vénus 	Orbe 	3°55'\r\n" + 
				"Soleil 	Sextile 	Saturne 	Orbe 	4°18'\r\n" + 
				"Uranus 	SemiCarré 	Neptune 	Orbe 	1°08'\r\n" + 
				"Soleil 	SemiCarré 	Uranus 	Orbe 	1°10'\r\n" + 
				"Mercure 	Quintile 	Saturne 	Orbe 	0°29'\r\n" + 
				"Jupiter 	SemiSextile 	Pluton 	Orbe 	0°14'\r\n" + 
				"Mercure 	SemiSextile 	Uranus 	Orbe 	0°22'\r\n" + 
				"Lune 	SemiSextile 	Pluton 	Orbe 	0°54'");
		System.out.println("--------------------------");
		
		data.eval(themeExcel);
		data.eval(themeText);
		
		
				
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
