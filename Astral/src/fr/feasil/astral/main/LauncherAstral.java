package fr.feasil.astral.main;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.WindowConstants;

import fr.feasil.astral.graphic.FenetreAstral;

public class LauncherAstral {
	
	private final static String SQLITE_FILENAME = "in/DataAstral.db";
	
	public static void main(String[] args) {
		
		//Fichiers non externalisés
		try {
			System.getProperties().load(LauncherAstral.class.getResourceAsStream("/fr/feasil/astral/main/version.properties"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		//-------------------
		
		//
		String version, subversion;
		if ( System.getProperty(LauncherAstral.class.getCanonicalName() + ".VERSION") != null )
			version = System.getProperty(LauncherAstral.class.getCanonicalName() + ".VERSION");
		else version = "?";
		if ( System.getProperty(LauncherAstral.class.getCanonicalName() + ".SUBVERSION") != null )
			subversion = System.getProperty(LauncherAstral.class.getCanonicalName() + ".SUBVERSION");
		else subversion = "?";
		//
		
		
		FenetreAstral fen = new FenetreAstral("Astralopithecus v" + version + "." + subversion, SQLITE_FILENAME);
		fen.addWindowListener(new WindowAdapter() {
				public void windowClosing(WindowEvent evt) {
					if ( evt.getSource() instanceof FenetreAstral )
						((FenetreAstral) evt.getSource()).quitter();
					else 
						System.exit(0);
				}
		});
		fen.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		
	}
}
