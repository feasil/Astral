package fr.feasil.astral.data.point;

import fr.feasil.astral.data.PointFixe;
import fr.feasil.utils.CharUtils;

public enum Asteroide implements PointFixe {
	CHIRON("Chiron", "⚷"), 
	CERES("Cérès", "⚳"), 
	PALLAS("Pallas", "⚴"), 
	JUNON("Junon", "⚵"), 
	VESTA("Vesta", "⚶");
	
	
	private String nom;
	private String symbole;
	
	private Asteroide(String nom, String symbole) {
		this.nom = nom;
		this.symbole = symbole;
	}
	
	@Override
	public String getNom() {
		return nom;
	}
	@Override
	public String getSymbole() {
		return symbole;
	}
	@Override
	public boolean hasMaison() {
		return true;
	}
	
	
	
	public static Asteroide getAsteroide(String nom) {
		if ( nom == null )
			return null;
		nom = CharUtils.removeAccent(nom);
		
		for ( Asteroide a : values() )
			if ( nom.equalsIgnoreCase(CharUtils.removeAccent(a.getNom())) 
					|| nom.equalsIgnoreCase(a.symbole) )
				return a;
		
		return null;
	}
}
