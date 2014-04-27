package julien.mechant.effets.gentils;

import java.util.ArrayList;

import jitou.global.BoardGame;
import jitou.global.Citoyen;
import jitou.global.CitoyenDehors;
import jitou.global.Fanatique;
import julien.mechant.Effet;
import julien.mechant.TypeEffet;

public class Rationnement extends Effet{

	double nbTourRationnement 	 = 500.0;
	double puissanceRationnement = 0.3;

	public Rationnement() {
		variationEnergie[0] = 0;
		variationEnergie[1] = 4;
		variationEnergie[2] = 1;
		
		type = TypeEffet.bien;
	}
	
	public void appliquer() {
		super.appliquer();
		ArrayList<Citoyen> tmp = BoardGame.boardGame.getCitoyens();
		for(int i=0;i<tmp.size();i++){
			tmp.get(i).rationnement(nbTourRationnement, puissanceRationnement);
		}
	}
	
}
