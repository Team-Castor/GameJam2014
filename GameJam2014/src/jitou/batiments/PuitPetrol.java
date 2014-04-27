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
	double nbTour=-1;
	double puissance=0.0;
	
	
	public PuitPetrol(Point pos) {
		super(pos, TypeBatiment.PuitPetrol);
		listePuitsPetrols.add(this);
	}

	public boolean placeDisponible(){
		return nb_occupant<nb_occupant_max;
	}

	public void effet(Citoyen citoyen, ObjectifType type) {

		if(placeDisponible() && type.getValue()==ObjectifType.allerPuitPetrole.getValue()){
			nb_occupant++;			
			citoyen.setWorkingTime(workingTime);
		}else{
			super.effet(citoyen, type);

		}
	}

	public void back(Citoyen c) {
		if( c.getObjectif().getType().getValue()==ObjectifType.allerPuitPetrole.getValue()){
			nb_occupant--;
			c.setRessourceTransporte(new Ressource( RessourceType.petrole, 4.0+puissance));
			c.getObjectif().rapporterPetrole();
			nb_occupant = Math.max(nb_occupant, 0);

		}
		else 		super.back(c);

	}

	public String info() {
		return nb_occupant+" / "+nb_occupant_max;
	}

	public void update(int delta){
		if(nbTour>0){
			nbTour-=delta;
		}
		else{
			puissance=0.0;
		}
	}
	
	public void filon(double nbTour, double puissance) {
		this.nbTour=nbTour;
		this.puissance = puissance;
		
	}
}
