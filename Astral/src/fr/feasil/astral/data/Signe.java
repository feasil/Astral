package fr.feasil.astral.data;

public enum Signe {
	BELIER("Bélier", "♈", "21/03", "20/04", Element.FEU), 
	TAUREAU("Taureau", "♉", "21/04", "21/05", Element.TERRE), 
	GEMEAUX("Gémeaux", "♊", "22/05", "21/06", Element.AIR), 
	CANCER("Cancer", "♋", "22/06", "22/07", Element.EAU), 
	LION("Lion", "♌", "23/07", "22/08", Element.FEU), 
	VIERGE("Vierge", "♍", "23/08", "22/09", Element.TERRE), 
	BALANCE("Balance", "♎", "23/09", "22/10", Element.AIR), 
	SCORPION("Scorpion", "♏", "23/10", "22/11", Element.EAU), 
	SAGITTAIRE("Sagittaire", "♐", "23/11", "21/12", Element.FEU), 
	CAPRICORNE("Capricorne", "♑", "22/12", "20/01", Element.TERRE), 
	VERSEAU("Verseau", "♒", "21/01", "19/02", Element.AIR), 
	POISSONS("Poissons", "♓", "20/02", "20/03", Element.EAU);
	
	
	private String nom;
	private String symbole;
	private String dateDebut;
	private String dateFin;
	private Element element;
	
	private Signe(String nom, String symbole, String dateDebut, String dateFin, Element element) {
		this.nom = nom;
		this.symbole = symbole;
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
		this.element = element;
	}
	
	public String getNom() {
		return nom;
	}
	public String getSymbole() {
		return symbole;
	}
	public String getDateDebut() {
		return dateDebut;
	}
	public String getDateFin() {
		return dateFin;
	}
	public Element getElement() {
		return element;
	}
}
