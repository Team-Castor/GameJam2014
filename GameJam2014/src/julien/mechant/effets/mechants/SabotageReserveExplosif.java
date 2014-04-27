package julien.mechant.effets.mechants;

import java.awt.Point;

import jitou.batiments.Generateur;
import jitou.batiments.MineDeFer;
import jitou.global.BoardGame;
import jitou.global.CitoyenDehors;
import jitou.global.Fanatique;
import julien.mechant.Effet;
import julien.mechant.TypeEffet;

public class SabotageReserveExplosif extends Effet{

	double dommage = 7000.0;
	double dommageSecondaire=2000.0;
	
	public SabotageReserveExplosif() {
		variationEnergie[0] = 2;
		variationEnergie[1] = 10;
		variationEnergie[2] = 5;
		
		type = TypeEffet.mal;
	}
	
	public void appliquer() {
		super.appliquer();
		int nb = Generateur.listeGenerateurs.size();
		if(nb>0){
			Generateur f =  Generateur.listeGenerateurs.get((int) (Math.random()*nb));
			f.setTuerPopulationSurPlace();
		}

	}
	
}
