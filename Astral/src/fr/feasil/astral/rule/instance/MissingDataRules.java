package fr.feasil.astral.rule.instance;

import fr.feasil.astral.data.Maison;
import fr.feasil.astral.data.PointFixe;
import fr.feasil.astral.data.Signe;
import fr.feasil.astral.data.point.PointFixeFactory;
import fr.feasil.astral.rule.DataRules;
import fr.feasil.astral.theme.ruleengine.ActionDispatcher;
import fr.feasil.astral.theme.ruleengine.ExpressionParser;


public class MissingDataRules extends DataRules {
	
	public MissingDataRules(ActionDispatcher dispatcher) {
		super(dispatcher);
		
		loadData();
	}
	
	private void loadData() {
		for ( PointFixe p : PointFixeFactory.getAllPointFixe() ) {
			String pl = p.getNom().replaceAll(" ", "_");
			for ( Signe s : Signe.values() )
				addRule(ExpressionParser.fromString(pl + " EN " + s.getNom().replaceAll(" ", "_")), null);
			for ( Maison m : Maison.values() )
				addRule(ExpressionParser.fromString(pl + " EN " + m.getNom().replaceAll(" ", "_")), null);
		}
	}

	@Override
	public DataRules newInstance(ActionDispatcher dispatcher) {
		return new MissingDataRules(dispatcher);
	}
}
