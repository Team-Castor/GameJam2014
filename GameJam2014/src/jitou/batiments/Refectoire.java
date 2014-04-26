package jitou.batiments;

import java.awt.Point;
import java.util.ArrayList;

import jitou.global.Citoyen;
import jitou.global.ObjectifType;

public class Refectoire extends Batiment{
	public static ArrayList<Refectoire> listeRefectoire = new ArrayList<Refectoire>();
	
	private double quantiteNourriture = 0.0;
	private double tempsManger = 60.0;
	private double tempsRangerNourriture = 60.0;

	
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
	
	public void effet(Citoyen citoyen, ObjectifType type) {
		if(type.getValue()==ObjectifType.manger.getValue()){
			if(quantiteNourriture>1.0){
				double q = 1.0;
				quantiteNourriture-=q;
				citoyen.mange(1.0);
				citoyen.setWorkingTime(tempsManger);
			}
		}else if(type.getValue()==ObjectifType.rapporterNourritureRefectoir.getValue()){
			if(quantiteNourriture>1.0){
				double q = citoyen.getRessourceTransporte().getQuantite();
				quantiteNourriture+=q;
				citoyen.setWorkingTime(tempsRangerNourriture);
			}
		}
		
		System.out.println("Refectoire type d'action non comprit...:s");
	}
	
}

