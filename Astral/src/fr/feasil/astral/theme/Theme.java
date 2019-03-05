package fr.feasil.astral.theme;

import java.util.List;

import fr.feasil.astral.data.Maison;
import fr.feasil.astral.data.PointFixe;
import fr.feasil.astral.data.Signe;


public abstract class Theme {
	
	public abstract List<ThemePointFixe> getListePointFixe();
	public abstract List<ThemeMaison> getListeMaison();
	
	
	public Signe getSigne(PointFixe point) {
		if ( getListePointFixe() != null ) {
			for ( ThemePointFixe p : getListePointFixe() ) {
				if ( p.getPointFixe() == point )
					return p.getSigne();
			}
		}
		return null;
	}
	
	public Signe getSigne(Maison maison) {
		if ( getListePointFixe() != null ) {
			for ( ThemeMaison m : getListeMaison() ) {
				if ( m.getMaison() == maison )
					return m.getSigne();
			}
		}
		return null;
	}
}
