package jitou.global;

public enum ObjectifType {
	se_reposer(0), manger(1), allerChercherArme(2), allerDefendre(3), allerRendreArme(4), 
	allerAUneFerme(5), rapporterNourritureRefectoir(6), aucun(7);
	
	
	 private final int value;
	 private ObjectifType(int value) {
	     this.value = value;
	 }

	 public int getValue() {
	     return value;
	 }
}
