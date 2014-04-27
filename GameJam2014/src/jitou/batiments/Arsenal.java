package jitou.batiments;

import java.awt.Point;
import java.util.ArrayList;

import jitou.global.Citoyen;
import jitou.global.ObjectifType;

public class Arsenal extends Batiment{
	
	public static ArrayList<Arsenal> listeArsenals= new ArrayList<Arsenal>();

	int arme_disponible = 10;
	
	public Arsenal(Point pos) {
		super(pos, TypeBatiment.Arsenal);
		listeArsenals.add(this);
	}

	public int getArmeDisponible(){
		return arme_disponible;
	}
	
	public void effet(Citoyen citoyen, ObjectifType type) {
		if(getArmeDisponible()>0 && type.getValue() == ObjectifType.allerChercherArme.getValue()){
			citoyen.setArme(true);
			arme_disponible--;
			citoyen.setWorkingTime(10);
		}else 	if(getArmeDisponible()>0 && type.getValue() == ObjectifType.rendreArme.getValue()){
			citoyen.setArme(false);
			arme_disponible++;
			citoyen.setWorkingTime(10);
		}
		else{
			super.effet(citoyen, type);

		}
	}
	
	public void back(Citoyen citoyen) {
		

		if(citoyen.aUneArme() && citoyen.getObjectif().getType().getValue() == ObjectifType.allerChercherArme.getValue()){
			citoyen.getObjectif().allerDefendre();
		}else{
			super.back(citoyen);
		}
		
	}

	
	
}
