package julien.mechant;

public class Carte {
	
	Effet effetBien;
	Effet effetMal;
	
	public Carte(Effet effetBien, Effet effetMal) {
		super();
		this.effetBien = effetBien;
		this.effetMal = effetMal;
	}
	
	public boolean selectionBien() {
		System.out.println("Selection du bien");
		if (effetBien.estJouable()) {
			jouer(effetBien);
			return true;
		} else {
			return false;
		}
	}
	
	public boolean selectionMal() {
		System.out.println("Selection du mal");
		if (effetMal.estJouable()) {
			System.out.println("Jouable!");
			jouer(effetMal);
			return true;
		} else {
			return false;
		}
	}
	
	public void jouer(Effet e) {
		Mechant.getInstance().defausser(this);
		e.appliquer();
	}

	public Effet getEffetBien() {
		return effetBien;
	}

	public void setEffetBien(Effet effetBien) {
		this.effetBien = effetBien;
	}

	public Effet getEffetMal() {
		return effetMal;
	}

	public void setEffetMal(Effet effetMal) {
		this.effetMal = effetMal;
	}
	
	
	
	

}
