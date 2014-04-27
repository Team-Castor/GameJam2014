package julien.mechant.effets.gentils;

import java.util.ArrayList;

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

public class EscouadeDeReparation extends Effet{

	int nbBatiment 	 = 1;

	public EscouadeDeReparation() {
		variationEnergie[0] = 0;
		variationEnergie[1] = 4;
		variationEnergie[2] = 1;

		type = TypeEffet.bien;
	}

	public String appliquer() {
		super.appliquer();
		
		int nb = 0;
		ArrayList<Batiment> tmp = BoardGame.boardGame.getListeBatiments();
		for(int i=0;i<tmp.size() && nbBatiment>0;i++){
			if(tmp.get(i).getDommage()>0){
				nbBatiment--;
				tmp.get(i).setDommage(-1);
				nb++;
			}
		}
		
		return "Reparation de "+nb+" batiments";
	}
}
