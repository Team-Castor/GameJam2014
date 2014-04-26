package jitou.global;

import java.awt.Point;
import java.util.ArrayList;

import jitou.batiments.Batiment;
import jitou.batiments.TypeBatiment;;

public class BoardGame {
	private Batiment[][] 			grille_batiments;
	private ArrayList<Batiment>		liste_batiments;
	private ArrayList<Citoyen>  citoyens = new ArrayList();
	
	
	private static final int dimensionWorldX=10;
	private static final int dimensionWorldY=10;

	public static BoardGame boardGame = new BoardGame();
	private BoardGame(){
		grille_batiments = new Batiment[dimensionWorldX][dimensionWorldY];
		citoyens.clear();
	}
	
	public void initialisation() {
		TypeBatiment batimentDepart[] = {TypeBatiment.Generateur, TypeBatiment.Refectoire, TypeBatiment.Dortoir};
		Point positionPossible[] = {new Point(dimensionWorldX/2, dimensionWorldY)};
	 ///...
	}
	
	public Batiment getBatiment(int x, int y){
		return  grille_batiments[x][y];
	}

	public ArrayList<Batiment> getListeBatiments(){
		return liste_batiments;
	}


	
	
	
}
