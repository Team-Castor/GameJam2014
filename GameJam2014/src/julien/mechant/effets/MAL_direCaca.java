package julien.mechant.effets;

import julien.mechant.Effet;
import julien.mechant.TypeEffet;

public class MAL_direCaca extends Effet{

	public MAL_direCaca() {
		variationEnergie[0] = -10;
		variationEnergie[1] = -10;
		variationEnergie[2] = -10;

		type = TypeEffet.mal;
	}
	
	public void appliquer() {
		super.appliquer();
		System.out.println("caca");
	}
	
}
