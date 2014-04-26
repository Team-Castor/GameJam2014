package jitou;

import jitou.global.BoardGame;
import julien.game.AppGame;

import org.newdawn.slick.SlickException;


public class Main {


	public static void main(String[] args)  {

		BoardGame.boardGame.initialisation();
		for(int i=0;i<1000;i++){
			BoardGame.boardGame.update(10);
			BoardGame.boardGame.update(10);
			BoardGame.boardGame.update(10);

		}
		AppGame app = null;
		try {
			app = new AppGame();
		} catch (SlickException e) {
			System.err.println("Probleme creation de AppGame\n"+e.getMessage());
		}

		/*	AppGame app = null;
		try {
			app = new AppGame();
		} catch (SlickException e) {
			System.err.println("Probleme creation de AppGame\n"+e.getMessage());
		}
		BoardGame.boardGame.initialisation();
		 */


	}

}
