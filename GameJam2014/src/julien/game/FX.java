package julien.game;

import java.util.ArrayList;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class FX extends Sprite{

	Animation anim;
	FXtype type;
	ArrayList<Image> sangFrames = new ArrayList<Image>();
	
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
		}

	}
	
	
	
	
	
	private void loadImages() {
		try {
			sangFrames.add(new Image("julien/images/sang00.png"));
			sangFrames.add(new Image("julien/images/sang01.png"));
			sangFrames.add(new Image("julien/images/sang02.png"));
			sangFrames.add(new Image("julien/images/sang03.png"));
			sangFrames.add(new Image("julien/images/sang04.png"));
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
