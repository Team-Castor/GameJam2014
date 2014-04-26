package jitou.batiments;

import java.awt.Point;
import java.util.ArrayList;

import jitou.global.Citoyen;
import jitou.global.ObjectifType;

public class Generateur extends Batiment {
	public static ArrayList<Generateur> listeGenerateurs =  new ArrayList<Generateur>();

	private double electricite = 100;
	private double workingTime = 200;
	
	public Generateur(Point pos) {
		super(pos, TypeBatiment.Generateur);
		listeGenerateurs.add(this);
	}

	
	public void effet(Citoyen citoyen, ObjectifType type) {
		if(type.getValue() == ObjectifType.rapporterGenerateur.getValue()){
			electricite+=citoyen.getRessourceTransporte().getQuantite();
			citoyen.setWorkingTime(workingTime);
		}
	}
	
}
