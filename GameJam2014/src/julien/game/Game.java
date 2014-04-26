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
	
	private static Game instance;
	private final int defilement = -10;
	
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
    private int pressedKey;
    Input input;
    
    private ArrayList<Case> casesADessiner = new ArrayList<Case>();
    private ArrayList<Case> casesHorsEcran = new ArrayList<Case>();
    
    private ArrayList<SpriteHumain> spritesHumains = new ArrayList<SpriteHumain>();

	public Game(String titre) {
		super(titre);
	}

	public void init(GameContainer container) throws SlickException {
		input = container.getInput();
        board = BoardGame.boardGame;
		containerH = container.getHeight();
		containerW = container.getWidth();
		instance = this;
		
		int h = board.getDimensionworldy() ;
		int w = board.getDimensionworldx() ;

		for (int x = 0 ; x < w ; x++) {
			for (int y = 0 ; y < h ; y++) {
				casesHorsEcran.add(new Case(x*Case.getDimensionX()-offsetX,y*Case.getDimensionY()-offsetY,x,y));
			}
		}
		computeCaseADessiner();
	}


	public void keyPressed(int key, char c) {
		super.keyPressed(key, c);
		System.out.println("Id : "+key+" , char : "+c);
		
		pressedKey = key;

		
	}
	public void keyReleased(int key, char c) {
		if (key == pressedKey) {
			pressedKey = 0;
		}
	}
	
	
	public void checkKey() {
		if (input.isKeyDown(Input.KEY_UP))
		{
			offsetY = offsetY - defilement;
			computeCaseADessiner();
		}
		else if (input.isKeyDown(Input.KEY_DOWN))
		{
			offsetY = offsetY + defilement;
			computeCaseADessiner();
		}
		else if (input.isKeyDown(Input.KEY_LEFT))
		{
			offsetX = offsetX + defilement;
			computeCaseADessiner();
		}
		else if (input.isKeyDown(Input.KEY_RIGHT))
		{
			offsetX = offsetX - defilement;
			computeCaseADessiner();
		}
		
	}
	
	
	
	public void mouseClicked(int button,int  x, int  y, int  clickCount) {
		super.mouseClicked(button, x, y, clickCount);
		System.out.println("Id : "+button+" , XY : "+x+","+y+" count : "+clickCount);

	}



	public void render(GameContainer container, Graphics g) throws SlickException {
		checkKey();
		
		for (int i = 0 ; i < casesADessiner.size() ; i++) {
			g.drawImage(casesADessiner.get(i).getImage(), casesADessiner.get(i).getX(), casesADessiner.get(i).getY());	
		}
	
		ArrayList<Citoyen> citoyens = board.getCitoyens();
		for(Citoyen cit : citoyens){
			g.drawImage(new SpriteHumain(this, null).getImage(),(float)((cit.getPos().x*Case.getDimensionX()) + (Case.getDimensionX()/2 + cit.getPosInside().getX()*(Case.getDimensionX()/2))-offsetX),
					(float)(((cit.getPos().y*Case.getDimensionY()) + (Case.getDimensionY()/2 + cit.getPosInside().getY()*(Case.getDimensionY()/2)))-offsetY - containerH) * -1		
			);
			//g.setColor(Color.red);
			//g.fillOval((float)((cit.getPos().x*Case.getDimensionX()) + (Case.getDimensionX()/2 + cit.getPosInside().getX()*(Case.getDimensionX()/2)))-offsetX,
			//		(float)(((cit.getPos().y*Case.getDimensionY()) + (Case.getDimensionY()/2 + cit.getPosInside().getY()*(Case.getDimensionY()/2)))-offsetY - containerH) * -1, (float)10,(float) 10);		
		}
		
		//g.drawString("Score : "+points, 100,50);

	}
	
	public static void addSpriteCitoyen(Citoyen c) {
		//this.spritesHumains.add(new SpriteHumain(this, c));
	}


	public void update(GameContainer container, int delta) throws SlickException {
		board.update(delta);
		
		containerH = container.getHeight();
		containerW = container.getWidth();

	}
	
	private void computeCaseADessiner() {
		int h = board.getDimensionworldy() ;
		int w = board.getDimensionworldx() ;
		
		for (int i = 0 ; i < casesHorsEcran.size(); i++) {
			if (batimentIsOnScreen(casesHorsEcran.get(i).getXcor(),casesHorsEcran.get(i).getYcor())) {
				casesADessiner.add(casesHorsEcran.get(i));
				casesHorsEcran.remove(i);
				i--;
			//	System.out.println("cases �� pas dessiner : " + casesHorsEcran.size());

			}
		}
		
		for (int i = 0 ; i < casesADessiner.size(); i++) {
			if (!batimentIsOnScreen(casesADessiner.get(i).getXcor(),casesADessiner.get(i).getYcor())) {
				casesHorsEcran.add(casesADessiner.get(i));
				casesADessiner.remove(i);
				i--;
				//System.out.println("cases �� dessiner : " + casesADessiner.size());
			}
			else {
				casesADessiner.get(i).calculerPosition(offsetX,offsetY,containerH);
			}
		}
		//	System.out.println("cases �� dessiner : " + casesADessiner.size());
		
		/*
		for (int x = 0 ; x < w ; x++) {
			for (int y = 0 ; y < h ; y++) {
				if (batimentIsOnScreen(x,y)) {
					casesADessiner.add(new Case(x*Case.getDimensionX()-offsetX,y*Case.getDimensionY()-offsetY,x,y));
				}
			}
		}*/
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

	public static Game getInstance() {
		return instance;
	}

	
}
