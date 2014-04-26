package julien.mechant;

import java.util.ArrayList;

import julien.game.Game;
import julien.mechant.effets.BIEN_direBonjour;
import julien.mechant.effets.MAL_direCaca;

public class Mechant {

	static Mechant instance;
	private int tailleMain = 5;
	private ArrayList<Carte> cartes = new ArrayList<Carte>();
	
	EnergieMal[] energies = new EnergieMal[3];
	
	
	public Mechant() {
		instance = this;
		energies[0] = new EnergieMal(TypeEnergie.foi,100);
		energies[1] = new EnergieMal(TypeEnergie.joie,100);
		energies[2] = new EnergieMal(TypeEnergie.espoir,100);
	}
	
	public void premierePioche() {
		for (int i = 0 ; i < tailleMain ; i++) {
			piocher();
		}
	}

	public static Mechant getInstance() {
		return instance;
	}

	public void defausser(Carte carte) {
		cartes.remove(carte);
		piocher(); //On recomplete la main		
	}
	
	public void piocher() {
		cartes.add(new Carte(new BIEN_direBonjour() , new MAL_direCaca()));
		Game.getInstance().redefinirLesCartes();
	}

	public void changerRessource(TypeEnergie type, int i) {
		energies[type.getValue()].addTotal(i);;	
	}

	public int getTailleMain() {
		return tailleMain;
	}

	public void setTailleMain(int tailleMain) {
		this.tailleMain = tailleMain;
	}

	public ArrayList<Carte> getCartes() {
		return cartes;
	}

	public void setCartes(ArrayList<Carte> cartes) {
		this.cartes = cartes;
	}

	public EnergieMal[] getEnergies() {
		return energies;
	}

	public void setEnergies(EnergieMal[] energies) {
		this.energies = energies;
	}
	
	
	
	
}
