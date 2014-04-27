package julien.mechant.effets.mechants;

import jitou.global.BoardGame;
import jitou.global.Citoyen;
import jitou.global.CitoyenDehors;
import jitou.global.Fanatique;
import julien.game.FXtype;
import julien.game.Game;
import julien.mechant.Effet;
import julien.mechant.TypeEffet;

public class NourritureEmpoisonnee extends Effet{
	double poucentage = 0.1;

	public NourritureEmpoisonnee() {
		variationEnergie[0] = -0;
		variationEnergie[1] = -3;
		variationEnergie[2] = -0;

		type = TypeEffet.mal;
	}

	public String appliquer() {
		super.appliquer();
		int x = BoardGame.boardGame.getCitoyens().size();
		int nb = 0;
		for(int i=0;i<x;i++){
			if(Math.random()<poucentage){
				BoardGame.boardGame.getCitoyens().get(i).tuer();
				nb++;
			}
		}

		return x+" citoyens meurent de la nourriture empoisonnee.";

	}

}
