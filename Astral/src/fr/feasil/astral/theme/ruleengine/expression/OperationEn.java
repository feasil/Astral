package fr.feasil.astral.theme.ruleengine.expression;

import java.util.Stack;

import fr.feasil.astral.data.Aspect;
import fr.feasil.astral.data.Maison;
import fr.feasil.astral.data.PointFixe;
import fr.feasil.astral.data.Signe;
import fr.feasil.astral.data.Special;
import fr.feasil.astral.theme.Theme;
import fr.feasil.astral.theme.ruleengine.Expression;

public class OperationEn extends Operation
{    
	public OperationEn()
	{
		super("EN");
	}
	
	public OperationEn copy()
	{
		return new OperationEn();
	}
	
	@Override
	public int parse(String[] tokens, int pos, Stack<Expression> stack)
	{
		if (pos-1 >= 0 && tokens.length >= pos+1)
		{
			this.leftOperand = ExpressionThemeElement.getExpressionElementTheme(tokens[pos-1]);
			this.rightOperand = ExpressionThemeElement.getExpressionElementTheme(tokens[pos+1]);
			if ( this.leftOperand == null )
				throw new IllegalArgumentException("Expression non reconnue : " + tokens[pos-1]);
			if ( this.rightOperand == null )
				throw new IllegalArgumentException("Expression non reconnue : " + tokens[pos+1]);
			
			stack.push(this);
			
			return pos+1;
		}
		throw new IllegalArgumentException("Cannot assign value to variable");
	}
	
	@Override
	public boolean interpret(Theme theme)
	{
		ExpressionThemeElement left = (ExpressionThemeElement) this.leftOperand;
		ExpressionThemeElement right = (ExpressionThemeElement) this.rightOperand;
		if ( theme == null || left == null || right == null )
			return false;
		
		if ( left.getThemeElement() instanceof Aspect ) {
			//TODO gérer les aspects
		}
		else if ( left.getThemeElement() instanceof Maison ) {
			//maison en planete || maison en signe
			if ( right.getThemeElement() instanceof PointFixe ) {
				return interpretPointFixeMaison(theme, (PointFixe)right.getThemeElement(), (Maison)left.getThemeElement());
			}
			else if ( right.getThemeElement() instanceof Signe ) {
				return interpretMaisonSigne(theme, (Maison)left.getThemeElement(), (Signe)right.getThemeElement());
			}
		}
		else if ( left.getThemeElement() instanceof PointFixe ) {
			//planete en maison || planete en signe || planete en retrograde
			if ( right.getThemeElement() instanceof Maison ) {
				return interpretPointFixeMaison(theme, (PointFixe)left.getThemeElement(), (Maison)right.getThemeElement());
			}
			else if ( right.getThemeElement() instanceof Signe ) {
				return interpretPointFixeSigne(theme, (PointFixe)left.getThemeElement(), (Signe)right.getThemeElement());
			}
			else if ( right.getThemeElement() instanceof Special ) {
				return interpretPointFixeSpecial(theme, (PointFixe)left.getThemeElement(), (Special)right.getThemeElement());
			}
			//TODO gérer les aspects
			
		}
		else if ( left.getThemeElement() instanceof Signe ) {
			//signe en planete || signe en maison
			if ( right.getThemeElement() instanceof PointFixe ) {
				return interpretPointFixeSigne(theme, (PointFixe)right.getThemeElement(), (Signe)left.getThemeElement());
			}
			else if ( right.getThemeElement() instanceof Maison ) {
				return interpretMaisonSigne(theme, (Maison)right.getThemeElement(), (Signe)left.getThemeElement());
			}
		}
		else if ( left.getThemeElement() instanceof Special ) {
			//special en planete
			if ( right.getThemeElement() instanceof PointFixe ) {
				return interpretPointFixeSpecial(theme, (PointFixe)right.getThemeElement(), (Special)left.getThemeElement());
			}
		}
		return false;
	}
	
	
	
}