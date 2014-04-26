package jitou.global;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Collections;

import org.omg.CORBA.SystemException;

import jitou.batiments.Arsenal;
import jitou.batiments.Batiment;
import jitou.batiments.Chaudiere;
import jitou.batiments.Dortoir;
import jitou.batiments.Generateur;
import jitou.batiments.Hopital;
import jitou.batiments.MineDeFer;
import jitou.batiments.PuitPetrol;
import jitou.batiments.Refectoire;
import jitou.batiments.TypeBatiment;;

public class BoardGame {
	private Batiment[][] 			grille_batiments=null;
	private ArrayList<Batiment>		liste_batiments=null;
	private ArrayList<Citoyen>  	citoyens =null;


	private static final int dimensionWorldX=10;
	private static final int dimensionWorldY=10;

	public static BoardGame boardGame = new BoardGame();
	private BoardGame(){
		grille_batiments = new Batiment[dimensionWorldX][dimensionWorldY];
		liste_batiments = new ArrayList<Batiment>();
		citoyens = new ArrayList();
		liste_batiments.clear();
		citoyens.clear();
	}

	public void update(int delta){
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
				TypeBatiment.PuitPetrol};
		
		Point positionPossible[] = {
				new Point(dimensionWorldX/2, dimensionWorldY-1), 
				new Point(dimensionWorldX/2, dimensionWorldY-2),
				new Point(dimensionWorldX/2+1, dimensionWorldY-2), 
				new Point(dimensionWorldX/2-1, dimensionWorldY-2), 
				new Point(dimensionWorldX/2-1, dimensionWorldY-3),
				new Point(dimensionWorldX/2+1, dimensionWorldY-3)};

		int j  = (int) (Math.random()*batimentDepart.length);
		for(int i=0;i<batimentDepart.length;i++){
			Batiment bat = null;
			int val = batimentDepart[j].getValue();

			
			
			if(val== TypeBatiment.Generateur.getValue()){
				bat = new Generateur(positionPossible[i]);
			}else if(val==TypeBatiment.Refectoire.getValue()){
				bat = new Refectoire(positionPossible[i]);
			}else if(val==TypeBatiment.Chaudiere.getValue()){
				bat = new Chaudiere(positionPossible[i]);
			}else if(val==TypeBatiment.Dortoir.getValue()){
				bat = new Dortoir(positionPossible[i]); 
			}else if(val==TypeBatiment.MineDeFer.getValue()){
				bat = new MineDeFer(positionPossible[i]); 
			}else if(val==TypeBatiment.PuitPetrol.getValue()){
				bat = new PuitPetrol(positionPossible[i]); 
			}
			else{
				System.out.println("Probleme initialisation boardGame");
				System.exit(0);
			}
			addBatiment(positionPossible[i], bat);

			j = (j+1)%batimentDepart.length;
		}
		
		for(int i=0;i<10;i++){
			citoyens.add(new Citoyen(positionPossible[(int) (Math.random()*positionPossible.length)]));
		}
	}

	public void addBatiment(Point p, Batiment bat){
		this.liste_batiments.add(bat);
		grille_batiments[p.x][p.y] = bat;
	}


	public Batiment getBatiment(int x, int y){
		return  grille_batiments[x][y];
	}

	public ArrayList<Batiment> getListeBatiments(){
		return liste_batiments;
	}

	public Refectoire nourritureDisponible() {
		ArrayList<Refectoire> liste = new ArrayList<Refectoire>() ;
		for(int i=0;i<Refectoire.listeRefectoire.size();i++){
			if(Refectoire.listeRefectoire.get(i).getQuantiteNourriture()>0){
				liste.add(Refectoire.listeRefectoire.get(i));
			}
		}
		if(liste.isEmpty()) return null;
		else
			return liste.get((int) (Math.random()*liste.size()));
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

	public static ArrayList<Batiment> findPath( Point depart, Point fin)
	{
		ArrayList<Batiment>  path 	= new ArrayList<Batiment> ();
		int distance[][] 		= new int[getDimensionworldx()][getDimensionworldy()];
		for(int x=0;x<getDimensionworldx();x++){
			for(int y=0;y<getDimensionworldy();y++){
				distance[x][y] = 100000;
			}
		}
		int poids = 0;
		Batiment batiments[][] 	= new Batiment[getDimensionworldx()][getDimensionworldy()];
		findPath(depart.x, depart.y, fin, distance, batiments, poids,null);
		
		path.add(boardGame.getBatiment(fin.x, fin.y));
		Batiment b = batiments[fin.x][fin.y];
		while(b!=null){
			path.add(b);
			b = batiments[b.getPos().x][b.getPos().y];
		}
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
				if(x>0){
					if(boardGame.getBatiment(x-1, y)!=null){
						findPath(x-1, y, f, distance, batiments, poids+1,boardGame.getBatiment(x, y) );
					}
				}
				if(x<boardGame.getDimensionworldx()-1){
					if(boardGame.getBatiment(x+1, y)!=null){
						findPath(x+1, y, f, distance, batiments, poids+1,boardGame.getBatiment(x, y) );
					}
				}
				if(y>0){
					if(boardGame.getBatiment(x, y-1)!=null){
						findPath(x, y-1, f, distance, batiments, poids+1,boardGame.getBatiment(x, y) );
					}
				}
				if(y<boardGame.getDimensionworldy()-1){
					if(boardGame.getBatiment(x, y+1)!=null){
						findPath(x, y+1, f, distance, batiments, poids+1,boardGame.getBatiment(x, y) );
					}
				}
			}

		}


	}

	public ArrayList<Citoyen> getCitoyens() {
		return citoyens;
	}

	public void setCitoyens(ArrayList<Citoyen> citoyens) {
		this.citoyens = citoyens;
	}


}
