package fr.feasil.astral.theme.ruleengine;

import fr.feasil.astral.theme.Theme;

public interface Expression {
	public boolean interpret(final Theme theme);
	public String getStringValue();
	public String getExpressionValue();
}