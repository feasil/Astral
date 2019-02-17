package fr.feasil.astral.data;

public enum Aspect {
	CONJONCTION("Conjonction"), 
	SEMICARRE("Semi-carré"), 
	SEXTILE("Sextile"), 
	CARRE("Carré"), 
	TRIGONE("Trigone"), 
	SESQUICARRE("Sesqui-carré"), 
	QUINCONCE("Quinconce"), 
	OPPOSITION("Opposition"), 
	SEMISEXTILE("Semi-sextile"), 
	QUINTILE("Quintile"),
	BIQUINTILE("Biquintile"); 
	
	
	private String nom;
	
	private Aspect(String nom) {
		this.nom = nom;
	}
	
	public String getNom() {
		return nom;
	}
}
