package jitou.global;

import java.util.ArrayList;

public class CitoyenDehors {
	private Citoyen citoyen;
	public static ArrayList<CitoyenDehors> liste_cit = new ArrayList<CitoyenDehors> ();
	
	double x = 0.0;
	double vx=(Math.random()-0.5)/100.0;
	
	public CitoyenDehors(Citoyen cit){
		this.citoyen=cit;
		this.citoyen.visible=false;
		liste_cit.add(this);
	}

	public static void removeWith(Citoyen c) {
		for(int i=0;i<liste_cit.size();i++){
			if(liste_cit.get(i).getCitoyen()==c){
				liste_cit.remove(i);
			}
		}
	}

	public Citoyen getCitoyen() {
		return citoyen;
	}

	public void setCitoyen(Citoyen citoyen) {
		this.citoyen = citoyen;
	}
	
	public void update(int delta){
		x+=vx*delta;
	}
	
	public double getX(){
		return x;
	}
	
	
}
