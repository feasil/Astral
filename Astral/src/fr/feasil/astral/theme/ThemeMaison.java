package fr.feasil.astral.theme;

import fr.feasil.astral.data.Maison;
import fr.feasil.astral.data.Signe;

public class ThemeMaison {
	
	private Maison maison;
	private float position;
	private Signe signe;
	
	
	public Maison getMaison() {
		return maison;
	}
	public void setMaison(Maison maison) {
		this.maison = maison;
	}
	
	public float getPosition() {
		return position;
	}
	public void setPosition(float position) {
		this.position = position;
	}
	
	public Signe getSigne() {
		return signe;
	}
	public void setSigne(Signe signe) {
		this.signe = signe;
	}
	
}
