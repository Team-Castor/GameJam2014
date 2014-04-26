package julien.game;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;

public class AppGame extends AppGameContainer{
	private static final String titreFenPrincipale = "Sous la surface";
	
	
	public AppGame() throws SlickException{
		super(new Game(titreFenPrincipale), 800, 600, false);
		this.setSmoothDeltas(true);
		this.setTargetFrameRate(60); 
		this.start();
	}
	
	
	
}
