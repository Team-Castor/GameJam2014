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
	private double workingTime = 1300;

	private double nbTour=-1, puissance=0.0;
	private double temps=-1,  ratio=1.0;

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

		if(getDisponible() && type.getValue()==ObjectifType.allerAUneMineDeFer.getValue()){
			nb_occupant++;			
			citoyen.setWorkingTime(workingTime);
		}
		else{
			super.effet(citoyen, type);

		}
	}

	public void back(Citoyen c) {
		if(c.getObjectif().getType().getValue()==ObjectifType.allerAUneMineDeFer.getValue()){
			nb_occupant--;
			nb_occupant = Math.max(nb_occupant, 0);
			c.setRessourceTransporte(new Ressource( RessourceType.fer, (5.+puissance)*ratio));
			c.getObjectif().rapporterFer();

		}
		else{
			super.back(c);
		}
	}

	public void update(int delta){
		if(nbTour>0){
			nbTour-=delta;
		}
		else{
			puissance=0.0;
		}
		if(temps>0){
			temps-=delta;
		}
		else{
			ratio=1.0;
		}
	}

	public String info() {
		return nb_occupant+" / "+nb_occupant_max;
	}

	public void filonDeFer(double nbTour, double puissance) {
		this.nbTour+=nbTour;
		this.puissance=puissance;
	}

	public void setRatioMalus(double temps, double ratio) {
		this.temps=temps;
		this.ratio=ratio;
	}
}
