package jitou.batiments;

import java.awt.Point;

import jitou.global.Citoyen;
import jitou.global.ObjectifType;

public class Batiment {
	private Point pos;
	private TypeBatiment type;
	private Batiment voisins[] = new Batiment[4];




	Batiment(Point pos, TypeBatiment type){
		this.setPos(pos);
		this.type = type;

		voisins[Orientation.est.getValue()]		=null;
		voisins[Orientation.ouest.getValue()]	=null;
		voisins[Orientation.nord.getValue()]	=null;
		voisins[Orientation.sud.getValue()]		=null;
	}




	public Point getPos() {
		return pos;
	}

	public void setPos(Point pos) {
		this.pos = pos;
	}

	public TypeBatiment getType() {
		return type;
	}




	public void effet(Citoyen citoyen, ObjectifType type) {
		System.out.println("Batiment doit etre virtuel...:s");
	}

	public String toString(){
		return getType().getNom()+"("+pos.x+","+pos.y+")";
	}




	public void back(Citoyen c) {
	}





}
