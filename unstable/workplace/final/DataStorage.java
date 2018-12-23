import java.io.Serializable;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.File;

public class DataStorage implements Serializable{

    private Map[] map;
    private SoundEffect[] soundEffect;
    private Tutorial tutorial;
    private Charts chart;
    private Skin[] skins;
    //private Color chosenColor;
    private String chosenColor;

    public DataStorage() {
        map = new Map[15];
        map[0] = new Map(6,3,5);
        map[1] = new Map(8,3,7);
        map[2] = new Map(10,3,9);
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
    public Map[] getMaps(){
        return map;
    }

    public Map getMap(int dimension) {
        if (dimension == 6){
            return map[0];
        }
        else if (dimension == 8){
            return map[1];
        }
        if (dimension == 10){
            return map[2];
        } else {
            return map[0];
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
}
