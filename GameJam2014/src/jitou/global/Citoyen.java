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
	protected Point2D posInsideF;


	protected float nourritureRestante, temperatureCorporelle, fatigue;
	protected Ressource ressourceTransporte;
	protected Objectif objectif=null;
	protected Maladie maladie = null;
	protected boolean armee = false;

	protected double workingTime;
	protected ArrayList<Batiment> pathfinder = null;
	protected double nbTourRationnement=-1, puissanceRationnement;

	protected double puissanceBasket=0.0;
	protected double tempsReveilZombie;

	
	public Citoyen(Point pos){
		this(pos, 4000f, 32.5f, (float) (1000+Math.random()*3000));
	}

	public Citoyen(Point pos, float nourritureRestante, float temperatureCorporelle, float fatigue){
		//this.pos = pos;
		posX = pos.x;
		posY = pos.y;
		posInsideF =new Point2D.Double(Math.random()-0.5,Math.random()-0.5);
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


		
		if(nbTourRationnement>0){
			nbTourRationnement-=delta;
		}
		else{
			puissanceRationnement=0.0;
		}
		tempsReveilZombie-=delta;

		final  double facteurDiv = 200.0+malusVitesse-puissanceBasket;
		Batiment salle = BoardGame.boardGame.getBatiment(posX, posY);
		
		if(maladie!=null){
			if(delta/1400.>Math.random()){
				//System.out.println("Transmission maladie");
				Citoyen c =salle.getCitoyenRandom();
				if(c.estMalade()==false) c.setMaladie(new Maladie(this.maladie));

			}
		}
		
		temperatureCorporelle = (float) ((float) (temperatureCorporelle*0.999+salle.getTemperatureSalle()*0.001)-malusPerteChaleur);
		nourritureRestante		-= (double)Math.max(0.1, delta-puissanceRationnement)/10.0;
		fatigue 				-= (delta+malusFatigue-Math.min(-10+temperatureCorporelle, 0.0))/12.0;

		if(salle.estEndommage()){
			salle.setDommage(salle.getDommage()-delta/2.0);
			//System.out.println("Reparation...");
		}else{

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
					this.nouvelObjectif();
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

									if((posInside.distance(posInsideF.getX(), posInsideF.getY())<0.08 && pathfinder.size()<=1 )|| 
											(posInside.distance(0.0,0.0)<0.08 && pathfinder.size()>1 )){
										pathfinder.remove(0);
										if(pathfinder.size()>0){
										}
										else{
											objectif.accomplirObjectif(BoardGame.boardGame.getBatiment( posX,  posY));
											pathfinder=null;
										}
									}else{
										if(pathfinder.size()<=1){
											if((posInside.getX())<posInsideF.getX()){
												posInside.setLocation(Math.min(posInsideF.getX(),
														posInside.getX()+delta/facteurDiv),
														posInside.getY());
											}else if((posInside.getX())>posInsideF.getX()){
												posInside.setLocation(Math.max(posInsideF.getX(),
														posInside.getX()-delta/facteurDiv),
														posInside.getY());
											}
											if((posInside.getY())<posInsideF.getY()){
												posInside.setLocation(
														posInside.getX(),
														Math.min(posInsideF.getY(),posInside.getY()+delta/facteurDiv));
											}else if((posInside.getY())>posInsideF.getY()){
												posInside.setLocation(
														posInside.getX(),
														Math.max(posInsideF.getY(),posInside.getY()-delta/facteurDiv));
											}
										}
										else{
											if((posInside.getX())<0.0){
												posInside.setLocation(Math.min(0.0,
														posInside.getX()+delta/facteurDiv),
														posInside.getY());
											}else if((posInside.getX())>0.0){
												posInside.setLocation(Math.max(0.0,
														posInside.getX()-delta/facteurDiv),
														posInside.getY());
											}
											if((posInside.getY())<0.0){
												posInside.setLocation(
														posInside.getX(),
														Math.min(0.0,posInside.getY()+delta/facteurDiv));
											}else if((posInside.getY())>0.0){
												posInside.setLocation(
														posInside.getX(),
														Math.max(0.0,posInside.getY()-delta/facteurDiv));
											}
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

		}
		testSurvie();
	}


	protected void nouvelObjectif() {
		objectif.trouverNouvelObjectif();	
		if(objectif.getType().getValue()==ObjectifType.allerVersSortie.getValue()){
			posInsideF.setLocation(0.0,-0.95);

		}
		else{
			posInsideF.setLocation(Math.random()-0.5,Math.random()-0.5);
		}
	}

	public void testSurvie(){
		if(this.nourritureRestante<0 || this.fatigue<0 || this.temperatureCorporelle<-8){
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
		nourritureRestante+=d*600.0+Math.random()*1000;
	}

	public void tuer() {
		BoardGame.boardGame.getBatiment(posX, posY).back(this);
		BoardGame.boardGame.killCitoyen(this);
		Fanatique.liste_fanatiques.remove(this);
		estMort=true;		

		if(tempsReveilZombie>0.0){
			new Ennemi(posX, posY, Ennemi.EnnemiType.zombi);
		}
	}

	public boolean aUneArme() {
		return armee;
	}

	public void setArme(boolean i) {
		armee = true;		
	}

	public void rationnement(double nbTourRationnement,
			double puissanceRationnement) {
		this.nbTourRationnement+=nbTourRationnement;
		this.puissanceRationnement = puissanceRationnement;
	}

	public double getPuissanceBasket() {
		return puissanceBasket;
	}
	public void puissanceBasket(double g) {
		puissanceBasket = g;
	}

	public void reveilZombie(double temps) {
		tempsReveilZombie = temps;
	}




}
