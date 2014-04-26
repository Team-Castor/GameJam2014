package jitou.ressources;

public enum RessourceType {
	nourriture(0), eau(1), bois(2), fer(3), petrole(4);
	
	 private final int value;
	 private RessourceType(int value) {
	     this.value = value;
	 }

	 public int getValue() {
	     return value;
	 }
}
