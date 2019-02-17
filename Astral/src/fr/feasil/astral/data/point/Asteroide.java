package fr.feasil.astral.data.point;

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
	@Override
	public boolean hasMaison() {
		return true;
	}
	
	
	
	public static Asteroide getAsteroide(String nom) {
		if ( nom == null )
			return null;
		
		for ( Asteroide a : values() )
			if ( nom.equalsIgnoreCase(a.getNom()) )
				return a;
		
		return null;
	}
}
