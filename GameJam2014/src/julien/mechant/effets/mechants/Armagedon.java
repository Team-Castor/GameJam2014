package julien.mechant.effets.mechants;

import jitou.global.CitoyenDehors;
import julien.mechant.Effet;
import julien.mechant.TypeEffet;

public class Armagedon extends Effet{

	double pourcentage = 1.0;
	
	public Armagedon() {
		variationEnergie[0] = -2;
		variationEnergie[1] = -10;
		variationEnergie[2] = -0;
		
		type = TypeEffet.mal;
	}
	
	public String appliquer() {
		super.appliquer();
		int nb =(CitoyenDehors.liste_cit.size());
		int n=0;
		for(int i=0;i<nb;i++){
			if(Math.random()<pourcentage){
				CitoyenDehors.liste_cit.get(i).getCitoyen().tuer();
				n++;
			}
			//CitoyenDehors.liste_cit.remove(i);
		}

		return n+" survivors outside of the bunker were killed by Armageddon!";

	}
	
}
