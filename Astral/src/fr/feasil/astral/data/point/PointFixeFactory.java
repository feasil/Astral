package fr.feasil.astral.data.point;

import fr.feasil.astral.data.PointFixe;

public class PointFixeFactory {
	
	private PointFixeFactory() {
	}
	
	public static PointFixe getPointFixe(String nom) {
		if ( nom == null )
			return null;
		PointFixe retour = null;
		
		retour = Angle.getAngle(nom);
		if ( retour == null )
			retour = Asteroide.getAsteroide(nom);
		if ( retour == null )
			retour = Planete.getPlanete(nom);
		if ( retour == null )
			retour = PointFictif.getPointFictif(nom);
		
		return retour;
	}
}
