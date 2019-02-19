package fr.feasil.astral.data.point;

import fr.feasil.astral.data.PointFixe;
import fr.feasil.utils.CharUtils;

public enum Planete implements PointFixe {
	SOLEIL("Soleil"), 
	LUNE("Lune"), 
	MERCURE("Mercure"), 
	VENUS("Vénus"), 
	MARS("Mars"), 
	JUPITER("Jupiter"), 
	SATURNE("Saturne"), 
	URANUS("Uranus"), 
	NEPTUNE("Neptune"), 
	PLUTON("Pluton");
	
	
	private String nom;
	
	private Planete(String nom) {
		this.nom = nom;
	}
	
	@Override
	public String getNom() {
		return nom;
	}
	@Override
	public boolean hasMaison() {
		return true;
	}
	
	
	public static Planete getPlanete(String nom) {
		if ( nom == null )
			return null;
		nom = CharUtils.removeAccent(nom);
		
		for ( Planete p : values() )
			if ( nom.equalsIgnoreCase(CharUtils.removeAccent(p.getNom())) )
				return p;
		
		return null;
	}
}
