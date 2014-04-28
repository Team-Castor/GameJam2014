package julien.mechant.effets.mechants;

import java.awt.Point;

import jitou.batiments.Generateur;
import jitou.batiments.MineDeFer;
import jitou.global.BoardGame;
import jitou.global.CitoyenDehors;
import jitou.global.Fanatique;
import julien.game.FXtype;
import julien.game.Game;
import julien.mechant.Effet;
import julien.mechant.TypeEffet;

public class SabotageDuGrappinAscenseur extends Effet{


	public SabotageDuGrappinAscenseur() {
		variationEnergie[0] = -2;
		variationEnergie[1] = -10;
		variationEnergie[2] = -5;

		type = TypeEffet.mal;
	}

	public String appliquer() {
		super.appliquer();

		int nb = Fanatique.liste_fanatiques.size();
		if(nb>0){
			Fanatique f =  Fanatique.liste_fanatiques.get((int) (Math.random()*nb));
			Point p = f.getPos();
			BoardGame.boardGame.getBatiment(p.x, p.y).setTuerPopulationSurPlace();
//			Game.getInstance().addFX(BoardGame.boardGame.getBatiment(p.x, p.y), FXtype.explosion);
			return "A fanatic sabotaged an elevator ... but dies with the other survivors";

		}

		
		return "There are no fanatic to sabotage something.";


	}
}
