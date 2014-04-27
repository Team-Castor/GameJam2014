package julien.mechant;

import java.util.ArrayList;

import julien.game.Game;
import julien.mechant.effets.*;
import julien.mechant.effets.gentils.*;

import julien.mechant.effets.mechants.*;


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
				new VolOutils(), new AttaqueTaupeZombie(), new Kamikaze(), 
				new SabotageReserveExplosif(), new ReveilZombie(), new VagueFanatique(), 
				new Rat(), new Rhume(), new Grippe(), new FuiteRadiationGenerateur()};
		
		return ef[(int)(Math.random()*ef.length)];
	}
	public Effet piocherBonner(){
		Effet[] ef = {new AvanceeMedicale(),new ChasseSorcieres(), new Rationnement(), 
				 new SuperMacon(), new SuperBasket(), new CapsuleDeReparation(),
				 new FilonDeFer()};
		
		return ef[(int)(Math.random()*ef.length)];
	}
	
	public void piocher() {
		
		
		
		cartes.add(new Carte(piocherBonner() , piocherMauvaise() ));
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
