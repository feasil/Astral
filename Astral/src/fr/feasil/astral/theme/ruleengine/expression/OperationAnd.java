package fr.feasil.astral.theme.ruleengine.expression;

import java.util.Stack;

import fr.feasil.astral.theme.Theme;
import fr.feasil.astral.theme.ruleengine.Expression;

public class OperationAnd extends Operation
{    
	public OperationAnd()
	{
		super("AND");
	}
	
	public OperationAnd copy()
	{
		return new OperationAnd();
	}
	
	@Override
	public int parse(String[] tokens, int pos, Stack<Expression> stack)
	{
		Expression left = stack.pop();
		int i = findNextExpression(tokens, pos+1, stack);
		Expression right = stack.pop();
		
		this.leftOperand = left;
		this.rightOperand = right;
		
		stack.push(this);
		
		return i;
	}
	
	@Override
	public boolean interpret(Theme theme)
	{
		return leftOperand.interpret(theme) && rightOperand.interpret(theme);
	}
}