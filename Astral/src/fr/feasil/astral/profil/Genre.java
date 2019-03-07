package fr.feasil.astral.profil;

import fr.feasil.utils.CharUtils;

public enum Genre {
	MASCULIN("Masculin", "♂"), 
	FEMININ("Féminin", "♀");
	
	private String libelle;
	private String symbole;
	
	private Genre(String libelle, String symbole) {
		this.libelle = libelle;
		this.symbole = symbole;
	}
	
	public String getLibelle() {
		return libelle;
	}
	public String getSymbole() {
		return symbole;
	}
	
	
	
	public static Genre getGenre(String nom) {
		if ( nom == null )
			return null;
		nom = CharUtils.removeAccent(nom);
		
		for ( Genre a : values() )
			if ( nom.equalsIgnoreCase(a.name())
					|| nom.equalsIgnoreCase(CharUtils.removeAccent(a.getLibelle())) 
					|| nom.equalsIgnoreCase(a.symbole) )
				return a;
		
		return null;
	}
}
