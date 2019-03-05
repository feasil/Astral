package fr.feasil.astral.rule;

import java.util.ArrayList;
import java.util.List;

import fr.feasil.astral.theme.Theme;
import fr.feasil.astral.theme.ruleengine.ActionDispatcher;
import fr.feasil.astral.theme.ruleengine.Expression;
import fr.feasil.astral.theme.ruleengine.Rule;

/**
 * https://stackoverflow.com/questions/20763189/creating-a-simple-rule-engine-in-java
 */
public abstract class DataRules {
	
	private List<Rule> rules = new ArrayList<>();
	private ActionDispatcher dispatcher;
	
	public DataRules(ActionDispatcher dispatcher) {
		this.dispatcher = dispatcher;
	}
	
	protected void addRule(Expression expression, Object argument) {
		rules.add(new Rule.Builder()
				.withExpression(expression)
				.withDispatcher(dispatcher)
				.withArgument(argument)
				.build());
	}
	
	public boolean eval(Theme theme) {
		boolean triggered = false;
		for ( Rule r : rules )
			triggered |= r.eval(theme);
		return triggered;
	}
	
}
