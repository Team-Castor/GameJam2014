package jitou.global;

import java.awt.Point;
import java.awt.geom.Point2D;
import java.util.ArrayList;

import jitou.batiments.Batiment;
import jitou.ressources.Ressource;
import julien.game.Game;

public class Citoyen {
	//private Point  	pos;
	public boolean visible=true;

	
	
	protected int posX, posY;
	protected boolean estMort = false;
	protected Point2D posInside;
	protected float nourritureRestante, temperatureCorporelle, fatigue;
	protected Ressource ressourceTransporte;
	protected Objectif objectif=null;
	protected Maladie maladie = null;
	protected boolean armee = false;

	protected double workingTime;
	protected ArrayList<Batiment> pathfinder = null;


	public Citoyen(Point pos){
		this(pos, 4000f, 32.5f, (float) (1000+Math.random()*3000));
	}

	public Citoyen(Point pos, float nourritureRestante, float temperatureCorporelle, float fatigue){
		//this.pos = pos;
		posX = pos.x;
		posY = pos.y;
		
		posInside = new Point2D.Double(0.0,0.0);
		this.nourritureRestante = nourritureRestante;
		this.temperatureCorporelle = temperatureCorporelle;
		this.fatigue =fatigue;
		ressourceTransporte = null;
		objectif = new Objectif(this);
		setWorkingTime(-1.0);
		Game.getInstance();
		Game.addSpriteCitoyen(this);

	}


	public void update(int delta){
		double malusVitesse=0.0;
		if(maladie!= null) malusVitesse = maladie.getMalusVitesse();
		double malusFatigue=0.0;
		if(maladie!= null) malusFatigue = maladie.getMalusFatigue();
		double malusTempsDeTravail=0.0;
		if(maladie!= null) malusTempsDeTravail = maladie.getMalusTempsTravail();
		double malusPerteChaleur=0.0;
		if(maladie!= null) malusPerteChaleur = maladie.getPerteChaleur();
		
		final  double facteurDiv = 200.0+malusVitesse;
		
		temperatureCorporelle = (float) ((float) (temperatureCorporelle*0.99*delta/50.0+BoardGame.boardGame.getBatiment(posX, posY).getTemperatureSalle()*0.01*delta/50.0)-malusPerteChaleur);
		nourritureRestante		-= delta/10.0;
		fatigue 				-= (delta+malusFatigue)/12.0;
		
		if(workingTime>=0.0){
			workingTime = workingTime - Math.max(0.2, (delta-malusTempsDeTravail));
			

			if(workingTime<0.0){
				pathfinder = null;
				objectif.getSeRendre().back(this);
			}	
			
		}
		else{
			if(objectif.getType().getValue()==ObjectifType.aucun.getValue()){
				pathfinder = null;
				objectif.trouverNouvelObjectif();
			}else{

				if(objectif.getType().getValue()==ObjectifType.aucun.getValue()){

				}else{
					if(objectif.getSeRendre()!=null){
						if(pathfinder == null){
							pathfinder = BoardGame.findPath(new Point(posX, posY), objectif.getSeRendre().getPos() );
						}

						if(pathfinder.size()>0){
							Point vers = new Point(pathfinder.get(0).getPos());
							int dx = (int) Math.signum(vers.x - posX);
							int dy = (int) Math.signum(vers.y - posY);
							if(dx!=0 || dy!=0){
								posInside.setLocation(
										posInside.getX()+dx*delta/facteurDiv,
										posInside.getY()+dy*delta/facteurDiv);
								if(posInside.getX()>=1){
									posInside.setLocation(-0.98, posInside.getY());
									 posX++;
								}
								else if(posInside.getX()<=-1){
									posInside.setLocation(0.98, posInside.getY());
									 posX--;
								}
								if(posInside.getY()>=1){
									posInside.setLocation( posInside.getX(), -0.98);
									 posY++;

								}
								else if(posInside.getY()<=-1){
									posInside.setLocation( posInside.getX(), 0.98);
									 posY--;

								}
								
								//System.out.println(this+"  "+pos+" "+this.posInside+"  "+dx+"  "+dy);

							}
							else{

								if(posInside.distance(0.0, 0.0)<0.2){
									pathfinder.remove(0);
									if(pathfinder.size()>0){
									}
									else{
										objectif.accomplirObjectif(BoardGame.boardGame.getBatiment( posX,  posY));
										pathfinder=null;
									}
								}else{
									if(Math.signum(posInside.getX())<0){
										posInside.setLocation(Math.min(0.0,
												posInside.getX()+delta/facteurDiv),
												posInside.getY());
									}else if(Math.signum(posInside.getX())>0){
										posInside.setLocation(Math.max(0.0,
												posInside.getX()-delta/facteurDiv),
												posInside.getY());
									}
									if(Math.signum(posInside.getY())<0){
										posInside.setLocation(
												posInside.getX(),
												Math.min(0.,posInside.getY()+delta/facteurDiv));
									}else if(Math.signum(posInside.getY())>0){
										posInside.setLocation(
												posInside.getX(),
												Math.max(0.,posInside.getY()-delta/facteurDiv));
									}
									

					
								}
							}
						}
					}else{
						this.nouvelObjectif();

					}
				}

			}

		}
		//System.out.println(this+"  "+pos+"-> "+this.posInside+"  ");


		testSurvie();
	}
	
	
	protected void nouvelObjectif() {
		objectif.trouverNouvelObjectif();		
	}

	public void testSurvie(){
		if(this.nourritureRestante<0 || this.fatigue<0 || this.temperatureCorporelle<0){
			tuer();
		}
	}
	
	public boolean getEstMort(){
		return estMort;
	}
	
	
	public boolean estActif(){
		return workingTime>0;
	}


	public Point getPos() {
		return new Point( posX,  posY);
	}

	/*public void setPos(Point pos) {
		this.pos = pos;
	}*/

	public Point2D getPosInside() {
		return posInside;
	}

	/*public void setPosInside(Point2D posInside) {
		this.posInside = posInside;
	}*/

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

	public Maladie getMaladie() {
		return maladie;
	}

	public void setMaladie(Maladie maladie) {
		this.maladie = maladie;
	}

	public double getWorkingTime() {
		return workingTime;
	}

	public void setWorkingTime(double workingTime) {
		this.workingTime = workingTime;
	}

	public void mange(double d) {
		nourritureRestante+=d*1000.0+Math.random()*1000;
	}

	public void tuer() {
		BoardGame.boardGame.getBatiment(posX, posY).back(this);
		BoardGame.boardGame.killCitoyen(this);
		estMort=true;		
	}

	public boolean aUneArme() {
		return armee;
	}

	public void setArme(boolean i) {
		armee = true;		
	}



}
