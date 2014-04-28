package julien.mechant;

public abstract class Effet {

	/**
	 * Les effets réels héritent de cette classe
	 * bla
	 */
	
	protected TypeEffet type;
	
	protected int[] variationEnergie = new int[3];
	
	public boolean estJouable() {
		EnergieMal[] ener = Mechant.getInstance().getEnergies();
		if (ener[0].getTotal() >= -1*variationEnergie[0] &&
				ener[1].getTotal() >= -1*variationEnergie[1] &&
				ener[2].getTotal() >= -1*variationEnergie[2]
				) {
			return true;
		}
		return false;
	}
	
	public String appliquer() {
		Mechant.getInstance().changerRessource(TypeEnergie.foi,variationEnergie[0]);
		Mechant.getInstance().changerRessource(TypeEnergie.joie,variationEnergie[1]);
		Mechant.getInstance().changerRessource(TypeEnergie.espoir,variationEnergie[2]);
		
		return "non";
	}
	
	public int[] getVariationEnergie(){
		return variationEnergie;
	}
	
	
}


