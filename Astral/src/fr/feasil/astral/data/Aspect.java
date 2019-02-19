package fr.feasil.astral.data;

import fr.feasil.utils.CharUtils;

public enum Aspect implements ThemeElement {
	CONJONCTION("Conjonction"), 
	SEMICARRE("Semi-carré"), 
	SEXTILE("Sextile"), 
	CARRE("Carré"), 
	TRIGONE("Trigone"), 
	SESQUICARRE("Sesqui-carré"), 
	QUINCONCE("Quinconce"), 
	OPPOSITION("Opposition"), 
	SEMISEXTILE("Semi-sextile"), 
	QUINTILE("Quintile"),
	BIQUINTILE("Biquintile"); 
	
	
	private String nom;
	
	private Aspect(String nom) {
		this.nom = nom;
	}
	
	public String getNom() {
		return nom;
	}
	
	public static Aspect getAspect(String nom) {
		if ( nom == null )
			return null;
		nom = CharUtils.removeAccent(nom.replaceAll("-", ""));
		
		for ( Aspect a : values() ) 
			if ( nom.equalsIgnoreCase(CharUtils.removeAccent(a.getNom().replaceAll("-", "")))  )
				return a;
		
		return null;
	}
}
