package fr.feasil.astral.data.point;

import fr.feasil.astral.data.PointFixe;

public enum Angle implements PointFixe {
	ASCENDANT("Ascendant", "AS"), //Maison I
	DESCENDANT("Descendant", "DS"), //opposé de l'ascendant
	FOND_DU_CIEL("Fond du Ciel", "FC"), //Maison IV
	MILIEU_DU_CIEL("Milieu du Ciel", "MC"); //Maison X
	
	
	private String nom;
	private String sigle;
	
	private Angle(String nom, String sigle) {
		this.nom = nom;
		this.sigle = sigle;
	}
	
	@Override
	public String getNom() {
		return nom;
	}
	@Override
	public boolean hasMaison() {
		return false;
	}
	
	public String getSigle() {
		return sigle;
	}
	
	
	public static Angle getAngle(String nom) {
		if ( nom == null )
			return null;
		nom = nom.replaceAll(" ", "").replaceAll("_", "");
		
		for ( Angle a : values() )
			if ( nom.equalsIgnoreCase(a.getNom().replaceAll(" ", "")) 
					|| nom.equalsIgnoreCase(a.sigle)  )
				return a;
		
		
		return null;
	}
}
