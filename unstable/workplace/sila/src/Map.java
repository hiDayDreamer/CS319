import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Map {
	private Car player;
	private Car[] cars;
	private Block[][] blocks;
	private int level;
	private int dimension;
	private int minMoveCount;
	private boolean isOpenMap;
	private int timeNeededinSec ;
	private int finishx,finishy;

	public Map() {
		cars = null;
		blocks = null;
		level = 0;
		dimension = 0;
		minMoveCount = 0;
		timeNeededinSec = 65;
		isOpenMap = true;
	}

	public Map(int dimension,int x,int y,int level){
		this.dimension = dimension;
		this.level = level;
		finishx = x;
		finishy = y;
		initMap(finishx,finishy);
	}

	public Map( int level, int dimension, int minMoveCount, boolean isOpenMap ) {
		this.level = level;
		this.dimension = dimension;
		this.minMoveCount = minMoveCount;
		this.isOpenMap = isOpenMap;
		timeNeededinSec = 65;
	}

	public Map clone() {
		Map newMap = new Map();
		newMap.level = level;
		newMap.dimension = dimension;
		newMap.minMoveCount = minMoveCount;
		newMap.isOpenMap = isOpenMap;
		newMap.timeNeededinSec = timeNeededinSec;
		newMap.finishx = finishx;
		newMap.finishy = finishy;
		Car[] newCars = new Car[cars.length];
		for ( int i = 0; i < cars.length; i++ )
			newCars[i] = cars[i].clone();
		Block[][] newBlocks = new Block[dimension][dimension];
		for ( int i = 0; i < dimension; i++) {
			for ( int j = 0; j < dimension; j++) {
				newBlocks[i][j] = new Block(i, j);
				newBlocks[i][j].setOccupied(blocks[i][j].isOccupied());
			}
		}
		newBlocks[finishx][finishy].setFinishBlock(true);
		newMap.cars = newCars;
		newMap.blocks = newBlocks;
		return newMap;
	}

	public Car getPlayer() {
		return cars[0];
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
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

	public void setCars(Car[] cars ){
		this.cars = cars;
	}

	public int getDifficulty() {
		return 0;
	}

	private void initMap(int finishBlockX,int finishBlockY){
		timeNeededinSec = 65;
		blocks = new Block[dimension][];
		int lengthIndex = 0;
		for (int rowIndex = 0; rowIndex < dimension; rowIndex++ ){
			blocks[rowIndex] = new Block[dimension];
			for (int columnIndex = 0; columnIndex < dimension; columnIndex++){
				blocks[rowIndex][columnIndex] = new Block(rowIndex,columnIndex);
			}
		}
		System.out.println("Inside the map the dinish is : " + finishx + " " + finishy);
		blocks[finishBlockX][finishBlockY].setFinishBlock(true);
		cars = getCarsFromStorage();

		int carX, carY, carEndX, carEndY;
		for (int i = 0; i < cars.length; i++){
			carX = cars[i].getX();
			carY = cars[i].getY();
			carEndX = cars[i].getHorizontalX();
			carEndY = cars[i].getVerticalY();

			if (carEndY-carY == 0){
				for (int j = 0; j < cars[i].getLength(); j++){
					//System.out.println("Row: " + (carX +j)+ "column " + carY);
					blocks[carX+j][carY].setOccupied(true);
				}
			} else if(carEndX-carX == 0){

				for (int j = 0; j < cars[i].getLength(); j++){
						//System.out.println("Row: " + (carX)+ "column " + (carY+j	));
					blocks[carX][carY + j].setOccupied(true);
				}
			} else {

			}
		}

	}

	private Car[] getCarsFromStorage(){
		int noOfCars = 7;
		cars = new Car[noOfCars];
		String s = "";
		try{
            BufferedReader br = new BufferedReader(new FileReader("src/skin.txt")); 
            s = br.readLine();
            br.close();
        }
        catch(IOException e) {
        	e.printStackTrace();
        }
		cars[0] = new Car();
		cars[0].setImageLocation(s);
		for (int i = 1; i < noOfCars; i++){
			cars[i] = new Car();
			cars[i].setImageLocation("/img/"+ (i - 1));
		}

		cars[0].setHorizontalX(3,3);
		cars[0].setVerticalY(1,2 );
		cars[0].setLength(2);
		cars[0].setCarDirection(2);
		cars[0].setPlayer(true);
		//player = cars[0];

		cars[1].setHorizontalX(0,0);
		cars[1].setVerticalY(4,5);
		cars[1].setLength(2);
		cars[1].setCarDirection(0);

		cars[2].setHorizontalX(1,1);
		cars[2].setVerticalY(4,5);
		cars[2].setLength(2);
		cars[2].setCarDirection(0);

		cars[3].setHorizontalX(1,2);
		cars[3].setVerticalY(3,3);
		cars[3].setLength(2);
		cars[3].setCarDirection(3);

		cars[4].setHorizontalX(2,2);
		cars[4].setVerticalY(0,2);
		cars[4].setLength(3);
		cars[4].setCarDirection(0);

		cars[5].setHorizontalX(0,1);
		cars[5].setVerticalY(2,2);
		cars[5].setLength(2);
		cars[5].setCarDirection(3);
		
		cars[6].setHorizontalX(3,5);
		cars[6].setVerticalY(4,4);
		cars[6].setLength(3);
		cars[6].setCarDirection(1);
		/*
		cars[1].setHorizontalX(0,2);
		cars[1].setVerticalY(2,2);
		cars[1].setLength(3);
		cars[1].setCarDirection(3);

/*		cars[6].setHorizontalX(3,5);
		cars[6].setVerticalY(4,4);
		cars[6].setLength(3);
		cars[6].setCarDirection(1);
/*
		cars[7].setHorizontalX(5,5);
		cars[7].setVerticalY(0,2);
		cars[7].setLength(3);
		cars[7].setCarDirection(0);
*/
		return cars;
	}

	public int getTime(){
		return timeNeededinSec;
	}

	public void printDim(){
		System.out.println(blocks.length);
	}

	public String toString(){
		int [][] mapper = new int[dimension][dimension];
		String tmp = "";

		for (int i = 0; i < cars.length; i++ ){
			Car car = cars[i];
			if ( car.getCarDirection() == 1 || car.getCarDirection() == 3 ) {
				for ( int j = car.getX(); j <= car.getHorizontalX(); j++) {
					mapper[j][car.getY()] = i+1;
				}
			} else {
				for ( int j = car.getY(); j <= car.getVerticalY(); j++) {
					mapper[car.getX()][j] = i+1;
				}
			}
		}
		for (int i = 0; i < blocks.length; i++){
			for (int j = 0; j < blocks.length; j++){
				tmp = tmp + blocks[i][j].isOccupied() + " ";
			}
			tmp += "\n";
		}
		return tmp;
	}
}
