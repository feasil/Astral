package fr.feasil.astral.data;

public enum Maison implements ThemeElement {
	MAISON_1("Maison I"), //Ascendant
	MAISON_2("Maison II"), 
	MAISON_3("Maison III"), 
	MAISON_4("Maison IV"), //Fond du ciel
	MAISON_5("Maison V"), 
	MAISON_6("Maison VI"), 
	MAISON_7("Maison VII"), 
	MAISON_8("Maison VIII"), 
	MAISON_9("Maison IX"), 
	MAISON_10("Maison X"), //Milieu du ciel
	MAISON_11("Maison XI"), 
	MAISON_12("Maison XII");
	
	
	private String nom;
	
	private Maison(String nom) {
		this.nom = nom;
	}
	@Override
	public String getNom() {
		return nom;
	}
	@Override
	public String getExpression() {
		return getNom().replaceAll(" ", "_");
	}
	
	public int getNumero() {
		return ordinal()+1;
	}
	
	
	public static Maison getMaison(String nom) {
		if ( nom == null )
			return null;
		nom = nom.replaceAll(" ", "").replaceAll("_", "");
		for ( Maison m : values() )
			if ( nom.equalsIgnoreCase(m.getNom().replaceAll(" ", "")) 
					|| nom.equalsIgnoreCase("Maison" + m.getNumero())  )
				return m;
		
		
		return null;
	}
}
