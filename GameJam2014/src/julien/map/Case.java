package julien.map;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.util.ResourceLoader;

import jitou.batiments.Batiment;
import jitou.batiments.TypeBatiment;
import jitou.global.BoardGame;
import julien.game.Sprite;

public class Case extends Sprite{

	static private int dimensionX = 175;
	static private int dimensionY = 175;
	public int xcor, ycor;
	
	public Case (int x , int y, int xcor, int ycor) {
		super(x,y,dimensionX,dimensionY);
		this.xcor = xcor;
		this.ycor = ycor;
		
		if (BoardGame.boardGame.getBatiment(xcor, ycor) != null) {
			try {
				img = new Image("julien/images/Terre01.png");
				img.getGraphics().drawString(BoardGame.boardGame.getBatiment(xcor, ycor).getType().toString(), xcor, ycor);
				
				img.setImageColor((float)Math.random(), (float)Math.random(),(float) Math.random());
			} catch (SlickException e) {
				e.printStackTrace();
			}
			
		} else {
			try {
				img = new Image("julien/images/Terre00.png");
			} catch (SlickException e) {
				e.printStackTrace();
			}
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

	public void calculerPosition(int offsetX, int offsetY, int h) {
		x = xcor*Case.getDimensionX()-offsetX;
		y = (ycor*Case.getDimensionY()-offsetY - h) * -1 - Case.getDimensionY();
	}
	
	
	
	
	
}
