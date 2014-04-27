package julien.mechant.effets.mechants;

import jitou.batiments.Chaudiere;
import jitou.global.CitoyenDehors;
import julien.mechant.Effet;
import julien.mechant.TypeEffet;

public class PanneChaudiere extends Effet {
	public double nbTour  = 15000;
	public double nbDelta = 0.5;

	public PanneChaudiere() {
		variationEnergie[0] = 0;
		variationEnergie[1] = 3;
		variationEnergie[2] = 3;
		
		type = TypeEffet.mal;
	}
	
	public String appliquer() {
		super.appliquer();
		Chaudiere c = Chaudiere.listeChaudieres.get((int) (Math.random()*Chaudiere.listeChaudieres.size()));
		c.infligerPenalite(this);
		
		return "La chaudière est en panne, il faudra bien "+nbTour+" tours pour la réparer";

	}
	
}
