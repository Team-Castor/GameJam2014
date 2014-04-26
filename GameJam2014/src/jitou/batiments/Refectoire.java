package jitou.batiments;

import java.awt.Point;
import java.util.ArrayList;

public class Refectoire extends Batiment{
	public static ArrayList<Refectoire> listeRefectoire = new ArrayList<Refectoire>();
	
	private double quantiteNourriture = 0.0;
	
	public Refectoire(Point pos) {
		super(pos, TypeBatiment.Refectoire);
		listeRefectoire.add(this);
	}

	public double getQuantiteNourriture() {
		return quantiteNourriture;
	}

	public void setQuantiteNourriture(double quantiteNourriture) {
		this.quantiteNourriture = quantiteNourriture;
	}
	
	
}

