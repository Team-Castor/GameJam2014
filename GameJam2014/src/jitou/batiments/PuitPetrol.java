package jitou.batiments;

import java.awt.Point;
import java.util.ArrayList;

public class PuitPetrol extends Batiment{
	
	public static ArrayList<PuitPetrol> listePuitsPetrols= new ArrayList<PuitPetrol>();

	int nb_occupant = 0;
	int nb_occupant_max = 20;
	
	public PuitPetrol(Point pos) {
		super(pos, TypeBatiment.PuitPetrol);
		listePuitsPetrols.add(this);
	}

	public boolean placeDisponible(){
		return nb_occupant<nb_occupant_max;
	}
	
}
