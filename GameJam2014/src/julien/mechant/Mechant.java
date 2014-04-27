package julien.mechant;

import java.util.ArrayList;

import julien.game.Game;
import julien.mechant.effets.*;
import julien.mechant.effets.mechants.Armagedon;
import julien.mechant.effets.mechants.AttaqueTaupeZombie;
import julien.mechant.effets.mechants.Cannibalisme;
import julien.mechant.effets.mechants.JeuneFou;
import julien.mechant.effets.mechants.PanneChaudiere;
import julien.mechant.effets.mechants.PetitePluieDeMeteroite;
import julien.mechant.effets.mechants.PluieDeMeteroite;
import julien.mechant.effets.mechants.VolOutils;

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
	
	public Effet piocherMauvaise(){
		Effet[] ef = {new Armagedon(),new Cannibalisme(), new JeuneFou(),
				new PanneChaudiere(), new PetitePluieDeMeteroite(), new PluieDeMeteroite(),
				new VolOutils(), new AttaqueTaupeZombie() };
		return ef[(int)(Math.random()*ef.length)];
	}
	
	
	public void piocher() {
		
		
		
		cartes.add(new Carte(new BIEN_direBonjour() , piocherMauvaise() ));
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
