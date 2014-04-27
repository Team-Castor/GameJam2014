package julien.game;

import java.util.ArrayList;

import jitou.global.BoardGame;
import jitou.global.Citoyen;
import jitou.global.CitoyenDehors;
import jitou.global.Fanatique;
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
	private static ArrayList<Image> imagesGauche = new ArrayList<Image>();
	private static ArrayList<Image> imagesDroite = new ArrayList<Image>();
	private static ArrayList<Image> imagesGaucheFanatique = new ArrayList<Image>();

	public SpriteHumain (Citoyen c) {
		super(0,0,dimensionX,dimensionY);
		this.c = c;
		if (SpriteHumain.imagesGauche.isEmpty()) {
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
	
	public SpriteHumain(CitoyenDehors c2) {
		// TODO Auto-generated constructor stub
	}

	private void initAnimation() {

		if (!(c instanceof Fanatique)) {
			anim = new Animation();
			anim.setAutoUpdate(true);
		for (int i = 0; i < 3; i++){
			anim.addFrame(imagesGauche.get(i),100);
			}
        }
        else {
    		anim = new Animation();
    		anim.setAutoUpdate(true);
            for (int i = 0; i < 3; i++){
            	anim.addFrame(imagesGaucheFanatique.get(i),100);
            }
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
		imagesGauche.add(new Image("julien/images/marche1.png"));
		imagesGauche.add(new Image("julien/images/marche2.png"));
		imagesGauche.add(new Image("julien/images/marche3.png"));
		imagesGauche.add(new Image("julien/images/marche4.png"));
		imagesGaucheFanatique.add(new Image("julien/images/marche1.png"));
		imagesGaucheFanatique.add(new Image("julien/images/marche2.png"));
		imagesGaucheFanatique.add(new Image("julien/images/marche3.png"));
		imagesGaucheFanatique.add(new Image("julien/images/marche4.png"));
		imagesGaucheFanatique.get(0).setImageColor(0.25f, 0.3f, 0.35f);
		imagesGaucheFanatique.get(1).setImageColor(0.25f, 0.3f, 0.35f);
		imagesGaucheFanatique.get(2).setImageColor(0.25f, 0.3f, 0.35f);
		imagesGaucheFanatique.get(3).setImageColor(0.25f, 0.3f, 0.35f);
		imagesDroite.add(new Image("julien/images/marche1.png"));
		imagesDroite.add(new Image("julien/images/marche2.png"));
		imagesDroite.add(new Image("julien/images/marche3.png"));
		imagesDroite.add(new Image("julien/images/marche4.png"));
		imagesDroite.get(0).getFlippedCopy(true, false);
		imagesDroite.get(1).getFlippedCopy(true, false);
		imagesDroite.get(2).getFlippedCopy(true, false);
		imagesDroite.get(3).getFlippedCopy(true, false);

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

	public boolean actualiser(Game game) {
		if (c.getEstMort()) {
			game.getSpritesHumains().remove(this);
			return true;
		}
		return false;
	}

	
}
