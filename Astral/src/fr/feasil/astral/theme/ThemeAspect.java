package fr.feasil.astral.theme;

import fr.feasil.astral.data.Aspect;
import fr.feasil.astral.data.PointFixe;

public class ThemeAspect {
	
	private PointFixe pointFixePrincipal;
	private Aspect aspect;
	private PointFixe pointFixeSecondaire;
	private float orbe;
	
	
	public PointFixe getPointFixePrincipal() {
		return pointFixePrincipal;
	}
	public void setPointFixePrincipal(PointFixe pointFixePrincipal) {
		this.pointFixePrincipal = pointFixePrincipal;
	}
	
	public Aspect getAspect() {
		return aspect;
	}
	public void setAspect(Aspect aspect) {
		this.aspect = aspect;
	}
	
	public PointFixe getPointFixeSecondaire() {
		return pointFixeSecondaire;
	}
	public void setPointFixeSecondaire(PointFixe pointFixeSecondaire) {
		this.pointFixeSecondaire = pointFixeSecondaire;
	}
	
	public float getOrbe() {
		return orbe;
	}
	public void setOrbe(float orbe) {
		this.orbe = orbe;
	}
}
