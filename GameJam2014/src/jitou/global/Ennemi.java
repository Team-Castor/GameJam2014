package jitou.global;

import java.awt.Point;
import java.util.ArrayList;

public class Ennemi {
	int x, y;
	EnnemiType type;
	public static ArrayList<Ennemi> ennemis= new ArrayList<Ennemi>();
	
	public enum EnnemiType{
		tompeZombi;
	}
	
	public Ennemi(int x, int y, EnnemiType type){
		this.x = x;
		this.y = y;
		this.type = type;
		ennemis.add(this);
	}
	
	public void update(int delta){
		System.out.println("AttaqueTaupeZombie");
		for(int i=0;i<BoardGame.boardGame.getCitoyens().size();i++){
			if(BoardGame.boardGame.getCitoyens().get(i).getPos().equals(new Point(x, y))){
				if(BoardGame.boardGame.getCitoyens().get(i).aUneArme()){
					meurt();
				}
				else{
					BoardGame.boardGame.getCitoyens().get(i).tuer();
				}
				return;
			}
		}
		Point listePts[] = {new Point(-1, 0),new Point(1, 0),new Point(0, 1),new Point(0, -1)};
		int j=(int) (Math.random()*4);
		for(int i=0;i<4;i++){
			if(x+listePts[j].x>=0 && x+listePts[j].x<BoardGame.getDimensionworldx() &&
					y+listePts[j].y>=0 && y+listePts[j].y<BoardGame.getDimensionworldy()-2){
				if(BoardGame.boardGame.getBatiment(x+listePts[j].x, y+listePts[j].y)!=null){
					x = x+listePts[j].x;//Pas encore d'animation
					y = y+listePts[j].y;
					return;
				}
			}
			j=(j+1)%4;
		}
	}
	
	public void meurt(){
		System.out.println("AttaqueTaupeZombie meurt");

		ennemis.remove(this);
	}

	public int getX() {
		return x;
	}


	public int getY() {
		return y;
	}


	public EnnemiType getType() {
		return type;
	}

	public void setType(EnnemiType type) {
		this.type = type;
	}
	
	
}
