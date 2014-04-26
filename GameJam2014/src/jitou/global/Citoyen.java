package jitou.global;

import java.awt.Point;
import java.awt.geom.Point2D;

import jitou.ressources.Ressource;

public class Citoyen {
	private Point  	pos;
	private Point2D posInside;
	private float nourritureRestante, temperatureCorporelle;
	private Ressource ressourceTransporte;
	private Objectif objectif;
	
	public Citoyen(Point pos){
		this(pos, 100f, 32.5f);
	}
	
	public Citoyen(Point pos, float nourritureRestante, float temperatureCorporelle){
		this.pos = pos;
		this.nourritureRestante = nourritureRestante;
		this.temperatureCorporelle = temperatureCorporelle;
		ressourceTransporte = null;
	}
	
	
	
}
