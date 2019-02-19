package fr.feasil.astral.theme.ruleengine.expression;

import fr.feasil.astral.data.ThemeElement;
import fr.feasil.astral.data.ThemeElementFactory;
import fr.feasil.astral.theme.Theme;
import fr.feasil.astral.theme.ruleengine.Expression;

public class ExpressionThemeElement implements Expression
{
	private String name;
	private ThemeElement themeElement;
	
	private ExpressionThemeElement(String name, ThemeElement themeElement)
	{
		this.name = name;
		this.themeElement = themeElement;;
	}
	
	public String getName()
	{
		return this.name;
	}
	
	public ThemeElement getThemeElement() {
		return themeElement;
	}
	
	@Override
	public boolean interpret(Theme theme)
	{
		return true;
	}
	
	
	public static ExpressionThemeElement getExpressionElementTheme(String string) {
		if (string == null)
			throw new IllegalArgumentException("The provided string must not be null");
		
		ThemeElement themeElement = ThemeElementFactory.getThemeElement(string);
		if ( themeElement != null )
			return new ExpressionThemeElement(string, themeElement);
		
		return null;
	}
}