package julien.mechant.effets.gentils;

import java.util.ArrayList;

import jitou.batiments.Hopital;
import jitou.global.BoardGame;
import jitou.global.Citoyen;
import jitou.global.CitoyenDehors;
import jitou.global.Fanatique;
import julien.mechant.Effet;
import julien.mechant.TypeEffet;

public class AvanceeMedicale extends Effet{

	double nbTourRationnement 	 = 500.0;
	double puissanceRationnement = 0.3;

	public AvanceeMedicale() {
		variationEnergie[0] = 0;
		variationEnergie[1] = 4;
		variationEnergie[2] = 1;
		
		type = TypeEffet.bien;
	}
	
	public String appliquer() {
		super.appliquer();
		Hopital.setWorkingTime(Hopital.getWorkingTime()/1.5);
		
		return "Bonus permanent hopitaux";
	}
	
}
