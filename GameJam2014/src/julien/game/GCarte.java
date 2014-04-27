package julien.game;

import jitou.batiments.Batiment;
import jitou.batiments.TypeBatiment;
import jitou.global.BoardGame;
import julien.map.Case;
import julien.mechant.Carte;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import de.matthiasmann.twl.Container;

public class GCarte extends Sprite{

	static private int dimensionX = 185;
	static private int dimensionY = 300;
	static private int decalX = 40;
	static private int decalY = 15;
	static private int vitesseDecalage = 8;
	static private int decalageMin = 20;
	static private int decalageMax = 300;
	
	private int decalage = decalageMin;
	public int xcor, ycor;
	public Batiment batiment;
	Carte carte;
	
	public GCarte(Carte carte, int i, GameContainer container) {
		super(0,0,dimensionX,dimensionY);
		this.carte = carte;
		redraw(i, container);
		
	}

	public void decaler() {
		if (decalage < decalageMax) {
			decalage += vitesseDecalage;
			this.y = - getDimensionY() + decalage;
		}
	}
	
	public void recaler() {
		if (decalage > decalageMin) {
			decalage -= vitesseDecalage;
			this.y = - getDimensionY() + decalage;
		}
	}
	
	public static int getDimensionX() {
		return dimensionX;
	}

	public static void setDimensionX(int dimensionX) {
		dimensionX = dimensionX;
	}

	public static int getDimensionY() {
		return dimensionY;
	}

	public static void setDimensionY(int dimensionY) {
		dimensionY = dimensionY;
	}

	public int getXcor() {
		return xcor;
	}

	public void setXcor(int xcor) {
		this.xcor = xcor;
	}

	public int getYcor() {
		return ycor;
	}

	public void setYcor(int ycor) {
		this.ycor = ycor;
	}

	public boolean collision(int x, int y){
		if (x>this.x && x<this.x+w &&  y>this.y && y<this.y+h) {
			System.out.println("Carte cliquée");
			if (y<(this.y)+(h/2)) {
				carte.selectionMal();
			} else {
				carte.selectionBien();
			}
		}
		return x>this.x && x<this.x+w &&  y>this.y && y<this.y+h;
	}
	
	public void survolDecalage(int x, int y){
		if (x>this.x && x<this.x+w &&  y>this.y && y<this.y+h) {
			decaler();
		} else {
			recaler();
		}
	}

	public void redraw(int i, GameContainer container) {
		
		this.x = (decalX * (i + 1)) + (dimensionX * i);
		this.y = - getDimensionY() + decalageMin;//container.getHeight() - getDimensionY();;
		
		try {
			img = new Image("julien/images/carte2.png");
			img.getGraphics().drawString(this.carte.getEffetMal().getClass().getSimpleName(), 10, 10);
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
					
	}
	
	
	
	
	
}
