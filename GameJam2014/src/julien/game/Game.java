package julien.game;

import java.io.IOException;
import java.util.ArrayList;

import jitou.global.BoardGame;
import jitou.global.Citoyen;
import julien.map.Case;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.*;
import org.newdawn.slick.util.ResourceLoader;

import de.matthiasmann.twl.GUI;
import de.matthiasmann.twl.Widget;
import de.matthiasmann.twl.renderer.lwjgl.LWJGLRenderer;
import de.matthiasmann.twl.theme.ThemeManager;

public class Game  extends BasicGame{
	private final int defilement = 10;
	
	private Sprite[] liste;
	private int points=0;
	
    private LWJGLRenderer lwjglRenderer;
    private ThemeManager theme;
    private GUI gui;
    private Widget root;
    private TWLInputAdapter twlInputAdapter;
    private BoardGame board;
    
    private int offsetX, offsetY;
    private int containerW, containerH;
    
    private ArrayList<Case> casesADessiner = new ArrayList<Case>();

    
	public Game(String titre) {
		super(titre);
	}

	public void init(GameContainer container) throws SlickException {
		Input input = container.getInput();
        liste = new Sprite[100];
        board = BoardGame.boardGame;
		for(int i=0;i<100;i++){
			liste[i]=new Sprite();
		}
		containerH = container.getHeight();
		containerW = container.getWidth();
		computeCaseADessiner();
	}


	public void keyPressed(int key, char c) {
		super.keyPressed(key, c);
		System.out.println("Id : "+key+" , char : "+c);
		
		switch (key) {
		
		case 203 : offsetX =- defilement;
					computeCaseADessiner();
			
		case 205 : offsetX =+ defilement;
					computeCaseADessiner();
				
		
		break;
		
		}

		
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
	
		for (int i = 0 ; i < casesADessiner.size() ; i++) {
			g.drawImage(casesADessiner.get(i).getImage(), casesADessiner.get(i).getX(), casesADessiner.get(i).getY());	
		}
	
		ArrayList<Citoyen> citoyens = board.getCitoyens();
		for(Citoyen cit : citoyens){
			System.out.println("citoyen");
			g.setColor(Color.red);
			g.fillOval((float)((cit.getPos().x*Case.getDimensionX()) + (Case.getDimensionX()/2 + cit.getPosInside().getX()*(Case.getDimensionX()/2))),
					(float)((cit.getPos().y*Case.getDimensionY()) + (Case.getDimensionY()/2 + cit.getPosInside().getY()*(Case.getDimensionY()/2))), (float)10,(float) 10);		
		}
		
		//g.drawString("Score : "+points, 100,50);

	}



	public void update(GameContainer container, int delta) throws SlickException {
		containerH = container.getHeight();
		containerW = container.getWidth();

		for(int i=0;i<this.liste.length;i++){
			liste[i].update(delta);		
		}
		

		
	}
	
	private void computeCaseADessiner() {
		casesADessiner.clear();
		int h = board.getDimensionworldy() ;
		int w = board.getDimensionworldx() ;
		
		for (int x = 0 ; x < w ; x++) {
			for (int y = 0 ; y < h ; y++) {
				if (batimentIsOnScreen(x,y)) {
					casesADessiner.add(new Case(x*Case.getDimensionX()-offsetX,y*Case.getDimensionY()-offsetY,x,y));
				}
			}
		}
	}
	
	private boolean batimentIsOnScreen(int x, int y) {
		int xmin = x*Case.getDimensionX() - offsetX;
		int ymin = y*Case.getDimensionY() - offsetY;



		if (xmin + Case.getDimensionX() >= 0 &&
			ymin + Case.getDimensionY() >= 0 &&
			xmin < containerW &&
			ymin < containerH
			) {
			return true;
		}
		
		return false;
		
	}

}
