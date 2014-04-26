package jitou.batiments;

import java.awt.Point;
import java.util.ArrayList;

import jitou.global.BoardGame;

public class Chaudiere extends Batiment{
	public static ArrayList<Chaudiere> listeChaudieres = new ArrayList<Chaudiere>();


	public Chaudiere(Point pos) {
		super(pos, TypeBatiment.Chaudiere);
		listeChaudieres.add(this);
	}


	public void chauffe() {
		if(this.getTemperatureSalle()<40.0){

			Generateur gen = BoardGame.boardGame.trouverGenerateurAvecElec();
			if(gen !=null){
				double consommation = (45-this.getTemperatureSalle())*0.3;
				if(gen.getElectricite()>consommation){
				this.setTemperatureSalle(45.0);
				gen.consommeElectricite(consommation);
				}
			}
		}
	}



}
