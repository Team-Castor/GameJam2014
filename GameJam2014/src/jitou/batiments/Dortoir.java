package jitou.batiments;

import java.awt.Point;
import java.util.ArrayList;

import jitou.global.Citoyen;
import jitou.global.ObjectifType;

public class Dortoir extends Batiment{
	
	public static ArrayList<Dortoir> listeDortoirs= new ArrayList<Dortoir>();

	int nb_occupant = 0;
	int nb_occupant_max = 20;
	double workingTime=300.0;
			
			
	public Dortoir(Point pos) {
		super(pos, TypeBatiment.Dortoir);
		listeDortoirs.add(this);
	}

	public boolean placeDisponible(){
		return nb_occupant<nb_occupant_max;
	}
	
	public void effet(Citoyen citoyen, ObjectifType type) {
		if(placeDisponible()){
			nb_occupant++;
			citoyen.setFatigue((float) (1000+Math.random()*5000));
			citoyen.setWorkingTime(workingTime);
		}
	}
	
	public void back(Citoyen c) {
		nb_occupant--;
	}
	
}
