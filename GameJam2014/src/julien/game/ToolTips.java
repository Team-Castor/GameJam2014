package julien.game;

import jitou.batiments.Batiment;
import jitou.batiments.TypeBatiment;
import jitou.global.BoardGame;
import julien.map.Case;
import julien.mechant.Carte;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import de.matthiasmann.twl.Container;

public class ToolTips{


	public int xcor, ycor;
	public String log;
	int dx, dy;
	double x, y;

	public ToolTips( GameContainer container) {
		this.dx = 40;
		this.dy = container.getHeight()-60;
		set("aucune");	

	}

	public void set(String log) {
		x=dx;
		y=dy;
		this.log=log;
	}


	public void draw(GameContainer container) {
		//System.out.println(x+"  "+y+"  "+log);
		if(!log.isEmpty()) container.getGraphics().drawString(log,(int) x,(int) y);
	}

	public void update(int delta) {
		y+=delta/20.0;
		if(y>dy+60) log="";
	}





}
