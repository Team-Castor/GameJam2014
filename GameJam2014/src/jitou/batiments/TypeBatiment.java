package jitou.batiments;

public enum TypeBatiment {
	Refectoire(0,"Réfectoire" ), Generateur(1, "Générateur"), Dortoir(2, "Dortoir"),
	Atelier(3, "Atelier"), Arsenal(4, "Arsenal"), Chaudiere(5, "Chaudière"),
	FermeHydroponique(6, "Ferme hydroponique"), Hopital(7, "Hôpital");


	private final int value;
	private final String nom;

	private TypeBatiment(int value, String nom) {
		this.value = value;
		this.nom=nom;
	}
	public String getNom() {
		return nom;
	}
	public int getValue() {
		return value;
	}
}
