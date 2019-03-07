package fr.feasil.astral.graphic.profil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Observable;

import fr.feasil.astral.profil.Genre;
import fr.feasil.astral.profil.Profil;
import fr.feasil.astral.profil.ProfilManager;
import fr.feasil.astral.theme.instance.TextTheme;

public class ModelHistoriqueProfils extends Observable {
	private final static ComparatorProfil COMPARATOR_PROFIL = new ComparatorProfil();
	
	private Profil profil = null;
	private ProfilManager<Profil> profilManager;
	private List<Profil> lastProfils = null;
	
	public ModelHistoriqueProfils(ProfilManager<Profil> profilManager) {
		this.profilManager = profilManager;
	}
	
	public void createProfil(String theme) {
		profil = profilManager.newEmptyProfil();
		profil.setTheme(new TextTheme(theme));
		
		setChanged();
		notifyObservers("createProfil");
	}
	public void finishProfil(String nom, Genre genre) {
		profil.setNom(nom);
		profil.setGenre(genre);
		profilManager.saveProfil(profil);
		
		setChanged();
		notifyObservers("finishProfil");
	}
	
	public void selectProfil(int i) {
		this.profil = getLastProfils().get(i);
		
		setChanged();
		notifyObservers("finishProfil");
	}
	
	public Profil getProfil() {
		return profil;
	}
	
	/**
	 * 
	 * @return last 9 profils
	 */
	public List<Profil> getLastProfils() {
		if ( lastProfils == null )
		{
			lastProfils = new ArrayList<Profil>(profilManager.getAllProfils());
			
			Collections.sort(lastProfils, COMPARATOR_PROFIL);
			for ( int i = lastProfils.size()-1 ; i > 9 ; i-- )
				lastProfils.remove(i);
		}
		return lastProfils;
	}
	
	private static class ComparatorProfil implements Comparator<Profil> {
		@Override
		public int compare(Profil p1, Profil p2) {
			if ( p1 == p2 ) return 0;
			if ( p1 == null ) return 1;
			if ( p2 == null ) return -1;
			return (int) (p2.getDateCreation() - p1.getDateCreation());
		}
		
	}
}
