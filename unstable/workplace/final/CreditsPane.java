import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.*;

public class CreditsPane extends Pane {
    //Constants
    private final String SOUND_ICON = "/img/soundIcon.png";
    private final String SETTINGS_ICON = "/img/Settings-icon.png";
    private final String BACK_ICON = "/img/backIcon.png";
    private final String TEAM_IMAGE = "/img/creditspage.png";
    private final String COPYRIGHT_LABEL = "Developed by Royal Flush";
    private final double WIDTH  = 1080;
    private final double HEIGHT = 720;
    private final double COPYRIGHT_PANEL_SIZE = 60;
    private final int ICON_SIZE = 64;

    //Variables
    int tutorialCount;

    //Labels
    private Label copyRightLabel;
    private Pane copyRightPanel;
    private Label version;

    //Buttons
    private Button soundButton;
    private Button settingsButton;
    private Button backButton;

    //Images
    private ImageView soundImage;
    private ImageView settingsImage;
    private ImageView backImage;
    private Image[] tutorialImage;
    private ImageView tutorial;

    public CreditsPane() {
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
        Label settings = new Label("");
        settings.setStyle("-fx-font-weight: bold;");
        ImageView labelImage = new ImageView("/img/credits.png");
        labelImage.setFitWidth(400);
        labelImage.setFitHeight(60);
        settings.setGraphic(labelImage);

        settings.setMaxSize(450,75);
        settings.setFont(new Font("Verdana", 45));
        settings.setLayoutX(335);
        settings.setLayoutY(30);

        //Sound button
        soundImage = new ImageView(new Image(SOUND_ICON));
        soundImage.setFitWidth(ICON_SIZE);
        soundImage.setFitHeight(ICON_SIZE);
        soundButton = new Button();
        soundButton.setGraphic(soundImage);
        soundButton.setStyle("-fx-background-color: transparent");
        soundButton.setMinSize(ICON_SIZE, ICON_SIZE);
        soundButton.setLayoutX(WIDTH - 100);
        soundButton.setLayoutY(25);

        //Settings button
        settingsImage = new ImageView(new Image(SETTINGS_ICON));
        settingsImage.setFitWidth(ICON_SIZE);
        settingsImage.setFitHeight(ICON_SIZE);
        settingsButton = new Button();
        settingsButton.setGraphic(settingsImage);
        settingsButton.setStyle("-fx-background-color: transparent");
        settingsButton.setMinSize(ICON_SIZE, ICON_SIZE);
        settingsButton.setLayoutX(WIDTH - 200);
        settingsButton.setLayoutY(25);

        //Back button
        backImage = new ImageView(BACK_ICON);
        backImage.setFitWidth(ICON_SIZE);
        backImage.setFitHeight(ICON_SIZE);
        backButton = new Button();
        backButton.setGraphic(backImage);
        backButton.setStyle("-fx-background-color: transparent");
        backButton.setMinSize(ICON_SIZE, ICON_SIZE);
        backButton.setLayoutX(25);
        backButton.setLayoutY(25);

        //Tutorial Images
        tutorialCount = 1;
        tutorialImage = new Image[tutorialCount];
        tutorialImage[0] = new Image(getClass().getResourceAsStream(TEAM_IMAGE), 950, 750, true, false);
        tutorial = new ImageView(tutorialImage[0]);
        tutorial.setLayoutX(50);
        tutorial.setLayoutY(150);
//        tutorial.setFitHeight(500);

        addAnimation(10);
        //Adding buttons to middle panel
        this.getChildren().addAll(settings, soundButton, settingsButton, backButton, tutorial);

        this.setCurrentColor(null);
        copyRightPanel.getChildren().add(copyRightLabel);
        copyRightPanel.getChildren().add(version);
    }

    public void setCurrentColor(String colorCSS){

        if (colorCSS == null){
            this.setStyle("-fx-background-color: #81aae6;");
        }else{
            this.setStyle(colorCSS);
        }
    }

    public void addAnimation(int factor) {
        soundButton.setOnMouseEntered(new GameManager.Animation(soundButton, factor, true));
        soundButton.setOnMouseExited(new GameManager.Animation(soundButton, factor, false));
        backButton.setOnMouseEntered(new GameManager.Animation(backButton, factor, true));
        backButton.setOnMouseExited(new GameManager.Animation(backButton, factor, false));
        settingsButton.setOnMouseEntered(new GameManager.Animation(settingsButton, factor, true));
        settingsButton.setOnMouseExited(new GameManager.Animation(settingsButton, factor, false));
    }

    public void addHandler( GameManager.ButtonListener e) {
        backButton.addEventHandler(MouseEvent.MOUSE_CLICKED, e);
        GameManager.ButtonListener settings = e.clone();
        settings.setIndex(4);
        settingsButton.addEventHandler(MouseEvent.MOUSE_CLICKED, settings);
    }
}
