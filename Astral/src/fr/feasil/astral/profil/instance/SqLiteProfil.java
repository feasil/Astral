package fr.feasil.astral.profil.instance;

import fr.feasil.astral.profil.Genre;
import fr.feasil.astral.profil.Profil;
import fr.feasil.astral.theme.Theme;

public class SqLiteProfil extends Profil {
	private int id;
	private boolean registered;
	
	SqLiteProfil() {
		this(null, null, null);
	}
	
	SqLiteProfil(String nom, Genre genre, Theme theme) {
		super(nom, genre, theme);
		registered = false;
	}
	SqLiteProfil(int id, String nom, Genre genre, Theme theme, long dateCreation) {
		super(nom, genre, theme, dateCreation);
		this.id = id;
		registered = true;
	}
	
	int getId() {
		return id;
	}
	void setId(int id) {
		this.id = id;
	}
	boolean isRegistered() {
		return registered;
	}
	void setRegistered(boolean registered) {
		this.registered = registered;
	}
	
}