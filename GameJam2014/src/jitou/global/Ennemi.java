package jitou.global;

import java.awt.Point;
import java.awt.geom.Point2D;
import java.util.ArrayList;

import julien.game.FXtype;
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
		vx+= (Math.random()-0.5)/200;
		vy+= (Math.random()-0.5)/200;
		double norme = Math.abs(vx)+Math.abs(vy);
		norme*=200;
		vx/=norme;
		vy/=norme;
		
		
		if(type.equals(EnnemiType.tompeZombi)){
			double bord = 0.98;
			if(Math.random()<0.5){
				double tmp = py;
				int yy=y;
				py = py+vy*delta;
				if(py<-bord){
					py = bord;
					y--;
				}
				else if(py>bord){
					py = -bord;
					y++;
				}
				if(BoardGame.boardGame.getBatiment(x, y)==null){
					vy = -vy;
					py  = tmp+vy;
					y=yy;
				}
				else
				{
					if(BoardGame.boardGame.getBatiment((int) (x), (int) (Math.signum(vy)+y))==null){
						if((vy<0 && py<-0.6) || (vy>0 && py>0.6) )vy=-vy;
					}
				}
			}else{

				double tmp= px;
				int xx = x;
				px = px+vx*delta;
				if(px<-bord){
					px = bord;
					x--;
				}
				else if(px>bord){
					px = -bord;
					x++;
				}
				
			//	System.out.println(x+"  "+y+"V "+vx+"  "+vy);

				
				if(BoardGame.boardGame.getBatiment(x, y)==null){
					vx = -vx;
					px = tmp+vx;
					x =xx;
				}
				else
				{
					if(BoardGame.boardGame.getBatiment((int) (Math.signum(vx)+x), y)==null){
						if((vx<0 && px<-0.6) || (vx>0 && px>0.6) )vx=-vx;
					}
			
				}
			}
		}else{
			double bord = 0.70;
			py = py+vy*delta;
			if(py<-bord || py>bord){
				vy=-vy;
				py += vy*delta*2;
			}

			px = px+vx*delta;
			if(px<-bord || px>bord){
				vx=-vx;
				px += vx*delta*2;
			}



		}
		//vx+=(Math.random()-0.5)/200.0;
		//vy+=(Math.random()-0.5)/200.0;


		//System.out.println("AttaqueTaupeZombie "+vx+"  "+vy);
		for(int i=0;i<BoardGame.boardGame.getCitoyens().size();i++){
			if(BoardGame.boardGame.getCitoyens().get(i).getPos().equals(new Point(x, y))){
				if(BoardGame.boardGame.getCitoyens().get(i).aUneArme()){
					if(Math.random()<0.1){
						julien.game.Game.addFX(BoardGame.boardGame.getCitoyens().get(i), FXtype.sang);
						BoardGame.boardGame.getCitoyens().get(i).tuer();	
					}
					else {
						meurt();
					}
				}
				else{
					if(Math.random()<0.05){
						julien.game.Game.addFX(BoardGame.boardGame.getCitoyens().get(i), FXtype.sang);
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

	public double getXReel() {
		return px;
	}

	public double getYReel() {
		return py;
	}


	public EnnemiType getType() {
		return type;
	}

	public void setType(EnnemiType type) {
		this.type = type;
	}

	public Point getPos() {
		return new Point(x, y);
	}
	public Point2D getPosInside() {
		return new Point2D.Double(px, py);
	}

}
