package julien.mechant.effets.gentils;

import jitou.global.BoardGame;
import jitou.global.CitoyenDehors;
import jitou.global.Fanatique;
import julien.game.FXtype;
import julien.mechant.Effet;
import julien.mechant.TypeEffet;

public class ChasseSorcieres extends Effet{

	
	public ChasseSorcieres() {
		variationEnergie[0] = 3;
		variationEnergie[1] = 0;
		variationEnergie[2] = 1;
		
		type = TypeEffet.bien;
	}
	
	public String appliquer() {
		super.appliquer();
		int nb = Fanatique.liste_fanatiques.size();
		if(nb>0){
			int v = (int) (Math.random()*nb);
			julien.game.Game.addFX(Fanatique.liste_fanatiques.get(v), FXtype.sang);
			Fanatique.liste_fanatiques.get(v).tuer();
			return "One of your fanatics was found and thrown out of the bunker.";
		}
		return "You don't have any fanatics in the bunker.";

	}
	
}
