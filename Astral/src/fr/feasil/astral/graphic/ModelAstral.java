package fr.feasil.astral.graphic;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import fr.feasil.astral.graphic.profil.ProfilSelectedListener;
import fr.feasil.astral.profil.Profil;
import fr.feasil.astral.profil.instance.SqLiteProfilManager;
import fr.feasil.astral.rule.DataRules;
import fr.feasil.astral.rule.DataRulesListener;
import fr.feasil.astral.rule.instance.SqLiteDataRules;
import fr.feasil.astral.theme.ruleengine.expression.Operation;
import fr.feasil.astral.theme.ruleengine.expression.OperationAnd;
import fr.feasil.astral.theme.ruleengine.expression.OperationEn;
import fr.feasil.astral.theme.ruleengine.expression.OperationNot;
import fr.feasil.astral.theme.ruleengine.expression.Operations;

public class ModelAstral extends Observable implements ProfilSelectedListener, DataRulesListener {
	
	private final SqLiteProfilManager profilManager;
	private final DataRules dataRules;
	private Profil profil;
	private List<String> themeEvals;
	
	private boolean callDataMissing = false;
	
	public ModelAstral(String sqLiteFile) {
		this.profilManager = SqLiteProfilManager.getManager(sqLiteFile);
		
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
		dataRules = new SqLiteDataRules(new ModelAstralAD(this), sqLiteFile);
		dataRules.addDataRulesListener(this);
	}
	
	public SqLiteProfilManager getProfilManager() {
		return profilManager;
	}
	
	public void setProfil(Profil profil) {
		this.profil = profil;
		callDataMissing = true;
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
			boolean retour = dataRules.eval(profil.getTheme());
			setChanged();
			notifyObservers("evaluate");
			
			if ( callDataMissing ) {
				callDataMissing = false;
				setChanged();
				notifyObservers("dataMissing");
			}
			
			return retour;
		}
		return false;
	}
	
	public List<String> getThemeEvals() {
		return themeEvals;
	}
	
	public DataRules getDataRules() {
		return dataRules;
	}
	
	void addEval(String eval) {
		themeEvals.add(eval);
	}

	@Override
	public void profilSelected(Profil profil) {
		setProfil(profil);
	}
	@Override
	public void rulesReloaded() {
		evaluateProfile();
	}
	
}
