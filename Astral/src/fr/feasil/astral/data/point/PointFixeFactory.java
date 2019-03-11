package fr.feasil.astral.data.point;

import java.util.ArrayList;
import java.util.List;

import fr.feasil.astral.data.PointFixe;

public class PointFixeFactory {
	
	private static List<PointFixe> allPointFixe = null;
	
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
	
	public static List<PointFixe> getAllPointFixe() {
		if ( allPointFixe == null ) {
			allPointFixe = new ArrayList<>();
			for ( Planete p : Planete.values() )
				allPointFixe.add(p);
			for ( Angle a : Angle.values() )
				allPointFixe.add(a);
			for ( Asteroide a : Asteroide.values() )
				allPointFixe.add(a);
			for ( PointFictif p : PointFictif.values() )
				allPointFixe.add(p);
		}
		return allPointFixe;
	}
}
