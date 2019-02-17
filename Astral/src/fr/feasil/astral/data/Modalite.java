package fr.feasil.astral.data;

public enum Modalite {
	CARDINAL("Cardinal"), 
	FIXE("Fixe"), 
	MUTABLE("Mutable");
	
	
	private String nom;
	
	private Modalite(String nom) {
		this.nom = nom;
	}
	
	public String getNom() {
		return nom;
	}
}
