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
	}
	
	public void trouverNouvelObjectif(){
		System.out.println("trouver un but ");
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
		 * - mine de fer -> atelier
		 * - puit de petrol ->  generateur
		 * - ferme nourriture 
		 * - exterieur
		 */
		
		if(citoyen.getNourritureRestante()<SEUIL_FAMINE_CRITIQUE && game.nourritureDisponible()!=null){
			// Allez chercher de la nourriture
		}
		else if(citoyen.getNourritureRestante()<SEUIL_FATIGUE_CRITIQUE  && game.dortoirDisponible()!=null){
			// Allez se reposer
		}
		else if(game.estAttaquer() && game.armeDisponible()!=null){
			//Allez defendre ville
		}
		else if(citoyen.estMalade() && game.hopitalDisponible()!=null){
			//Allez se soigner
		}
		else if(citoyen.getNourritureRestante()<SEUIL_FAMINE && game.nourritureDisponible()!=null){
			// Allez chercher de la nourriture
		}
		else if(citoyen.getNourritureRestante()<SEUIL_FATIGUE  && game.dortoirDisponible()!=null){
			// Allez se reposer
		}
		else{
			int proba = (int) (Math.random()*100);
		}
		
		
	}
	
	
}
