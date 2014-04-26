package julien.mechant.effets;

import jitou.global.CitoyenDehors;
import julien.mechant.Effet;
import julien.mechant.TypeEffet;

public class Armagedon extends Effet{

	double pourcentage = 1.0;
	
	public Armagedon() {
		variationEnergie[0] = 2;
		variationEnergie[1] = 10;
		variationEnergie[2] = 0;
		
		type = TypeEffet.mal;
	}
	
	public void appliquer() {//Pas d'effet gentil !
		super.appliquer();
		int nb = (int) Math.ceil(CitoyenDehors.liste_cit.size()*pourcentage);
		for(int i=0;i<nb;i++){
			CitoyenDehors.liste_cit.get(i).getCitoyen().tuer();
			CitoyenDehors.liste_cit.remove(i);
		}
	}
	
}
