package jitou.global;

import jitou.batiments.Atelier;
import jitou.batiments.Batiment;
import jitou.batiments.Dortoir;
import jitou.batiments.Generateur;
import jitou.batiments.Refectoire;

public class Objectif {
	protected Citoyen citoyen;

	protected double SEUIL_FAMINE 				= 2000.0;
	protected double SEUIL_FAMINE_CRITIQUE    	= 500.0;
	protected double SEUIL_FATIGUE 				= 1500.0;
	protected double SEUIL_FATIGUE_CRITIQUE   	= 500.0;

	protected Batiment		seRendre = null;
	protected ObjectifType type = null;



	public Objectif(Citoyen citoyen){
		this.citoyen= citoyen;
		type= ObjectifType.aucun;
	}

	public void trouverNouvelObjectif(){
		BoardGame game = BoardGame.boardGame;
		/*
		 * - Nourrire Critique
		 * - Se reposer Critique
		 * - malade -> se soigner
		 * - si attaque :
		 * ---- si arme allezattaque
		 * ---- sinon allez chercher arme
		 * - Nourrire
		 * - Se reposer
		 * - si pas attaque une arme -> allez la ramener 
		 * 
		 * random : 
		 * 
		 * - mine de fer -> atelier
		 * - puit de petrol ->  generateur
		 * - ferme nourriture 
		 * - exterieur
		 */

		Batiment refDispo = game.nourritureDisponible();
		Batiment dorDispo = game.dortoirDisponible();

		Batiment arsDispo = game.armeDisponible();
		Batiment hopDispo = game.hopitalDisponible();
		Batiment ferDispo = game.trouverFerme();
		Batiment minDispo = game.trouverMine();

		Batiment puiDispo = game.trouverPuitPetrol();
		Batiment sortie = game.trouverSortie();


		if(citoyen.getNourritureRestante()<SEUIL_FAMINE_CRITIQUE && refDispo!=null){
			// Allez chercher de la nourriture
			seRendre 	= refDispo;
			type 		= ObjectifType.manger;
		}
		else if(citoyen.getFatigue()<SEUIL_FATIGUE_CRITIQUE  && dorDispo!=null){
			// Allez se reposer
			seRendre 	= dorDispo;
			type 		= ObjectifType.se_reposer;
		}
		else if(game.estAttaquer() && arsDispo!=null){
			//Allez defendre ville
			seRendre 	= arsDispo;
			type 		= ObjectifType.allerChercherArme;
		}
		else if(citoyen.estMalade() && hopDispo!=null){
			seRendre 	= hopDispo;
			type 		= ObjectifType.se_soigner;
		}
		else if(citoyen.getNourritureRestante()<SEUIL_FAMINE && refDispo!=null){
			// Allez chercher de la nourriture
			seRendre 	= refDispo;
			type 		= ObjectifType.manger;
		}
		else if(citoyen.getFatigue()<SEUIL_FATIGUE  && dorDispo!=null){
			// Allez se reposer
			seRendre 	= dorDispo;
			type 		= ObjectifType.se_reposer;
		}
		else{
			//Test nourriture
			{
				Refectoire r = Refectoire.listeRefectoire.get((int) (Math.random()*Refectoire.listeRefectoire.size()));

				if(r.getQuantiteNourriture()<50 && ferDispo!=null && Math.random()<0.7){
					seRendre 	= ferDispo;
					type 		= ObjectifType.allerAUneFerme;
				}
			}
			//Test electricite
			{
				Generateur r = Generateur.listeGenerateurs.get((int) (Math.random()*Generateur.listeGenerateurs.size()));
				if(r.getElectricite()<50 && puiDispo!=null && Math.random()<0.7){
					seRendre 	= puiDispo;
					type 		= ObjectifType.allerPuitPetrole;
				}
			}

			int proba = (int) (Math.random()*100);
			if(proba<20){
				seRendre 	= ferDispo;
				type 		= ObjectifType.allerAUneFerme;
			}
			else if(proba<40){
				seRendre 	= minDispo;
				type 		= ObjectifType.allerAUneMineDeFer;
			}
			else if(proba<60){
				seRendre 	= puiDispo;
				type 		= ObjectifType.allerPuitPetrole;
			}
			else if(proba<80 && Dortoir.listeDortoirs.size()*30>=game.getCitoyens().size()){
				seRendre 	= sortie;
				type 		= ObjectifType.allerVersSortie;
			}
		}


	}


	public void accomplirObjectif(Batiment batiment) {
		//System.out.println("On fait l'objectif dans le "+batiment+" "+type.getValue());
		batiment.effet(citoyen, type);

	}


	public void reset() {
		type= ObjectifType.aucun;
		seRendre = null;
	}

	public void rapporterNourriture() {
		Batiment refDispo = Refectoire.listeRefectoire.get((int) (Math.random()*Refectoire.listeRefectoire.size()));
		type= ObjectifType.rapporterNourritureRefectoir;
		seRendre = refDispo;
	}


	public void rapporterFer() {
		Batiment refDispo = Atelier.listeAtelier.get((int) (Math.random()*Atelier.listeAtelier.size()));
		type= ObjectifType.rapporterferAtelier;
		seRendre = refDispo;		
	}

	public void rapporterPetrole() {
		Batiment refDispo = Generateur.listeGenerateurs.get((int) (Math.random()*Generateur.listeGenerateurs.size()));
		type= ObjectifType.rapporterGenerateur;
		seRendre = refDispo;		 	
	}

	public Batiment getSeRendre() {
		return seRendre;
	}

	public void setSeRendre(Batiment seRendre) {
		this.seRendre = seRendre;
	}

	public ObjectifType getType() {
		return type;
	}

	public void setType(ObjectifType type) {
		this.type = type;
	}


	public String toString(){
		return this.getSeRendre()+" "+this.type.getValue();
	}

	public void trouverNouvelObjectifFanatique() {
		BoardGame game = BoardGame.boardGame;

		Batiment refDispo = game.nourritureDisponible();
		Batiment dorDispo = game.dortoirDisponible();

		Batiment hopDispo = game.hopitalDisponible();


		if(citoyen.getNourritureRestante()<SEUIL_FAMINE_CRITIQUE && refDispo!=null){
			// Allez chercher de la nourriture
			seRendre 	= refDispo;
			type 		= ObjectifType.manger;
		}
		else if(citoyen.getFatigue()<SEUIL_FATIGUE_CRITIQUE  && dorDispo!=null){
			// Allez se reposer
			seRendre 	= dorDispo;
			type 		= ObjectifType.se_reposer;
		}
		else if(citoyen.estMalade() && hopDispo!=null){
			//Allez se soigner
			seRendre 	= hopDispo;
			type 		= ObjectifType.se_soigner;
		}
		else if(citoyen.getNourritureRestante()<SEUIL_FAMINE && refDispo!=null){
			// Allez chercher de la nourriture
			seRendre 	= refDispo;
			type 		= ObjectifType.manger;
		}
		else if(citoyen.getFatigue()<SEUIL_FATIGUE  && dorDispo!=null){
			// Allez se reposer
			seRendre 	= dorDispo;
			type 		= ObjectifType.se_reposer;
		}
		else{
			type= ObjectifType.faireSemblant;
			seRendre = game.randomSalle(); 
		}		
	}

	public void allerDefendre() {
		if(Ennemi.ennemis.size()>0){
			int i = (int) (Math.random()*Ennemi.ennemis.size());
			int x = Ennemi.ennemis.get(i).getX();
			int y = Ennemi.ennemis.get(i).getY();
			seRendre = BoardGame.boardGame.getBatiment(x, y);
			type= ObjectifType.allezAuCombat;
		}else{
			Batiment arsDispo = BoardGame.boardGame.armeDisponible();
			seRendre = arsDispo;
			type= ObjectifType.rendreArme;
		}
	}




}
