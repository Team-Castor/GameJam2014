package jitou.batiments;

public class Batiment {
	private int posX, posY;
	private TypeBatiment type;
	private Batiment voisins[] = new Batiment[4];




	Batiment(int posX, int posY, TypeBatiment type){
		this.posX=posX;
		this.posY=posY;
		this.type=type;

		voisins[Orientation.est.getValue()]		=null;
		voisins[Orientation.ouest.getValue()]	=null;
		voisins[Orientation.nord.getValue()]	=null;
		voisins[Orientation.sud.getValue()]		=null;
	}





}
