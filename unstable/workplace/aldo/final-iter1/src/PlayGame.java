
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.layout.GridPane;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.geometry.Insets;
import javafx.geometry.*;
import javafx.application.Application; 
import javafx.scene.Scene; 
import javafx.scene.control.*; 
import javafx.scene.layout.*; 
import javafx.stage.Stage; 
import javafx.geometry.*; 
import javafx.scene.paint.*; 
import javafx.scene.canvas.*; 
import javafx.scene.text.*; 
import javafx.scene.Group; 
import javafx.scene.shape.*; 

public class PlayGame extends Pane {
    //Constants
    private final String GAME_ICON_LOG = "java_318-32027.jpg";
    private final String SOUND_ICON = "soundIcon.png";
    private final String SETTINGS_ICON = "Settings-icon.png";
    private final String BACK_ICON = "backIcon.png";
    private final String COPYRIGHT_LABEL = "Developed by Royal Flush";
    private final double WIDTH  = 1080;
    private final double HEIGHT = 720;
    private final double PLAY_WIDTH = 700;
    private final double PLAY_HEIGHT = 500;
    private final double COPYRIGHT_PANEL_SIZE = 60;
    private final int ICON_SIZE = 64;

    //Variables
    //Labels
    private Label copyRightLabel;
    private Pane copyRightPanel;
    private GridPane playGameSubpanel;
    private Label version;
    private Label[] leftAt;
    private Label[] numberOfStarsLabel;

    //Buttons
    private Button soundButton;
    private Button settingsButton;
    private Button backButton;
    private Button undoButton;
    private Button resetButton;
    private Button howToButton;
    private Button[] sixToSix;

    //Images
    private Image soundImage;
    private Image settingsImage;
    private Image backImage;
    private Image undoImage;
    private Image resetImage;
    private Image howToImage;
    private Image[] stars;
    private ImageView fullStar;

    //ProgressBar
    private ProgressBar[] dimensionProgression;

    public PlayGame() {
        super();
        initialize();
    }

    public void initialize(){

        //Creating middle panel
        this.setMinHeight(HEIGHT - COPYRIGHT_PANEL_SIZE);
        this.setMinWidth(WIDTH);

        //Creating copyright panel
        copyRightPanel = new Pane();

        //Copyright label
        copyRightLabel = new Label(COPYRIGHT_LABEL);

        //Version label
        version = new Label("Version b0.1");

        //How_To Panel
        //Sound button
        soundImage = new Image(getClass().getResourceAsStream(SOUND_ICON));
        soundButton = new Button();
        soundButton.setGraphic(new ImageView(soundImage));
        //soundButton.setStyle("-fx-background-color: transparent");
        soundButton.setMinSize(ICON_SIZE, ICON_SIZE);
        soundButton.setLayoutX(WIDTH - 100);
        soundButton.setLayoutY(35);

        //Settings button
        settingsImage = new Image(getClass().getResourceAsStream(SETTINGS_ICON));
        settingsButton = new Button();
        settingsButton.setGraphic(new ImageView(settingsImage));
        //settingsButton.setStyle("-fx-background-color: transparent");
        settingsButton.setMinSize(ICON_SIZE, ICON_SIZE);
        settingsButton.setLayoutX(WIDTH - 200);
        settingsButton.setLayoutY(35);

        //Back button
        backImage = new Image(getClass().getResourceAsStream(BACK_ICON));
        backButton = new Button();
        backButton.setGraphic(new ImageView(backImage));
        //backButton.setStyle("-fx-background-color: transparent");
        backButton.setMinSize(ICON_SIZE, ICON_SIZE);
        backButton.setLayoutX(25);
        backButton.setLayoutY(35);

        undoImage = new Image(getClass().getResourceAsStream(BACK_ICON));
        undoButton = new Button();
        undoButton.setGraphic(new ImageView(undoImage));
        //backButton.setStyle("-fx-background-color: transparent");
        undoButton.setMinSize(ICON_SIZE, ICON_SIZE);
        undoButton.setLayoutX(150);
        undoButton.setLayoutY(300);

        resetImage = new Image(getClass().getResourceAsStream(BACK_ICON));
        resetButton = new Button();
        resetButton.setGraphic(new ImageView(undoImage));
        //backButton.setStyle("-fx-background-color: transparent");
        resetButton.setMinSize(ICON_SIZE, ICON_SIZE);
        resetButton.setLayoutX(150);
        resetButton.setLayoutY(500);

    
        howToImage = new Image(getClass().getResourceAsStream(BACK_ICON));
        howToButton = new Button();
        howToButton.setGraphic(new ImageView(howToImage));
        //backButton.setStyle("-fx-background-color: transparent");
        howToButton.setMinSize(ICON_SIZE, ICON_SIZE);
        howToButton.setLayoutX(WIDTH-100);
        howToButton.setLayoutY(400);

        int[][] map;
        map = new int[][]{
            new int[]{0,1,2,0,1,0},
            new int[]{0,1,2,0,1,0},
            new int[]{0,1,2,0,1,0},
            new int[]{0,1,2,0,1,0},
            new int[]{0,1,2,0,1,0},
            new int[]{0,1,2,0,1,0},
        };

        BorderPane title = new BorderPane();
        Label royalFlush = new Label("Royal Flush Got this Bitch Baby :*"); 
        royalFlush.setLayoutX(300);
        royalFlush.setLayoutY(10);
        royalFlush.setFont(new Font(30));
        playGameSubpanel = buildGrid(map,new Insets(90,0,0,300));
        this.getChildren().addAll(royalFlush,soundButton, settingsButton, backButton,undoButton,resetButton,howToButton,playGameSubpanel);
        
        //Adding labels to panel
        copyRightPanel.getChildren().add(copyRightLabel);
        copyRightPanel.getChildren().add(version);

        //Default theme
        setCurrentColor(null);
    }
    
    
    private GridPane buildGrid(int[][] map, Insets constraints){
        playGameSubpanel = new GridPane();
        //playGameSubpanel.setPadding(new Insets(90, 0, 0,300));
        playGameSubpanel.setPadding(constraints);
        
        RowConstraints rowHeight = new RowConstraints(90);
        ColumnConstraints columnWidth = new ColumnConstraints(90);
        for (int rowIndex = 0; rowIndex < getCurrentDimensionSize(); rowIndex++){
            for (int columnIndex = 0; columnIndex < getCurrentDimensionSize(); columnIndex++){
                GridPane box = new GridPane();
                box.getRowConstraints().add(rowHeight);
                box.getColumnConstraints().add(columnWidth);
                box.setStyle("-fx-background-color: black, -fx-control-inner-background; -fx-background-insets: 0, 2; -fx-padding:2;");
                Image img = new Image(chooseMapImage(map[rowIndex][columnIndex]));
                ImageView possibleCar = new ImageView(img);

                GridPane.setConstraints(box, columnIndex, rowIndex);
                possibleCar.fitHeightProperty().bind(box.heightProperty().subtract(10));
                possibleCar.fitWidthProperty().bind(box.widthProperty().subtract(5));
                box.getChildren().add(possibleCar);
                playGameSubpanel.getChildren().add(box);
            }
        }
       
       return playGameSubpanel;
    }

    private String chooseMapImage(int number){
        switch(number) {
               case 0: return "fullStar.png";
               case 1: return BACK_ICON;
               case 2: return SETTINGS_ICON;
               default: return "fullstar.png"; 
        }
    }

    public void setCurrentColor(String colorCSS){

        if (colorCSS == null){
            this.setStyle("-fx-background-color: lightblue;");
        }else{
            this.setStyle(colorCSS);
        }
    }

    
    public void setCurrentDimensionSize(int dimension){
        
    }
    private int getCurrentDimensionSize(){
        return 6;
    }

}
