package jitou.batiments;

import java.awt.Point;
import java.util.ArrayList;

import jitou.global.Citoyen;
import jitou.global.ObjectifType;
import jitou.ressources.Ressource;
import jitou.ressources.RessourceType;

public class MineDeFer extends Batiment{
	
	public static ArrayList<MineDeFer> listeMineDeFer= new ArrayList<MineDeFer>();

	private int nb_occupant = 0;
	private int nb_occupant_max = 3;
	private double workingTime = 1200;
	
	
	public MineDeFer(Point pos) {
		super(pos, TypeBatiment.MineDeFer);
		listeMineDeFer.add(this);
	}

	public boolean placeDisponible(){
		return nb_occupant<nb_occupant_max;
	}
	
	public boolean getDisponible(){
		return nb_occupant<nb_occupant_max;
	}
	
	public void effet(Citoyen citoyen, ObjectifType type) {
		super.effet(citoyen, type);

		if(getDisponible() && type.getValue()==ObjectifType.allerAUneMineDeFer.getValue()){
			nb_occupant++;			
			citoyen.setWorkingTime(workingTime);
		}
	}
	
	public void back(Citoyen c) {
		if(c.getObjectif().getType().getValue()==ObjectifType.allerAUneMineDeFer.getValue()){
		nb_occupant--;
		c.setRessourceTransporte(new Ressource( RessourceType.fer, 5));
		c.getObjectif().rapporterFer();
		}
		else{
			super.back(c);
		}
	}
	
	public String info() {
		return nb_occupant+" / "+nb_occupant_max;
	}
}
