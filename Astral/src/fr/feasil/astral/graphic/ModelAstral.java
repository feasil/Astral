package fr.feasil.astral.graphic;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import fr.feasil.astral.profil.Profil;
import fr.feasil.astral.rule.DataRules;
import fr.feasil.astral.rule.instance.SqLiteDataRules;
import fr.feasil.astral.theme.ruleengine.ActionDispatcher;
import fr.feasil.astral.theme.ruleengine.expression.Operation;
import fr.feasil.astral.theme.ruleengine.expression.OperationAnd;
import fr.feasil.astral.theme.ruleengine.expression.OperationEn;
import fr.feasil.astral.theme.ruleengine.expression.OperationNot;
import fr.feasil.astral.theme.ruleengine.expression.Operations;

public class ModelAstral extends Observable {
	
	private final DataRules datas;
	private Profil profil;
	private List<String> themeEvals;
	
	public ModelAstral() {
		Operations operations = Operations.INSTANCE;
		// register new operations with the previously created container
		Operation ot = new OperationAnd();
		operations.registerOperation(ot, "and"); operations.registerOperation(ot, "AND");
		operations.registerOperation(ot, "et"); operations.registerOperation(ot, "ET");
		ot = new OperationEn();
		operations.registerOperation(ot, "en"); operations.registerOperation(ot, "EN");
		ot = new OperationNot();
		operations.registerOperation(ot, "not"); operations.registerOperation(ot, "NOT");
		operations.registerOperation(ot, "non"); operations.registerOperation(ot, "NON");
		
		themeEvals = new ArrayList<>();
//		datas = new ExcelDataRules(new Dispatcher(), "in/DataTest.xlsx");
		datas = new SqLiteDataRules(new Dispatcher(), "in/DataAstral.db");
	}
	
	public void setProfil(Profil profil) {
		this.profil = profil;
		setChanged();
		notifyObservers("profil");
		
		evaluateProfile();
	}
	public Profil getProfil() {
		return profil;
	}
	
	private boolean evaluateProfile() {
		if ( profil != null && profil.getTheme() != null ) {
			themeEvals.clear();
			boolean retour = datas.eval(profil.getTheme());
			setChanged();
			notifyObservers("evaluate");
			
			return retour;
		}
		return false;
	}
	
	public List<String> getThemeEvals() {
		return themeEvals;
	}
	
	
	
	private class Dispatcher implements ActionDispatcher {
		@Override
		public void fire(Object argument) {
			if ( argument != null )
				themeEvals.add(argument.toString());
		}
	}
}
