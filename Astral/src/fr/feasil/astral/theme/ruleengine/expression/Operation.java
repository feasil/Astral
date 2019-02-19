package fr.feasil.astral.theme.ruleengine.expression;

import java.util.Stack;

import fr.feasil.astral.data.Maison;
import fr.feasil.astral.data.PointFixe;
import fr.feasil.astral.data.Signe;
import fr.feasil.astral.data.Special;
import fr.feasil.astral.theme.Theme;
import fr.feasil.astral.theme.ThemeMaison;
import fr.feasil.astral.theme.ThemePointFixe;
import fr.feasil.astral.theme.ruleengine.Expression;

public abstract class Operation implements Expression
{
	protected String symbol;
	
	protected Expression leftOperand = null;
	protected Expression rightOperand = null;
	
	public Operation(String symbol)
	{
		this.symbol = symbol;
	}
	
	public abstract Operation copy();
	
	public String getSymbol()
	{
		return this.symbol;
	}
	
	public abstract int parse(final String[] tokens, final int pos, final Stack<Expression> stack);
	
	protected Integer findNextExpression(String[] tokens, int pos, Stack<Expression> stack)
	{
		Operations operations = Operations.INSTANCE;
		
		for (int i = pos; i < tokens.length; i++)
		{
			Operation op = operations.getOperation(tokens[i]);
			if (op != null)
			{
				op = op.copy();
				// we found an operation
				i = op.parse(tokens, i, stack);
				
				return i;
			}
		}
		return null;
	}
	
	
	
	protected boolean interpretPointFixeMaison(Theme theme, PointFixe pointFixe, Maison maison) {
		if ( !pointFixe.hasMaison() )
			return false;
		for ( ThemePointFixe p : theme.getListePointFixe() )
			if ( p.getPointFixe() == pointFixe 
					&& p.getMaison() == maison )
				return true;
		return false;
	}
	protected boolean interpretPointFixeSigne(Theme theme, PointFixe pointFixe, Signe signe) {
		for ( ThemePointFixe p : theme.getListePointFixe() )
			if ( p.getPointFixe() == pointFixe 
					&& p.getSigne() == signe )
				return true;
		return false;
	}
	protected boolean interpretPointFixeSpecial(Theme theme, PointFixe pointFixe, Special special) {
		switch(special) {
		case RETROGRADE:
			for ( ThemePointFixe p : theme.getListePointFixe() )
				if ( p.getPointFixe() == pointFixe 
						&& p.isRetrograde() )
					return true;
			break;
			
		default:
		}
		return false;
	}
	
	protected boolean interpretMaisonSigne(Theme theme, Maison maison, Signe signe) {
		for ( ThemeMaison m : theme.getListeMaison() )
			if ( m.getMaison() == maison 
					&& m.getSigne() == signe )
				return true;
		return false;
	}
}
