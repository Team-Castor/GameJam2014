package jitou.ressources;

public class Ressource {
	private RessourceType typeRessource;
	private double quantite;
	
	public Ressource(RessourceType typeRessource, double quantite ){
		this.typeRessource = typeRessource;
		this.quantite = quantite;
	}

	public RessourceType getTypeRessource() {
		return typeRessource;
	}

	public void setTypeRessource(RessourceType typeRessource) {
		this.typeRessource = typeRessource;
	}

	public double getQuantite() {
		return quantite;
	}

	public void setQuantite(double quantite) {
		this.quantite = quantite;
	}
	
	
}
