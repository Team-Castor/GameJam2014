package jitou.batiments;

import java.awt.Point;
import java.util.ArrayList;

import jitou.global.Citoyen;
import jitou.global.ObjectifType;

public class Generateur extends Batiment {
	public static ArrayList<Generateur> listeGenerateurs =  new ArrayList<Generateur>();
	private final double ratioPetrolToElec = 0.6;
	private double electricite = 50;
	private double workingTime = 500;
	
	public Generateur(Point pos) {
		super(pos, TypeBatiment.Generateur);
		listeGenerateurs.add(this);
	}

	
	public void effet(Citoyen citoyen, ObjectifType type) {
		if(type.getValue() == ObjectifType.rapporterGenerateur.getValue()){
			electricite+=citoyen.getRessourceTransporte().getQuantite()*ratioPetrolToElec;
			citoyen.setRessourceTransporte(null);
			citoyen.setWorkingTime(workingTime);
		}
	}
	
	public String info() {
		return "Elec : "+electricite;
	}


	public double getElectricite() {
		return electricite;
	}


	public void consommeElectricite(double d) {
		electricite -=d;
		
	}
	
}
