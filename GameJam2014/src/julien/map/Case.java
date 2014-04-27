package julien.map;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.util.ResourceLoader;

import jitou.batiments.Batiment;
import jitou.batiments.Orientation;
import jitou.batiments.TypeBatiment;
import jitou.global.BoardGame;
import julien.game.Sprite;

public class Case extends Sprite{

	static private int dimensionX = 175;
	static private int dimensionY = 175;
	public int xcor, ycor;
	public Batiment batiment;
	
	public Case (int x , int y, int xcor, int ycor) {
		super(x,y,dimensionX,dimensionY);
		this.xcor = xcor;
		this.ycor = ycor;
		
		redraw();
		
	}

	public static int getDimensionX() {
		return dimensionX;
	}

	public static void setDimensionX(int dimensionX) {
		Case.dimensionX = dimensionX;
	}

	public static int getDimensionY() {
		return dimensionY;
	}

	public static void setDimensionY(int dimensionY) {
		Case.dimensionY = dimensionY;
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

	public void calculerPosition(int offsetX, int offsetY, int h) {
		x = xcor*Case.getDimensionX()-offsetX;
		y = (ycor*Case.getDimensionY()-offsetY - h) * -1 - Case.getDimensionY();
	}

	public void redraw() {
		if (BoardGame.boardGame.getBatiment(xcor, ycor) != null) {
			try {
				if (BoardGame.boardGame.getBatiment(xcor, ycor).getType() == TypeBatiment.Refectoire) {
					img = new Image("julien/images/Salle_refectoire.png");
					img.getGraphics().drawString(BoardGame.boardGame.getBatiment(xcor, ycor).getType().toString(), xcor, ycor);
					batiment = BoardGame.boardGame.getBatiment(xcor, ycor);
				} else
					if (BoardGame.boardGame.getBatiment(xcor, ycor).getType() == TypeBatiment.Hopital) {
						img = new Image("julien/images/Salle_hopital.png");
						img.getGraphics().drawString(BoardGame.boardGame.getBatiment(xcor, ycor).getType().toString(), xcor, ycor);
						batiment = BoardGame.boardGame.getBatiment(xcor, ycor);
					} 
			 else
				if (BoardGame.boardGame.getBatiment(xcor, ycor).getType() == TypeBatiment.Chaudiere) {
					img = new Image("julien/images/Salle_Chaudiere.png");
					img.getGraphics().drawString(BoardGame.boardGame.getBatiment(xcor, ycor).getType().toString(), xcor, ycor);
					batiment = BoardGame.boardGame.getBatiment(xcor, ycor);
				} 
		 else
			if (BoardGame.boardGame.getBatiment(xcor, ycor).getType() == TypeBatiment.Dortoir) {
				img = new Image("julien/images/Salle_dortoir.png");
				img.getGraphics().drawString(BoardGame.boardGame.getBatiment(xcor, ycor).getType().toString(), xcor, ycor);
				batiment = BoardGame.boardGame.getBatiment(xcor, ycor);
			} 
	 else if (BoardGame.boardGame.getBatiment(xcor, ycor).getType() == TypeBatiment.FermeHydroponique) {
			img = new Image("julien/images/Salle_ferme.png");
			img.getGraphics().drawString(BoardGame.boardGame.getBatiment(xcor, ycor).getType().toString(), xcor, ycor);
			batiment = BoardGame.boardGame.getBatiment(xcor, ycor);
		} 
				else {
					img = new Image("julien/images/Terre01.png");
					img.getGraphics().drawString(BoardGame.boardGame.getBatiment(xcor, ycor).getType().toString(), xcor, ycor);
					batiment = BoardGame.boardGame.getBatiment(xcor, ycor);
					img.setImageColor((float)Math.random(), (float)Math.random(),(float) Math.random());
				}
				
				//Dessin des couloirs
				if (batiment != null) {
					if (batiment.getVoisin(Orientation.sud) != null) {
						Image couloir = new Image("julien/images/couloir_bas.png");
						img.getGraphics().drawImage(couloir,61,131 + couloir.getHeight());
					}
					if (batiment.getVoisin(Orientation.ouest) != null) {
						Image couloir = new Image("julien/images/couloir_gauche.png");
						img.getGraphics().drawImage(couloir,0,95 + couloir.getHeight());
					}
					if (batiment.getVoisin(Orientation.nord) != null) {
						Image couloir = new Image("julien/images/couloir_haut.png");
						img.getGraphics().drawImage(couloir,61,00);
					}
				/*	if (batiment.getVoisin(Orientation.est) != null) {
						Image couloir = new Image("julien/images/couloir_droit.png");
						img.getGraphics().drawImage(couloir,134,93 + couloir.getHeight());
					}*/
				}
				
			} catch (SlickException e) {
				e.printStackTrace();
			}
			
		} else {
			try {
				img = new Image("julien/images/Terre00.png");
			} catch (SlickException e) {
				e.printStackTrace();
			}
		}		
	}
	
	
	
	
	
}
