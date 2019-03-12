package fr.feasil.astral.theme.ruleengine.expression;

import fr.feasil.astral.data.ThemeElement;
import fr.feasil.astral.data.ThemeElementFactory;
import fr.feasil.astral.theme.Theme;
import fr.feasil.astral.theme.ruleengine.Expression;

public class ExpressionThemeElement implements Expression
{
	private ThemeElement themeElement;
	
	private ExpressionThemeElement(ThemeElement themeElement) {
		this.themeElement = themeElement;
	}
	
	public ThemeElement getThemeElement() {
		return themeElement;
	}
	
	@Override
	public boolean interpret(Theme theme) {
		return true;
	}
	
	@Override
	public String getStringValue() {
		return themeElement.getNom();
	}
	@Override
	public String getExpressionValue() {
		return themeElement.getExpression();
	}
	
	public static ExpressionThemeElement getExpressionElementTheme(String string) {
		if (string == null)
			throw new IllegalArgumentException("The provided string must not be null");
		
		ThemeElement themeElement = ThemeElementFactory.getThemeElement(string);
		if ( themeElement != null )
			return new ExpressionThemeElement(themeElement);
		
		return null;
	}
}