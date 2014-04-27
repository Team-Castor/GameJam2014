package julien.game;

import jitou.batiments.Batiment;
import jitou.batiments.TypeBatiment;
import jitou.global.BoardGame;
import julien.map.Case;
import julien.mechant.Carte;
import julien.mechant.Effet;
import julien.mechant.effets.gentils.*;
import julien.mechant.effets.mechants.*;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import de.matthiasmann.twl.Container;

public class GCarte extends Sprite{

	static private int dimensionX = 185;
	static private int dimensionY = 300;
	static private int decalX = 40;
	static private int decalY = 15;
	static private int vitesseDecalage = 18;
	static private int decalageMin = 20;
	static private int decalageMax = 300;
	
	private int decalage = decalageMin;
	public int xcor, ycor;
	public Batiment batiment;
	private String log="";
	private Image imgMal, imgBonne;
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

	public String getLog() {
		return log;
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

	public String collisionCarte(int x, int y){
	//	String obj=null;
		if (x>this.x && x<this.x+w &&  y>this.y && y<this.y+h) {
			if (y<(this.y)+(h/2)) {
				carte.selectionMal();
			} else {
				carte.selectionBien();
			} 

		}
		return (carte.getLog());//x>this.x && x<this.x+w &&  y>this.y && y<this.y+h;
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
			
			imgMal = getImage(this.carte.getEffetMal());
			imgBonne = getImage(this.carte.getEffetBien());

		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
					
	}
	
	
	private Image getImage(Effet c) throws SlickException{
		if(c.getClass().toString().equals(Armagedon.class.toString())){
			return  new Image("julien/images/effets/Armageddon.png");
		}
		else if(c.getClass().toString().equals(AvanceeMedicale.class.toString())){
			return  new Image("julien/images/effets/Progrès médical.png");
		}
		else if(c.getClass().toString().equals(CapsuleDeSurvivant.class.toString())){
			return  new Image("julien/images/effets/Capsule de survivor.png");
		}
		else if(c.getClass().toString().equals(ChasseSorcieres.class.toString())){
			return  new Image("julien/images/effets/Chasse aux sorcières.png");
		}
		else if(c.getClass().toString().equals(EscouadeDeReparation.class.toString())){
			return  new Image("julien/images/effets/escouade de réparation.png");
		}
		else if(c.getClass().toString().equals(FilonDeFer.class.toString())){
			return  new Image("julien/images/effets/Veine de fer.png");
		}
		else if(c.getClass().toString().equals(FilonDePetrole.class.toString())){
			return  new Image("julien/images/effets/Armageddon.png");
		}
		else if(c.getClass().toString().equals(Rationnement.class.toString())){
			return  new Image("julien/images/effets/Rationnement.png");
		}
		else if(c.getClass().toString().equals(SuperBasket.class.toString())){
			return  new Image("julien/images/effets/Super chaussures.png");
		}
		else if(c.getClass().toString().equals(SuperMacon.class.toString())){
			return  new Image("julien/images/effets/Maçon strike.png");
		}
		else if(c.getClass().toString().equals(AttaqueTaupeZombie.class.toString())){
			return  new Image("julien/images/effets/Armageddon.png");
		}
		else if(c.getClass().toString().equals(Cannibalisme.class.toString())){
			return  new Image("julien/images/effets/Canibalism.png");
		}
		else if(c.getClass().toString().equals(FuiteRadiationGenerateur.class.toString())){
			return  new Image("julien/images/effets/sabotage des réacteurs.png");
		}
		else if(c.getClass().toString().equals(Grippe.class.toString())){
			return  new Image("julien/images/effets/Armageddon.png");
		}
		else if(c.getClass().toString().equals(JeuneFou.class.toString())){
			return  new Image("julien/images/effets/Armageddon.png");
		}
		else if(c.getClass().toString().equals(Rhume.class.toString())){
			return  new Image("julien/images/effets/Rhum.png");
		}
		else if(c.getClass().toString().equals(Kamikaze.class.toString())){
			return  new Image("julien/images/effets/Kammikaze.png");
		}
		else if(c.getClass().toString().equals(PanneChaudiere.class.toString())){
			return  new Image("julien/images/effets/panne de chaudière.png");
		}
		else if(c.getClass().toString().equals(PetitePluieDeMeteroite.class.toString())){
			return  new Image("julien/images/effets/Petite pluie de météorites.png");
		}
		else if(c.getClass().toString().equals(Rat.class.toString())){
			return  new Image("julien/images/effets/Armageddon.png");
		}
		else if(c.getClass().toString().equals(PluieDeMeteroite.class.toString())){
			return  new Image("julien/images/effets/Pluie de météorites.png");
		}
		else if(c.getClass().toString().equals(ReveilZombie.class.toString())){
			return  new Image("julien/images/effets/Zombification.png");
		}
		else if(c.getClass().toString().equals(SabotageReserveExplosif.class.toString())){
			return  new Image("julien/images/effets/Sabotage des explosifs.png");
		}
		else if(c.getClass().toString().equals(VagueFanatique.class.toString())){
			return  new Image("julien/images/effets/Armageddon.png");
		}
		else if(c.getClass().toString().equals(VolOutils.class.toString())){
			return  new Image("julien/images/effets/vol d'outils.png");
		}
		else if(c.getClass().toString().equals(Mouton.class.toString())){
			return  new Image("julien/images/effets/mouton.png");
		}
		else if(c.getClass().toString().equals(VagueFanatique.class.toString())){
			return  new Image("julien/images/effets/Fanaticism.png");
		}
		else if(c.getClass().toString().equals(TroupeauMouton.class.toString())){
			return  new Image("julien/images/effets/Troupeau de mouton.png");
		}
		else if(c.getClass().toString().equals(IndendiePetrole.class.toString())){
			return  new Image("julien/images/effets/incendie du pétrole.png");
		}
		else if(c.getClass().toString().equals(RatCreve.class.toString())){
			return  new Image("julien/images/effets/rat crevé.png");
		}
		else if(c.getClass().toString().equals(NourritureEmpoisonnee.class.toString())){
			return  new Image("julien/images/effets/nourriture empoisonnée.png");
		}
		else if(c.getClass().toString().equals(ChariotBug.class.toString())){
			return  new Image("julien/images/effets/bug du chariot.png");
		}
		else if(c.getClass().toString().equals(NourritureAvarie.class.toString())){
			return  new Image("julien/images/effets/Armageddon.png");
		}
		
		
		System.out.println("Pas fait : "+c);
		return  null;

	}

	public Image getImageEffetMal() {
		return imgMal;
	}
	public Image getImageEffetBonne() {
		return imgBonne;
	}
	
	
	
	
}
