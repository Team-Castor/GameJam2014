package julien.mechant.effets.mechants;

import jitou.global.BoardGame;
import jitou.global.Citoyen;
import jitou.global.CitoyenDehors;
import jitou.global.Fanatique;
import julien.game.FXtype;
import julien.game.Game;
import julien.mechant.Effet;
import julien.mechant.TypeEffet;

public class Cannibalisme extends Effet{
	
	
	public Cannibalisme() {
		variationEnergie[0] = 0;
		variationEnergie[1] = 3;
		variationEnergie[2] = 0;
		
		type = TypeEffet.mal;
	}
	
	public String appliquer() {
		super.appliquer();
		int x = Fanatique.liste_fanatiques.size();

		for(int i=0;i<x;i++){
			Citoyen c = BoardGame.boardGame.getCitoyens().get((int) (Math.random()*BoardGame.boardGame.getCitoyens().size()));
			Game.addFX(c,FXtype.sang);
			c.tuer();
		}
		
		return x+" citoyens sont retrouvés dévorés";

	}
	
}
