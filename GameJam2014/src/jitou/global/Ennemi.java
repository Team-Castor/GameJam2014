package jitou.global;

import java.awt.Point;
import java.util.ArrayList;

import julien.game.Game;

public class Ennemi {
	int x, y;
	double px, py;
	double vx, vy;
	EnnemiType type;
	public static ArrayList<Ennemi> ennemis= new ArrayList<Ennemi>();
	
	public enum EnnemiType{
		tompeZombi, zombi;
	}

	public Ennemi(int x, int y, EnnemiType type){
		px=0.0;
		py=0.0;
		vx = (Math.random()-0.5)/100.0;
		vy = (Math.random()-0.5)/100.0;
		
		this.x = x;
		this.y = y;
		this.type = type;
		ennemis.add(this);
		Game.getInstance().nouvelEnnemi(this);
	}

	public void update(int delta){
		
		px = px+vx;
		py = py+vy;
		if(py<-0.98){
			py = 0.98;
			y--;
		}
		else if(py>0.98){
			py = -0.98;
			y++;
		}
		if(px<-0.98){
			px = 0.98;
			x--;
		}
		else if(x>0.98){
			px = -0.98;
			x++;
		}
		
		if(BoardGame.boardGame.getBatiment(x, y)==null){
				vx = -vx;
				vy = -vy;
				
				px +=vx;
				px = px+vx;
	
				if(px<-0.98){
					px = 0.98;
					x--;
				}
				else if(x>0.98){
					px = -0.98;
					x++;
				}
		
			
				py +=vy;
				py = py+vy;

				if(py<-0.98){
					py = 0.98;
					y--;
				}
				else if(py>0.98){
					py = -0.98;
					y++;
				}
		}
		
		
		
		System.out.println("AttaqueTaupeZombie ");
		for(int i=0;i<BoardGame.boardGame.getCitoyens().size();i++){
			if(BoardGame.boardGame.getCitoyens().get(i).getPos().equals(new Point(x, y))){
				if(BoardGame.boardGame.getCitoyens().get(i).aUneArme()){
					meurt();
				}
				else{
					if(Math.random()<0.1){
						BoardGame.boardGame.getCitoyens().get(i).tuer();
					}
				}
				return;
			}
		}
		//Ne se deplace pas?
		/*Point listePts[] = {new Point(-1, 0),new Point(1, 0),new Point(0, 1),new Point(0, -1)};
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
		}*/
	}

	public void meurt(){
		System.out.println("AttaqueTaupeZombie meurt");
		Game.getInstance().ennemiCreve(this);

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
