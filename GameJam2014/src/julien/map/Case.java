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
	private int typeDeSol;
	
	public Case (int x , int y, int xcor, int ycor) {
		super(x,y,dimensionX,dimensionY);
		this.xcor = xcor;
		this.ycor = ycor;
		typeDeSol = (int) (Math.random() * 5);
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
						if (BoardGame.boardGame.getBatiment(xcor, ycor).getType() == TypeBatiment.PuitPetrol) {
							img = new Image("julien/images/Salle_petrol.png");
							img.getGraphics().drawString(BoardGame.boardGame.getBatiment(xcor, ycor).getType().toString(), xcor, ycor);
							batiment = BoardGame.boardGame.getBatiment(xcor, ycor);
						}
						else
							if (BoardGame.boardGame.getBatiment(xcor, ycor).getType() == TypeBatiment.MineDeFer) {
								img = new Image("julien/images/Salle_mine de fer.png");
								img.getGraphics().drawString(BoardGame.boardGame.getBatiment(xcor, ycor).getType().toString(), xcor, ycor);
								batiment = BoardGame.boardGame.getBatiment(xcor, ycor);
							} 
							else
								if (BoardGame.boardGame.getBatiment(xcor, ycor).getType() == TypeBatiment.Generateur) {
									img = new Image("julien/images/Salle_Generateur.png");
									img.getGraphics().drawString(BoardGame.boardGame.getBatiment(xcor, ycor).getType().toString(), xcor, ycor);
									batiment = BoardGame.boardGame.getBatiment(xcor, ycor);
								}
								else
									if (BoardGame.boardGame.getBatiment(xcor, ycor).getType() == TypeBatiment.Arsenal) {
										img = new Image("julien/images/Salle_Armement.png");
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
					if (batiment.getVoisin(Orientation.nord) != null) {
						Image couloir = new Image("julien/images/couloir_bas.png");
						img.getGraphics().drawImage(couloir,(dimensionX - (couloir.getWidth()))/2,dimensionY - couloir.getHeight());
					}
					if (batiment.getVoisin(Orientation.ouest) != null) {
						Image couloir = new Image("julien/images/couloir_gauche.png");
						img.getGraphics().drawImage(couloir,0,dimensionY/2);
					}
					if (batiment.getVoisin(Orientation.sud) != null) {
						Image couloir = new Image("julien/images/couloir_haut.png");
						img.getGraphics().drawImage(couloir,(dimensionX - (couloir.getWidth()))/2,0);
					}
					if (batiment.getVoisin(Orientation.est) != null) {
						Image couloir = new Image("julien/images/couloir_droit.png");
						img.getGraphics().drawImage(couloir,dimensionX - (couloir.getWidth()),dimensionY/2);
				}}
				
			} catch (SlickException e) {
				e.printStackTrace();
			}
			
		} else {
			try {
				switch (typeDeSol) {
				case 0 :
					img = new Image("julien/images/Terre00.png");
					break;
				case 1 :
					img = new Image("julien/images/Terre01.png");
					break;
				case 2 :
					img = new Image("julien/images/Terre02.png");
					break;
				case 3 :
					img = new Image("julien/images/Terre03.png");
					break;
				case 4 :
					img = new Image("julien/images/Terre04.png");
					break;
				
				}
				
			} catch (SlickException e) {
				e.printStackTrace();
			}
		}		
	}
	
	
	
	
	
}
