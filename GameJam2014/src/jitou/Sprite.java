package jitou;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Sprite {
	private Image img;
	private float x, y;
	private float vx, vy;

	public Sprite(){
		x = 20f;
		y = 20f;
		vx = (float) (Math.random()-0.5);
		vy = (float) (Math.random()-0.5);
		try {
			img = new Image(20,20);
			img.getGraphics().drawLine(0, 0,10,10);
			img.getGraphics().drawLine(10, 0,0,10);
		} catch (SlickException e) {
			e.printStackTrace();
		}
		img.setImageColor((float)Math.random(), (float)Math.random(),(float) Math.random());
	}
	

	public void update(int delta) {
		vx+=(Math.random()-0.5)/10.0;
		vy+=(Math.random()-0.5)/10.0;
		
		x+=vx*delta/10.0;
		y+=vy*delta/10.0;
		
		int dimX= 640;
		int dimY=480;
		if(x<0) x=dimX;
		else if(x>dimX) x=0;
		if(y<0) y=dimY;
		else if(y>dimY) y=0;
	}

	public Image getImage(){
		return img;
	}
	
	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	
	
}
