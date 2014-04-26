package jitou.global;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Collections;

import jitou.batiments.Arsenal;
import jitou.batiments.Atelier;
import jitou.batiments.Batiment;
import jitou.batiments.Chaudiere;
import jitou.batiments.Dortoir;
import jitou.batiments.Ferme;
import jitou.batiments.Generateur;
import jitou.batiments.Hopital;
import jitou.batiments.MineDeFer;
import jitou.batiments.PuitPetrol;
import jitou.batiments.Refectoire;
import jitou.batiments.TypeBatiment;;

public class BoardGame {
	//private Batiment[][] 			grille_batiments=null;
	private ArrayList<Batiment>		liste_batiments=null;
	private ArrayList<Citoyen>  	citoyens =null;


	private static final int dimensionWorldX=10;
	private static final int dimensionWorldY=10;

	public static BoardGame boardGame = new BoardGame();
	private BoardGame(){
		//grille_batiments = new Batiment[dimensionWorldX][dimensionWorldY];
		liste_batiments = new ArrayList<Batiment>();
		citoyens = new ArrayList();
		liste_batiments.clear();
		citoyens.clear();
	}

	public void update(int delta){
		Point listePts[] = {new Point(-1, 0),new Point(1, 0),new Point(0, 1),new Point(0, -1)};
		for(int i = 0;i<this.liste_batiments.size();i++){
			int x = liste_batiments.get(i).getPos().x;
			int y = liste_batiments.get(i).getPos().y;

			double somme = 0.0;
			int nb = 0;
			for(int j=0;j<listePts.length;j++){
				if(x+listePts[j].x>=0 && x+listePts[j].x<BoardGame.getDimensionworldx()&&
						y+listePts[j].y>=0 && y+listePts[j].y<BoardGame.getDimensionworldy()){
					if(this.getBatiment(x+listePts[j].x, y+listePts[j].y)!=null){
						somme+=getBatiment(x+listePts[j].x, y+listePts[j].y).getTemperatureSalle();
						nb++;
					}
				}
			}
			this.liste_batiments.get(i).setTemperatureSalle(
					this.liste_batiments.get(i).getTemperatureSalle()+(0.7*somme/nb));
		}



		// Tour civil
		for(int i=0;i<citoyens.size();i++){
			citoyens.get(i).update(delta);
		}


	}

	public void initialisation() {



		TypeBatiment batimentDepart[] = {
				TypeBatiment.Generateur, 
				TypeBatiment.Refectoire, 
				TypeBatiment.Dortoir, 
				TypeBatiment.Chaudiere, 
				TypeBatiment.MineDeFer, 
				TypeBatiment.PuitPetrol, 
				TypeBatiment.FermeHydroponique, 
				TypeBatiment.Atelier};

		Point positionPossible[] = {
				new Point(dimensionWorldX/2, dimensionWorldY-1), 
				new Point(dimensionWorldX/2, dimensionWorldY-2),
				new Point(dimensionWorldX/2+1, dimensionWorldY-2), 
				new Point(dimensionWorldX/2-1, dimensionWorldY-2), 
				new Point(dimensionWorldX/2-1, dimensionWorldY-3),
				new Point(dimensionWorldX/2+1, dimensionWorldY-3),
				new Point(dimensionWorldX/2-1, dimensionWorldY-4),
				new Point(dimensionWorldX/2+1, dimensionWorldY-4)};

		int j  = (int) (Math.random()*batimentDepart.length);
		for(int i=0;i<batimentDepart.length;i++){
			Batiment bat = null;
			int val = batimentDepart[j].getValue();



			if(val== TypeBatiment.Generateur.getValue()){
				bat = new Generateur(positionPossible[i]);
			}else if(val==TypeBatiment.Refectoire.getValue()){
				bat = new Refectoire(positionPossible[i]);
				((Refectoire)bat).setQuantiteNourriture(20);
			}else if(val==TypeBatiment.Chaudiere.getValue()){
				bat = new Chaudiere(positionPossible[i]);
			}else if(val==TypeBatiment.Dortoir.getValue()){
				bat = new Dortoir(positionPossible[i]); 
			}else if(val==TypeBatiment.MineDeFer.getValue()){
				bat = new MineDeFer(positionPossible[i]); 
			}else if(val==TypeBatiment.PuitPetrol.getValue()){
				bat = new PuitPetrol(positionPossible[i]); 
			}else if(val==TypeBatiment.FermeHydroponique.getValue()){
				bat = new Ferme(positionPossible[i]); 
			}else if(val==TypeBatiment.Atelier.getValue()){
				bat = new Atelier(positionPossible[i]); 
			}
			else{
				System.out.println("Probleme initialisation boardGame");
				System.exit(0);
			}
			addBatiment(positionPossible[i], bat);

			j = (j+1)%batimentDepart.length;
		}
		System.out.println(liste_batiments);
		for(int i=0;i<15;i++){
			citoyens.add(new Citoyen(positionPossible[(int) (Math.random()*positionPossible.length)]));
		}
	}

	public void addBatiment(Point p, Batiment bat){
		this.liste_batiments.add(bat);
		//grille_batiments[p.x][p.y] = bat;
	}


	public Batiment getBatiment(int x, int y){
		for(Batiment b : liste_batiments){
			if(b.getPos().equals(new Point(x, y))) return b;
		}
		return  null;
	}

	public ArrayList<Batiment> getListeBatiments(){
		return liste_batiments;
	}

	public Refectoire nourritureDisponible() {
		ArrayList<Refectoire> liste = new ArrayList<Refectoire>() ;
		for(int i=0;i<Refectoire.listeRefectoire.size();i++){
			if(Refectoire.listeRefectoire.get(i).getQuantiteNourriture()>0.0){
				liste.add(Refectoire.listeRefectoire.get(i));
			}
		}
		if(liste.isEmpty()) return null;
		else return liste.get((int) (Math.random()*liste.size()));
	}

	public Dortoir dortoirDisponible() {
		ArrayList<Dortoir> liste = new ArrayList<Dortoir>() ;
		for(int i=0;i<Dortoir.listeDortoirs.size();i++){
			if(Dortoir.listeDortoirs.get(i).placeDisponible()){
				liste.add(Dortoir.listeDortoirs.get(i));
			}
		}
		if(liste.isEmpty()) return null;
		else
			return liste.get((int) (Math.random()*liste.size()));
	}

	public boolean estAttaquer() {
		return false;
	}

	public Hopital hopitalDisponible() {
		ArrayList<Hopital> liste = new ArrayList<Hopital>() ;
		for(int i=0;i<Hopital.listeHopitaux.size();i++){
			if(Hopital.listeHopitaux.get(i).placeDisponible()){
				liste.add(Hopital.listeHopitaux.get(i));
			}
		}
		if(liste.isEmpty()) return null;
		else
			return liste.get((int) (Math.random()*liste.size()));
	}

	public Arsenal armeDisponible() {
		ArrayList<Arsenal> liste = new ArrayList<Arsenal>() ;
		for(int i=0;i<Arsenal.listeArsenals.size();i++){
			if(Arsenal.listeArsenals.get(i).getArmeDisponible()>0){
				liste.add(Arsenal.listeArsenals.get(i));
			}
		}
		if(liste.isEmpty()) return null;
		else
			return liste.get((int) (Math.random()*liste.size()));
	}




	public static int getDimensionworldx() {
		return dimensionWorldX;
	}

	public static int getDimensionworldy() {
		return dimensionWorldY;
	}




	public ArrayList<Citoyen> getCitoyens() {
		return citoyens;
	}

	public void setCitoyens(ArrayList<Citoyen> citoyens) {
		this.citoyens = citoyens;
	}

	public Ferme trouverFerme() {
		ArrayList<Ferme> liste = new ArrayList<Ferme>() ;
		for(int i=0;i<Ferme.listeFerme.size();i++){
			if(Ferme.listeFerme.get(i).getDisponible()){
				liste.add(Ferme.listeFerme.get(i));
			}
		}
		if(liste.isEmpty()) return null;
		else
			return liste.get((int) (Math.random()*liste.size()));
	}

	public MineDeFer trouverMine() {
		ArrayList<MineDeFer> liste = new ArrayList<MineDeFer>() ;
		for(int i=0;i<MineDeFer.listeMineDeFer.size();i++){
			if(MineDeFer.listeMineDeFer.get(i).getDisponible()){
				liste.add(MineDeFer.listeMineDeFer.get(i));
			}
		}
		if(liste.isEmpty()) return null;
		else
			return liste.get((int) (Math.random()*liste.size()));		
	}

	public PuitPetrol trouverPuitPetrol() {
		ArrayList<PuitPetrol> liste = new ArrayList<PuitPetrol>() ;
		for(int i=0;i<PuitPetrol.listePuitsPetrols.size();i++){
			if(PuitPetrol.listePuitsPetrols.get(i).placeDisponible()){
				liste.add(PuitPetrol.listePuitsPetrols.get(i));
			}
		}
		if(liste.isEmpty()) return null;
		else
			return liste.get((int) (Math.random()*liste.size()));	
	}


	public static ArrayList<Batiment> findPath( Point depart, Point fin)
	{
		ArrayList<Batiment>  path 	= new ArrayList<Batiment> ();
		int distance[][] 		= new int[getDimensionworldx()][getDimensionworldy()];
		Batiment batiments[][] 	= new Batiment[getDimensionworldx()][getDimensionworldy()];

		for(int x=0;x<getDimensionworldx();x++){
			for(int y=0;y<getDimensionworldy();y++){
				distance[x][y]  = 1000000;
				batiments[x][y] = null;
			}
		}
		int poids = 0;
		findPath(depart.x, depart.y, fin, distance, batiments, poids, null);


		path.add(boardGame.getBatiment(fin.x, fin.y));
		Batiment b = batiments[fin.x][fin.y];
		if(!fin.equals(depart) && b!=null){
			while(b!=null){
				path.add(b);
				b = batiments[b.getPos().x][b.getPos().y];
			}
		}
	
		
		System.out.println(boardGame.getListeBatiments());
		System.out.println(depart+"  "+batiments[depart.x][depart.y]);
		path.add(boardGame.getBatiment(depart.x,depart.y));

		Collections.reverse(path);
		return path;	
	}


	public static void findPath( int x, int y, Point f, int distance[][],Batiment batiments[][], int poids, Batiment bat) {

		if(poids<distance[x][y]){
			distance[x][y]  = poids;
			batiments[x][y] = bat;

			if(x==f.x&& y==f.y ){
				//Oui !!!
			}
			else{
				Point listePts[] = {new Point(-1, 0),new Point(1, 0),new Point(0, 1),new Point(0, -1)};

				for(int j=0;j<listePts.length;j++){
					if(x+listePts[j].x>=0 && x+listePts[j].x<BoardGame.getDimensionworldx()&&
							y+listePts[j].y>=0 && y+listePts[j].y<BoardGame.getDimensionworldy()){
						if(boardGame.getBatiment(x+listePts[j].x, y+listePts[j].y)!=null){
							findPath(x+listePts[j].x, y+listePts[j].y, f, distance, batiments, poids+1,boardGame.getBatiment(x, y) );

						}
					}
				}



			}


		}
	}

	
	public void killCitoyen(Citoyen citoyen) {
		System.out.println("Mort : "+citoyen.getFatigue()+" , "+citoyen.getNourritureRestante()+" , "+citoyen.getTemperatureCorporelle());
		boardGame.getCitoyens().remove(citoyen);
	}
}
