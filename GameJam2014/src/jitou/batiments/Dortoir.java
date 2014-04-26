package jitou.batiments;

import java.awt.Point;
import java.util.ArrayList;

public class Dortoir extends Batiment{
	
	public static ArrayList<Dortoir> listeDortoirs= new ArrayList<Dortoir>();

	int nb_occupant = 0;
	int nb_occupant_max = 20;
	
	public Dortoir(Point pos) {
		super(pos, TypeBatiment.Dortoir);
		listeDortoirs.add(this);
	}

	public boolean placeDisponible(){
		return nb_occupant<nb_occupant_max;
	}
	
}
