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
		if (effetBien.estJouable()) {
			jouer();
			return true;
		} else {
			return false;
		}
	}
	
	public boolean selectionMal() {
		if (effetMal.estJouable()) {
			jouer();
			return true;
		} else {
			return false;
		}
	}
	
	public void jouer() {
		Mechant.getInstance().defausser(this);
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
