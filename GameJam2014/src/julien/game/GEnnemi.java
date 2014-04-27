package julien.game;

import java.util.ArrayList;

import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import jitou.batiments.Orientation;
import jitou.global.BoardGame;
import jitou.global.CitoyenDehors;
import jitou.global.Ennemi;
import jitou.global.Fanatique;
import julien.map.Case;

public class GEnnemi extends Sprite{

	Ennemi ennemi;
	Animation anim;
	ArrayList<Image> frames = new ArrayList<Image>();

	public GEnnemi(Ennemi e) {
		this.ennemi = e;
		System.out.println("init");
		if (frames.isEmpty()) {
			try {
				loadImages();
			} catch (SlickException ee) {
				// TODO Auto-generated catch block
				ee.printStackTrace();
			}
		}

		initAnimation();
	}

	public void loadImages() throws SlickException {
		if(ennemi.getType().equals(Ennemi.EnnemiType.tompeZombi)){
			frames.add(new Image("julien/images/taupe01.png"));
			frames.add(new Image("julien/images/taupe02.png"));
			frames.add(new Image("julien/images/taupe03.png"));
			frames.add(new Image("julien/images/taupe04.png"));
			frames.add(new Image("julien/images/taupe05.png"));
		}else{
			frames.add(new Image("julien/images/marche1.png"));
			frames.add(new Image("julien/images/marche2.png"));
			frames.add(new Image("julien/images/marche3.png"));
			frames.add(new Image("julien/images/marche4.png"));
			
			for (int i = 0; i < frames.size(); i++){
				frames.get(i).setImageColor((float) 0.2,(float)0.5,(float)0.1,(float)0.7); 
			}
		}
	}

	public void initAnimation() {
		anim = new Animation();
		anim.setAutoUpdate(true);
		for (int i = 0; i < frames.size(); i++){
			anim.addFrame(frames.get(i),100);
		}
		w = anim.getImage(0).getWidth();
		h = anim.getImage(0).getHeight();

	}



	public Animation getAnim() {
		return anim;
	}

	public void setAnim(Animation anim) {
		this.anim = anim;
	}

	public void changeState(GameContainer container , Game game) {

		//	float tempX = x;
		//	float tempY = y;

		//		x = (float) ((ennemi.getX()*Case.getDimensionX()) + (Case.getDimensionX()/2)-game.getOffsetX() - (0.5 * w)) ;
		//		y = (float) (((ennemi.getY()*Case.getDimensionY()) + (Case.getDimensionY()/2)-game.getOffsetY() - container.getHeight()) - (0.5 * h) * -1) ;
		//		//initAnimation();
		x = (float) ((ennemi.getPos().x*Case.getDimensionX()) + (Case.getDimensionX()/2 + ennemi.getPosInside().getX()*(Case.getDimensionX()/2))-game.getOffsetX());
		y = (float)(((ennemi.getPos().y*Case.getDimensionY()) + (Case.getDimensionY()/2 + ennemi.getPosInside().getY()*(Case.getDimensionY()/2)))-game.getOffsetY() - container.getHeight()) * -1;

		/*	Orientation newdir = null; 
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
		} */


	}

}
