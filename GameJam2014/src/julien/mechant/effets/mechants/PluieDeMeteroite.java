package julien.mechant.effets.mechants;

import jitou.global.CitoyenDehors;
import julien.mechant.Effet;
import julien.mechant.TypeEffet;

public class PluieDeMeteroite extends Effet{

	double pourcentage = 0.7;

	public PluieDeMeteroite() {
		variationEnergie[0] = -1;
		variationEnergie[1] = -5;
		variationEnergie[2] = 0;

		type = TypeEffet.mal;
	}

	public String appliquer() {//Pas d'effet gentil !
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

		return "The meteor rain caused the death of "+n+" survivors.";

	}

}
