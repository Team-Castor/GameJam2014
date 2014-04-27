package jitou.batiments;

import java.awt.Point;

import jitou.global.BoardGame;
import jitou.global.Citoyen;
import jitou.global.CitoyenDehors;
import jitou.global.Ennemi;
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



		if(x<BoardGame.getDimensionworldx()) 		voisins[Orientation.est.getValue()]= BoardGame.boardGame.getBatiment(x+1, y);
		if(x>0)voisins[Orientation.ouest.getValue()]	= BoardGame.boardGame.getBatiment(x-1, y);
		if(y>0) voisins[Orientation.nord.getValue()]	= BoardGame.boardGame.getBatiment(x, y-1);
		if(y<BoardGame.getDimensionworldy())	voisins[Orientation.sud.getValue()]		= BoardGame.boardGame.getBatiment(x, y+1); 

		if(voisins[Orientation.est.getValue()]!=null)voisins[Orientation.est.getValue()].setVoisin(this, Orientation.ouest);
		if(voisins[Orientation.ouest.getValue()]!=null)voisins[Orientation.ouest.getValue()].setVoisin(this, Orientation.est);
		if(voisins[Orientation.nord.getValue()]!=null)voisins[Orientation.nord.getValue()].setVoisin(this, Orientation.sud);
		if(voisins[Orientation.sud.getValue()]!=null)voisins[Orientation.sud.getValue()].setVoisin(this, Orientation.nord);

	}




	public void setVoisin(Batiment batiment, Orientation nord) {
		voisins[nord.getValue()] = batiment;
	}
	public Batiment getVoisin(Orientation n) {
		return voisins[n.getValue()];
	}

public void update(int delta){
	
}

	public final Point getPos() {
		return new Point(x, y);
	}



	public TypeBatiment getType() {
		return type;
	}




	public void effet(Citoyen citoyen, ObjectifType type) {
		if(type.getValue()==ObjectifType.allerVersSortie.getValue()){
			new CitoyenDehors(citoyen);
		}
		else if(type.getValue()==ObjectifType.allezAuCombat.getValue()){
			this.killEnnemiCase(this);
			citoyen.getObjectif().allerDefendre();
		}
			
			
	}

	private void killEnnemiCase(Batiment batiment) {
		for(int i=0;i<Ennemi.ennemis.size();i++){
			if(Ennemi.ennemis.get(i).getX()==this.x && Ennemi.ennemis.get(i).getY()==this.y){
				Ennemi.ennemis.get(i).meurt();
				return;
			}
		}
	}




	public String toString(){
		return getType().getNom()+"("+x+","+y+")";
	}




	public void back(Citoyen c) {
		if(c.getObjectif().getType().getValue()==ObjectifType.allerVersSortie.getValue()){
			c.visible = true;
			CitoyenDehors.removeWith(c);
			if(Math.random()*BoardGame.boardGame.getCitoyens().size()<=0.5*(BoardGame.boardGame.getCitoyens().size()-10)) BoardGame.boardGame.getCitoyens().add(
					new Citoyen(
							(Point) BoardGame.boardGame.getListeBatiments().get((int)(Math.random()*BoardGame.boardGame.getListeBatiments().size())).getPos().clone()));
		}

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
