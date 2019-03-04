package fr.feasil.astral.theme;

import java.util.ArrayList;
import java.util.List;

import fr.feasil.astral.data.Maison;
import fr.feasil.astral.data.PointFixe;
import fr.feasil.astral.data.Signe;
import fr.feasil.astral.data.Special;

public class ThemePointFixe {
	
	private PointFixe pointFixe;
	private float position;
//	private boolean retrograde;
	private Signe signe;
	private Maison maison;
	
	private List<Special> listeSpecial = new ArrayList<>();
	
	private List<ThemeAspect> listeAspect = new ArrayList<>();
	
	
	
	public PointFixe getPointFixe() {
		return pointFixe;
	}
	public void setPointFixe(PointFixe pointFixe) {
		this.pointFixe = pointFixe;
	}
	
	public float getPosition() {
		return position;
	}
	public void setPosition(float position) {
		this.position = position;
	}
	
	public boolean isRetrograde() {
		return listeSpecial.contains(Special.RETROGRADE);
	}
	public void setRetrograde() {
		listeSpecial.add(Special.RETROGRADE);
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
	
	
	public List<ThemeAspect> getListeAspect() {
		return listeAspect;
	}
	public void addAspect(ThemeAspect aspect) {
		listeAspect.add(aspect);
	}
	public void removeAspect(ThemeAspect aspect) {
		listeAspect.remove(aspect);
	}
}
