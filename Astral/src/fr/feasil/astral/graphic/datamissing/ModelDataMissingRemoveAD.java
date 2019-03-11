package fr.feasil.astral.graphic.datamissing;

import fr.feasil.astral.theme.ruleengine.ActionDispatcher;
import fr.feasil.astral.theme.ruleengine.Expression;

public class ModelDataMissingRemoveAD implements ActionDispatcher {
	
	private ModelDataMissing model;
	
	public ModelDataMissingRemoveAD(ModelDataMissing model) {
		this.model = model;
	}
	
	@Override
	public void fire(Expression expression, Object argument) {
		if ( expression != null )
			model.removeDataMissing(expression);
	}
	
}
