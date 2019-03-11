package fr.feasil.astral.theme.ruleengine.expression;

import fr.feasil.astral.theme.Theme;
import fr.feasil.astral.theme.ruleengine.Expression;

public class ExpressionBaseType<T> implements Expression
{
	public T value;
	public Class<T> type;
	
	private ExpressionBaseType(T value, Class<T> type)
	{
		this.value = value;
		this.type = type;
	}
	
	public T getValue()
	{
		return this.value;
	}
	
	public Class<T> getType()
	{
		return this.type;
	}
	
	@Override
	public boolean interpret(Theme theme)
	{
		return true;
	}
	@Override
	public String getStringValue() {
		return value.toString();
	}
	
	public static ExpressionBaseType<?> getBaseType(String string)
	{
		if (string == null)
			throw new IllegalArgumentException("The provided string must not be null");
		
		if ("true".equals(string) || "false".equals(string))
			return new ExpressionBaseType<>(Boolean.getBoolean(string), Boolean.class);
		else if (string.startsWith("'"))
			return new ExpressionBaseType<>(string, String.class);
		else if (string.contains("."))
			return new ExpressionBaseType<>(Float.parseFloat(string), Float.class);
		else
			return new ExpressionBaseType<>(Integer.parseInt(string), Integer.class);
	}
}
