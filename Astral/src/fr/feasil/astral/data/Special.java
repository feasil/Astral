package fr.feasil.astral.data;

public enum Special implements ThemeElement {
	RETROGRADE("Retrograde", "Я");
	
	private String nom;
	private String symbole;
	
	private Special(String nom, String symbole) {
		this.nom = nom;
		this.symbole = symbole;
	}
	
	public String getNom() {
		return nom;
	}
	public String getSymbole() {
		return symbole;
	}
	
	
	
	public static Special getSpecial(String nom) {
		if ( nom == null )
			return null;
		
		for ( Special s : values() )
			if ( nom.equalsIgnoreCase(s.getNom())
					|| nom.equalsIgnoreCase(s.getSymbole()) )
				return s;
		
		return null;
	}

}
