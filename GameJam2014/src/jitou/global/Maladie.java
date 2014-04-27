package jitou.global;

public class Maladie {
	private double malusVitesse;
	private double malusTempsTravail;
	private double malusFatigue;
	private double perteChaleur;
	
	public Maladie(double malusVitesse, double malusTempsTravail,
			double malusFatigue, double perteChaleur) {
		super();
		this.malusVitesse = malusVitesse;
		this.malusTempsTravail = malusTempsTravail;
		this.malusFatigue = malusFatigue;
		this.setPerteChaleur(perteChaleur);
	}

	public double getPerteChaleur() {
		return perteChaleur;
	}

	public void setPerteChaleur(double perteChaleur) {
		this.perteChaleur = perteChaleur;
	}

	public double getMalusVitesse() {
		return malusVitesse;
	}

	public void setMalusVitesse(double malusVitesse) {
		this.malusVitesse = malusVitesse;
	}

	public double getMalusTempsTravail() {
		return malusTempsTravail;
	}

	public void setMalusTempsTravail(double malusTempsTravail) {
		this.malusTempsTravail = malusTempsTravail;
	}

	public double getMalusFatigue() {
		return malusFatigue;
	}

	public void setMalusFatigue(double malusFatigue) {
		this.malusFatigue = malusFatigue;
	}

	
	
	
}
