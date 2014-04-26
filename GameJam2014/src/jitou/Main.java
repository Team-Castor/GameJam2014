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
	
    public Main()  {
        super("Sous la surface");    	
    }

    @Override
    public void init(GameContainer container) throws SlickException {
    }

    @Override
    public void render(GameContainer container, Graphics g) throws SlickException {
    	System.out.println("render ");
 

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
