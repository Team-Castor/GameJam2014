package jitou.batiments;

import java.awt.Point;
import java.util.ArrayList;

import jitou.global.Citoyen;
import jitou.global.ObjectifType;
import jitou.ressources.Ressource;
import jitou.ressources.RessourceType;

public class Hopital extends Batiment{

	public static ArrayList<Hopital> listeHopitaux= new ArrayList<Hopital>();
	private static double workingTime= 1200.0;

	int nb_occupant = 0;
	int nb_occupant_max = 2;

	public Hopital(Point pos) {
		super(pos, TypeBatiment.Hopital);
		listeHopitaux.add(this);
	}

	public boolean placeDisponible(){
		return nb_occupant<nb_occupant_max;
	}

	public void effet(Citoyen citoyen, ObjectifType type) {

		if(placeDisponible() && type.getValue()==ObjectifType.se_soigner.getValue()){
			nb_occupant++;			
			citoyen.setWorkingTime(workingTime);
			citoyen.setMaladie(null);
		}
		else{
			super.effet(citoyen, type);

		}
	}

	public void back(Citoyen c) {
		if(c.getObjectif().getType().getValue()==ObjectifType.se_soigner.getValue()){
			nb_occupant--;
			nb_occupant = Math.max(nb_occupant, 0);
			c.getObjectif().reset();
		}
		else{
			super.back(c);
		}
	}

	public String info() {
		return nb_occupant+" / "+nb_occupant_max;
	}

	public static double getWorkingTime() {
		return workingTime;
	}
	public static void setWorkingTime(double w) {
		workingTime=w;
	}
}
