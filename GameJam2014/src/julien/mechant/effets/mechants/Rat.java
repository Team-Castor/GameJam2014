package julien.mechant.effets.mechants;

import jitou.batiments.Chaudiere;
import jitou.batiments.Refectoire;
import jitou.global.CitoyenDehors;
import julien.mechant.Effet;
import julien.mechant.TypeEffet;

public class Rat extends Effet {
	public double nbTourRat    = 15000;
	public double puissanceRat = 0.5;

	public Rat() {
		variationEnergie[0] = 0;
		variationEnergie[1] = 3;
		variationEnergie[2] = 3;
		
		type = TypeEffet.mal;
	}
	
	public void appliquer() {
		super.appliquer();
		Refectoire c = Refectoire.listeRefectoire.get((int) (Math.random()*Refectoire.listeRefectoire.size()));
		c.rat(nbTourRat, puissanceRat);
	}
	
}
