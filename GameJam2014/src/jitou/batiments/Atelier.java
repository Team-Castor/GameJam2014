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
		if(type.getValue() == ObjectifType.rapporterferAtelier.getValue()){
			fer+=citoyen.getRessourceTransporte().getQuantite();
			citoyen.setWorkingTime(workingTime);
		}
	}
	
	public void back(Citoyen c) {
	}
	
}
