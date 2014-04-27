package jitou.batiments;

import java.awt.Point;
import java.util.ArrayList;

import jitou.global.BoardGame;
import jitou.global.Citoyen;
import jitou.global.ObjectifType;

public class Atelier extends Batiment{

	public static ArrayList<Atelier> listeAtelier= new ArrayList<Atelier>();
	private double workingTime=300.0;

	private double fer = 0.0;
	private double nbTour=-1, puissance=1.0;




	public Atelier(Point pos) {
		super(pos, TypeBatiment.Atelier);
		listeAtelier.add(this);
	}


	public void effet(Citoyen citoyen, ObjectifType type) {
		if(type.getValue() == ObjectifType.rapporterferAtelier.getValue()){
			fer+=citoyen.getRessourceTransporte().getQuantite();
			citoyen.setRessourceTransporte(null);

			citoyen.setWorkingTime(workingTime);

		}else 	if(type.getValue() == ObjectifType.allerVolerOutils.getValue()){
			fer=Math.max(0.0, fer-10.0);
			citoyen.setWorkingTime(45);
		}else{
			super.effet(citoyen, type);

		}
	}


	public String info() {
		return "Fer : "+fer;
	}

	public double getFer() {
		return fer;
	}


	public void setFer(double fer) {
		this.fer = fer;
	}

	public void update(int delta){
		if(nbTour>0){
			nbTour-=delta;
		}
		else{
			puissance=1.0;
		}
		
		double prix = BoardGame.boardGame.getListeBatiments().size()*15+50-Atelier.listeAtelier.size()*20;
		prix=prix*puissance;

		if(getFer()>prix){
			constructionSalle();
			setFer(getFer()-prix);
		}

	}
	
	

	private void constructionSalle() {
		final TypeBatiment bats[] = {
				TypeBatiment.Generateur, 
				TypeBatiment.Refectoire, 
				TypeBatiment.Dortoir, 
				TypeBatiment.Chaudiere, 
				TypeBatiment.MineDeFer, 
				TypeBatiment.PuitPetrol, 
				TypeBatiment.FermeHydroponique, 
				TypeBatiment.Atelier, 
				TypeBatiment.Arsenal, 
				TypeBatiment.Hopital};

		int nb = (int) (Math.random()*bats.length);
		int val = bats[nb].getValue();
		
		if(BoardGame.boardGame.randomSalle().getTemperatureSalle()<14){
			if(Generateur.listeGenerateurs.get((int) (Math.random()*Generateur.listeGenerateurs.size())).getElectricite()<15){
				if(Math.random()<0.5)val = TypeBatiment.Generateur.getValue();
				else val = TypeBatiment.PuitPetrol.getValue();
			}
			else{
				val = TypeBatiment.Chaudiere.getValue();
			}
		}
		else if(Arsenal.listeArsenals.size()==0 && Math.random()<0.5){
			val = TypeBatiment.Arsenal.getValue();
		}else  if(Hopital.listeHopitaux.size()==0 && Math.random()<0.5){
			val = TypeBatiment.Hopital.getValue();
		}else  if(Dortoir.listeDortoirs.size()*15<BoardGame.boardGame.getCitoyens().size() && Math.random()<0.5){
			val = TypeBatiment.Dortoir.getValue();
		}

	

		ArrayList<Point> liste = new ArrayList<Point>();
		Point listePts[] = {new Point(-1, 0),new Point(1, 0),new Point(0, 1),new Point(0, -1)};

		for(Batiment b : BoardGame.boardGame.getListeBatiments()){
			int x = b.getPos().x;
			int y = b.getPos().y;

			for(int j=0;j<listePts.length;j++){
				if(x+listePts[j].x>=0 && x+listePts[j].x<BoardGame.getDimensionworldx() &&
						y+listePts[j].y>=0 && y+listePts[j].y<BoardGame.getDimensionworldy()-2){
					if(BoardGame.boardGame.getBatiment(x+listePts[j].x, y+listePts[j].y)==null){
						liste.add(new Point((x+listePts[j].x), (y+listePts[j].y)));
					}
				}
			}

		}

		Point p = (liste.get((int) (Math.random()*liste.size())));
		System.out.println("Construction "+p);
		Batiment bat=null;
		if(val== TypeBatiment.Generateur.getValue()){
			bat = new Generateur(p);
		}else if(val==TypeBatiment.Refectoire.getValue()){
			bat = new Refectoire(p);
		}else if(val==TypeBatiment.Chaudiere.getValue()){
			bat = new Chaudiere(p);
		}else if(val==TypeBatiment.Dortoir.getValue()){
			bat = new Dortoir(p); 
		}else if(val==TypeBatiment.MineDeFer.getValue()){
			bat = new MineDeFer(p); 
		}else if(val==TypeBatiment.PuitPetrol.getValue()){
			bat = new PuitPetrol(p); 
		}else if(val==TypeBatiment.FermeHydroponique.getValue()){
			bat = new Ferme(p); 
		}else if(val==TypeBatiment.Atelier.getValue()){
			bat = new Atelier(p); 
		}else if(val==TypeBatiment.Hopital.getValue()){
			bat = new Hopital(p); 
		}else if(val==TypeBatiment.Arsenal.getValue()){
			bat = new Arsenal(p); 
		}
		BoardGame.boardGame.addBatiment(p,bat);

		julien.game.Game.getInstance().changementCase(p.x, p.y);
	}

	public void superMacon(double nbTour, double puissance) {
		this.nbTour+=nbTour;
		this.puissance =puissance;
	}


}
