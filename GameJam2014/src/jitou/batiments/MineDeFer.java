package jitou.batiments;

import java.awt.Point;
import java.util.ArrayList;

public class MineDeFer extends Batiment{
	
	public static ArrayList<MineDeFer> listeMineDeFer= new ArrayList<MineDeFer>();

	int nb_occupant = 0;
	int nb_occupant_max = 20;
	
	public MineDeFer(Point pos) {
		super(pos, TypeBatiment.MineDeFer);
		listeMineDeFer.add(this);
	}

	public boolean placeDisponible(){
		return nb_occupant<nb_occupant_max;
	}
	
}
