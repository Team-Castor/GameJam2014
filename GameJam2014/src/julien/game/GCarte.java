package julien.game;


import java.awt.Font;

import jitou.batiments.Batiment;
import jitou.batiments.TypeBatiment;
import jitou.global.BoardGame;
import julien.map.Case;
import julien.mechant.Carte;
import julien.mechant.Effet;
import julien.mechant.effets.gentils.*;
import julien.mechant.effets.mechants.*;

import org.newdawn.slick.Color;
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
	static private int decalageMin = 30;
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
			//img.getGraphics().drawString(this.carte.getEffetMal().getClass().getSimpleName(), 10, 10);

			imgMal = getImage(this.carte.getEffetMal());
			imgBonne = getImage(this.carte.getEffetBien());


			img.getGraphics().setColor(Color.cyan);
			img.getGraphics().drawString(
					new Integer(carte.getEffetMal().getVariationEnergie()[0]).toString()
					, 100,125);
			img.getGraphics().setColor(Color.blue);

			img.getGraphics().drawString(
					new Integer(carte.getEffetMal().getVariationEnergie()[1]).toString()
					, 128,125);
			img.getGraphics().setColor(Color.red);

			img.getGraphics().drawString(
					new Integer(carte.getEffetMal().getVariationEnergie()[2]).toString()
					, 156,125);


			img.getGraphics().setColor(Color.cyan);

			img.getGraphics().drawString(
					new Integer(carte.getEffetBien().getVariationEnergie()[0]).toString()
					, 100,128+h/2-10);
			img.getGraphics().setColor(Color.blue);

			img.getGraphics().drawString(
					new Integer(carte.getEffetBien().getVariationEnergie()[1]).toString()
					, 128,128+h/2-10);
			img.getGraphics().setColor(Color.red);

			img.getGraphics().drawString(
					new Integer(carte.getEffetBien().getVariationEnergie()[2]).toString()
					, 156,128+h/2-10);

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
			return  new Image("julien/images/effets/Progres medical.png");
		}
		else if(c.getClass().toString().equals(CapsuleDeSurvivant.class.toString())){
			return  new Image("julien/images/effets/Capsule de survivor.png");
		}
		else if(c.getClass().toString().equals(ChasseSorcieres.class.toString())){
			return  new Image("julien/images/effets/Chasse aux sorcieres.png");
		}
		else if(c.getClass().toString().equals(EscouadeDeReparation.class.toString())){
			return  new Image("julien/images/effets/escouade de reparation.png");
		}
		else if(c.getClass().toString().equals(FilonDeFer.class.toString())){
			return  new Image("julien/images/effets/Veine de fer.png");
		}
		else if(c.getClass().toString().equals(FilonDePetrole.class.toString())){
			return  new Image("julien/images/effets/gisement de petrole.png");
		}
		else if(c.getClass().toString().equals(Rationnement.class.toString())){
			return  new Image("julien/images/effets/Rationnement.png");
		}
		else if(c.getClass().toString().equals(SuperBasket.class.toString())){
			return  new Image("julien/images/effets/Super chaussures.png");
		}
		else if(c.getClass().toString().equals(SuperMacon.class.toString())){
			return  new Image("julien/images/effets/Macon strike.png");
		}
		else if(c.getClass().toString().equals(AttaqueTaupeZombie.class.toString())){
			return  new Image("julien/images/effets/Attaque de Top.png");
		}
		else if(c.getClass().toString().equals(Cannibalisme.class.toString())){
			return  new Image("julien/images/effets/Canibalism.png");
		}
		else if(c.getClass().toString().equals(FuiteRadiationGenerateur.class.toString())){
			return  new Image("julien/images/effets/sabotage des reacteurs.png");
		}
		else if(c.getClass().toString().equals(Grippe.class.toString())){
			return  new Image("julien/images/effets/Grippe.png");
		}
		else if(c.getClass().toString().equals(JeuneFou.class.toString())){
			return  new Image("julien/images/effets/Jeune fou.png");
		}
		else if(c.getClass().toString().equals(Rhume.class.toString())){
			return  new Image("julien/images/effets/Rhum.png");
		}
		else if(c.getClass().toString().equals(Kamikaze.class.toString())){
			return  new Image("julien/images/effets/Kammikaze.png");
		}
		else if(c.getClass().toString().equals(PanneChaudiere.class.toString())){
			return  new Image("julien/images/effets/panne de chaudiere.png");
		}
		else if(c.getClass().toString().equals(PetitePluieDeMeteroite.class.toString())){
			return  new Image("julien/images/effets/Petite pluie de meteorites.png");
		}
		else if(c.getClass().toString().equals(Rat.class.toString())){
			return  new Image("julien/images/effets/Invasion de rats.png");
		}
		else if(c.getClass().toString().equals(PluieDeMeteroite.class.toString())){
			return  new Image("julien/images/effets/Pluie de meteorites.png");
		}
		else if(c.getClass().toString().equals(ReveilZombie.class.toString())){
			return  new Image("julien/images/effets/Zombification.png");
		}
		else if(c.getClass().toString().equals(SabotageReserveExplosif.class.toString())){
			return  new Image("julien/images/effets/sabotage des explosifs.png");
		}
		else if(c.getClass().toString().equals(VolOutils.class.toString())){
			return  new Image("julien/images/effets/vol doutils.png");
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
			return  new Image("julien/images/effets/incendie du petrole.png");
		}
		else if(c.getClass().toString().equals(RatCreve.class.toString())){
			return  new Image("julien/images/effets/rat creve.png");
		}
		else if(c.getClass().toString().equals(NourritureEmpoisonnee.class.toString())){
			return  new Image("julien/images/effets/nourriture empoisonnee.png");
		}
		else if(c.getClass().toString().equals(ChariotBug.class.toString())){
			return  new Image("julien/images/effets/bug du chariot.png");
		}
		else if(c.getClass().toString().equals(NourritureAvarie.class.toString())){
			return  new Image("julien/images/effets/nourriture avarie.png");
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
