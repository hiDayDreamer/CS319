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
    private final String TEAM_IMAGE = "/img/team.png";
    private final String COPYRIGHT_LABEL = "Developed by Royal Flush";
    private final double WIDTH  = 1080;
    private final double HEIGHT = 720;
    private final double COPYRIGHT_PANEL_SIZE = 60;
    private final int ICON_SIZE = 64;

    //Variables
    int tutorialCount;
    int counter;

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
    private ImageView[] tutorialImage;
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
        ImageView labelImage = new ImageView("/img/tutorial.png");
        labelImage.setFitWidth(400);
        labelImage.setFitHeight(60);
        settings.setGraphic(labelImage);

        settings.setMaxSize(450,75);
        settings.setFont(new Font("Verdana", 45));
        settings.setLayoutX(325);
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
<<<<<<< HEAD:unstable/workplace/Hygerta/CreditsPane.java
        tutorialCount = 1;
        tutorialImage = new Image[tutorialCount];
        tutorialImage[0] = new Image(getClass().getResourceAsStream(TEAM_IMAGE), 800, 400, true, false);
        tutorial = new ImageView(tutorialImage[0]);
        tutorial.setLayoutX(WIDTH / 2 - 400);
        tutorial.setLayoutY(HEIGHT / 2 - 170);
//        tutorial.setFitHeight(500);
=======
        tutorialImage = new ImageView[10];
        tutorialImage[0] = new ImageView("/img/start.png");
        tutorialImage[1] = new ImageView("/img/tutorial_1.png");
        tutorialImage[2] = new ImageView("/img/tutorial_2.png");
        tutorialImage[3] = new ImageView("/img/tutorial_3.png");
        tutorialImage[4] = new ImageView("/img/tutorial_4.png");
        tutorialImage[5] = new ImageView("/img/tutorial_5.png");
        tutorialImage[6] = new ImageView("/img/tutorial_6.png");
        tutorialImage[7] = new ImageView("/img/tutorial_7.png");
        tutorialImage[8] = new ImageView("/img/tutorial_8.png");
        tutorialImage[9] = new ImageView("/img/finish.png");
        for (int i = 0; i < 10; i++){
            tutorialImage[i].setLayoutX(WIDTH / 2 - 275);
            tutorialImage[i].setLayoutY(HEIGHT / 2 - 240);
            tutorialImage[i].setFitHeight(500);
            tutorialImage[i].setFitWidth(500);
        }
        tutorial = tutorialImage[0];

        //Next and back button for tutorial images
        pagePassButtons = new Button[2];
        pagePassImages = new Image[2];
        pagePassImages[0] = new Image(getClass().getResourceAsStream("/img/play-symbol.png"));
        pagePassImages[1] = new Image(getClass().getResourceAsStream("/img/left-angle-bracket.png"));
        for ( int i = 0; i < 2; i++){
            pagePassButtons[i] = new Button();
            pagePassButtons[i].setMinSize(ICON_SIZE, ICON_SIZE);
            pagePassButtons[i].setGraphic(new ImageView(pagePassImages[i]));
            //backButton[i].setStyle("-fx-background-color: transparent");
            pagePassButtons[i].setLayoutY(HEIGHT / 2 - 75);

        }
        pagePassButtons[0].setLayoutX(WIDTH - 200);
        pagePassButtons[1].setLayoutX(75);
>>>>>>> e4b80f7930a45f705d7bbf135606d4317afad402:unstable/workplace/baris/src/UI/How_To.java

        addAnimation(10);
        //Adding buttons to middle panel
<<<<<<< HEAD:unstable/workplace/Hygerta/CreditsPane.java
        this.getChildren().addAll(settings, soundButton, settingsButton, backButton, tutorial);
=======
        //How_To Panel ends
>>>>>>> e4b80f7930a45f705d7bbf135606d4317afad402:unstable/workplace/baris/src/UI/How_To.java

        this.getChildren().addAll(settings, soundButton, settingsButton, backButton, tutorial, pagePassButtons[0], pagePassButtons[1]);
        counter = 0;
        pagePassButtons[0].addEventHandler(MouseEvent.MOUSE_CLICKED,
                e -> {
                    counter++;
                    if(counter < 10) {
                        getChildren().remove(tutorial);
                        tutorial = tutorialImage[counter];
                        getChildren().add(tutorial);
                    }else{
                        counter = 0;
                    }
                });

        pagePassButtons[1].addEventHandler(MouseEvent.MOUSE_CLICKED,
                e -> {
                    counter--;
                    if(counter > 0) {
                        getChildren().remove(tutorial);
                        tutorial = tutorialImage[counter];
                        getChildren().add(tutorial);
                    }else{
                        counter = 10;
                    }
                });

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


    public void addHandler( GameManager.ButtonListener e) {
        backButton.addEventHandler(MouseEvent.MOUSE_CLICKED, e);
        GameManager.ButtonListener settings = e.clone();
        settings.setIndex(4);
        settingsButton.addEventHandler(MouseEvent.MOUSE_CLICKED, settings);
    }
    public void addAnimation(int factor) {
        soundButton.setOnMouseEntered(new GameManager.Animation(soundButton, factor, true));
        soundButton.setOnMouseExited(new GameManager.Animation(soundButton, factor, false));
        settingsButton.setOnMouseEntered(new GameManager.Animation(settingsButton, factor, true));
        settingsButton.setOnMouseExited(new GameManager.Animation(settingsButton, factor, false));
        backButton.setOnMouseEntered(new GameManager.Animation(backButton, factor, true));
        backButton.setOnMouseExited(new GameManager.Animation(backButton, factor, false));
    }
}
