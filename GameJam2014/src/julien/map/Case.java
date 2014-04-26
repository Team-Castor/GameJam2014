package julien.map;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import jitou.batiments.Batiment;
import jitou.batiments.TypeBatiment;
import jitou.global.BoardGame;
import julien.game.Sprite;

public class Case extends Sprite{

	static private int dimensionX = 100;
	static private int dimensionY = 70;
	public int xcor, ycor;
	
	public Case (int x , int y, int xcor, int ycor) {
		super(x,y,dimensionX,dimensionY);
		this.xcor = xcor;
		this.ycor = ycor;
		
		if (BoardGame.boardGame.getBatiment(xcor, ycor) != null) {
			try {
				img = new Image((int)w,(int)h);
				img.getGraphics().drawRect(0, 0, w-1, h-1);

				img.getGraphics().drawLine(0, 0,w,h);
				img.getGraphics().drawLine(w, 0,0,h);
			} catch (SlickException e) {
				e.printStackTrace();
			}
			img.setImageColor((float)Math.random(), (float)Math.random(),(float) Math.random());
		} else {
			try {
				img = new Image((int)w,(int)h);
				img.getGraphics().drawRect(0, 0, w-1, h-1);

				img.getGraphics().drawLine(0, 0,w,h);
				img.getGraphics().drawLine(w, 0,0,h);
			} catch (SlickException e) {
				e.printStackTrace();
			}
			img.setImageColor(0.2f,0.2f,0.2f);
		}

		
	}

	public static int getDimensionX() {
		return dimensionX;
	}

	public static void setDimensionX(int dimensionX) {
		Case.dimensionX = dimensionX;
	}

	public static int getDimensionY() {
		return dimensionY;
	}

	public static void setDimensionY(int dimensionY) {
		Case.dimensionY = dimensionY;
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
	
	
	
	
	
}
