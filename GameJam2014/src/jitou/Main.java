package jitou;

import org.newdawn.slick.Animation;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;


public class Main extends BasicGame {
	private int x, y;
	private Animation anim = null;
	
    public Main()  {
        super("Lesson 1 :: WindowGame");
        x = 0;
    	y = 0;
    	
    	
    }

    @Override
    public void init(GameContainer container) throws SlickException {
       	Image [] imgs = {new Image(20,20),new Image(20,20),new Image(20,20),new Image(20,20),new Image(20,20),new Image(20,20)};
    	for(int i=0;i<imgs.length;i++)
    		{
    		imgs[i].setImageColor((float)(0.1*i),0.5f, 0.1f, 0.5f);

    		
    		}
    	anim =new Animation(imgs, 100, false);
    	anim.setLooping(true);
    
    }

    @Override
    public void render(GameContainer container, Graphics g) throws SlickException {
    	//g.drawOval(x, y, 100,100);
    	x=(x+1)%400;x=50;
    	y=(y+1)%400;y=50;
    	System.out.println("render "+anim);
    	anim.update(50);
    	g.drawAnimation(anim, x, y);
    	anim.draw();

    }

    @Override
    public void update(GameContainer container, int delta) throws SlickException {
    	System.out.println("update "+delta);
    	
    }
    
    
	
	public static void main(String[] args) throws SlickException {
		// TODO Auto-generated method stub
		AppGameContainer game  = new AppGameContainer(new Main(), 640, 480, false);
		game.setSmoothDeltas(true);
		game.setTargetFrameRate(60); 
		game.start();
		//test

	}

}
