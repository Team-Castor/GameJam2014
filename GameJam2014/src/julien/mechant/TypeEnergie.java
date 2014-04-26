package julien.mechant;

public enum TypeEnergie {
	
	foi(0), joie(1), espoir(2);

	 private final int value;
	 private TypeEnergie(int value) {
	     this.value = value;
	 }

	 public int getValue() {
	     return value;
	 }
	 
}
