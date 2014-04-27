package julien.game;

import java.awt.Font;
import java.awt.GraphicsEnvironment;

import jitou.batiments.Batiment;
import jitou.batiments.TypeBatiment;
import jitou.global.BoardGame;
import julien.map.Case;
import julien.mechant.Carte;

import org.newdawn.slick.*;

import de.matthiasmann.twl.Container;

public class PresentationDebut{

	public Font font;
	public TrueTypeFont trueTypeFont;
	
	
	
	double progressBar = 0.0;
	int    el = 0;
	
	public PresentationDebut( GameContainer container) {
		GraphicsEnvironment ge=
				null;
				 
				        ge=GraphicsEnvironment.getLocalGraphicsEnvironment();
				 
				        String []fontNames=ge.getAvailableFontFamilyNames();
				 
				        for (int i = 0; i< fontNames.length; i++) {
				            System.out.println(fontNames[i]);
				        }
		//System.exit(1);
		font = new Font("Purisa", Font.BOLD, 40);
		trueTypeFont = new TrueTypeFont(font, true);
	}

	public void set(String log) {

	}


	public void draw(GameContainer container) {
		int w = container.getWidth()/2;
		int h = container.getHeight()/2;
		switch(el){
		case 0:
				trueTypeFont.drawString(w-100, h-50, "Bienvenue :)", 
						new Color((float)0.6,(float)0.99,(float)0.99, (float) (progressBar/100.0)));
			break;
		case 1:
			trueTypeFont.drawString(w-100, h-50, "Bienvenue :)", 
					new Color((float)(0.6+(progressBar/100.0)*0.4),(float)(0.99-(progressBar/100.0)*0.4),(float)0.99, (float) 1.0));
		break;
		case 2:
			trueTypeFont.drawString(w-100, h-50, "Hey l'ami viens boire un verre", 
					new Color((float)(0.6+(progressBar/100.0)*0.4),(float)(0.99-(progressBar/100.0)*0.4),(float)0.99, (float) 1.0));
		break;
		}
		
	}

	public void update(int delta) {
		progressBar+=delta/20.;
		if(progressBar>100.0){
			progressBar = 0.0;
			el++;
		}
		
		
	}





}
