package julien.game;

import jitou.batiments.Batiment;
import jitou.batiments.TypeBatiment;
import jitou.global.BoardGame;
import julien.map.Case;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class GCarte extends Sprite{

	static private int dimensionX = 185;
	static private int dimensionY = 300;
	public int xcor, ycor;
	public Batiment batiment;
	
	public GCarte() {
		super(0,0,dimensionX,dimensionY);

		redraw();
		
	}

	public static int getDimensionX() {
		return dimensionX;
	}

	public static void setDimensionX(int dimensionX) {
		dimensionX = dimensionX;
	}

	public static int getDimensionY() {
		return dimensionY;
	}

	public static void setDimensionY(int dimensionY) {
		dimensionY = dimensionY;
	}

	public int getXcor() {
		return xcor;
	}

	public void setXcor(int xcor) {
		this.xcor = xcor;
	}

	public int getYcor() {
		return ycor;
	}

	public void setYcor(int ycor) {
		this.ycor = ycor;
	}


	public void redraw() {
		try {
			img = new Image("julien/images/carte.png");
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
					
	}
	
	
	
	
	
}
