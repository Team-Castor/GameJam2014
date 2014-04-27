package julien.game;

import java.util.ArrayList;

import jitou.batiments.Orientation;
import jitou.global.BoardGame;
import jitou.global.Citoyen;
import jitou.global.CitoyenDehors;
import jitou.global.Fanatique;
import julien.map.Case;

import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import de.matthiasmann.twl.Container;

public class SpriteHumain extends Sprite {

	static private int dimensionX = 15;
	static private int dimensionY = 32;
	public int xcor, ycor;
	public float oldX, oldY;
	
	Citoyen c;
	Animation anim;
	Orientation dir = Orientation.est;
	
	private static ArrayList<Image> imagesGauche = new ArrayList<Image>();
	private static ArrayList<Image> imagesDroite = new ArrayList<Image>();
	private static ArrayList<Image> imagesGaucheFanatique = new ArrayList<Image>();
	private static ArrayList<Image> imagesDroiteFanatique = new ArrayList<Image>();
	private static ArrayList<Image> imagesTravail = new ArrayList<Image>();
	private boolean etaitActif = false;

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

	public void initAnimation() {

		if (dir.equals(Orientation.ouest))
		 if (c.estActif()) {
			// System.out.println("est actif");
	    		anim = new Animation();
	    		anim.setAutoUpdate(true);
	            for (int i = 0; i < 1; i++){
	            	anim.addFrame(imagesTravail.get(i),100);
	            }
			} 
		 else if (!(c instanceof Fanatique)) {
			anim = new Animation();
			anim.setAutoUpdate(true);
			for (int i = 0; i < 4; i++){
				anim.addFrame(imagesGauche.get(i),100);
			}
        }
        else{
    		anim = new Animation();
    		anim.setAutoUpdate(true);
            for (int i = 0; i < 4; i++){
            	anim.addFrame(imagesGaucheFanatique.get(i),100);
            }
        }
		else if (dir.equals(Orientation.est)){
			 if (c.estActif()) {
					// System.out.println("est actif");
			    		anim = new Animation();
			    		anim.setAutoUpdate(true);
			            for (int i = 0; i < 1; i++){
			            	anim.addFrame(imagesTravail.get(i),100);
			            }
					} 
				 else if (!(c instanceof Fanatique)) {
					anim = new Animation();
					anim.setAutoUpdate(true);
					for (int i = 0; i < 4; i++){
						anim.addFrame(imagesDroite.get(i),100);
					}
		        }
		        else{
		    		anim = new Animation();
		    		anim.setAutoUpdate(true);
		            for (int i = 0; i < 4; i++){
		            	anim.addFrame(imagesDroiteFanatique.get(i),100);
		            }
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
		imagesDroiteFanatique.add(new Image("julien/images/marche1.png"));
		imagesDroiteFanatique.add(new Image("julien/images/marche2.png"));
		imagesDroiteFanatique.add(new Image("julien/images/marche3.png"));
		imagesDroiteFanatique.add(new Image("julien/images/marche4.png"));
		imagesDroiteFanatique.get(0).setImageColor(0.25f, 0.3f, 0.35f);
		imagesDroiteFanatique.get(1).setImageColor(0.25f, 0.3f, 0.35f);
		imagesDroiteFanatique.get(2).setImageColor(0.25f, 0.3f, 0.35f);
		imagesDroiteFanatique.get(3).setImageColor(0.25f, 0.3f, 0.35f);
		imagesDroiteFanatique.set(0 , imagesDroiteFanatique.get(0).getFlippedCopy(true, false));
		imagesDroiteFanatique.set(1 , imagesDroiteFanatique.get(1).getFlippedCopy(true, false));
		imagesDroiteFanatique.set(2 , imagesDroiteFanatique.get(2).getFlippedCopy(true, false));
		imagesDroiteFanatique.set(3 , imagesDroiteFanatique.get(3).getFlippedCopy(true, false));
		imagesDroite.add(new Image("julien/images/marche1.png"));
		imagesDroite.add(new Image("julien/images/marche2.png"));
		imagesDroite.add(new Image("julien/images/marche3.png"));
		imagesDroite.add(new Image("julien/images/marche4.png"));
		imagesDroite.set(0 , imagesDroite.get(0).getFlippedCopy(true, false));
		imagesDroite.set(1 , imagesDroite.get(1).getFlippedCopy(true, false));
		imagesDroite.set(2 , imagesDroite.get(2).getFlippedCopy(true, false));
		imagesDroite.set(3 , imagesDroite.get(3).getFlippedCopy(true, false));
		imagesTravail.add(new Image("julien/images/perso face.png"));


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

	public void changeState(GameContainer container , Game game) {
		
		float tempX = x;
		float tempY = y;
		
		CitoyenDehors dehors = null;
		if (!c.visible) {
			for (CitoyenDehors cit : CitoyenDehors.liste_cit) {
				//Utilisons des méthodes simples pour les agents exterieurs
				if (cit.getCitoyen() == c) {
					dehors = cit;
				}
			}
				
				x = (float)((-1*(dehors.getX() * Case.getDimensionX()))+(Case.getDimensionX() * BoardGame.boardGame.getDimensionworldx())/2-game.getOffsetX());
				y = (float)(-1000 + game.getOffsetY());
		}
		else {
			x = (float) ((c.getPos().x*Case.getDimensionX()) + (Case.getDimensionX()/2 + c.getPosInside().getX()*(Case.getDimensionX()/2))-game.getOffsetX());
			y = (float)(((c.getPos().y*Case.getDimensionY()) + (Case.getDimensionY()/2 + c.getPosInside().getY()*(Case.getDimensionY()/2)))-game.getOffsetY() - container.getHeight()) * -1;
		}
		
		Orientation newdir = null;
		if (x > oldX) {
			newdir = Orientation.est;
		} else if (x <= oldX){
			newdir = Orientation.ouest;
		}
		
		oldX = tempX;
		oldY = tempY;

		if (!newdir.equals(dir)) {
			dir = newdir;
			initAnimation();
		}
		
		if (etaitActif && !c.estActif()) {
			etaitActif = false;
			initAnimation();
		} else if (!etaitActif && c.estActif()){
			etaitActif = true;
			initAnimation();
		}
		
		
	}

	
}
