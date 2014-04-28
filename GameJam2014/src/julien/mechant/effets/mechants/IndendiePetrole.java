package julien.mechant.effets.mechants;

import java.awt.Point;

import jitou.batiments.Generateur;
import jitou.batiments.MineDeFer;
import jitou.batiments.PuitPetrol;
import jitou.global.BoardGame;
import jitou.global.CitoyenDehors;
import jitou.global.Fanatique;
import julien.game.FXtype;
import julien.mechant.Effet;
import julien.mechant.TypeEffet;

public class IndendiePetrole extends Effet{

	double dommage = 7000.0;
	double dommageSecondaire=2000.0;
	
	public IndendiePetrole() {
		variationEnergie[0] = -2;
		variationEnergie[1] = -10;
		variationEnergie[2] = -5;
		
		type = TypeEffet.mal;
	}
	
	public String appliquer() {

		
		super.appliquer();
		int nb = PuitPetrol.listePuitsPetrols.size();
		if(nb>0){
			PuitPetrol f =  PuitPetrol.listePuitsPetrols.get((int) (Math.random()*nb));
			f.setDommage(dommage);
			BoardGame.boardGame.getBatimentAdjacent(f.getPos()).setDommage(dommageSecondaire);
			julien.game.Game.addFX(f, FXtype.explosion);
			
		}
		return "Oil ignites and explodes, causing collateral damages.";
	}
	
}
