import java.io.Serializable;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.File;
import java.util.*;

public class DataStorage implements Serializable{

    private Map[] map;
    private SoundEffect[] soundEffect;
    private Tutorial tutorial;
    private Charts chart;
    private Skin[] skins;
    //private Color chosenColor;
    private String chosenColor;
    private Map[][] storedMaps; 

    public DataStorage() {
        storedMaps = new Map[3][15];
        storedMaps[0] = getMaps(6);
        //storedMaps[1] = getMaps(8);
        //storedMaps[2] = getMaps(10);
        //map = new Map[15];
        //map[0] = new Map(6,3,5,1);
        //map[1] = new Map(8,3,7,1);
        //map[2] = new Map(10,3,9,1);
        /*
        //==========================SOUND EFFECTS=======================================
        soundEffect = new SoundEffect[2];
        File soundFolder = new File("/Users/Desktop/soundFolder/");   //update the path
        File[] listOfFiles = soundFolder.listFiles();

        for (File file : listOfFiles) {
            int i = 0;
            if (file.isFile()) {
                soundEffect[i] = new SoundEffect(file.getPath(), 50);
            }
            i++;
        }

        //==========================TUTORIAL============================================
        String tutorialLoc = "./ / / / ";  //path to the directory where tutorial is saved
        tutorial = new Tutorial(tutorialLoc, ".jpg");

        //==========================SKINS===============================================
        skins = new Skin[5];
        File skinFolder = new File("/Users/Desktop/skinFolder/");   //update the path
        listOfFiles = skinFolder.listFiles();

        for (File file : listOfFiles) {
            int i = 0;
            BufferedImage image = null;
            try {
                image = ImageIO.read(new File(file.getPath()));
            } catch( Exception e) {}

            if (file.isFile()) {
                skins[i] = new Skin(i, image, true);
            }
            i++;
        }
        */
        //===========================CHOSEN COLOR=======================================
        chosenColor = "LIGHT_GRAY";
    }
    //returns maps
    public Map[] getMaps(int dimension){
        Map[] dimensionMaps = new Map[15];
        List<Car> perMapCarList = new ArrayList<>();
        Car car ;
        boolean firstConfig = true;

		String maps = "";
		switch(dimension){
			case 6: maps = "./storage/6x6dim.csv"; break;
			case 8: maps = "./storage/8x8dim.csv"; break;
			case 10: maps = "./storage/10x10im.csv"; break;
		}

        int mapIndex = 0;
        boolean player = false;
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(maps));
			String line = reader.readLine();
            
			do {
                player = false;
                int skinNo = (int)(Math.random()*(10));
				String[] array = line.split(",");
                int index = Integer.parseInt(array[0]);
                int direction = Integer.parseInt(array[1]);       
                int startx = Integer.parseInt(array[2]);
                int endx = Integer.parseInt(array[3]);
                int starty = Integer.parseInt(array[4]);
                int endy = Integer.parseInt(array[5]);
                int length = Integer.parseInt(array[6]);
                if (index == 0){
                    player = true;
                    if (!firstConfig){
                        mapIndex++;
                        Car[] carArray =  new Car[perMapCarList.size()]; //perMapCarList.toArray(new Integer(perMapCarList.size()));
                        int indexer =0;
                        for (Car tmp : perMapCarList) {
                            //System.out.println("Indexer " + indexer);
                            carArray[indexer] = tmp.clone();
                            indexer++;
                            
                        }
                        int finishX = carArray[0].getX();
                        Map newMap = new Map();   
                        newMap.setCars(carArray);
                        newMap.setDimension(dimension);
                        newMap.setLevel(mapIndex);
                        newMap.setExitCoordinates(finishX,dimension-1);
                        //System.out.println("The finish: " +  finishX + " " + (dimension-1));
                        newMap.initMap(finishX,dimension-1);
                        dimensionMaps[mapIndex-1] = newMap.clone();     
                        perMapCarList = new ArrayList<>();  
                        System.out.println(newMap);  
                        //return dimensionMaps;             
                    }
                    firstConfig = false;
                }
                car = new Car();
                car.setImageLocation("/img/"+skinNo);
                car.setHorizontalX(startx,endx);
	            car.setVerticalY(starty,endy );
		        car.setLength(length);
		        car.setCarDirection(direction);
		        car.setPlayer(player);
                car.setCarSkin(skinNo);
                perMapCarList.add(car.clone());

				line = reader.readLine();
			} while (line != null);
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
        mapIndex++;
        Car[] carArray =  new Car[perMapCarList.size()]; //perMapCarList.toArray(new Integer(perMapCarList.size()));
        int indexer =0;
        for (Car tmp : perMapCarList) {
            //System.out.println("Indexer " + indexer);
            carArray[indexer] = tmp.clone();
            indexer++;
        }
        int finishX = carArray[0].getX();
        Map newMap = new Map();   
        newMap.setCars(carArray);
        newMap.setDimension(dimension);
        newMap.setLevel(mapIndex);
        newMap.setExitCoordinates(finishX,dimension-1);
        //System.out.println("The finish: " +  finishX + " " + (dimension-1));
        newMap.initMap(finishX,dimension-1);
        dimensionMaps[mapIndex-1] = newMap.clone();     
        perMapCarList = new ArrayList<>();   
        for (int i = mapIndex; i < dimensionMaps.length; i++){
            dimensionMaps[i] = dimensionMaps[i%mapIndex].clone();
        }

        return dimensionMaps;
    }

    public Map getMap(int dimension,int level) {
        System.out.println("My level is : " + level);
        if (dimension == 6){
            return storedMaps[0][level];
        }
        else if (dimension == 8){
            return storedMaps[1][level];
        }
        else {
            return storedMaps[2][level];
        }        
    }
    //returns soundEffect
    public SoundEffect[] getSoundEffects(){
        return soundEffect;
    }

    //return tutorial file
    public Tutorial getTutorial(){
        return tutorial;
    }

    //return chart
    public Charts getCharts(){
        return chart;
    }

    //return skins
    public Skin[] getSkins(){
        return skins;
    }

    // return chosenColor, default at first
    public String getChosenColor(){
        return chosenColor;
    }

    public boolean[] getOpenMapArray(int dimension){
		int fileLine = 0;
		switch(dimension){
			case 6: fileLine = 0; break;
			case 8: fileLine = 1; break;
			case 10: fileLine = 2; break;
		}

		BufferedReader reader;
		int LineIndex = 0;
		boolean[] openMaps = new boolean[15];
		try {
			reader = new BufferedReader(new FileReader(
					"./storage/MapsLocked.txt"));
			String line = reader.readLine();
			do {
				//System.out.println((line.split(",")));
				// read next line
				//System.out.println(line);

				if (fileLine == LineIndex){
					String[] array = line.split(",");
					for (int i = 0 ; i < array.length; i++){
						if (array[i].equals("0")){
							openMaps[i] = false;
						}else {
							openMaps[i] = true;
						}
					}
				}
				line = reader.readLine();
				LineIndex++;
			} while (line != null);
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return openMaps;
	}

    public void updateOpenMaps(int dimension, boolean[] openMaps){
        int fileLine = 0;
		switch(dimension){
			case 6: fileLine = 0; break;
			case 8: fileLine = 1; break;
			case 10: fileLine = 2; break;
		}

		BufferedReader reader;
		int LineIndex = 0;
		String[] readOpenMaps = new String[3];
		try {
			reader = new BufferedReader(new FileReader(
					"./storage/MapsLocked.txt"));
			String line = reader.readLine();
			do {
				if (fileLine == LineIndex){
                    String tmp = "";
					for (int i = 0 ; i < openMaps.length; i++){
                        System.out.println(openMaps[i]);
						if (openMaps[i]){
							tmp += "1,";
						}else {
							tmp += "0,";
						}
					}
                    readOpenMaps[LineIndex] = tmp;
				} else {
                       readOpenMaps[LineIndex] = line;
                }
				line = reader.readLine();
				LineIndex++;
			} while (line != null);
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		try (PrintWriter out = new PrintWriter("./storage/MapsLocked.txt")) {
            for (int i = 0; i < readOpenMaps.length; i++){
                System.out.println(readOpenMaps[i]);
                out.println(readOpenMaps[i].substring(0,readOpenMaps[i].length()-1));
            }
        }catch (IOException e) {
			e.printStackTrace();
		}
    }

    /*public static void main(String[] args){
        DataStorage tmp = new DataStorage();
       /* Map[] test = tmp.getMaps(6);
        for (int i = 0; i < test.length; i++){
             System.out.println("Map " +  i + " " +test[i]); 
             System.out.println();
        }
        Map tmpe = tmp.getMap(6,0);
        System.out.println("After the cal :");
        System.out.println(tmpe);
       
    }*/
}
