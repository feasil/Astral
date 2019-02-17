package fr.feasil.astral.association;

import fr.feasil.astral.data.PointFixe;
import fr.feasil.astral.data.Maison;
import fr.feasil.astral.data.Signe;

public class ObjetSigneMaison {
	
	private PointFixe objetCeleste;
	private Signe signe;
	private Maison maison;
	
	
	public PointFixe getObjetCeleste() {
		return objetCeleste;
	}
	public void setObjetCeleste(PointFixe objetCeleste) {
		this.objetCeleste = objetCeleste;
	}
	
	public Signe getSigne() {
		return signe;
	}
	public void setSigne(Signe signe) {
		this.signe = signe;
	}
	
	public Maison getMaison() {
		return maison;
	}
	public void setMaison(Maison maison) {
		this.maison = maison;
	}
}
