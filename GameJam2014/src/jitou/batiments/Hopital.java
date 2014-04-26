package jitou.batiments;

import java.awt.Point;
import java.util.ArrayList;

public class Hopital extends Batiment{
	
	public static ArrayList<Hopital> listeHopitaux= new ArrayList<Hopital>();

	int nb_occupant = 0;
	int nb_occupant_max = 20;
	
	public Hopital(Point pos) {
		super(pos, TypeBatiment.Hopital);
		listeHopitaux.add(this);
	}

	public boolean placeDisponible(){
		return nb_occupant<nb_occupant_max;
	}
	
}
