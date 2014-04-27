package julien.game;

import java.util.ArrayList;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class FX extends Sprite{

	Animation anim;
	FXtype type;
	ArrayList<Image> sangFrames = new ArrayList<Image>();
	ArrayList<Image> exploFrames = new ArrayList<Image>();
	ArrayList<Image> maladieFrames = new ArrayList<Image>();

	public FX(FXtype type , int x, int y) {
		super(x,y,type.getX(),type.getY());
		this.type = type;
		if (sangFrames.isEmpty()) {
			loadImages();
		}
		initAnimation();
		
	}

	
	public void initAnimation() {
		if (type.equals(FXtype.sang)) {
    		anim = new Animation();
    		anim.setAutoUpdate(true);
            for (int i = 0; i < 5; i++){
            	anim.addFrame(sangFrames.get(i),100);
            }
            anim.stopAt(4);
		}
		if (type.equals(FXtype.explosion)) {
    		anim = new Animation();
    		anim.setAutoUpdate(true);
            for (int i = 0; i < 8; i++){
            	anim.addFrame(exploFrames.get(i),100);
            }
            anim.stopAt(7);
		}
		if (type.equals(FXtype.maladie)) {
    		anim = new Animation();
    		anim.setAutoUpdate(true);
            for (int i = 0; i < 3; i++){
            	anim.addFrame(maladieFrames.get(i),100);
            }
            anim.stopAt(2);
		}

	}
	
	
	
	
	
	private void loadImages() {
		try {
			sangFrames.add(new Image("julien/images/sang00.png"));
			sangFrames.add(new Image("julien/images/sang01.png"));
			sangFrames.add(new Image("julien/images/sang02.png"));
			sangFrames.add(new Image("julien/images/sang03.png"));
			sangFrames.add(new Image("julien/images/sang04.png"));
			exploFrames.add(new Image("julien/images/explosion01_00.png"));
			exploFrames.add(new Image("julien/images/explosion01_01.png"));
			exploFrames.add(new Image("julien/images/explosion01_02.png"));
			exploFrames.add(new Image("julien/images/explosion01_03.png"));
			exploFrames.add(new Image("julien/images/explosion01_04.png"));
			exploFrames.add(new Image("julien/images/explosion01_05.png"));
			exploFrames.add(new Image("julien/images/explosion01_07.png"));
			exploFrames.add(new Image("julien/images/explosion01_08.png"));
			maladieFrames.add(new Image("julien/images/Malade00.png"));
			maladieFrames.add(new Image("julien/images/Malade01.png"));
			maladieFrames.add(new Image("julien/images/Malade02.png"));
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
	}






	public Animation getAnim() {
		return anim;
	}
	public void setAnim(Animation anim) {
		this.anim = anim;
	}
	public FXtype getType() {
		return type;
	}
	public void setType(FXtype type) {
		this.type = type;
	}

	
	
	
	
}
