package fr.feasil.astral.data;

import fr.feasil.utils.CharUtils;

public enum Aspect implements ThemeElement {
	CONJONCTION("Conjonction", "☌"),
	OPPOSITION("Opposition", "☍"), 
	SEMICARRE("Semi-carré", null), 
	SEXTILE("Sextile", null), 
	CARRE("Carré", null), 
	TRIGONE("Trigone", null), 
	SESQUICARRE("Sesqui-carré", null), 
	QUINCONCE("Quinconce", null), 
	SEMISEXTILE("Semi-sextile", null), 
	QUINTILE("Quintile", null),
	BIQUINTILE("Biquintile", null); 
	
	
	private String nom;
	private String symbole;
	
	private Aspect(String nom, String symbole) {
		this.nom = nom;
		this.symbole = symbole;
	}
	
	public String getNom() {
		return nom;
	}
	
	public static Aspect getAspect(String nom) {
		if ( nom == null )
			return null;
		nom = CharUtils.removeAccent(nom.replaceAll("-", ""));
		
		for ( Aspect a : values() ) 
			if ( nom.equalsIgnoreCase(CharUtils.removeAccent(a.getNom().replaceAll("-", "")))
					|| (a.symbole != null && nom.equalsIgnoreCase(a.symbole)) )
				return a;
		
		return null;
	}
}
