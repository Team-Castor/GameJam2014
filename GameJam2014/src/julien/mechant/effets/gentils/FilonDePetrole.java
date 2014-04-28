package julien.mechant.effets.gentils;

import java.util.ArrayList;

import jitou.batiments.Atelier;
import jitou.batiments.Hopital;
import jitou.batiments.PuitPetrol;
import jitou.global.BoardGame;
import jitou.global.Citoyen;
import jitou.global.CitoyenDehors;
import jitou.global.Fanatique;
import julien.mechant.Effet;
import julien.mechant.TypeEffet;

public class FilonDePetrole extends Effet{

	double nbTour 	 = 500.0;
	double puissance = 0.3;

	public FilonDePetrole() {
		variationEnergie[0] = 3;
		variationEnergie[1] = 0;
		variationEnergie[2] = 0;

		type = TypeEffet.bien;
	}

	public String appliquer() {
		super.appliquer();


		ArrayList<PuitPetrol> tmp = PuitPetrol.listePuitsPetrols;
		for(int i=0;i<tmp.size();i++){
			tmp.get(i).filon(nbTour, puissance);
		}
		
		return tmp.size()+" Oil wells produce "+puissance+" more oil during "+nbTour+" turns.";


	}
}
