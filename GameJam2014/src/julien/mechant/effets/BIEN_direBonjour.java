package julien.mechant.effets;

import julien.mechant.Effet;
import julien.mechant.TypeEffet;

public class BIEN_direBonjour extends Effet{

	public BIEN_direBonjour() {
		variationEnergie[0] = 10;
		variationEnergie[1] = 10;
		variationEnergie[2] = 10;
		
		type = TypeEffet.bien;
	}
	
	public void appliquer() {
		super.appliquer();
		System.out.println("bonjour");
	}
	
}
