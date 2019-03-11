package fr.feasil.astral.graphic;

import fr.feasil.astral.theme.ruleengine.ActionDispatcher;
import fr.feasil.astral.theme.ruleengine.Expression;

public class ModelAstralAD implements ActionDispatcher {
	
	private ModelAstral model;
	
	public ModelAstralAD(ModelAstral model) {
		this.model = model;
	}
	
	@Override
	public void fire(Expression expression, Object argument) {
		if ( argument != null )
			model.addEval(argument.toString());
	}
	
}
