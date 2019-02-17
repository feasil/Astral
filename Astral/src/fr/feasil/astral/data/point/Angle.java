package fr.feasil.astral.data.point;

import fr.feasil.astral.data.PointFixe;

public enum Angle implements PointFixe {
	ASCENDANT("Ascendant", "AS"), //Maison I
	DESCENDANT("Descendant", null), //opposé de l'ascendant
	FOND_DU_CIEL("Fond du Ciel", null), //Maison IV
	MILIEU_DU_CIEL("Milieu du Ciel", "MC"); //Maison X
	
	
	private String nom;
	private String nom2;
	
	private Angle(String nom, String nom2) {
		this.nom = nom;
		this.nom2 = nom2;
	}
	
	@Override
	public String getNom() {
		return nom;
	}
	@Override
	public boolean hasMaison() {
		return false;
	}
	
	
	
	public static Angle getAngle(String nom) {
		if ( nom == null )
			return null;
		
		for ( Angle a : values() )
			if ( nom.equalsIgnoreCase(a.getNom()) 
					|| nom.equalsIgnoreCase(a.nom2)  )
				return a;
		
		
		return null;
	}
}
