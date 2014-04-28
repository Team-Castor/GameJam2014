package julien.game;

import java.io.File;

import jitou.global.BoardGame;
import julien.mechant.Mechant;

import org.newdawn.slick.SlickException;


public class Main {


	public static void main(String[] args)  {
		
	 /*     final String os = System.getProperty("os.name").toLowerCase();
	      String path = "native/";
	      
	      if(os.contains("win"))
	         path += "windows";
	      else if(os.contains("mac"))
	         path += "mac";
	      else if(os.contains("nix") || os.contains("nux") || os.contains("aix"))
	         path += "linux";
	      else if(os.contains("sunos"))
	         path += "solaris";
	      
	      System.setProperty("org.lwjgl.librarypath", new File(path).getAbsolutePath());
	      */
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
