package fr.feasil.astral.graphic.datamissing;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Observable;

import fr.feasil.astral.profil.Profil;
import fr.feasil.astral.rule.DataRules;
import fr.feasil.astral.rule.instance.MissingDataRules;
import fr.feasil.astral.theme.ruleengine.Expression;

public class ModelDataMissing extends Observable {
	
	private MissingDataRules dataRulesMisses;
	private DataRules dataRulesExists;
	private DataRules dataRules;
	private Profil profil;
	
	private Map<String, Expression> listMissing = new HashMap<>();
	private Map<Expression, Object> listText = new HashMap<>();
	private int selectedIndex = 0;
	
	public ModelDataMissing(DataRules dataRules, Profil profil) {
		this.dataRules = dataRules;
		this.dataRulesMisses = new MissingDataRules(new ModelDataMissingAddAD(this));
		this.dataRulesExists = dataRules.newInstance(new ModelDataMissingRemoveAD(this));
		this.profil = profil;
	}
	
	
	public void loadDataMissing() {
		listMissing.clear();
		listText.clear();
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
	
	public Collection<Expression> getListMissing() {
		return listMissing.values();
	}
	
	public boolean isFirstIndex() {
		return selectedIndex <= 0;
	}
	public boolean isLastIndex() {
		return selectedIndex >= getListMissing().size()-1;
	}
	public Expression getSelectedValue() {
		Iterator<Expression> it = getListMissing().iterator();
		for ( int i = 0 ; it.hasNext() ; i++ ) {
			if ( i == selectedIndex )
				return it.next();
			else it.next();
		}
		return null;
	}
	public void setSelectedIndex(int selectedIndex) {
		this.selectedIndex = selectedIndex;
		setChanged();
		notifyObservers("indexChanged");
	}
	
	public void incrementeIndex() {
		if ( selectedIndex < listMissing.values().size()-1 )
			setSelectedIndex(selectedIndex+1);
	}
	public void decrementeIndex() {
		if ( selectedIndex > 0 )
			setSelectedIndex(selectedIndex-1);
	}
	
	
	public void setSelectedText(String text) {
		setText(getSelectedValue(), text);
	}
	public void setText(Expression expression, String text) {
		if ( text == null || text.replaceAll("\n", "").replaceAll("\r", "").replaceAll("\t", "").trim().length() == 0 )
			listText.remove(expression);
		else
			listText.put(expression, text);
	}
	public String getSelectedText() {
		return getText(getSelectedValue());
	}
	public String getText(Expression expression) {
		if ( listText.containsKey(expression) )
			return listText.get(expression).toString();
		return null;
	}
	
	public void insertRules() {
		dataRules.insertRules(listText);
	}
}
