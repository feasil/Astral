package fr.feasil.astral.theme.ruleengine;

public interface ActionDispatcher {
	public void fire(Expression expression, Object argument);
}
