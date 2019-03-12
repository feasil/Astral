package fr.feasil.astral.rule;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
	
	private List<DataRulesListener> listeners = new ArrayList<>();
	
	public DataRules(ActionDispatcher dispatcher) {
		this.dispatcher = dispatcher;
	}
	
	protected Rule addRule(Expression expression, Object argument) {
		Rule rule = new Rule.Builder()
				.withExpression(expression)
				.withDispatcher(dispatcher)
				.withArgument(argument)
				.build();
		rules.add(rule);
		return rule;
	}
	
	protected void clearRules() {
		rules.clear();
	}
	protected void fireReloaded() {
		for ( DataRulesListener l : listeners )
			l.rulesReloaded();
	}
	
	public boolean eval(Theme theme) {
		boolean triggered = false;
		for ( Rule r : rules )
			triggered |= r.eval(theme);
		return triggered;
	}
	
	public void addDataRulesListener(DataRulesListener listener) {
		listeners.add(listener);
	}
	public void removeDataRulesListener(DataRulesListener listener) {
		listeners.remove(listener);
	}
	
//	public abstract void reload();
	public abstract DataRules newInstance(ActionDispatcher dispatcher);
	public abstract void insertRules(Map<Expression, Object> newRules);
	public abstract void insertRule(Expression expression, Object argument);
	
}
