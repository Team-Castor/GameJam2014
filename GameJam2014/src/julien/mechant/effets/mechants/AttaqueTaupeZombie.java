package julien.mechant.effets.mechants;

import jitou.batiments.Batiment;
import jitou.global.BoardGame;
import jitou.global.Citoyen;
import jitou.global.CitoyenDehors;
import jitou.global.Ennemi;
import jitou.global.Fanatique;
import jitou.global.ObjectifType;
import julien.mechant.Effet;
import julien.mechant.TypeEffet;

public class AttaqueTaupeZombie extends Effet{
	
	
	public AttaqueTaupeZombie() {
		variationEnergie[0] = -0;
		variationEnergie[1] = -6;
		variationEnergie[2] = -0;
		
		type = TypeEffet.mal;
	}
	
	public String appliquer() {
		super.appliquer();

		Batiment p = BoardGame.boardGame.randomSalle();
		new Ennemi(p.getPos().x, p.getPos().y, Ennemi.EnnemiType.tompeZombi);

		return " Une tombe zombie attaque!";

	}
	
}
