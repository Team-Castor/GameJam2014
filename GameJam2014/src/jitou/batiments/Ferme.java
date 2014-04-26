package jitou.batiments;

import java.awt.Point;
import java.util.ArrayList;

import jitou.global.Citoyen;
import jitou.global.ObjectifType;
import jitou.ressources.Ressource;
import jitou.ressources.RessourceType;

public class Ferme extends Batiment{
	
	public static ArrayList<Ferme> listeFerme =  new ArrayList<Ferme>();

	int nb_occupant = 0;
	int nb_occupant_max = 5;
	double workingTime=1000.0;
	
	
	public Ferme(Point pos) {
		super(pos, TypeBatiment.FermeHydroponique);
		listeFerme.add(this);
	}

	public boolean getDisponible(){
		return nb_occupant<nb_occupant_max;
	}
	
	public void effet(Citoyen citoyen, ObjectifType type) {
		if(getDisponible()){
			nb_occupant++;			
			citoyen.setWorkingTime(workingTime);
		}
	}
	
	public void back(Citoyen c) {
		nb_occupant--;
		c.setRessourceTransporte(new Ressource( RessourceType.nourriture, 10));
		c.getObjectif().rapporterNourriture();
	}
	
	public String info() {
		return nb_occupant+" / "+nb_occupant_max;
	}
}
