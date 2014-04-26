package jitou.global;

import java.awt.Point;
import java.awt.geom.Point2D;
import java.util.ArrayList;

import jitou.batiments.Batiment;
import jitou.ressources.Ressource;

public class Citoyen {
	private Point  	pos;
	private Point2D posInside;
	private float nourritureRestante, temperatureCorporelle, fatigue;
	private Ressource ressourceTransporte;
	private Objectif objectif=null;
	private Maladie maladie = null;


	private double workingTime;
	private ArrayList<Batiment> pathfinder = null;


	public Citoyen(Point pos){
		this(pos, 500f, 32.5f, 400f);
	}

	public Citoyen(Point pos, float nourritureRestante, float temperatureCorporelle, float fatigue){
		this.pos = pos;
		posInside = new Point2D.Double(0.0,0.0);
		this.nourritureRestante = nourritureRestante;
		this.temperatureCorporelle = temperatureCorporelle;
		this.fatigue =fatigue;
		ressourceTransporte = null;
		objectif = new Objectif(this);
		setWorkingTime(0.0);
	}


	public void update(int delta){
		if(workingTime>=0.0){
			workingTime = workingTime - delta;
		}
		else{
			if(objectif.getType().getValue()==ObjectifType.aucun.getValue()){
				pathfinder = null;
				objectif.trouverNouvelObjectif();
			}else{
				if(pathfinder == null){
					pathfinder = BoardGame.findPath(pos, objectif.getSeRendre().getPos() );
				}

				Point vers = pathfinder.get(0).getPos();
				int dx = vers.x - pos.x;
				int dy = vers.y - pos.y;
				if(dx!=0 || dy!=0){//On va changer de case
					posInside.setLocation(posInside.getX()+dx*delta/10.0, posInside.getY()+dy*delta/10.0);
				}
				else{ //On se dirige vers le centre
					posInside.setLocation(
							posInside.getX()-Math.signum(posInside.getX())*delta/10.0,
							posInside.getY()-Math.signum(posInside.getY())*delta/10.0);
					if(posInside.distance(0.0, 0.0)<0.1){
						if(pathfinder.size()>0){//Changement de case
							pathfinder.remove(0);
						}
						else{
							objectif.accomplirObjectif(BoardGame.boardGame.getBatiment(pos.x,pos.y));
							pathfinder=null;
						}
					}
				}

			}

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
