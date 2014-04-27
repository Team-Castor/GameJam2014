package julien.mechant.effets.gentils;

import java.util.ArrayList;

import jitou.global.BoardGame;
import jitou.global.Citoyen;
import jitou.global.CitoyenDehors;
import jitou.global.Fanatique;
import julien.mechant.Effet;
import julien.mechant.TypeEffet;

public class SuperBasket extends Effet{

	double puissanceBasket = 0.1;

	public SuperBasket() {
		variationEnergie[0] = 0;
		variationEnergie[1] = 4;
		variationEnergie[2] = 1;
		
		type = TypeEffet.bien;
	}
	
	public String appliquer() {
		super.appliquer();
		ArrayList<Citoyen> tmp = BoardGame.boardGame.getCitoyens();
		for(int i=0;i<tmp.size();i++){
			tmp.get(i).puissanceBasket(tmp.get(i).getPuissanceBasket()+puissanceBasket);
		}
		
		return" Les citoyens se déplaceront  x"+puissanceBasket+" plus vite";

	}
	
}
