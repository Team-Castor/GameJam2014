package julien.mechant.effets.mechants;

import jitou.global.BoardGame;
import jitou.global.Citoyen;
import jitou.global.CitoyenDehors;
import jitou.global.Fanatique;
import julien.mechant.Effet;
import julien.mechant.TypeEffet;

public class VagueFanatique extends Effet{
	
	int x = 1;
	
	public VagueFanatique() {
		variationEnergie[0] = 0;
		variationEnergie[1] = -3;
		variationEnergie[2] = 0;
		
		type = TypeEffet.mal;
	}
	
	public String appliquer() {
		super.appliquer();
		
		for(int i=0;i<x;i++){
			Citoyen c = BoardGame.boardGame.getCitoyens().get((int) (Math.random()*BoardGame.boardGame.getCitoyens().size()));
			c.tuer();
			BoardGame.boardGame.getCitoyens().add(new Fanatique(c.getPos()));

		}
		return x+" survivors join your cult.";
	}
	
}
