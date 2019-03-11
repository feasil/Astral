package fr.feasil.astral.graphic.datamissing;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;

import fr.feasil.astral.profil.Profil;
import fr.feasil.astral.rule.DataRules;
import fr.feasil.astral.rule.instance.MissingDataRules;
import fr.feasil.astral.theme.ruleengine.Expression;

public class ModelDataMissing extends Observable {
	
	private MissingDataRules dataRulesMisses;
	private DataRules dataRulesExists;
	private Profil profil;
	
	private Map<String, Expression> listMissing = new HashMap<>();
	
	public ModelDataMissing(DataRules dataRules, Profil profil) {
		this.dataRulesMisses = new MissingDataRules(new ModelDataMissingAddAD(this));
		this.dataRulesExists = dataRules.newInstance(new ModelDataMissingRemoveAD(this));
		this.profil = profil;
	}
	
	
	public void loadDataMissing() {
		listMissing.clear();
		dataRulesMisses.eval(profil.getTheme());
		dataRulesExists.eval(profil.getTheme());
		
		setChanged();
		notifyObservers("missingReady");
	}
	void addDataMissing(Expression expression) {
		listMissing.put(expression.getStringValue(), expression);
	}
	void removeDataMissing(Expression expression) {
		listMissing.remove(expression.getStringValue());
	}
	
	public List<Expression> getListMissing() {
		return new ArrayList<>(listMissing.values());
	}
	
	
	
}
