package fr.feasil.astral.data.celeste;

import fr.feasil.astral.data.PointFixe;

public enum Angle implements PointFixe {
	ASCENDANT("Ascendant"), //Maison I
	DESCENDANT("Descendant"), //opposé de l'ascendant
	FOND_DU_CIEL("Fond du Ciel"), //Maison IV
	MILIEU_DU_CIEL("Milieu du Ciel"); //Maison X
	
	
	private String nom;
	
	private Angle(String nom) {
		this.nom = nom;
	}
	
	@Override
	public String getNom() {
		return nom;
	}
}
