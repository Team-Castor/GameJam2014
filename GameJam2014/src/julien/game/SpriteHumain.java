package julien.game;

import java.util.ArrayList;

import jitou.global.BoardGame;
import jitou.global.Citoyen;
import julien.map.Case;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class SpriteHumain extends Sprite {

	static private int dimensionX = 15;
	static private int dimensionY = 32;
	public int xcor, ycor;
	Citoyen c;
	Animation anim;
	private static ArrayList<Image> images = new ArrayList<Image>();
	
	public SpriteHumain (Citoyen c) {
		super(0,0,dimensionX,dimensionY);
		this.c = c;
		if (SpriteHumain.images.isEmpty()) {
			try {
				loadImages();
			} catch (SlickException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		initAnimation();
		try {
			img = new Image("julien/images/perso1.png");
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	private void initAnimation() {

		anim = new Animation();
		anim.setAutoUpdate(true);
        for (int i = 0; i < 3; i++){
        	anim.addFrame(images.get(i),100);
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

	public void loadImages() throws SlickException {
		images.add(new Image("julien/images/marche1.png"));
		images.add(new Image("julien/images/marche2.png"));
		images.add(new Image("julien/images/marche3.png"));
		images.add(new Image("julien/images/marche4.png"));

	}

	public Animation getAnim() {
		return anim;
	}

	public void setAnim(Animation anim) {
		this.anim = anim;
	}

	public Citoyen getC() {
		return c;
	}

	public void setC(Citoyen c) {
		this.c = c;
	}

	
}
