package julien.game;

public enum FXtype {

	sang(32,32),explosion(32,32);

	private final int x;
	private final int y;

	private FXtype(int x, int y) {
		this.x = x;
		this.y = y;
	}
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
}
