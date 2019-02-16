package fr.feasil.astral.data;

public enum Element {
	FEU("Feu"), 
	TERRE("Terre"), 
	AIR("Air"), 
	EAU("Eau");
	
	
	private String nom;
	
	private Element(String nom) {
		this.nom = nom;
	}
	
	public String getNom() {
		return nom;
	}
}
