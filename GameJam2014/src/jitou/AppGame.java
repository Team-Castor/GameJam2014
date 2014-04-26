package jitou;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;

public class AppGame extends AppGameContainer{
	private static final String titreFenPrincipale = "Sous la surface";
	
	
	public AppGame() throws SlickException{
		super(new Game(titreFenPrincipale), 640, 480, false);
		this.setSmoothDeltas(true);
		this.setTargetFrameRate(60); 
		this.start();
	}
	
	
	
}
