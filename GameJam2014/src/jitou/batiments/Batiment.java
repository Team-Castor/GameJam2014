package jitou.batiments;

import java.awt.Point;

import jitou.global.Citoyen;
import jitou.global.ObjectifType;

public class Batiment {
	private int x, y;
	private TypeBatiment type;
	private Batiment voisins[] = new Batiment[4];

	private double temperatureSalle = 30.0;



	Batiment(Point pos, TypeBatiment type){
		x = pos.x;
		y = pos.y;
		this.type = type;

		voisins[Orientation.est.getValue()]		=null;
		voisins[Orientation.ouest.getValue()]	=null;
		voisins[Orientation.nord.getValue()]	=null;
		voisins[Orientation.sud.getValue()]		=null;
	}




	public final Point getPos() {
		return new Point(x, y);
	}



	public TypeBatiment getType() {
		return type;
	}




	public void effet(Citoyen citoyen, ObjectifType type) {
		System.out.println("Batiment doit etre virtuel...:s");
	}

	public String toString(){
		return getType().getNom()+"("+x+","+y+")";
	}




	public void back(Citoyen c) {
		c.getObjectif().reset();
	}

	public double getTemperatureSalle() {
		return temperatureSalle;
	}

	public void setTemperatureSalle(double temperatureSalle) {
		this.temperatureSalle = temperatureSalle;
	}




	public String info() {
		return "nothing";
	}





}
