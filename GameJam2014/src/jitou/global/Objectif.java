package jitou.global;

import jitou.batiments.Batiment;

public class Objectif {
	private Citoyen citoyen;
	
	private double SEUIL_FAMINE 			= 200.0;
	private double SEUIL_FAMINE_CRITIQUE    = 50.0;
	private double SEUIL_FATIGUE 			= 200.0;
	private double SEUIL_FATIGUE_CRITIQUE   = 50.0;

	public Batiment		seRendre = null;
	public ObjectifType type = null;

	
	
	public Objectif(Citoyen citoyen){
		this.citoyen= citoyen;
		type= ObjectifType.aucun;
	}
	
	public void trouverNouvelObjectif(){
		System.out.println("Je vais chercher un but ");
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
		
		if(citoyen.getNourritureRestante()<SEUIL_FAMINE_CRITIQUE && refDispo!=null){
			// Allez chercher de la nourriture
			seRendre 	= refDispo;
			type 		= ObjectifType.manger;
		}
		else if(citoyen.getNourritureRestante()<SEUIL_FATIGUE_CRITIQUE  && dorDispo!=null){
			// Allez se reposer
			seRendre 	= dorDispo;
			type 		= ObjectifType.se_reposer;
		}
		else if(game.estAttaquer() && arsDispo!=null){
			//Allez defendre ville
		}
		else if(citoyen.estMalade() && hopDispo!=null){
			//Allez se soigner
		}
		else if(citoyen.getNourritureRestante()<SEUIL_FAMINE && refDispo!=null){
			// Allez chercher de la nourriture
		}
		else if(citoyen.getNourritureRestante()<SEUIL_FATIGUE  && dorDispo!=null){
			// Allez se reposer
		}
		else{
			int proba = (int) (Math.random()*100);
		}
		
		System.out.println("Je vais faire "+type.getValue());

	}
	
	
	public void accomplirObjectif(Batiment batiment) {
		System.out.println("On fait l'objectif!");
		
		//Apres on reinitialise
		seRendre 	= null;
		type 		= ObjectifType.aucun;		
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


	
	
	
	
	
	
}
