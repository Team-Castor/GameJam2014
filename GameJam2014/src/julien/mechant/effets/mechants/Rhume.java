package julien.mechant.effets.mechants;

import java.util.ArrayList;

import jitou.global.BoardGame;
import jitou.global.Citoyen;
import jitou.global.Fanatique;
import jitou.global.Maladie;
import julien.mechant.Effet;
import julien.mechant.TypeEffet;

public class Rhume extends Effet{
	private double contamination = 0.1;
	private double malusVitesse=0.7;  //delta
	private double malusTempsTravail=5.0; //delta
	private double malusFatigue=0.01;  //delta
	private double perteChaleur=0.0001; //temperatureDirecte
	
	private Maladie mal = new Maladie(malusVitesse, malusTempsTravail, malusFatigue, perteChaleur);
	public Rhume() {
		variationEnergie[0] = 0;
		variationEnergie[1] = 0;
		variationEnergie[2] = 2;
		
		type = TypeEffet.mal;
	}
	
	public String appliquer() {
		super.appliquer();
		ArrayList<Citoyen> tmp = BoardGame.boardGame.getCitoyens();
		int nb=0;
		for(int i=0;i<tmp.size();i++){
			if(Math.random()<contamination){
			tmp.get(i).setMaladie(new Maladie(mal));
			nb++;}
		}
		
		return nb+" citoyens tombent malade";

	}
	
}