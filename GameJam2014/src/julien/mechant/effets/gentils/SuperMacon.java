package julien.mechant.effets.gentils;

import java.util.ArrayList;

import jitou.batiments.Atelier;
import jitou.batiments.Batiment;
import jitou.batiments.Hopital;
import jitou.batiments.MineDeFer;
import jitou.global.BoardGame;
import jitou.global.Citoyen;
import jitou.global.CitoyenDehors;
import jitou.global.Fanatique;
import julien.mechant.Effet;
import julien.mechant.TypeEffet;

public class SuperMacon extends Effet{

	double nbTour 	 = 500.0;
	double puissance = 2.0;
	public SuperMacon() {
		variationEnergie[0] = 0;
		variationEnergie[1] = 4;
		variationEnergie[2] = 1;

		type = TypeEffet.bien;
	}

	public String appliquer() {
		super.appliquer();

		ArrayList<Atelier> tmp = Atelier.listeAtelier;
		for(int i=0;i<tmp.size();i++){
			tmp.get(i).superMacon(nbTour, puissance);
		}
	
		return "Workshops need fewer tools to build new rooms for some times.";
	}
}
