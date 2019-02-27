package fr.feasil.astral.theme.ruleengine;

import java.util.ArrayList;
import java.util.List;
import fr.feasil.astral.theme.Theme;

/**
 * https://stackoverflow.com/questions/20763189/creating-a-simple-rule-engine-in-java
 */
public class Rules {
	private List<Rule> rules = new ArrayList<>();

	public void addRule(Rule rule) {
		rules.add(rule);
	}

	public boolean eval(Theme theme) {
		boolean triggered = false;
		for ( Rule r : rules )
			triggered |= r.eval(theme);
		return triggered;
	}
	
	
}
