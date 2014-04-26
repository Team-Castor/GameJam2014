package jitou.batiments;

import java.awt.Point;
import java.util.ArrayList;

public class Arsenal extends Batiment{
	
	public static ArrayList<Arsenal> listeArsenals= new ArrayList<Arsenal>();

	int arme_disponible = 0;
	
	public Arsenal(Point pos) {
		super(pos, TypeBatiment.Arsenal);
		listeArsenals.add(this);
	}

	public int getArmeDisponible(){
		return arme_disponible;
	}
	
}
