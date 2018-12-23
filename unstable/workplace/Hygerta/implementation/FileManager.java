import java.io.Serializable;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;


public class FileManager implements Serializable{

  private Maps[] map;
  private SoundEffects[] soundEffect;
  private Tutorial tutorial;
  private Charts chart;
  private Skin[] skins;
  //private Color chosenColor;
  private String chosenColor;


  map = new Map[15];

//==========================SOUND EFFECTS=======================================
  soundEffect = new SoundEffect[2];
  File soundFolder = new File("/Users/Desktop/soundFolder/");   //update the path
  File[] listOfFiles = soundFolder.listFiles();

  for (File file : listOfFiles) {
    int i = 0;
      if (file.isFile()) {
        soundEffect[i] = new SoundEffect(file.getPath(), true);
      }
      i++;
  }

//==========================TUTORIAL============================================
  String tutorialLoc = "./ / / / "  //path to the directory where tutorial is saved
  tutorial = new Tutorial(tutorialLocation, ".jpg");

//==========================SKINS===============================================
  skins = new Skin[5];
  File skinFolder = new File("/Users/Desktop/skinFolder/");   //update the path
  File[] listOfFiles = skinFolder.listFiles();

  for (File file : listOfFiles) {
    int i = 0;
    BufferedImage image = ImageIO.read(new File(file.getPath()));
      if (file.isFile()) {
        skin[i] = new Skin(i, image, true);
      }
      i++;
  }

//===========================CHOSEN COLOR=======================================
  chosenColor = "LIGHT_GRAY";

  //constructor
  public FileManager(){

  }

  //returns maps
  public Map getMaps(){
    return map;
  }

  //returns soundEffect
  public SoundEffect[] getSoundEffects(){
    return SoundEffects;
  }

  //return tutorial file
  public Tutorial getTutorial(){
    return tutorial;
  }

  //return chart
  public Chart getCharts(){
    return chart;
  }

  //return skins
  public Skin getSkins(){
    return skin;
  }

  // return chosenColor, default at first
  public String getChosenColor(){
    return chosenColor;
  }

}
