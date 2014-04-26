package julien;

import jitou.global.BoardGame;

import org.newdawn.slick.SlickException;


public class Main {


	public static void main(String[] args)  {
		AppGame app = null;
		try {
			app = new AppGame();
		} catch (SlickException e) {
			System.err.println("Probleme creation de AppGame\n"+e.getMessage());
		}
		BoardGame.boardGame.initialisation();
		


	}

}
