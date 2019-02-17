package fr.feasil.astral.main;

import org.jeasy.rules.api.Facts;
import org.jeasy.rules.api.Rule;
import org.jeasy.rules.api.Rules;
import org.jeasy.rules.api.RulesEngine;
import org.jeasy.rules.core.DefaultRulesEngine;
import org.jeasy.rules.mvel.MVELRule;

import fr.feasil.astral.data.Signe;
import fr.feasil.astral.theme.Theme;
import fr.feasil.astral.theme.ThemePointFixe;

public class MainTest {
	
	public static void main(String[] args) {
		//MainTest m = new MainTest();
		
		Theme theme = new Theme("in/test.xlsx");
		for ( ThemePointFixe tpf : theme.getListePointFixe() )
			System.out.println(tpf.getPointFixe() + "   " + tpf.getPosition() + "   " + tpf.isRetrograde() + "   " + tpf.getSigne() + "      //   " + tpf.getMaison());
		
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
        facts.put("rain", true);
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
	
	
}
