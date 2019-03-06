package fr.feasil.astral.profil;

import fr.feasil.astral.theme.Theme;

public class Profil {
	
	private String nom;
	private Genre genre;
	private Theme theme;
	
	
	public Profil() {
		this(null, null, null);
	}
	
	public Profil(String nom, Genre genre, Theme theme) {
		this.nom = nom;
		this.genre = genre;
		this.theme = theme;
	}
	
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getNom() {
		return nom;
	}
	public void setGenre(Genre genre) {
		this.genre = genre;
	}
	public Genre getGenre() {
		return genre;
	}
	public void setTheme(Theme theme) {
		this.theme = theme;
	}
	public Theme getTheme() {
		return theme;
	}
}
