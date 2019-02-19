package fr.feasil.astral.theme.ruleengine.expression;

import java.util.Stack;

import fr.feasil.astral.theme.Theme;
import fr.feasil.astral.theme.ruleengine.Expression;

public class OperationNot extends Operation
{    
	public OperationNot()
	{
		super("NOT");
	}
	
	public OperationNot copy()
	{
		return new OperationNot();
	}
	
	@Override
	public int parse(String[] tokens, int pos, Stack<Expression> stack)
	{
		int i = findNextExpression(tokens, pos+1, stack);
		Expression right = stack.pop();
		
		this.rightOperand = right;
		stack.push(this);
		
		return i;
	}
	
	@Override
	public boolean interpret(final Theme theme)
	{
		return !this.rightOperand.interpret(theme);
	}    
}
