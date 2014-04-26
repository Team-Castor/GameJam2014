package jitou.batiments;

import java.awt.Point;
import java.util.ArrayList;

import jitou.global.BoardGame;
import julien.mechant.effets.PanneChaudiere;

public class Chaudiere extends Batiment{
	public static ArrayList<Chaudiere> listeChaudieres = new ArrayList<Chaudiere>();
	private PanneChaudiere panne=null;

	public Chaudiere(Point pos) {
		super(pos, TypeBatiment.Chaudiere);
		listeChaudieres.add(this);
	}


	public void chauffe() {
		double malus=0.0;
		if(panne!=null) malus = panne.nbDelta*40.0;

		if(this.getTemperatureSalle()<40.0-malus){

			Generateur gen = BoardGame.boardGame.trouverGenerateurAvecElec();
			if(gen !=null){
				double consommation = (45-malus-this.getTemperatureSalle())*0.3;
				if(gen.getElectricite()>consommation){
				this.setTemperatureSalle(45.0-malus);
				gen.consommeElectricite(consommation);
				}
			}
		}
	}

	public void update(int delta){
		if(panne!=null){
			panne.nbTour -= delta;
			if(panne.nbTour<0) panne=null;

		}
	}
	
	public void infligerPenalite(PanneChaudiere panne) {
		this.panne = panne;
		}



}
