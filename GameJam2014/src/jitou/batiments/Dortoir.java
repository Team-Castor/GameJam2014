package jitou.batiments;

import java.awt.Point;
import java.util.ArrayList;

import jitou.global.Citoyen;
import jitou.global.ObjectifType;

public class Dortoir extends Batiment{

	public static ArrayList<Dortoir> listeDortoirs= new ArrayList<Dortoir>();

	int nb_occupant = 0;
	int nb_occupant_max = 10;
	double workingTime=2300.0;


	public Dortoir(Point pos) {
		super(pos, TypeBatiment.Dortoir);
		listeDortoirs.add(this);
	}

	public boolean placeDisponible(){
		return nb_occupant<nb_occupant_max;
	}

	public void effet(Citoyen citoyen, ObjectifType type) {
		super.effet(citoyen, type);

		if(placeDisponible() && type.getValue()==ObjectifType.se_reposer.getValue()){
			nb_occupant++;
			citoyen.setFatigue((float) (3000.0+Math.random()*2000));
			citoyen.setWorkingTime(workingTime);
		}
	}

	public void back(Citoyen c) {
		super.back(c);
		if(c.getObjectif().getType().getValue()==ObjectifType.se_reposer.getValue()){
			nb_occupant--;
			c.getObjectif().reset();
		}
	}


	public String info() {
		return nb_occupant+" / "+nb_occupant_max;
	}
}
