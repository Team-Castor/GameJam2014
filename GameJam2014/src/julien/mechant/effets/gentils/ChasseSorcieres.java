package julien.mechant.effets.gentils;

import jitou.global.CitoyenDehors;
import jitou.global.Fanatique;
import julien.mechant.Effet;
import julien.mechant.TypeEffet;

public class ChasseSorcieres extends Effet{

	
	public ChasseSorcieres() {
		variationEnergie[0] = 3;
		variationEnergie[1] = 0;
		variationEnergie[2] = 1;
		
		type = TypeEffet.bien;
	}
	
	public void appliquer() {
		super.appliquer();
		int nb = Fanatique.liste_fanatiques.size();
		if(nb>0){
			Fanatique.liste_fanatiques.get((int) (Math.random()*nb)).tuer();
		}
	}
	
}
