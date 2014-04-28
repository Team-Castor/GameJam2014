package julien.mechant.effets.gentils;

import java.util.ArrayList;
import java.util.List;

import jitou.batiments.Atelier;
import jitou.batiments.Batiment;
import jitou.batiments.Hopital;
import jitou.batiments.MineDeFer;
import jitou.global.BoardGame;
import jitou.global.Citoyen;
import jitou.global.CitoyenDehors;
import jitou.global.Fanatique;
import julien.mechant.Effet;
import julien.mechant.TypeEffet;

public class CapsuleDeSurvivant extends Effet{

	int nb 	 = 2;

	public CapsuleDeSurvivant() {
		variationEnergie[0] = 2;
		variationEnergie[1] = 5;
		variationEnergie[2] = 5;

		type = TypeEffet.bien;
	}

	public String appliquer() {
		super.appliquer();


		ArrayList<Batiment> tmp = BoardGame.boardGame.getListeBatiments();
		for(int i=0;i<nb;i++){
			Batiment b=tmp.get((int) (Math.random()*tmp.size()));
			new Citoyen(b.getPos());
		}
		
		return nb+" new survivors joined the bunker.";
	}
}
