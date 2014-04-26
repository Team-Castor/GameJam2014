package jitou;

import org.newdawn.slick.*;

public class Game  extends BasicGame{
	private Sprite[] liste;
	private int points=0;
	
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
	public void mouseClicked(int button,int  x, int  y, int  clickCount) {
		super.mouseClicked(button, x, y, clickCount);
		System.out.println("Id : "+button+" , XY : "+x+","+y+" count : "+clickCount);
		for(int i=0;i<this.liste.length;i++){
			boolean ok = liste[i].collision(x, y);
			if(ok){
				System.out.println("Collision !");
				points++;
			}
		}
	}



	public void render(GameContainer container, Graphics g) throws SlickException {
		for(int i=0;i<this.liste.length;i++){
			g.drawImage(liste[i].getImage(), (int)liste[i].getX(), (int)liste[i].getY());				
		}
		
		g.drawString("Score : "+points, 100,50);

	}



	public void update(GameContainer container, int delta) throws SlickException {
		for(int i=0;i<this.liste.length;i++){
			liste[i].update(delta);
			
		}
	}

}
