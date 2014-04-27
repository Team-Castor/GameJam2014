package julien.mechant.effets.mechants;

import jitou.global.BoardGame;
import jitou.global.Citoyen;
import jitou.global.CitoyenDehors;
import jitou.global.Fanatique;
import jitou.global.ObjectifType;
import julien.mechant.Effet;
import julien.mechant.TypeEffet;

public class VolOutils extends Effet{
	
	
	public VolOutils() {
		variationEnergie[0] = 0;
		variationEnergie[1] = -3;
		variationEnergie[2] = 0;
		
		type = TypeEffet.mal;
	}
	
	public String appliquer() {
		super.appliquer();
		int x = Fanatique.liste_fanatiques.size();

		for(int i=0;i<x;i++){
			 Fanatique.liste_fanatiques.get(i).ordonner(ObjectifType.allerVolerOutils);
		}
		
		return x+" de vos serviteurs vont aller voler des outils";

	}
	
}
