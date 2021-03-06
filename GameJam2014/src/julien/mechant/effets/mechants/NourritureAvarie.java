package julien.mechant.effets.mechants;

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

public class NourritureAvarie extends Effet{


	double gain 	 = 10.0+Math.random()*20.0;

	public NourritureAvarie() {
		variationEnergie[0] = -0;
		variationEnergie[1] = -4;
		variationEnergie[2] = -1;

		type = TypeEffet.mal;
	}

	public String appliquer() {
		super.appliquer();

		ArrayList<Refectoire> tmp = Refectoire.listeRefectoire;
		int n = (int) (Math.random()*tmp.size());
		tmp.get(n).setQuantiteNourriture(tmp.get(n).getQuantiteNourriture()-gain);

		return gain+" foods is wasted because it is rotten.";
	}
}
