package fr.feasil.astral.theme.instance;

import java.util.ArrayList;
import java.util.List;

import fr.feasil.astral.data.Aspect;
import fr.feasil.astral.data.Maison;
import fr.feasil.astral.data.PointFixe;
import fr.feasil.astral.data.Signe;
import fr.feasil.astral.data.Special;
import fr.feasil.astral.data.point.PointFixeFactory;
import fr.feasil.astral.theme.Theme;
import fr.feasil.astral.theme.ThemeAspect;
import fr.feasil.astral.theme.ThemeMaison;
import fr.feasil.astral.theme.ThemePointFixe;


public class TextTheme extends Theme {
//	private final static String[][] POSITIONS_PLANETES = {{"Positions des planètes"}, {"planete", "position", "retrograde", "signe"}};
//	private final static String[][] PLANETES_MAISONS = {{"Planètes en maisons"}, {"planete", "maison"}};
//	private final static String[][] POSITIONS_MAISONS = {{"Positions des maisons"}, {"maison", "position", "signe"}};
//	private final static String[][] LISTE_ASPECTS = {{"Liste des aspects"}, {"planete principale", "aspect", "planete secondaire", "orbe"}};
	
	
	private String[] lines;
	private List<ThemePointFixe> listePointFixe = new ArrayList<>();
	private List<ThemeMaison> listeMaison = new ArrayList<>();
	
	
	public TextTheme(String content) {
		if ( content == null )
			return;
		this.lines = content.replaceAll("\r", "").split("\n");
		
		
		loadPositionPlanetes();
		loadPlanetesMaisons();
		loadPositionMaisons();
		loadAspects();
	}
	
	@Override
	public List<ThemePointFixe> getListePointFixe() {
		return listePointFixe;
	}
	@Override
	public List<ThemeMaison> getListeMaison() {
		return listeMaison;
	}
	
	
	
	
	
	private void loadPositionPlanetes() {
		for ( String line : lines )
		{
			String[] elements = line.split("	");
			if ( elements.length != 4 || elements[0] == null || elements[0].length() == 0 )
				continue;
			
			PointFixe point = null;
			float position = 0;
			Special retrograde = null;
			Signe signe = null;
			//planete
			point = PointFixeFactory.getPointFixe(elements[0].trim()); 
			//position
			try { position = Float.parseFloat(elements[1].trim().replaceAll("°", ".").replaceAll("'", "")); }
			catch(NumberFormatException e) { e.printStackTrace(); }
			//retrograde
			retrograde = Special.getSpecial(elements[2].trim());
			//signe
			signe = Signe.getSigne(elements[3].trim());
			
			if ( point != null ) {
				ThemePointFixe tpoint = getThemePointFixe(point);
				tpoint.setPosition(position);
				if ( retrograde == Special.RETROGRADE )
					tpoint.setRetrograde();
				tpoint.setSigne(signe);
			}
		}
	}
	private void loadPlanetesMaisons() {
		for ( String line : lines )
		{
			String[] elements = line.split("	");
			if ( elements.length != 2 || elements[0] == null || elements[0].length() == 0 )
				continue;
			
			PointFixe point = null;
			Maison maison = null;
			//planete
			point = PointFixeFactory.getPointFixe(elements[0].trim()); 
			//maison
			maison = Maison.getMaison(elements[1].trim());
			
			if ( point != null )
				getThemePointFixe(point).setMaison(maison);
		}
	}
	private void loadPositionMaisons() {
		for ( String line : lines )
		{
			String[] elements = line.split("	");
			if ( elements.length != 3 || elements[0] == null || elements[0].length() == 0 )
				continue;
			
			Maison maison = null;
			float position = 0;
			Signe signe = null;
			//maison
			maison = Maison.getMaison(elements[0].trim()); 
			//position
			try { position = Float.parseFloat(elements[1].trim().replaceAll("°", ".").replaceAll("'", "")); }
			catch(NumberFormatException e) { e.printStackTrace(); }
			//signe
			signe = Signe.getSigne(elements[2].trim());
			
			if ( maison != null ) {
				ThemeMaison tmaison = getThemeMaison(maison);
				tmaison.setPosition(position);
				tmaison.setSigne(signe);
			}
		}
	}
	private void loadAspects() {
		for ( String line : lines )
		{
			String[] elements = line.split("	");
			if ( elements.length != 5 || elements[0] == null || elements[0].length() == 0 )
				continue;
			
			PointFixe point = null;
			Aspect aspect = null;
			PointFixe point2 = null;
			float orbe = 0;
			//planete
			point = PointFixeFactory.getPointFixe(elements[0].trim()); 
			//aspect
			aspect = Aspect.getAspect(elements[1].trim());
			//planete 2
			point2 = PointFixeFactory.getPointFixe(elements[2].trim());
			//orbe
			try { orbe = Float.parseFloat(elements[4].trim().replaceAll("°", ".").replaceAll("'", "")); }
			catch(NumberFormatException e) { e.printStackTrace(); }
			
			if ( point != null ) {
				ThemeAspect taspect = new ThemeAspect();
				taspect.setPointFixePrincipal(point);
				taspect.setAspect(aspect);
				taspect.setPointFixeSecondaire(point2);
				taspect.setOrbe(orbe);
				
				ThemePointFixe tpoint = getThemePointFixe(point);
				tpoint.addAspect(taspect);
			}
		}
	}
	private ThemePointFixe getThemePointFixe(PointFixe p) {
		for ( ThemePointFixe t : listePointFixe )
			if ( t.getPointFixe() == p )
				return t;
		ThemePointFixe tpoint = new ThemePointFixe();
		tpoint.setPointFixe(p);
		listePointFixe.add(tpoint);
		
		return tpoint;
	}
	private ThemeMaison getThemeMaison(Maison m) {
		for ( ThemeMaison t : listeMaison )
			if ( t.getMaison() == m )
				return t;
		ThemeMaison tmaison = new ThemeMaison();
		tmaison.setMaison(m);
		listeMaison.add(tmaison);
		
		return tmaison;
	}

	
}
