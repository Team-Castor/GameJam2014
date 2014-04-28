package julien.mechant.effets.mechants;

import java.awt.Point;

import jitou.batiments.Generateur;
import jitou.batiments.MineDeFer;
import jitou.global.BoardGame;
import jitou.global.CitoyenDehors;
import jitou.global.Fanatique;
import julien.game.FXtype;
import julien.mechant.Effet;
import julien.mechant.TypeEffet;

public class FuiteRadiationGenerateur extends Effet{


	public FuiteRadiationGenerateur() {
		variationEnergie[0] = -2;
		variationEnergie[1] = -10;
		variationEnergie[2] = -5;

		type = TypeEffet.mal;
	}

	public String appliquer() {
		super.appliquer();

		int nb = Generateur.listeGenerateurs.size();
		if(nb>0){
			Generateur f =  Generateur.listeGenerateurs.get((int) (Math.random()*nb));
			f.setTuerPopulationSurPlace();
			julien.game.Game.addFX(f, FXtype.explosion);

		}
		
		return "A generator explodes. BOOM!";


	}
}
