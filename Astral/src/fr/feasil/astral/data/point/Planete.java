package fr.feasil.astral.data.point;

import fr.feasil.astral.data.PointFixe;
import fr.feasil.utils.CharUtils;

public enum Planete implements PointFixe {
	SOLEIL("Soleil", "☉"), 
	LUNE("Lune", "☽"), 
	MERCURE("Mercure", "☿"), 
	VENUS("Vénus", "♀"), 
	TERRE("Terre", "♁"), 
	MARS("Mars", "♂"), 
	JUPITER("Jupiter", "♃"), 
	SATURNE("Saturne", "♄"), 
	URANUS("Uranus", "♅"), 
	NEPTUNE("Neptune", "♆"), 
	PLUTON("Pluton", "♇");
	
	
	private String nom;
	private String symbole;
	
	private Planete(String nom, String symbole) {
		this.nom = nom;
		this.symbole = symbole;
	}
	
	@Override
	public String getNom() {
		return nom;
	}
	@Override
	public String getSymbole() {
		return symbole;
	}
	@Override
	public boolean hasMaison() {
		return true;
	}
	
	
	public static Planete getPlanete(String nom) {
		if ( nom == null )
			return null;
		nom = CharUtils.removeAccent(nom);
		
		for ( Planete p : values() )
			if ( nom.equalsIgnoreCase(CharUtils.removeAccent(p.getNom()))
					|| nom.equalsIgnoreCase(p.getSymbole()) )
				return p;
		
		return null;
	}
}
