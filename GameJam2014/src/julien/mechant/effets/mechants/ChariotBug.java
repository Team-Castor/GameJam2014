package julien.mechant.effets.mechants;

import java.awt.Point;

import jitou.batiments.Generateur;
import jitou.batiments.MineDeFer;
import jitou.global.BoardGame;
import jitou.global.CitoyenDehors;
import jitou.global.Fanatique;
import julien.game.FXtype;
import julien.mechant.Effet;
import julien.mechant.TypeEffet;

public class ChariotBug extends Effet{

	double temps = 4000.0;
	double ratio = 0.5;
	
	public ChariotBug() {
		variationEnergie[0] = 2;
		variationEnergie[1] = 10;
		variationEnergie[2] = 5;
		
		type = TypeEffet.mal;
	}
	
	public String appliquer() {

		
		super.appliquer();
		int nb = MineDeFer.listeMineDeFer.size();
		if(nb>0){
			MineDeFer f =  MineDeFer.listeMineDeFer.get((int) (Math.random()*nb));
			f.setRatioMalus(temps, ratio);
		}
		return "Une mine de fer pruoduit désormais "+ratio+" moins vite";
	}
	
}
