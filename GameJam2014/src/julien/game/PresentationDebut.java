package julien.game;

import java.awt.Font;
import java.awt.GraphicsEnvironment;

import jitou.batiments.Batiment;
import jitou.batiments.TypeBatiment;
import jitou.global.BoardGame;
import julien.map.Case;
import julien.mechant.Carte;
import julien.mechant.Effet;
import julien.mechant.effets.gentils.Mouton;
import julien.mechant.effets.mechants.Armagedon;

import org.newdawn.slick.*;

import de.matthiasmann.twl.Container;

public class PresentationDebut{

	public Font font;
	public TrueTypeFont trueTypeFont;
	private Image img_haut = null;
	private Image img_g = null;

	private GCarte carte;

	double progressBar = 0.0;
	int    el = 0;
	private double div = 15.0;

	public PresentationDebut( GameContainer container) {	
		font = new Font("Purisa", Font.BOLD, 25);
		trueTypeFont = new TrueTypeFont(font, true);


		try {
			img_g = new Image("julien/images/flecheGauche.png");
			img_haut = new Image("julien/images/flecheHaut.png");
			carte = new GCarte(new Carte(new Mouton(), new Armagedon()), 0, container);
		} catch (SlickException e) {
			e.printStackTrace();
		}

	}

	public void set(String log) {

	}


	public void draw(GameContainer container) {
		int w = container.getWidth()/2;
		int h = container.getHeight()/2;

		int posTextX = 100;
		int posTextY = 150;

		String str[]={
				"Bienvenue ChaosMan :)", //Couleur 1
				"Votre but est de détruire l'espèce humaine", 
				"Pour cela vous disposez de cartes",
				"So...",
				"Voici une carte type", 
				"Deux effets sont possibles", 
				"En haut des effets néfastes qui font prend de l'energie",
				"En bas des effets positifs qui vous procure de l'énergie",
				"Bonne chance et soyez cruel !",
		};



		switch(el){
		case 0: //Bienvenue
			trueTypeFont.drawString(posTextX, posTextY, str[el], 
					new Color((float)0.6,(float)0.99,(float)0.99, (float) (progressBar/100.0)));
			break;
		case 1: //But
			trueTypeFont.drawString(posTextX, (float) (posTextY-progressBar*2), str[el-1],
					new Color((float)(0.6+(progressBar/100.0)*0.4),(float)(0.99-(progressBar/100.0)*0.4),(float)0.99, (float) 1.0));
			trueTypeFont.drawString(posTextX, posTextY, str[el],
					new Color((float)(0.6+(progressBar/100.0)*0.4),(float)(0.99),(float)0.99, (float) (progressBar/100.0)));

			break;
		case 2://Pour cela
			trueTypeFont.drawString(posTextX, (float) (posTextY-progressBar*2), str[el-1],
					new Color((float)(0.6+(progressBar/100.0)*0.4),(float)(0.99-(progressBar/100.0)*0.4),(float)0.99, (float) 1.0));
			
			img_haut.setImageColor((float)(0.8), (float)(0.1), (float)(0.8), (float) (progressBar/100.0));			
			container.getGraphics().drawImage(this.img_haut, 60, 30);

			trueTypeFont.drawString(posTextX, posTextY, str[el],
					new Color((float)(0.6+(progressBar/100.0)*0.4),(float)(0.99),(float)0.99, (float) (progressBar/100.0)));
			div = 20;
			break;
		case 3://Donc
			trueTypeFont.drawString(posTextX, (float) (posTextY-progressBar*2), str[el-1],
					new Color((float)(0.6+(progressBar/100.0)*0.4),(float)(0.99-(progressBar/100.0)*0.4),(float)0.99, (float) 1.0));
			container.getGraphics().drawImage(this.img_haut, 60, (float) (30-progressBar*1.5));
			trueTypeFont.drawString(posTextX, posTextY, str[el],
					new Color((float)(0.6+(progressBar/100.0)*0.4),(float)(0.99),(float)0.99, (float) (progressBar/100.0)));
			break;

		case 4://Vous disposez de carte
			div = 30;
			trueTypeFont.drawString(posTextX, (float) (posTextY-progressBar*2), str[el-1],
					new Color((float)(0.6+(progressBar/100.0)*0.4),(float)(0.99-(progressBar/100.0)*0.4),(float)0.99, (float) 1.0));

			carte.getImage().setImageColor((float)(0.1), (float)(0.5), (float)(0.8), (float) (progressBar/100.0));	
			carte.getImageEffetMal().setImageColor((float)(0.1), (float)(0.5), (float)(0.8), (float) (progressBar/100.0));	
			carte.getImageEffetBonne().setImageColor((float)(0.1), (float)(0.5), (float)(0.8), (float) (progressBar/100.0));	

			carte.redraw(0, container);		
			carte.setX(10);
			carte.setY(50);
			container.getGraphics().drawImage(carte.getImage(), carte.getX(), carte.getY());	
			container.getGraphics().drawImage(carte.getImageEffetMal(), carte.getX()+10, carte.getY()+20);	
			container.getGraphics().drawImage(carte.getImageEffetBonne(), carte.getX()+10, carte.getY()+carte.getDimensionY()/2+20);	


			trueTypeFont.drawString(posTextX+100, posTextY, str[el],
					new Color((float)(0.6+(progressBar/100.0)*0.4),(float)(0.99),(float)0.99, (float) (progressBar/100.0)));
			break;
		case 5://Deux effets
			trueTypeFont.drawString(posTextX+100, (float) (posTextY-progressBar*2), str[el-1],
					new Color((float)(0.6+(progressBar/100.0)*0.4),(float)(0.99-(progressBar/100.0)*0.4),(float)0.99, (float) 1.0));

			container.getGraphics().drawImage(carte.getImage(), carte.getX(), carte.getY());	
			container.getGraphics().drawImage(carte.getImageEffetMal(), carte.getX()+10, carte.getY()+20);	
			container.getGraphics().drawImage(carte.getImageEffetBonne(), carte.getX()+10, carte.getY()+carte.getDimensionY()/2+20);	

			img_g.setImageColor((float)(0.1), (float)(0.5), (float)(0.8), (float) (progressBar/100.0));	
			container.getGraphics().drawImage(img_g, 180,60);
			container.getGraphics().drawImage(img_g, 180,170);



			trueTypeFont.drawString(posTextX+100, posTextY, str[el],
					new Color((float)(0.6+(progressBar/100.0)*0.4),(float)(0.99),(float)0.99, (float) (progressBar/100.0)));
			break;
		case 6://Mauvais
			trueTypeFont.drawString(posTextX+100, (float) (posTextY-progressBar*2), str[el-1],
					new Color((float)(0.6+(progressBar/100.0)*0.4),(float)(0.99-(progressBar/100.0)*0.4),(float)0.99, (float) 1.0));

			container.getGraphics().drawImage(carte.getImage(), carte.getX(), carte.getY());	
			container.getGraphics().drawImage(carte.getImageEffetMal(), carte.getX()+10, carte.getY()+20);	
			container.getGraphics().drawImage(carte.getImageEffetBonne(), carte.getX()+10, carte.getY()+carte.getDimensionY()/2+20);	

			container.getGraphics().drawImage(img_g, 180,60);


			trueTypeFont.drawString(posTextX+100, posTextY, str[el],
					new Color((float)(0.6+(progressBar/100.0)*0.4),(float)(0.99),(float)0.99, (float) (progressBar/100.0)));
			break;
		case 7://Bon
			trueTypeFont.drawString(posTextX+100, (float) (posTextY-progressBar*2), str[el-1],
					new Color((float)(0.6+(progressBar/100.0)*0.4),(float)(0.99-(progressBar/100.0)*0.4),(float)0.99, (float) 1.0));

			container.getGraphics().drawImage(carte.getImage(), carte.getX(), carte.getY());	
			container.getGraphics().drawImage(carte.getImageEffetMal(), carte.getX()+10, carte.getY()+20);	
			container.getGraphics().drawImage(carte.getImageEffetBonne(), carte.getX()+10, carte.getY()+carte.getDimensionY()/2+20);	

			container.getGraphics().drawImage(img_g, 180,170);

			trueTypeFont.drawString(posTextX+100, posTextY, str[el],
					new Color((float)(0.6+(progressBar/100.0)*0.4),(float)(0.99),(float)0.99, (float) (progressBar/100.0)));
			break;

		case 8://Fin
			div = 30.0;
			trueTypeFont.drawString(posTextX+100, (float) (posTextY-progressBar*2), str[el-1],
					new Color((float)(0.6+(progressBar/100.0)*0.4),(float)(0.99-(progressBar/100.0)*0.4),(float)0.99, (float) 1.0));

			container.getGraphics().drawImage(carte.getImage(), (float) (carte.getX()-progressBar*2), carte.getY());	
			container.getGraphics().drawImage(carte.getImageEffetMal(), (float) (carte.getX()+10-progressBar*2), carte.getY()+20);	
			container.getGraphics().drawImage(carte.getImageEffetBonne(), (float) (carte.getX()+10-progressBar*2), carte.getY()+carte.getDimensionY()/2+20);	

			trueTypeFont.drawString(posTextX+100, posTextY, str[el],
					new Color((float)(0.6+(progressBar/100.0)*0.4),(float)(0.99),(float)0.99, (float) (progressBar/100.0)));
			break;
		case 9://Fin
			div = 10.0;
			trueTypeFont.drawString(posTextX+100, (float) (posTextY-progressBar*2), str[el-1],
					new Color((float)(0.6+(progressBar/100.0)*0.4),(float)(0.99-(progressBar/100.0)*0.4),(float)0.99, (float) 1.0));
			break;
		}
	}



		public void update(int delta) {
			progressBar+=delta/div ;
			if(progressBar>100.0){
				progressBar = 0.0;
				el++;
			}


		}





	}
