package jitou.global;

import java.awt.Point;
import java.util.ArrayList;

import org.omg.CORBA.SystemException;

import jitou.batiments.Arsenal;
import jitou.batiments.Batiment;
import jitou.batiments.Chaudiere;
import jitou.batiments.Dortoir;
import jitou.batiments.Generateur;
import jitou.batiments.Hopital;
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

	public void initialisation() {
		TypeBatiment batimentDepart[] = {TypeBatiment.Generateur, TypeBatiment.Refectoire, TypeBatiment.Dortoir, TypeBatiment.Chaudiere};
		Point positionPossible[] = {new Point(dimensionWorldX/2, dimensionWorldY-1), 
				new Point(dimensionWorldX/2, dimensionWorldY-2),
				new Point(dimensionWorldX/2+1, dimensionWorldY-2), 
				new Point(dimensionWorldX/2-1, dimensionWorldY-2)};

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
			}
			else{
				System.out.println("Probleme initialisation boardGame");
				System.exit(0);
			}
			addBatiment(positionPossible[i], bat);

			j = (j+1)%batimentDepart.length;
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



	   public static ArrayList<Point> findPath( int x, int y,  ArrayList<Point>  path) {
	        
	        return null;
	    }
	   
	   public static int getDimensionworldx() {
		return dimensionWorldX;
	}

	public static int getDimensionworldy() {
		return dimensionWorldY;
	}

	public static ArrayList<Point> findPath( int x, int y, boolean[][] passed, ArrayList<Point>  path) {
	        passed[x][y] = true;
	        path.add(new Point(x,y));
	        
	        if(x==boardGame.dimensionWorldX-1&&y==boardGame.dimensionWorldY-1) return path;
	        ArrayList<Point> temp=null;
	        
	        if(x<boardGame.dimensionWorldX-1&& boardGame.getBatiment(x+1,y)!=null&&!passed[x+1][y]) temp = findPath(x+1,y,passed,path);
	        if(temp!=null) return temp;
	        if(y<boardGame.dimensionWorldY-1&& boardGame.getBatiment(x,y+1)!=null&&!passed[x][y+1]) temp = findPath(x,y+1,passed,path);
	        if(temp!=null) return temp;
	        if(x>0&& boardGame.getBatiment(x-1,y)!=null&&!passed[x-1][y]) temp = findPath(x-1,y,passed,path);
	        if(temp!=null) return temp;
	        if(y>0&& boardGame.getBatiment(x,y-1)!=null&&!passed[x][y-1]) temp = findPath(x,y-1,passed,path);
	        if(temp!=null) return temp;
	        
	        path.remove(path.size()-1);
	        return null;
	    }


}
