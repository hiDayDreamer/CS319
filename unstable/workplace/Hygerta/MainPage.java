import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public class MainPage extends Pane{

    //Constants
    private final String PLAY_ICON = "/img/PLAY.png";
    private final String HOW_TO_ICON = "/img/HOW_TO.png";
    private final String DASHBOARD_ICON = "/img/DASHBOARD.png";
    private final String SETTINGS_ICON = "/img/SETTINGS.png";
    private final String SOUND_ICON = "/img/soundIcon.png";
    private final String CREDITS_ICON = "/img/Credits_button.png";
    private final String COPYRIGHT_LABEL = "Developed by Royal Flush";
    private final String GAME_TITLE = "Rush Hour";
    private final double WIDTH  = 1080;
    private final double HEIGHT = 720;
    private final double COPYRIGHT_PANEL_SIZE = 60;
    private final double BUTTON_X = WIDTH / 2 - 125;
    private final double BUTTON_Y = 125;
    private final double BUTTON_WIDTH = 250;
    private final double BUTTON_HEIGHT = 75;
    private final int ICON_SIZE = 64;

    //Variables
    private Label copyRightLabel;
    private Label name;
    private Label version;
    private Button[] frameButtons;
    private Button soundButton;
    private Pane copyRightPanel;
    private ImageView soundImage;

    public MainPage() {
        super();
        initialize();
    }

    public void initialize(){

        //Creating middle panel
        this.setMinHeight(HEIGHT - COPYRIGHT_PANEL_SIZE);
        this.setMinWidth(WIDTH);

        //Creating copyright panel
        copyRightPanel = new Pane();
        copyRightPanel.setMinHeight(COPYRIGHT_PANEL_SIZE);
        copyRightPanel.setMinWidth(WIDTH);

        //Copyright label
        copyRightLabel = new Label(COPYRIGHT_LABEL);

        //Version label
        version = new Label("Version b0.1");
        version.setLayoutX(WIDTH - 100);

        //Name of the game label
        name = new Label("");
        name.setStyle("-fx-font-weight: bold;");
        name.setGraphic(new ImageView("/img/rushhour.png"));
        name.setMaxSize(450,75);
        name.setFont(new Font("American Typewriter", 75));
        name.setLayoutX(150 );
        name.setLayoutY(0);

        //Creating main buttons
        int space = 0;
        frameButtons = new Button[5];
        for ( int i = 0; i < 5; i++){
            space = space + 80;
            frameButtons[i] = new Button();
            frameButtons[i].setMinSize(BUTTON_WIDTH, BUTTON_HEIGHT);
            frameButtons[i].setLayoutX(BUTTON_X);
            frameButtons[i].setLayoutY(BUTTON_Y + space);
            frameButtons[i].setStyle("-fx-background-color: transparent");
        }
        ImageView playIcon = new ImageView(new Image(PLAY_ICON));
        playIcon.setFitHeight(BUTTON_HEIGHT);
        playIcon.setFitWidth(BUTTON_WIDTH);
        frameButtons[0].setGraphic(playIcon);

        ImageView howToIcon = new ImageView(new Image(HOW_TO_ICON));
        howToIcon.setFitHeight(BUTTON_HEIGHT);
        howToIcon.setFitWidth(BUTTON_WIDTH);
        frameButtons[1].setGraphic(howToIcon);

        ImageView dashboardIcon = new ImageView(new Image(DASHBOARD_ICON));
        dashboardIcon.setFitHeight(BUTTON_HEIGHT);
        dashboardIcon.setFitWidth(BUTTON_WIDTH);
        frameButtons[2].setGraphic(dashboardIcon);

        ImageView settingsIcon = new ImageView(new Image(SETTINGS_ICON));
        settingsIcon.setFitHeight(BUTTON_HEIGHT);
        settingsIcon.setFitWidth(BUTTON_WIDTH);
        frameButtons[3].setGraphic(settingsIcon);

        ImageView creditsIcon = new ImageView(new Image(CREDITS_ICON));
        settingsIcon.setFitHeight(BUTTON_HEIGHT);
        settingsIcon.setFitWidth(BUTTON_WIDTH);
        frameButtons[4].setGraphic(creditsIcon);

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

        //Add animation to the buttons
        addAnimation(10);

        //Adding buttons to middle panel
        this.getChildren().addAll(name, frameButtons[0], frameButtons[1], frameButtons[2], frameButtons[3], frameButtons[4], soundButton);

        //Adding labels to panel
        copyRightPanel.getChildren().add(copyRightLabel);
        copyRightPanel.getChildren().add(version);
        //Default theme
        setCurrentColor(null);
    }

    public void setCurrentColor(String colorCSS){

        if (colorCSS == null){
            this.setStyle("-fx-background-color: rgba(22,24,23,0.94);");
        }else{
            this.setStyle(colorCSS);
        }
    }

    public void addHandler( GameManager.ButtonListener e) {
        frameButtons[0].addEventHandler(MouseEvent.MOUSE_CLICKED, e);
        GameManager.ButtonListener newE = e.clone();
        newE.setIndex(2);
        frameButtons[1].addEventHandler(MouseEvent.MOUSE_CLICKED, newE);
        GameManager.ButtonListener dashboard = e.clone();
        dashboard.setIndex(3);
        frameButtons[2].addEventHandler(MouseEvent.MOUSE_CLICKED, dashboard);
        GameManager.ButtonListener settings = e.clone();
        settings.setIndex(4);
        frameButtons[3].addEventHandler(MouseEvent.MOUSE_CLICKED, settings);
        GameManager.ButtonListener credits = e.clone();
        credits.setIndex(5);
        frameButtons[4].addEventHandler(MouseEvent.MOUSE_CLICKED, credits);

    }

    public void addAnimation(int factor) {
        for ( int i = 0; i < 4; i++ ) {
            frameButtons[i].setOnMouseEntered(new GameManager.Animation(frameButtons[i], factor, true));
            frameButtons[i].setOnMouseExited(new GameManager.Animation(frameButtons[i], factor, false));
        }
        soundButton.setOnMouseEntered(new GameManager.Animation(soundButton, factor, true));
        soundButton.setOnMouseExited(new GameManager.Animation(soundButton, factor, false));
    }

}
