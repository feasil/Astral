package fr.feasil.astral.data.celeste;

import fr.feasil.astral.data.PointFixe;

public enum PointFictif implements PointFixe {
	NOEUD_NORD("Noeud Nord"),
	LILITH("Lilith"), //aussi appelé LUNE_NOIRE("Lune Noire") ?, à séparer ?
	FORTUNE("Fortune"); 
//	VERTEX("Vertex");
	
	
	private String nom;
	
	private PointFictif(String nom) {
		this.nom = nom;
	}
	
	@Override
	public String getNom() {
		return nom;
	}
}
