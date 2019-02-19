package fr.feasil.astral.data.point;

import fr.feasil.astral.data.PointFixe;

public enum PointFictif implements PointFixe {
	NOEUD_NORD("Noeud Nord"),
	LILITH("Lilith"), //aussi appelé "Lune Noire"
	FORTUNE("Fortune"), 
	POINT_EST("Point Est");
	
	
	private String nom;
	
	private PointFictif(String nom) {
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
	
	

	public static PointFictif getPointFictif(String nom) {
		if ( nom == null )
			return null;
		nom = nom.replaceAll(" ", "").replaceAll("_", "");
		
		for ( PointFictif p : values() )
			if ( nom.equalsIgnoreCase(p.getNom().replaceAll(" ", ""))  )
				return p;
		
		if ( nom.equalsIgnoreCase("Lunenoire"))
			return LILITH;
		
		return null;
	}
}
