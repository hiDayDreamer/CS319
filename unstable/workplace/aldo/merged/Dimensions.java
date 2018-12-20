import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.input.MouseEvent;

public class Dimensions extends Pane {
    //Constants
    private final String SOUND_ICON = "/img/soundIcon.png";
    private final String SETTINGS_ICON = "/img/Settings-icon.png";
    private final String BACK_ICON = "/img/backIcon.png";
    private final String COPYRIGHT_LABEL = "Developed by Royal Flush";
    private final double WIDTH  = 1080;
    private final double HEIGHT = 720;
    private final double COPYRIGHT_PANEL_SIZE = 60;
    private final int ICON_SIZE = 64;

    //Variables
    //Labels
    private Label copyRightLabel;
    private Pane copyRightPanel;
    private Label version;
    private Label[] leftAt;
    private Label[] numberOfStarsLabel;

    //Buttons
    private Button soundButton;
    private Button settingsButton;
    private Button backButton;
    private Button[] sixToSix;

    //Images
    private ImageView soundImage;
    private ImageView settingsImage;
    private Image backImage;
    private Image[] stars;
    private ImageView fullStar;

    //ProgressBar
    private ProgressBar[] dimensionProgression;

    public Dimensions() {
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
        backImage = new Image(getClass().getResourceAsStream(BACK_ICON));
        backButton = new Button();
        backButton.setGraphic(new ImageView(backImage));
        backButton.setStyle("-fx-background-color: transparent");
        backButton.setMinSize(ICON_SIZE, ICON_SIZE);
        backButton.setLayoutX(25);
        backButton.setLayoutY(25);

        //Images for stars
        stars = new Image[3];
        stars[0] = new Image(getClass().getResourceAsStream("/img/fullStar.png"));
        fullStar = new ImageView(stars[0]);
        //There is a problem here.
        ImageView fullStar2 = new ImageView(stars[0]);
        ImageView fullStar3 = new ImageView(stars[0]);

        //Left at label array
        int variable = 5;
        int variable2 = 10;
        int variable3 = 15;
        //charts.getLeftAt();
        leftAt = new Label[3];
        for ( int i = 0; i < 3; i++){
            leftAt[i] = new Label();
            leftAt[i].setStyle("-fx-border-color: black ; -fx-border-width: 3px ;");
            leftAt[i].setMinWidth(200);
        }
        leftAt[0].setText("Left at #" + variable);
        leftAt[1].setText("Left at #" + variable2);
        leftAt[2].setText("Left at #" + variable3);

        //Number of stars label array
        int numberOfStars = 5;
        int numberOfStars2 = 10;
        int numberOfStars3 = 15;
        //charts.getLeftAt();
        numberOfStarsLabel = new Label[3];
        for ( int i = 0; i < 3; i++){
            numberOfStarsLabel[i] = new Label();
            numberOfStarsLabel[i].setStyle("-fx-border-color: black ; -fx-border-width: 3px ;");
            numberOfStarsLabel[i].setMinWidth(176);
        }
        numberOfStarsLabel[0].setText("" + numberOfStars);
        numberOfStarsLabel[1].setText("" + numberOfStars2);
        numberOfStarsLabel[2].setText("" + numberOfStars3);

        //Dimensions array
        dimensionProgression = new ProgressBar[3];
        for ( int i = 0; i < 3; i++){
            dimensionProgression[i] = new ProgressBar();
            dimensionProgression[i].setStyle("-fx-accent: #914b28 ; -fx-border-color: black ; -fx-border-width: 3px ;");
            dimensionProgression[i].setMinWidth(200);
        }
        //charts.getProgression();
        dimensionProgression[0].setProgress(0.5);
        dimensionProgression[1].setProgress(0.7);
        dimensionProgression[2].setProgress(1);

        //Buttons array
        sixToSix = new Button[3];
        for ( int i = 0; i < 3; i++){
            sixToSix[i] = new Button();
            sixToSix[i].setStyle("-fx-border-color: black ; -fx-border-width: 3px ; -fx-font-family: 'Verdana'; -fx-font-weight: bold; -fx-font-size: 25pt ;");
            sixToSix[i].setStyle("-fx-background-color: transparent");
            sixToSix[i].setMinSize(200, (HEIGHT - 175) / 2);
        }

        sixToSix[0].setStyle("-fx-background-image: url('/img/dim1.png')");

        sixToSix[1].setStyle("-fx-background-image: url('/img/dim2.png')");

        sixToSix[2].setStyle("-fx-background-image: url('/img/dim3.png')");

        VBox vBox = new VBox(0); // spacing = 8
        VBox vBox2 = new VBox(0); // spacing = 8
        VBox vBox3 = new VBox(0); // spacing = 8

        HBox hBox = new HBox(0); // spacing = 8
        HBox hBox2 = new HBox(0); // spacing = 8
        HBox hBox3 = new HBox(0); // spacing = 8

        HBox panel = new HBox((WIDTH - 600) / 4); // spacing = 8
        panel.setLayoutX((WIDTH - 650) / 4);
        panel.setLayoutY(HEIGHT / 2 - 200);

        hBox.getChildren().addAll(fullStar, numberOfStarsLabel[0]);
        vBox.getChildren().addAll(dimensionProgression[0], sixToSix[0], leftAt[0], hBox);
        hBox2.getChildren().addAll(fullStar2, numberOfStarsLabel[1]);
        vBox2.getChildren().addAll(dimensionProgression[1], sixToSix[1], leftAt[1], hBox2);
        hBox3.getChildren().addAll(fullStar3, numberOfStarsLabel[2]);
        vBox3.getChildren().addAll(dimensionProgression[2], sixToSix[2], leftAt[2], hBox3);
        panel.getChildren().addAll(vBox, vBox2, vBox3);

        //Adding buttons to middle panel
        this.getChildren().addAll(soundButton, settingsButton, backButton, panel);
        //How_To Panel ends

        //Adding labels to panel
        copyRightPanel.getChildren().add(copyRightLabel);
        copyRightPanel.getChildren().add(version);

        //Default theme
        setCurrentColor(null);
    }

    public void setCurrentColor(String colorCSS){

        if (colorCSS == null){
            this.setStyle("-fx-background-color: #81aae6;");
        }else{
            this.setStyle(colorCSS);
        }
    }


    public void addHandler( GameManager.ButtonListener e) {
              // add the button listener to the back button
              backButton.addEventHandler(MouseEvent.MOUSE_CLICKED, e);

              // add button listeners to all the dimensions
              GameManager.ButtonListener dim1 = e.clone();
              GameManager.ButtonListener dim2 = e.clone();
              GameManager.ButtonListener dim3 = e.clone();
              dim1.setIndex(6);
              dim2.setIndex(8);
              dim3.setIndex(10);
              sixToSix[0].addEventHandler(MouseEvent.MOUSE_CLICKED, dim1);
              sixToSix[1].addEventHandler(MouseEvent.MOUSE_CLICKED, dim2);
              sixToSix[2].addEventHandler(MouseEvent.MOUSE_CLICKED, dim3);


              GameManager.ButtonListener settings = e.clone();
              settings.setIndex(4);
              settingsButton.addEventHandler(MouseEvent.MOUSE_CLICKED, settings);
     }
}
