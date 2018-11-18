
public class Map {
	private Car player;
	private Car[] cars;
	private Block[][] blocks;
	private int stars;
	private int difficulty;
	private int dimension;
	private int minMoveCount;
	private boolean isOpenMap;
	
	public Map(int dimension){
		this.dimension = dimension;
		initMap();
	}
	public Map(int stars, int difficulty, int dimension, int minMoveCount, boolean isOpenMap ) {
		this.stars = stars;
		this.difficulty = difficulty;
		this.dimension = dimension;
		this.minMoveCount = minMoveCount;
		this.isOpenMap = isOpenMap;
	}

	public int getStars() {
		return stars;
	}

	public void setStars(int stars) {
		this.stars = stars;
	}

	public int getDifficulty() {
		return difficulty;
	}

	public void setDifficulty(int difficulty) {
		this.difficulty = difficulty;
	}

	public Car getPlayer() {
		return player;
	}

	public void setPlayer(Car player) {
		this.player = player;
	}

	public int getDimension() {
		return dimension;
	}

	public void setDimension(int dimension) {
		this.dimension = dimension;
	}

	public int getMinMoveCount() {
		return minMoveCount;
	}

	public void setMinMoveCount(int minMoveCount) {
		this.minMoveCount = minMoveCount;
	}

	public boolean isOpenMap() {
		return isOpenMap;
	}

	public void setOpenMap(boolean isOpenMap) {
		this.isOpenMap = isOpenMap;
	}
	
	public Block[][] getBlocks(){
		return blocks;
	}
	public Car[] getCars(){
		return cars;
	}
	
	private void initMap(){
		blocks = new Block[dimension][];
		int lengthIndex = 0; 
		for (int rowIndex = 0; rowIndex < dimension; rowIndex++ ){
			blocks[rowIndex] = new Block[dimension];
			for (int columnIndex = 0; columnIndex < dimension; columnIndex++){
				blocks[rowIndex][columnIndex] = new Block(rowIndex,columnIndex);
			}
		}

		cars = getCarsFromStorage();

		int carX, carY, carEndX, carEndY;
		for (int i = 0; i < cars.length; i++){
			carX = cars[i].getX();
			carY = cars[i].getY();
			carEndX = cars[i].getHorizontalX();
			carEndY = cars[i].getVerticalY();
			for (int j = 0; j <= carEndX-carX; j++){
				blocks[carX+j][carY].setOccupied(true);
			}
			for (int j = 0; j <= carEndY-carY; j++){
				blocks[carX][carY + j].setOccupied(true);
			}
		}

	}

	private Car[] getCarsFromStorage(){
		int noOfCars = 8;
		cars = new Car[noOfCars];
		for (int i = 0; i < noOfCars; i++){
			cars[i] = new Car();
		}

		cars[0].setHorizontalX(0,1);
		cars[0].setVerticalY(2,2);
		cars[0].setLength(2);
		cars[0].setCarDirection(3);

		/*cars[1].setHorizontalX(0,0);
		cars[1].setVerticalY(4,5);
		cars[1].setLength(2);
		cars[0].setCarDirection(0);
		
		cars[2].setHorizontalX(1,1);
		cars[2].setVerticalY(4,5);
		cars[2].setLength(2);
		cars[0].setCarDirection(0);
		cars[3].setHorizontalX(1,2);
		cars[3].setVerticalY(3,3);
		cars[3].setLength(2);
		cars[0].setCarDirection(3);
		
		cars[4].setHorizontalX(2,2);
		cars[4].setVerticalY(0,2);
		cars[4].setLength(3);
		cars[0].setCarDirection(0);
		cars[5].setHorizontalX(3,3);
		cars[5].setVerticalY(1,2);
		cars[5].setLength(2);
		cars[0].setCarDirection(2);
		
		cars[6].setHorizontalX(3,5);
		cars[6].setVerticalY(4,4);
		cars[6].setLength(3);
		cars[0].setCarDirection(1);
		cars[7].setHorizontalX(5,5);
		cars[7].setVerticalY(0,2);
		cars[7].setLength(3);
		cars[0].setCarDirection(0);*/

		return cars;
	}

}