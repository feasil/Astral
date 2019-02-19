package fr.feasil.astral.theme.ruleengine;

import java.util.ArrayList;
import java.util.List;

import fr.feasil.astral.theme.Theme;

public class Rule
{
	private List<Expression> expressions;
	private ActionDispatcher dispatcher;
	
	public static class Builder
	{
		private List<Expression> expressions = new ArrayList<>();
		private ActionDispatcher dispatcher = new NullActionDispatcher();
		
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
		
		public Rule build()
		{
			return new Rule(expressions, dispatcher);
		}
	}
	
	private Rule(List<Expression> expressions, ActionDispatcher dispatcher)
	{
		this.expressions = expressions;
		this.dispatcher = dispatcher;
	}
	
	public boolean eval(Theme theme)
	{
		boolean eval = false;
		for (Expression expression : expressions)
		{
			eval = expression.interpret(theme);
			if (eval)
				dispatcher.fire();
		}
		return eval;
	}
}
