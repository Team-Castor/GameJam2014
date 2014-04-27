package julien.mechant.effets.mechants;

import java.util.ArrayList;

import jitou.batiments.Batiment;
import jitou.global.BoardGame;
import jitou.global.Citoyen;
import jitou.global.CitoyenDehors;
import jitou.global.Ennemi;
import jitou.global.Fanatique;
import jitou.global.ObjectifType;
import julien.mechant.Effet;
import julien.mechant.TypeEffet;

public class ReveilZombie extends Effet{
	
	double temps = 1200.0;
	
	public ReveilZombie() {
		variationEnergie[0] = 0;
		variationEnergie[1] = 6;
		variationEnergie[2] = 0;
		
		type = TypeEffet.mal;
	}
	
	public void appliquer() {
		super.appliquer();

		ArrayList<Citoyen> tmp = BoardGame.boardGame.getCitoyens();
		for(int i=0;i<tmp.size();i++){
			tmp.get(i).reveilZombie(temps);
		}

		
	}
	
}
