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

public class FilonDeFer extends Effet{
	double nbTour 	 = 500.0;
	double puissance = 0.3;
	double gain 	 = 10.0+Math.random()*20.0;

	public FilonDeFer() {
		variationEnergie[0] = 0;
		variationEnergie[1] = 4;
		variationEnergie[2] = 1;

		type = TypeEffet.bien;
	}

	public String appliquer() {
		super.appliquer();



		ArrayList<MineDeFer> tmp = MineDeFer.listeMineDeFer;
		for(int i=0;i<tmp.size();i++){
			tmp.get(i).filonDeFer(nbTour, puissance);
		}
		
return tmp.size()+" mines de fer produiront "+puissance+" metaux en plus pendant "+nbTour+" tours";

	}
}
