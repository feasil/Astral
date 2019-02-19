package fr.feasil.astral.data;

import fr.feasil.astral.data.point.PointFixeFactory;

public class ThemeElementFactory {
	
	private ThemeElementFactory() {
	}
	
	public static ThemeElement getThemeElement(String nom) {
		if ( nom == null )
			return null;
		ThemeElement retour = null;
		
		retour = Aspect.getAspect(nom);
		if ( retour == null )
			retour = Aspect.getAspect(nom);
		if ( retour == null )
			retour = Maison.getMaison(nom);
		if ( retour == null )
			retour = Signe.getSigne(nom);
		if ( retour == null )
			retour = PointFixeFactory.getPointFixe(nom);
		if ( retour == null )
			retour = Special.getSpecial(nom);
		
		return retour;
	}
}
