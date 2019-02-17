package fr.feasil.astral.theme;

import java.util.ArrayList;
import java.util.List;

import fr.feasil.astral.data.Maison;
import fr.feasil.astral.data.PointFixe;
import fr.feasil.astral.data.Signe;
import fr.feasil.astral.data.point.PointFixeFactory;
import fr.feasil.utils.xlsloader.ExcelSheet;


public class Theme {
	
	private final static String[][] POSITIONS_PLANETES = {{"Positions des planètes"}, {"planete", "position", "retrograde", "signe"}};
	private final static String[][] PLANETES_MAISONS = {{"Planètes en maisons"}, {"planete", "maison"}};
	private final static String[][] POSITIONS_MAISONS = {{"Positions des maisons"}, {"maison", "position", "signe"}};
	private final static String[][] LISTE_ASPECTS = {{"Liste des aspects"}, {"planete principale", "aspect", "planete secondaire", "orbe"}};
	
	
	private String fileName = null;
	private List<ThemePointFixe> listePointFixe = new ArrayList<>();
	private List<ThemeMaison> listeMaison = new ArrayList<>();
	
	
	public Theme(String fileName) {
		this.fileName = fileName;
		loadPositionPlanetes();
		loadPlanetesMaisons();
//		load(fileName, "Positions des maisons");
//		load(fileName, "Liste des aspects");
	}
	
	public List<ThemePointFixe> getListePointFixe() {
		return listePointFixe;
	}
	
	public List<ThemeMaison> getListeMaison() {
		return listeMaison;
	}
	
	
	
	
	private void loadPositionPlanetes() {
		ExcelSheet sheet = ExcelSheet.load(fileName, POSITIONS_PLANETES[0][0]);
		
		for ( Object[] ligne : sheet.getBody() )
		{
			PointFixe p = null;
			float position = 0;
			boolean retrograde = false;
			Signe signe = null;
			for ( int i = 0 ; i < ligne.length ; i++ )
			{
				if ( POSITIONS_PLANETES[1][0].equalsIgnoreCase(sheet.getHeader()[i]) )
				{//planete
					p = PointFixeFactory.getPointFixe(ligne[i].toString()); 
				}
				else if ( POSITIONS_PLANETES[1][1].equalsIgnoreCase(sheet.getHeader()[i]) )
				{//position
					try { 
						position = Float.parseFloat(ligne[i].toString().replaceAll("°", ".").replaceAll("'", ""));
					}
					catch(NumberFormatException e) { e.printStackTrace(); }
				}
				else if ( POSITIONS_PLANETES[1][2].equalsIgnoreCase(sheet.getHeader()[i]) )
				{//retrograde
					retrograde = "Я".equals(ligne[i]);
				}
				else if ( POSITIONS_PLANETES[1][3].equalsIgnoreCase(sheet.getHeader()[i]) )
				{//signe
					signe = Signe.getSigne(ligne[i].toString());
				}
			}
			if ( p != null ) {
				ThemePointFixe tpoint = getThemePointFixe(p);
				tpoint.setPosition(position);
				tpoint.setRetrograde(retrograde);
				tpoint.setSigne(signe);
			}
		}
	}
	
	private void loadPlanetesMaisons() {
		ExcelSheet sheet = ExcelSheet.load(fileName, PLANETES_MAISONS[0][0]);
		
		for ( Object[] ligne : sheet.getBody() )
		{
			PointFixe p = null;
			Maison maison = null;
			for ( int i = 0 ; i < ligne.length ; i++ )
			{
				if ( PLANETES_MAISONS[1][0].equalsIgnoreCase(sheet.getHeader()[i]) )
				{//planete
					p = PointFixeFactory.getPointFixe(ligne[i].toString()); 
				}
				else if ( PLANETES_MAISONS[1][1].equalsIgnoreCase(sheet.getHeader()[i]) )
				{//maison
					maison = Maison.getMaison(ligne[i].toString());
				}
			}
			if ( p != null )
				getThemePointFixe(p).setMaison(maison);
		}
		
	}
	
	
	//TODO les deux derniers onglets
	
	
	
	private ThemePointFixe getThemePointFixe(PointFixe p) {
		for ( ThemePointFixe t : listePointFixe )
			if ( t.getPointFixe() == p )
				return t;
		ThemePointFixe tpoint = new ThemePointFixe();
		tpoint.setPointFixe(p);
		listePointFixe.add(tpoint);
		
		return tpoint;
	}
	

	
}
