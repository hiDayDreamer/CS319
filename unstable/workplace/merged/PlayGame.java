import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
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
    private final String GAME_ICON_LOG = "/img/java_318-32027.jpeg";
    private final String SOUND_ICON = "/img/soundIcon.png";
    private final String SETTINGS_ICON = "/img/Settings-icon.png";
    private final String BACK_ICON = "/img/backIcon.png";
    private final String UNDO_ICON = "/img/undo.png";
    private final String RESET_ICON = "/img/reset.png";
    private final String HINT_ICON = "/img/hintImage.png";
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

    private Map gameMap;

    public PlayGame(Map map) {
        super();
        gameMap = map;
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
        soundImage = new Image(getClass().getResourceAsStream(SOUND_ICON),ICON_SIZE,ICON_SIZE,false,false);
        soundButton = new Button();
        //soundButton.setStyle("-fx-background-color: transparent");
        soundButton.setMinSize(ICON_SIZE,ICON_SIZE);
        soundButton.setLayoutX(WIDTH - 100);
        soundButton.setLayoutY(25);

        ImageView s = new ImageView(soundImage);

        s.fitWidthProperty().bind(soundButton.widthProperty());
        s.fitWidthProperty().bind(soundButton.heightProperty());
        soundButton.setGraphic(s);

        //Settings button
        settingsImage = new Image(getClass().getResourceAsStream(SETTINGS_ICON),ICON_SIZE,ICON_SIZE,false,false);
        settingsButton = new Button();
        //settingsButton.setStyle("-fx-background-color: transparent");
        settingsButton.setMinSize(ICON_SIZE, ICON_SIZE);
        settingsButton.setLayoutX(WIDTH - 200);
        settingsButton.setLayoutY(25);
        ImageView b = new ImageView(settingsImage);
        b.fitWidthProperty().bind(settingsButton.widthProperty());
        b.fitWidthProperty().bind(settingsButton.heightProperty());
        settingsButton.setGraphic(b);

        //Back button
        backImage = new Image(getClass().getResourceAsStream(BACK_ICON),ICON_SIZE,ICON_SIZE,false,false);
        backButton = new Button();
        //backButton.setStyle("-fx-background-color: transparent");
        backButton.setMinSize(ICON_SIZE, ICON_SIZE);
        backButton.setLayoutX(25);
        backButton.setLayoutY(25);

        ImageView b1 = new ImageView(backImage);
        b1.fitWidthProperty().bind(backButton.widthProperty());
        b1.fitWidthProperty().bind(backButton.heightProperty());
        backButton.setGraphic(b1);
        backButton.setGraphic(new ImageView(backImage));

        undoImage = new Image(getClass().getResourceAsStream(UNDO_ICON),ICON_SIZE,ICON_SIZE,false,false);
        undoButton = new Button();
        //undoButton.setStyle("-fx-background-color: transparent");
        undoButton.setMinSize(ICON_SIZE, ICON_SIZE);
        undoButton.setLayoutX(150);
        undoButton.setLayoutY(300);
        undoButton.setGraphic(new ImageView(undoImage));

        ImageView b2 = new ImageView(undoImage);
        b2.fitWidthProperty().bind(undoButton.widthProperty());
        b2.fitWidthProperty().bind(undoButton.heightProperty());
        undoButton.setGraphic(b2);


        resetImage = new Image(getClass().getResourceAsStream(RESET_ICON),ICON_SIZE,ICON_SIZE,false,false);
        resetButton = new Button();
        resetButton.setGraphic(new ImageView(undoImage));
        //resetButton.setStyle("-fx-background-color: transparent");
        resetButton.setMinSize(ICON_SIZE, ICON_SIZE);
        resetButton.setLayoutX(150);
        resetButton.setLayoutY(500);

        ImageView b3 = new ImageView(resetImage);
        b3.fitWidthProperty().bind(resetButton.widthProperty());
        b3.fitWidthProperty().bind(resetButton.heightProperty());
        resetButton.setGraphic(b3);

        howToImage = new Image(getClass().getResourceAsStream(HINT_ICON),ICON_SIZE,ICON_SIZE,false,false);
        howToButton = new Button();
        howToButton.setGraphic(new ImageView(howToImage));
        //howToButton.setStyle("-fx-background-color: transparent");
        howToButton.setMinSize(ICON_SIZE, ICON_SIZE);
        howToButton.setLayoutX(WIDTH-200);
        howToButton.setLayoutY(300);
        ImageView b5 = new ImageView(howToImage);
        b5.fitWidthProperty().bind(howToButton.widthProperty());
        b5.fitWidthProperty().bind(howToButton.heightProperty());
        howToButton.setGraphic(b5);

        BorderPane title = new BorderPane();
        Label royalFlush = new Label("Royal Flush Got this Bitch Baby :*");
        royalFlush.setLayoutX(300);
        royalFlush.setLayoutY(10);
        royalFlush.setFont(new Font(30));

        //reset
        Label royalFlush = new Label("Royal Flush Got this Bitch Baby :*");
        royalFlush.setLayoutX(300);
        royalFlush.setLayoutY(10);
        royalFlush.setFont(new Font(30));

        //undo
         Label royalFlush = new Label("Royal Flush Got this Bitch Baby :*");
        royalFlush.setLayoutX(300);
        royalFlush.setLayoutY(10);
        royalFlush.setFont(new Font(30));

        //hint
        Label royalFlush = new Label("Royal Flush Got this Bitch Baby :*");
        royalFlush.setLayoutX(300);
        royalFlush.setLayoutY(10);
        royalFlush.setFont(new Font(30));

        playGameSubpanel = buildGrid(new Insets(90,0,0,300));

        this.getChildren().addAll(royalFlush, soundButton, settingsButton, howToButton, playGameSubpanel, backButton, undoButton, resetButton);

        //this.getChildren().addAll(soundButton);

        //Adding labels to panel
        copyRightPanel.getChildren().add(copyRightLabel);
        copyRightPanel.getChildren().add(version);

        //Default theme
        setCurrentColor(null);
    }


    private GridPane buildGrid(Insets constraints){
        playGameSubpanel = new GridPane();
        //playGameSubpanel.setPadding(new Insets(90, 0, 0,300));
        playGameSubpanel.setPadding(constraints);

        Block[][] mapBlocks = gameMap.getBlocks();
        Car[] cars = gameMap.getCars();

        String loc = "/img/grass.jpg";
        int gridBoxSize = 90;

        GridPane box = new GridPane();

        box.setGridLinesVisible(true);
        box.setStyle("-fx-background-color: black, -fx-control-inner-background; -fx-background-insets: 0, 2; -fx-padding:2;");

        RowConstraints rowHeight = new RowConstraints(gridBoxSize);
        ColumnConstraints columnWidth = new ColumnConstraints(gridBoxSize);
        for (int rowIndex = 0; rowIndex < getCurrentDimensionSize(); rowIndex++){
            rowHeight = new RowConstraints(gridBoxSize);
            columnWidth = new ColumnConstraints(gridBoxSize);
            box.getRowConstraints().add(rowHeight);
            box.getColumnConstraints().add(columnWidth);
            for (int columnIndex = 0; columnIndex < getCurrentDimensionSize(); columnIndex++){
               // GridPane.setConstraints(box, columnIndex, rowIndex);
                //if (!mapBlocks[rowIndex][columnIndex].isOccupied()){
                    //System.out.println("It is free ");
                    Image img = new Image(loc);
                    ImageView possibleCar = new ImageView(img);
                    possibleCar.setFitWidth(gridBoxSize);
                    possibleCar.setFitHeight(gridBoxSize);
                    box.add(possibleCar,columnIndex,rowIndex);
*/
                //} //else {
                    //loc = "settings.png";
               // }


            }
        }

        box.setStyle("-fx-background-color: #C0C0C0;");

        int direction = -1;
        loc = "/img/grass.png";
        for (int carIndex = 0 ; carIndex < cars.length; carIndex++){
            direction = cars[carIndex].getCarDirection();
            loc = cars[carIndex].getImageLocation();
            Image img = new Image(loc);
            ImageView possibleCar = new ImageView(img);


            if ( direction == 1 || direction == 3){
                if (direction == 1 && carIndex != 6){
                    possibleCar.setRotate(90);
                }
                if (direction == 3 && carIndex != 6){
                    possibleCar.setRotate(270);
                }
                if (carIndex == 0){
                     possibleCar.setRotate(180);
                }
                
                possibleCar.setFitHeight(gridBoxSize*cars[carIndex].getLength());
               //possibleCar.setON
                GridPane.setRowSpan(possibleCar,cars[carIndex].getLength());
                GridPane.setRowIndex(possibleCar,cars[carIndex].getX());
                GridPane.setColumnIndex(possibleCar,cars[carIndex].getY());
                possibleCar.setFitWidth(gridBoxSize);
                box.getChildren().add(possibleCar);

            } else {
                if (direction == 2 && carIndex != 6){
                    possibleCar.setRotate(180);
                }
                possibleCar.setFitWidth(cars[carIndex].getLength()* gridBoxSize);
                possibleCar.setFitHeight(gridBoxSize);
                GridPane.setRowIndex(possibleCar,cars[carIndex].getX());
                GridPane.setColumnIndex(possibleCar,cars[carIndex].getY());
                box.getChildren().add(possibleCar);
            }

    }
        playGameSubpanel.getChildren().add(box);


       return playGameSubpanel;
    }

    private String chooseMapImage(int number){
        switch(number) {
               case 0: return  "/img/gertasila.png";
               case 1: return  "/img/gertasila.png";
               case 2: return  "/img/gertasila.png";
               default: return  "/img/gertasila.png";
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

    public void addHandler( GameManager.ButtonListener e) {
              // add the button listener to the back button
              backButton.addEventHandler(MouseEvent.MOUSE_CLICKED, e);

              GameManager.ButtonListener settings = e.clone();
              settings.setIndex(4);
              settingsButton.addEventHandler(MouseEvent.MOUSE_CLICKED, settings);
     }
}
