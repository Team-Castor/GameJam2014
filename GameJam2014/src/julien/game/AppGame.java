package julien.game;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.util.Log;

public class AppGame extends AppGameContainer{
	private static final String titreFenPrincipale = "Sous la surface";
	
	
	public AppGame() throws SlickException{
		super(new Game(titreFenPrincipale), 1200, 760, false);
		Log.setVerbose(false);
		this.setSmoothDeltas(true);
		this.setTargetFrameRate(60);
		
		this.start();
	}
	
	
	
}
