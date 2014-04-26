package julien.mechant.effets;

import jitou.global.CitoyenDehors;
import julien.mechant.Effet;
import julien.mechant.TypeEffet;

public class PetitePluieDeMeteroite extends Effet{

	double pourcentage = 0.1;
	
	public PetitePluieDeMeteroite() {
		variationEnergie[0] = 0;
		variationEnergie[1] = 2;
		variationEnergie[2] = 0;
		
		type = TypeEffet.mal;
	}
	
	public void appliquer() {
		super.appliquer();
		int nb = (int) Math.ceil(CitoyenDehors.liste_cit.size()*pourcentage);
		for(int i=0;i<nb;i++){
			CitoyenDehors.liste_cit.get(i).getCitoyen().tuer();
			CitoyenDehors.liste_cit.remove(i);
		}
	}
	
}
