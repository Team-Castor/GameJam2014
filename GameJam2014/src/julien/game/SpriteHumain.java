package julien.game;

import jitou.global.BoardGame;
import jitou.global.Citoyen;
import julien.map.Case;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class SpriteHumain extends Sprite {

	static private int dimensionX = 15;
	static private int dimensionY = 32;
	public int xcor, ycor;
	
	public SpriteHumain (Game game, Citoyen c) {
		super(0,0,dimensionX,dimensionY);

		

		try {
			img = new Image("julien/images/perso1.png");
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

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

	public void calculerPosition(int offsetX, int offsetY, int h) {
		x = xcor*Case.getDimensionX()-offsetX;
		y = (ycor*Case.getDimensionY()-offsetY - h) * -1 - Case.getDimensionY();
	}
}
