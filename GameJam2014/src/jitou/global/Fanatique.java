package jitou.global;

import java.awt.Point;
import java.util.ArrayList;

import jitou.batiments.Atelier;
import jitou.batiments.Batiment;

public class Fanatique extends Citoyen{
	public static ArrayList<Fanatique> liste_fanatiques = new ArrayList<Fanatique>();
	private ArrayList<ObjectifType> listeMaitre = new ArrayList<ObjectifType>();
	private double tempsConversion = 0.0;

	public Fanatique(Point pos) {
		super(pos);
		liste_fanatiques.add(this);
	}


	protected void trouverNouvelObjectif() {
		if(listeMaitre.size()>0){
			ObjectifType t = listeMaitre.get(0);
			listeMaitre.remove(0);
			if(t.getValue()==ObjectifType.allerVolerOutils.getValue()){
				Atelier at = BoardGame.boardGame.trouverAtelier();
				if(at!=null){
					this.objectif.setType(t);
					this.objectif.setSeRendre(at);
				}
			}
		}else{
			objectif.trouverNouvelObjectifFanatique();	
		}

		//System.out.println("Ordre fanatique "+ objectif);

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

		tempsConversion+=delta;
		if(tempsConversion>5000){
			if(Math.random()<0.01){
				Citoyen c =salle.getCitoyenRandom();
				if(c!=this){
					c.convertion();
				}
			}
			tempsConversion=0.0;
		}

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
					posInsideF.setLocation(Math.random()-0.5,Math.random()-0.5);

				}	

			}
			else{
				if(objectif.getType().getValue()==ObjectifType.aucun.getValue()){
					pathfinder = null;
					trouverNouvelObjectif();
					//objectif.trouverNouvelObjectif();
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

									if((posInside.distance(posInsideF)<0.1 && pathfinder.size()<=1 )|| 
											(posInside.distance(0.0,0.0)<0.1 && pathfinder.size()>1 )){
										pathfinder.remove(0);
										if(pathfinder.size()>0){
										}
										else{
											objectif.accomplirObjectif(BoardGame.boardGame.getBatiment( posX,  posY));
											pathfinder=null;
										}
									}else{
										if(pathfinder.size()<=1){
											if(Math.signum(posInside.getX())<posInsideF.getX()){
												posInside.setLocation(Math.min(posInsideF.getX(),
														posInside.getX()+delta/facteurDiv),
														posInside.getY());
											}else if(Math.signum(posInside.getX())>posInsideF.getX()){
												posInside.setLocation(Math.max(posInsideF.getX(),
														posInside.getX()-delta/facteurDiv),
														posInside.getY());
											}
											if(Math.signum(posInside.getY())<posInsideF.getY()){
												posInside.setLocation(
														posInside.getX(),
														Math.min(posInsideF.getY(),posInside.getY()+delta/facteurDiv));
											}else if(Math.signum(posInside.getY())>posInsideF.getY()){
												posInside.setLocation(
														posInside.getX(),
														Math.max(posInsideF.getY(),posInside.getY()-delta/facteurDiv));
											}
										}
										else{
											if(Math.signum(posInside.getX())<0.0){
												posInside.setLocation(Math.min(0.0,
														posInside.getX()+delta/facteurDiv),
														posInside.getY());
											}else if(Math.signum(posInside.getX())>0.0){
												posInside.setLocation(Math.max(0.0,
														posInside.getX()-delta/facteurDiv),
														posInside.getY());
											}
											if(Math.signum(posInside.getY())<0.0){
												posInside.setLocation(
														posInside.getX(),
														Math.min(0.0,posInside.getY()+delta/facteurDiv));
											}else if(Math.signum(posInside.getY())>0.0){
												posInside.setLocation(
														posInside.getX(),
														Math.max(0.0,posInside.getY()-delta/facteurDiv));
											}
										}


									}
								}
							}
						}else{
							trouverNouvelObjectif();

						}
					}

				}

			}
			//System.out.println(this+"  "+pos+"-> "+this.posInside+"  ");

		}
		testSurvie();
	}


	public void ordonner(ObjectifType type){
		listeMaitre .add(type);
	}



}
