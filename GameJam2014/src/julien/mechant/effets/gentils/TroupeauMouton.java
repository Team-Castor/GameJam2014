package julien.mechant.effets.gentils;

import java.util.ArrayList;

import jitou.batiments.Atelier;
import jitou.batiments.Hopital;
import jitou.batiments.MineDeFer;
import jitou.batiments.Refectoire;
import jitou.global.BoardGame;
import jitou.global.Citoyen;
import jitou.global.CitoyenDehors;
import jitou.global.Fanatique;
import julien.mechant.Effet;
import julien.mechant.TypeEffet;

public class TroupeauMouton extends Effet{


	double gain 	 = 20.0+Math.random()*20.0;

	public TroupeauMouton() {
		variationEnergie[0] = 0;
		variationEnergie[1] = 4;
		variationEnergie[2] = 1;

		type = TypeEffet.bien;
	}

	public String appliquer() {
		super.appliquer();

		ArrayList<Refectoire> tmp = Refectoire.listeRefectoire;
		for(int i=0;i<tmp.size();i++){
			tmp.get(i).setQuantiteNourriture(tmp.get(i).getQuantiteNourriture()+gain);
		}
		return "Le mouton rapporte "+gain+" nourriture e tous les refrectoires";
	}
}
