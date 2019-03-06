package fr.feasil.astral.graphic.profil;

import java.util.Observable;

import fr.feasil.astral.profil.Profil;
import fr.feasil.astral.theme.instance.TextTheme;

public class ModelHistoriqueProfils extends Observable {
	
	private Profil profil = null;
	
	
	public void createProfil(String theme) {
		profil = new Profil();
		profil.setNom("TOTO !");
		profil.setTheme(new TextTheme(theme));
		
		setChanged();
		notifyObservers("createProfil");
	}
	
	public Profil getProfil() {
		return profil;
	}
}
