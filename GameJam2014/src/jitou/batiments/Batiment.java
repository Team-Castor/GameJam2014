package jitou.batiments;

import java.awt.Point;

public class Batiment {
	private Point pos;
	private TypeBatiment type;
	private Batiment voisins[] = new Batiment[4];




	Batiment(Point pos, TypeBatiment type){
		this.pos=pos;
		this.type=type;

		voisins[Orientation.est.getValue()]		=null;
		voisins[Orientation.ouest.getValue()]	=null;
		voisins[Orientation.nord.getValue()]	=null;
		voisins[Orientation.sud.getValue()]		=null;
	}





}
