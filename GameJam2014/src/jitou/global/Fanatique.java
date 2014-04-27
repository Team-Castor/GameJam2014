package jitou.global;

import java.awt.Point;
import java.util.ArrayList;

import jitou.batiments.Atelier;

public class Fanatique extends Citoyen{
	public static ArrayList<Fanatique> liste_fanatiques = new ArrayList<Fanatique>();
	private ArrayList<ObjectifType> listeMaitre = new ArrayList<ObjectifType>();


	public Fanatique(Point pos) {
		super(pos);
		liste_fanatiques.add(this);
	}


	protected void nouvelObjectif() {
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

	public void tuer() {
		BoardGame.boardGame.getBatiment(posX, posY).back(this);
		BoardGame.boardGame.killCitoyen(this);
		liste_fanatiques.remove(this);
		estMort=true;		
	}
	
	
	public void update(int delta){
		//System.out.println("Ordre fanatique "+ objectif);

		final  double facteurDiv = 200.0;

		temperatureCorporelle = (float) (temperatureCorporelle*0.99*delta/50.0+BoardGame.boardGame.getBatiment(posX, posY).getTemperatureSalle()*0.01*delta/50.0);
		nourritureRestante		-= delta/10.0;
		fatigue 				-= delta/12.0;

		if(workingTime>=0.0){
			workingTime = workingTime - delta;
			

			if(workingTime<0.0){
				pathfinder = null;
				objectif.getSeRendre().back(this);
			}	
			
		}
		else{
			if(objectif.getType().getValue()==ObjectifType.aucun.getValue()){
				pathfinder = null;
				nouvelObjectif();
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
						nouvelObjectif();

					}
				}

			}

		}
		//System.out.println(this+"  "+pos+"-> "+this.posInside+"  ");


		testSurvie();
	}
	
	public void ordonner(ObjectifType type){
		listeMaitre .add(type);
	}
	
	public void testSurvie(){
		if(this.nourritureRestante<0 || this.fatigue<0 || this.temperatureCorporelle<0){
			tuer();
		}
	}
	
}
