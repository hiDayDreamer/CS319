public class Car {
	private int x;
	private int y;
	private int endX;
	private int endY;
	private int length;
	private boolean isPlayer;
	private boolean isMoveable;
	private int carSkin;
	private int moveCount;
	private int carDirection;
	private String imageLocation;
	
	public Car(){
		isMoveable = false;
		length = 1;
		isPlayer = false;
	}

	public Car(int x, int y, int length, boolean isPlayer, boolean isMoveable, int carSkin) {
		this.x = x;
		this.y = y;
		this.length = length;
		this.isPlayer = isPlayer;
		this.isMoveable = isMoveable;
		this.carSkin = carSkin;
	}

	public void setCarDirection(int dir){
		this.carDirection = dir;
	}
	public int getCarDirection(){
		return carDirection;
	}

	public void move(int x, int y) { //MOVES TO DO PARAMETERS
		this.x = x;
		this.y = y;
	}
	
	public void changeCarSkin(int skinNumber) {
		//TO DO
	}
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public boolean isPlayer() {
		return isPlayer;
	}

	public void setPlayer(boolean isPlayer) {
		this.isPlayer = isPlayer;
	}

	public boolean isMoveable() {
		return isMoveable;
	}

	public void setMoveable(boolean isMoveable) {
		this.isMoveable = isMoveable;
	}

	public int getCarSkin() {
		return carSkin;
	}

	public void setCarSkin(int carSkin) {
		this.carSkin = carSkin;
	}

	public int getMoveCount() {
		return moveCount;
	}

	public void setMoveCount(int moveCount) {
		this.moveCount = moveCount;
	}
	
	public void setImageLocation(String loc){
		imageLocation = loc;
	}

	public String getImageLocation(){
		return imageLocation;
	}
	
	public void setHorizontalX(int x, int endX) {
		this.x = x;
		this.endX = endX;
	}

	public int getVerticalY() {
		return endY;
	}

	public void setVerticalY(int y, int endY) {
		this.y = y;
		this.endY = endY;
	}

	public int getHorizontalX() {
		return endX;
	}
}
