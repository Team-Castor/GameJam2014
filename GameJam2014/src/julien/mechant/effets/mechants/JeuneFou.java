package julien.mechant.effets.mechants;

import jitou.global.BoardGame;
import jitou.global.CitoyenDehors;
import jitou.global.Fanatique;
import julien.mechant.Effet;
import julien.mechant.TypeEffet;

public class JeuneFou extends Effet{
	
	public JeuneFou() {
		variationEnergie[0] = -0;
		variationEnergie[1] = -0;
		variationEnergie[2] = -0;
		
		type = TypeEffet.mal;
	}
	
	public String appliquer() {
		super.appliquer();
		BoardGame.boardGame.getCitoyens().add(new Fanatique(BoardGame.boardGame.trouverSortie().getPos()));
		
		return "Un fanatique arrive";

	}
	
}
