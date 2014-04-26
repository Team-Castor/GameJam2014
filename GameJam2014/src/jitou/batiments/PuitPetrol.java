package jitou.batiments;

import java.awt.Point;
import java.util.ArrayList;

import jitou.global.Citoyen;
import jitou.global.ObjectifType;
import jitou.ressources.Ressource;
import jitou.ressources.RessourceType;

public class PuitPetrol extends Batiment{

	public static ArrayList<PuitPetrol> listePuitsPetrols= new ArrayList<PuitPetrol>();

	int nb_occupant = 0;
	int nb_occupant_max = 3;
	double workingTime = 1200;

	public PuitPetrol(Point pos) {
		super(pos, TypeBatiment.PuitPetrol);
		listePuitsPetrols.add(this);
	}

	public boolean placeDisponible(){
		return nb_occupant<nb_occupant_max;
	}

	public void effet(Citoyen citoyen, ObjectifType type) {
		super.effet(citoyen, type);

		if(placeDisponible() && type.getValue()==ObjectifType.allerPuitPetrole.getValue()){
			nb_occupant++;			
			citoyen.setWorkingTime(workingTime);
		}
	}

	public void back(Citoyen c) {
		if( c.getObjectif().getType().getValue()==ObjectifType.allerPuitPetrole.getValue()){
			nb_occupant--;
			c.setRessourceTransporte(new Ressource( RessourceType.petrole, 4.0));
			c.getObjectif().rapporterPetrole();
		}
		else 		super.back(c);

	}

	public String info() {
		return nb_occupant+" / "+nb_occupant_max;
	}
}
