package julien.game;

import java.awt.Font;

import jitou.batiments.Batiment;
import jitou.batiments.TypeBatiment;
import jitou.global.BoardGame;
import julien.map.Case;
import julien.mechant.Carte;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.*;

import de.matthiasmann.twl.Container;

public class ToolTips{


	public int xcor, ycor;
	public String log;
	int dx, dy;
	double x, y;
	public Font font;
	public TrueTypeFont trueTypeFont;

	public ToolTips( GameContainer container) {
		this.dx = 40;
		this.dy = container.getHeight()-60;
		set("");	

		font = new Font("Verdana", Font.BOLD, 20);
		trueTypeFont = new TrueTypeFont(font, true);
	    trueTypeFont.drawString(20.0f, 20.0f, "Slick displaying True Type Fonts", Color.green);

	}

	public void set(String log) {
		x=dx;
		y=dy;
		this.log=log;

	}


	public void draw(GameContainer container) {
		//System.out.println(x+"  "+y+"  "+log);

		if(!log.isEmpty())trueTypeFont.drawString((int)x, (int)y, log);// container.getGraphics().drawString(log,(int) x,(int) y);
	}

	public void update(int delta) {
		y+=delta/40.0;
		if(y>dy+60) log="";
	}





}
