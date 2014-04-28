package julien.mechant.effets.mechants;

import jitou.global.CitoyenDehors;
import julien.mechant.Effet;
import julien.mechant.TypeEffet;

public class PetitePluieDeMeteroite extends Effet{

	double pourcentage = 0.1;
	
	public PetitePluieDeMeteroite() {
		variationEnergie[0] = 0;
		variationEnergie[1] = -2;
		variationEnergie[2] = 0;
		
		type = TypeEffet.mal;
	}
	
	public String appliquer() {
		super.appliquer();
		int n=0;
		for(int i=0;i<(CitoyenDehors.liste_cit.size());i++){
			if(Math.random()<pourcentage){
				CitoyenDehors.liste_cit.get(i).getCitoyen().tuer();
				n++;i--;
			}
			//CitoyenDehors.liste_cit.remove(i);
		}
		return "The small meteor rain caused the death of "+n+" survivors.";

	}
	
}
