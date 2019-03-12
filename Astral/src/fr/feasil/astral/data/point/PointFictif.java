package fr.feasil.astral.data.point;

import fr.feasil.astral.data.PointFixe;

public enum PointFictif implements PointFixe {
	NOEUD_NORD("Noeud Nord", null),
	LILITH("Lilith", "⚸"), //aussi appelé "Lune Noire"
	FORTUNE("Fortune", null), 
	POINT_EST("Point Est", null);
	
	
	private String nom;
	private String symbole;
	
	private PointFictif(String nom, String symbole) {
		this.nom = nom;
		this.symbole = symbole;
	}
	
	@Override
	public String getNom() {
		return nom;
	}
	@Override
	public String getExpression() {
		return getNom().replaceAll(" ", "_");
	}
	
	@Override
	public String getSymbole() {
		return symbole;
	}
	@Override
	public boolean hasMaison() {
		return true;
	}
	
	

	public static PointFictif getPointFictif(String nom) {
		if ( nom == null )
			return null;
		nom = nom.replaceAll(" ", "").replaceAll("_", "");
		
		for ( PointFictif p : values() )
			if ( nom.equalsIgnoreCase(p.getNom().replaceAll(" ", "")) 
					|| (p.symbole != null && nom.equalsIgnoreCase(p.symbole)) )
				return p;
		
		if ( nom.equalsIgnoreCase("Lunenoire"))
			return LILITH;
		
		return null;
	}
}
