package fr.feasil.astral.profil;

import fr.feasil.astral.theme.Theme;

public abstract class Profil {
	private String nom;
	private Genre genre;
	private Theme theme;
	private long dateCreation;
	
	public Profil(String nom, Genre genre, Theme theme) {
		this(nom, genre, theme, System.currentTimeMillis());
	}
	public Profil(String nom, Genre genre, Theme theme, long dateCreation) {
		this.nom = nom;
		this.genre = genre;
		this.theme = theme;
		this.dateCreation = dateCreation;
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
	public void setDateCreation(long dateCreation) {
		this.dateCreation = dateCreation;
	}
	public long getDateCreation() {
		return dateCreation;
	}
}
