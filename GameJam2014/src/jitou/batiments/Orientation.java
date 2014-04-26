package jitou.batiments;

public enum Orientation {
 sud(0), ouest(1), nord(2), est(3);

 private final int value;
 private Orientation(int value) {
     this.value = value;
 }

 public int getValue() {
     return value;
 }
}
