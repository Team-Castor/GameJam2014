package jitou;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;


public class Main extends BasicGame {
	private int x, y;
	
	
    public Main()  {
        super("Lesson 1 :: WindowGame");
        x = 0;
    	y = 0;
    
    }

    @Override
    public void init(GameContainer container) throws SlickException {
    }

    @Override
    public void render(GameContainer container, Graphics g) throws SlickException {
    	g.drawOval(x, y, 100,100);
    	x=(x+1)%400;
    	y=(y+1)%400;
    	
    }

    @Override
    public void update(GameContainer container, int delta) throws SlickException {
    	
    }
    
    
	
	public static void main(String[] args) throws SlickException {
		// TODO Auto-generated method stub
		AppGameContainer game  = new AppGameContainer(new Main(), 640, 480, false);
		
		game.start();

	}

}
