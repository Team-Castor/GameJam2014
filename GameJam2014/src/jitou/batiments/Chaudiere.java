package jitou.batiments;

import java.awt.Point;
import java.util.ArrayList;

public class Chaudiere extends Batiment{
	public ArrayList<Chaudiere> listeChaudieres = new ArrayList<Chaudiere>();
	
	
	public Chaudiere(Point pos) {
		super(pos, TypeBatiment.Chaudiere);
		listeChaudieres.add(this);
	}
	
	

}
