package julien.mechant;

public class EnergieMal {

	int total;
	TypeEnergie type;
	
	public EnergieMal(TypeEnergie type, int valeurInitiale) {
		total = valeurInitiale;
		this.type = type;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public TypeEnergie getType() {
		return type;
	}
	
	public void addTotal (int i) {
		total += i;
	}
	
	
	
}
