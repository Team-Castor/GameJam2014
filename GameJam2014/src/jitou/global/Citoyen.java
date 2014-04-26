package jitou.global;

import java.awt.Point;
import java.awt.geom.Point2D;

import jitou.ressources.Ressource;

public class Citoyen {
	private Point  	pos;
	private Point2D posInside;
	private float nourritureRestante, temperatureCorporelle, fatigue;
	private Ressource ressourceTransporte;
	private Objectif objectif;
	private Maladie maladie = null;
	private double workingTime;
	
	public Citoyen(Point pos){
		this(pos, 500f, 32.5f, 400f);
	}
	
	public Citoyen(Point pos, float nourritureRestante, float temperatureCorporelle, float fatigue){
		this.pos = pos;
		this.nourritureRestante = nourritureRestante;
		this.temperatureCorporelle = temperatureCorporelle;
		this.fatigue =fatigue;
		ressourceTransporte = null;
		setWorkingTime(0.0);
	}

	
	public void update(int delta){
		if(workingTime>=0.0){
			workingTime = workingTime - delta;
		}
		else{
			//Suivre objectif
		}
	}
	
	
	
	
	public Point getPos() {
		return pos;
	}

	public void setPos(Point pos) {
		this.pos = pos;
	}

	public Point2D getPosInside() {
		return posInside;
	}

	public void setPosInside(Point2D posInside) {
		this.posInside = posInside;
	}

	public float getNourritureRestante() {
		return nourritureRestante;
	}

	public void setNourritureRestante(float nourritureRestante) {
		this.nourritureRestante = nourritureRestante;
	}

	public float getTemperatureCorporelle() {
		return temperatureCorporelle;
	}

	public void setTemperatureCorporelle(float temperatureCorporelle) {
		this.temperatureCorporelle = temperatureCorporelle;
	}

	public float getFatigue() {
		return fatigue;
	}

	public void setFatigue(float fatigue) {
		this.fatigue = fatigue;
	}

	public Ressource getRessourceTransporte() {
		return ressourceTransporte;
	}

	public void setRessourceTransporte(Ressource ressourceTransporte) {
		this.ressourceTransporte = ressourceTransporte;
	}

	public Objectif getObjectif() {
		return objectif;
	}

	public void setObjectif(Objectif objectif) {
		this.objectif = objectif;
	}

	public boolean estMalade() {
		return maladie!=null;
	}

	public double getWorkingTime() {
		return workingTime;
	}

	public void setWorkingTime(double workingTime) {
		this.workingTime = workingTime;
	}
	
	
	
}
