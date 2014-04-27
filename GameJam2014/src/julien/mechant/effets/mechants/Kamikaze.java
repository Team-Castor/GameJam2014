package julien.mechant.effets.mechants;

import java.awt.Point;

import jitou.global.BoardGame;
import jitou.global.CitoyenDehors;
import jitou.global.Fanatique;
import julien.mechant.Effet;
import julien.mechant.TypeEffet;

public class Kamikaze extends Effet{

	double dommage = 7000.0;
	
	public Kamikaze() {
		variationEnergie[0] = 5;
		variationEnergie[1] = 4;
		variationEnergie[2] = 0;
		
		type = TypeEffet.mal;
	}
	
	public void appliquer() {
		super.appliquer();
		int nb = Fanatique.liste_fanatiques.size();
		if(nb>0){
			Fanatique f =  Fanatique.liste_fanatiques.get((int) (Math.random()*nb));
			Point p = f.getPos();
			BoardGame.boardGame.getBatiment(p.x, p.y).setDommage(dommage);
			f.tuer();
		}

	}
	
}
