package jitou.batiments;

import java.awt.Point;
import java.text.DecimalFormat;
import java.util.ArrayList;

import jitou.global.Citoyen;
import jitou.global.ObjectifType;

public class Refectoire extends Batiment{
	public static ArrayList<Refectoire> listeRefectoire = new ArrayList<Refectoire>();

	private double quantiteNourriture = 0.0;
	private double tempsManger = 60.0;
	private double tempsRangerNourriture = 200.0;

	private double puissanceRat  = 0.0;
	private double nbTourRat = 0.0;


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
		//System.out.println("Manger ? "+quantiteNourriture);
		super.effet(citoyen, type);

		if(type.getValue()==ObjectifType.manger.getValue()){
			if(quantiteNourriture>=1.0){
				double q = Math.min(quantiteNourriture, 10.0);
				quantiteNourriture-=q;
				citoyen.mange(q);
				citoyen.setWorkingTime(tempsManger);
				//System.out.println("Miam");

			}
		}else if(type.getValue()==ObjectifType.rapporterNourritureRefectoir.getValue()){
			if(quantiteNourriture>=0.0){
				double q = citoyen.getRessourceTransporte().getQuantite();
				citoyen.setRessourceTransporte(null);
				quantiteNourriture+=q;
				citoyen.setWorkingTime(tempsRangerNourriture);
			}
		}
		else{
			//System.out.println("Refectoire type d'action non comprit...:s");
		}
		
	}

	public void update(int delta){
		if(nbTourRat>0) nbTourRat-=delta;
		else puissanceRat=0.0;
		
		quantiteNourriture -= delta/2000.0 + puissanceRat*delta;
		quantiteNourriture = Math.max(0, quantiteNourriture);
	}
	
	public void back(Citoyen c) {
		if( c.getObjectif().getType().getValue()==ObjectifType.rapporterNourritureRefectoir.getValue() ||
				c.getObjectif().getType().getValue()==ObjectifType.manger.getValue()){

			c.getObjectif().reset();
		}
		else 		super.back(c);
	}

	public String info() {
		DecimalFormat df = new DecimalFormat("000.0");

		return "Nourriture: "+df.format(quantiteNourriture);
	}

	public void rat(double nbTourRat, double puissanceRat) {
		this.nbTourRat = nbTourRat;
		this.puissanceRat = puissanceRat;

	}

}

