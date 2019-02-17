package fr.feasil.astral.data.celeste;

import fr.feasil.astral.data.PointFixe;

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
}
