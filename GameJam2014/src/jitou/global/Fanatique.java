package jitou.global;

import java.awt.Point;
import java.util.ArrayList;

public class Fanatique extends Citoyen{
	public ArrayList<Fanatique> liste_fanatiques = new ArrayList<Fanatique>();


	public Fanatique(Point pos) {
		super(pos);
		liste_fanatiques.add(this);
	}


	protected void nouvelObjectif() {
		objectif.trouverNouvelObjectifFanatique();		
	}

}
