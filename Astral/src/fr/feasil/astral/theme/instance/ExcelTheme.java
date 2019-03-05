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
import fr.feasil.utils.xlsloader.ExcelFile;
import fr.feasil.utils.xlsloader.ExcelSheet;


public class ExcelTheme extends Theme {
	private final static String[][] POSITIONS_PLANETES = {{"Positions des planètes"}, {"planete", "position", "retrograde", "signe"}};
	private final static String[][] PLANETES_MAISONS = {{"Planètes en maisons"}, {"planete", "maison"}};
	private final static String[][] POSITIONS_MAISONS = {{"Positions des maisons"}, {"maison", "position", "signe"}};
	private final static String[][] LISTE_ASPECTS = {{"Liste des aspects"}, {"planete principale", "aspect", "planete secondaire", "orbe"}};
	
	
	private ExcelFile file;
	private List<ThemePointFixe> listePointFixe = new ArrayList<>();
	private List<ThemeMaison> listeMaison = new ArrayList<>();
	
	
	public ExcelTheme(String fileName) {
		this.file = ExcelFile.load(fileName);
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
		ExcelSheet sheet = file.getSheet(POSITIONS_PLANETES[0][0]);
		
		for ( Object[] ligne : sheet.getBody() )
		{
			PointFixe point = null;
			float position = 0;
			Special retrograde = null;
			Signe signe = null;
			for ( int i = 0 ; i < ligne.length ; i++ ) {
				if ( POSITIONS_PLANETES[1][0].equalsIgnoreCase(sheet.getHeader()[i]) ) {//planete
					point = PointFixeFactory.getPointFixe(ligne[i].toString().trim()); 
				}
				else if ( POSITIONS_PLANETES[1][1].equalsIgnoreCase(sheet.getHeader()[i]) ) {//position
					try { position = Float.parseFloat(ligne[i].toString().trim().replaceAll("°", ".").replaceAll("'", "")); }
					catch(NumberFormatException e) { e.printStackTrace(); }
				}
				else if ( POSITIONS_PLANETES[1][2].equalsIgnoreCase(sheet.getHeader()[i]) ) {//retrograde
					retrograde = Special.getSpecial(ligne[i].toString().trim());
				}
				else if ( POSITIONS_PLANETES[1][3].equalsIgnoreCase(sheet.getHeader()[i]) ) {//signe
					signe = Signe.getSigne(ligne[i].toString().trim());
				}
			}
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
		ExcelSheet sheet = file.getSheet(PLANETES_MAISONS[0][0]);
		
		for ( Object[] ligne : sheet.getBody() ) {
			PointFixe point = null;
			Maison maison = null;
			for ( int i = 0 ; i < ligne.length ; i++ ) {
				if ( PLANETES_MAISONS[1][0].equalsIgnoreCase(sheet.getHeader()[i]) ) {//planete
					point = PointFixeFactory.getPointFixe(ligne[i].toString().trim()); 
				}
				else if ( PLANETES_MAISONS[1][1].equalsIgnoreCase(sheet.getHeader()[i]) ) {//maison
					maison = Maison.getMaison(ligne[i].toString().trim());
				}
			}
			if ( point != null )
				getThemePointFixe(point).setMaison(maison);
		}
	}
	private void loadPositionMaisons() {
		ExcelSheet sheet = file.getSheet(POSITIONS_MAISONS[0][0]);
		
		for ( Object[] ligne : sheet.getBody() ) {
			Maison maison = null;
			float position = 0;
			Signe signe = null;
			for ( int i = 0 ; i < ligne.length ; i++ ) {
				if ( POSITIONS_MAISONS[1][0].equalsIgnoreCase(sheet.getHeader()[i]) ) {//maison
					maison = Maison.getMaison(ligne[i].toString().trim()); 
				}
				else if ( POSITIONS_MAISONS[1][1].equalsIgnoreCase(sheet.getHeader()[i]) ) {//position
					try { position = Float.parseFloat(ligne[i].toString().trim().replaceAll("°", ".").replaceAll("'", "")); }
					catch(NumberFormatException e) { e.printStackTrace(); }
				}
				else if ( POSITIONS_MAISONS[1][2].equalsIgnoreCase(sheet.getHeader()[i]) ) {//signe
					signe = Signe.getSigne(ligne[i].toString().trim());
				}
			}
			if ( maison != null ) {
				ThemeMaison tmaison = getThemeMaison(maison);
				tmaison.setPosition(position);
				tmaison.setSigne(signe);
			}
		}
	}
	private void loadAspects() {
		ExcelSheet sheet = file.getSheet(LISTE_ASPECTS[0][0]);
		
		for ( Object[] ligne : sheet.getBody() ) {
			PointFixe point = null;
			Aspect aspect = null;
			PointFixe point2 = null;
			float orbe = 0;
			for ( int i = 0 ; i < ligne.length ; i++ ) {
				if ( LISTE_ASPECTS[1][0].equalsIgnoreCase(sheet.getHeader()[i]) ) {//planete
					point = PointFixeFactory.getPointFixe(ligne[i].toString().trim()); 
				}
				else if ( LISTE_ASPECTS[1][1].equalsIgnoreCase(sheet.getHeader()[i]) ) {//aspect
					aspect = Aspect.getAspect(ligne[i].toString().trim());
				}
				else if ( LISTE_ASPECTS[1][2].equalsIgnoreCase(sheet.getHeader()[i]) ) {//planete 2
					point2 = PointFixeFactory.getPointFixe(ligne[i].toString().trim());
				}
				else if ( LISTE_ASPECTS[1][3].equalsIgnoreCase(sheet.getHeader()[i]) ) {//orbe
					try { orbe = Float.parseFloat(ligne[i].toString().trim().replaceAll("°", ".").replaceAll("'", "")); }
					catch(NumberFormatException e) { e.printStackTrace(); }
				}
			}
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
