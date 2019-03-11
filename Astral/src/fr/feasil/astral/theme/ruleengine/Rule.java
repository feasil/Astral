package fr.feasil.astral.theme.ruleengine;

import java.util.ArrayList;
import java.util.List;

import fr.feasil.astral.theme.Theme;

public class Rule
{
	private List<Expression> expressions;
	private ActionDispatcher dispatcher;
	private Object argument;
	
	public static class Builder
	{
		private List<Expression> expressions = new ArrayList<>();
		private ActionDispatcher dispatcher = new NullActionDispatcher();
		private Object argument = null;
		
		public Builder withExpression(Expression expr)
		{
			expressions.add(expr);
			return this;
		}
		
		public Builder withDispatcher(ActionDispatcher dispatcher)
		{
			this.dispatcher = dispatcher;
			return this;
		}
		
		public Builder withArgument(Object argument)
		{
			this.argument = argument;
			return this;
		}
		public Rule build()
		{
			return new Rule(expressions, dispatcher, argument);
		}
	}
	
	private Rule(List<Expression> expressions, ActionDispatcher dispatcher, Object argument)
	{
		this.expressions = expressions;
		this.dispatcher = dispatcher;
		this.argument = argument;
	}
	
	public boolean eval(Theme theme)
	{
		boolean eval = false;
		for (Expression expression : expressions)
		{
			eval = expression.interpret(theme);
			if (eval)
				dispatcher.fire(expression, argument);
		}
		return eval;
	}
}
