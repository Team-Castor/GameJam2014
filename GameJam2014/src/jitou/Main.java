package jitou;

import jitou.global.BoardGame;
import julien.game.AppGame;
import julien.mechant.Mechant;

import org.newdawn.slick.SlickException;


public class Main {


	public static void main(String[] args)  {
		
		new Mechant();
		BoardGame.boardGame.initialisation();

		AppGame app = null;
		try {
			app = new AppGame();
		} catch (SlickException e) {
			System.err.println("Probleme creation de AppGame\n"+e.getMessage());
		}



	}

}
