package julien.mechant.effets.mechants;

import jitou.global.BoardGame;
import jitou.global.Citoyen;
import jitou.global.CitoyenDehors;
import jitou.global.Fanatique;
import julien.mechant.Effet;
import julien.mechant.TypeEffet;

public class Cannibalisme extends Effet{
	
	
	public Cannibalisme() {
		variationEnergie[0] = 0;
		variationEnergie[1] = 3;
		variationEnergie[2] = 0;
		
		type = TypeEffet.mal;
	}
	
	public void appliquer() {
		super.appliquer();
		int x = Fanatique.liste_fanatiques.size();

		for(int i=0;i<x;i++){
			Citoyen c = BoardGame.boardGame.getCitoyens().get((int) (Math.random()*BoardGame.boardGame.getCitoyens().size()));
			c.tuer();
		}
	}
	
}
