package fr.feasil.astral.data;

public enum Planete {
    SOLEIL("Soleil"), 
    LUNE("Lune"), 
    MERCURE("Mercure"), 
    VENUS("Vénus"), 
    MARS("Mars"), 
    JUPITER("Jupiter"), 
    SATURNE("Saturne"), 
    URANUS("Uranus"), 
    NEPTUNE("Neptune"), 
    PLUTON("Pluton");
	
	
	private String nom;
	
	private Planete(String nom) {
		this.nom = nom;
	}
	
	public String getNom() {
		return nom;
	}
}
