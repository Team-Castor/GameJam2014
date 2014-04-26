package jitou;

import org.newdawn.slick.*;

public class Game  extends BasicGame{
	private Sprite[] liste;
	
	
	public Game(String titre) {
		super(titre);
	}

	public void init(GameContainer container) throws SlickException {
		Input input = container.getInput();
		liste = new Sprite[100];
		for(int i=0;i<100;i++){
			liste[i]=new Sprite();
		}
	}


	public void keyPressed(int key, char c) {
		super.keyPressed(key, c);
		System.out.println("Id : "+key+" , char : "+c);
	}
	public void keyReleased(int key, char c) {
		super.keyReleased(key, c);
		System.out.println("Id : "+key+" , char : "+c);
	}


	public void render(GameContainer container, Graphics g) throws SlickException {
		for(int i=0;i<this.liste.length;i++){
			g.drawImage(liste[i].getImage(), (int)liste[i].getX(), (int)liste[i].getY());				
		}
	}



	public void update(GameContainer container, int delta) throws SlickException {
		for(int i=0;i<this.liste.length;i++){
			liste[i].update(delta);
			
		}
	}

}
