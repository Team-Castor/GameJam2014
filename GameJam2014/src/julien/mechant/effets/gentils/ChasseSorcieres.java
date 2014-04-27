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
			Fanatique.liste_fanatiques.get(v).tuer();
			julien.game.Game.addFX(Fanatique.liste_fanatiques.get(v), FXtype.sang);
			return "Un fanatique est trouvé!";
		}
		return "Il n'y a pas de fanatique!";

	}
	
}
