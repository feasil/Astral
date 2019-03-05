package fr.feasil.astral.profil;

public enum Genre {
	MASCULIN("Masculin", "♂"), 
	FEMININ("Féminin", "♀");
	
	private String libelle;
	private String symbole;
	
	private Genre(String libelle, String symbole) {
		this.libelle = libelle;
		this.symbole = symbole;
	}
	
	public String getLibelle() {
		return libelle;
	}
	public String getSymbole() {
		return symbole;
	}
}
