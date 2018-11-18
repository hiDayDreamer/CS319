
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
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public class Settings extends Pane {
    //Constants
    private final String GAME_ICON_LOG = "/img/java_318-32027.jpg";
    private final String SOUND_ICON = "/img/soundIcon.png";
    private final String SETTINGS_ICON = "/img/Settings-icon.png";
    private final String BACK_ICON = "/img/backIcon.png";
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

    public Settings() {
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

        //Back button
        backImage = new Image(getClass().getResourceAsStream(BACK_ICON));
        backButton = new Button();
        backButton.setGraphic(new ImageView(backImage));
        //backButton.setStyle("-fx-background-color: transparent");
        backButton.setMinSize(ICON_SIZE, ICON_SIZE);
        backButton.setLayoutX(25);
        backButton.setLayoutY(35);

        Label settings = new Label("Settings");
        settings.setLayoutX(500);
        settings.setLayoutY(20);
        settings.setFont(new Font(30));

        VBox left = new VBox(new Label("left"));
        left.setStyle("-fx-background-color: darkgreen");
        left.setPadding(new Insets(10, 50, 50, 50));

        left.setLayoutX(10);
        left.setLayoutY(150);

        left.setMinHeight(2*HEIGHT/3);
        left.setMinWidth(WIDTH/3 - 15);
        left.setMaxHeight(2*HEIGHT/3);
        left.setMaxWidth(WIDTH/3 - 15);


        ImageView audioImage = new ImageView(new Image(BACK_ICON));
        audioImage.setFitWidth(WIDTH/3 - 150);
        audioImage.setFitHeight(2*HEIGHT/3 - 200);
        left.getChildren().addAll(audioImage);

        VBox center = new VBox(new Label("center"));
        center.setStyle("-fx-background-color: red");
        center.setPadding(new Insets(10, 50, 50, 50));

        center.setLayoutX(10 + WIDTH/3);
        center.setLayoutY(150);

        center.setMinHeight(2*HEIGHT/3);
        center.setMinWidth(WIDTH/3 -15);

        VBox right = new VBox(new Label("right"));
        right.setStyle("-fx-background-color: yellow");
        right.setPadding(new Insets(10, 50, 50, 50));

        right.setLayoutX(10 + 2*WIDTH/3);
        right.setLayoutY(150);

        right.setMinHeight(2*HEIGHT/3);
        right.setMinWidth(WIDTH/3 -15);


        this.getChildren().addAll(settings,backButton,left,center,right);

        //Adding labels to panel
        copyRightPanel.getChildren().add(copyRightLabel);
        copyRightPanel.getChildren().add(version);

        //Default theme
        setCurrentColor(null);
    }

    public void setCurrentColor(String colorCSS){

        if (colorCSS == null){
            this.setStyle("-fx-background-color: lightblue;");
        }else{
            this.setStyle(colorCSS);
        }
    }

    public void addHandler( GameManager.ButtonListener e) {
      backButton.addEventHandler(MouseEvent.MOUSE_CLICKED, e);
  }
}
