package julien.mechant;

public abstract class Effet {

	/**
	 * Les effets réels héritent de cette classe
	 */
	
	protected TypeEffet type;
	
	protected int[] variationEnergie = new int[3];
	
	public boolean estJouable() {
		EnergieMal[] ener = Mechant.getInstance().getEnergies();
		if (ener[0].getTotal() > variationEnergie[0] &&
				ener[1].getTotal() > variationEnergie[1] &&
				ener[2].getTotal() > variationEnergie[2]
				) {
			return true;
		}
		return false;
	}
	
	public void appliquer() {
		Mechant.getInstance().changerRessource(TypeEnergie.foi,variationEnergie[0]);
		Mechant.getInstance().changerRessource(TypeEnergie.joie,variationEnergie[1]);
		Mechant.getInstance().changerRessource(TypeEnergie.espoir,variationEnergie[2]);
	}
	
	
	
}


