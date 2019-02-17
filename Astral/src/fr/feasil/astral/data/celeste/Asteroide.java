package fr.feasil.astral.data.celeste;

import fr.feasil.astral.data.PointFixe;

public enum Asteroide implements PointFixe {
	CHIRON("Chiron"), 
	CERES("Cérès"), 
	PALLAS("Pallas"), 
	JUNON("Junon"), 
	VESTA("Vesta");
	
	
	private String nom;
	
	private Asteroide(String nom) {
		this.nom = nom;
	}
	
	@Override
	public String getNom() {
		return nom;
	}
}
