package jitou.batiments;

import java.awt.Point;
import java.util.ArrayList;

import jitou.global.Citoyen;
import jitou.global.ObjectifType;

public class Atelier extends Batiment{
	
	public static ArrayList<Atelier> listeAtelier= new ArrayList<Atelier>();
	private double workingTime=300.0;
	
	private double fer = 0.0;
			
			
	

	public Atelier(Point pos) {
		super(pos, TypeBatiment.Atelier);
		listeAtelier.add(this);
	}


	public void effet(Citoyen citoyen, ObjectifType type) {
		super.effet(citoyen, type);
		if(type.getValue() == ObjectifType.rapporterferAtelier.getValue()){
			fer+=citoyen.getRessourceTransporte().getQuantite();
			citoyen.setRessourceTransporte(null);

			citoyen.setWorkingTime(workingTime);
			
		}else 	if(type.getValue() == ObjectifType.allerVolerOutils.getValue()){
			fer=Math.max(0.0, fer-10.0);
			citoyen.setWorkingTime(50.0);
		}
	}
	
	
	public String info() {
		return "Fer : "+fer;
	}
	
	public double getFer() {
		return fer;
	}


	public void setFer(double fer) {
		this.fer = fer;
	}

	
}
