import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import java.io.IOException;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public class MainPage extends Pane{

    //Constants
    private final boolean IS_RESIZABLE = false;
    private final String FRAME_TITLE = "Fun for all ages";
    private final String GAME_ICON_LOG = "/img/java_318-32027.jpg";
    private final String PLAY_ICON = "/img/PLAY.png";
    private final String HOW_TO_ICON = "/img/HOW_TO.png";
    private final String DASHBOARD_ICON = "/img/DASHBOARD.png";
    private final String SETTINGS_ICON = "/img/SETTINGS.png";
    private final String SOUND_ICON = "/img/soundIcon.png";
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
        name = new Label(GAME_TITLE);
        name.setStyle("-fx-font-weight: bold;");
        name.setMaxSize(450,75);
        name.setFont(new Font("American Typewriter", 75));
        name.setLayoutX(WIDTH / 2 - 165 );
        name.setLayoutY(75);

        //Creating buttons
        int space = 0;
        frameButtons = new Button[4];
        for ( int i = 0; i < 4; i++){
            space = space + 100;
            frameButtons[i] = new Button();
            frameButtons[i].setMinSize(BUTTON_WIDTH, BUTTON_HEIGHT);
            frameButtons[i].setLayoutX(BUTTON_X);
            frameButtons[i].setLayoutY(BUTTON_Y + space);
            frameButtons[i].setStyle("-fx-background-color: transparent");
        }
        //frameButtons[0].setText("");
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

              frameButtons[0].setOnMouseEntered(new EventHandler<MouseEvent>(){
                   public void handle(MouseEvent e){
                       //System.out.println("asdfads");
                       (( ImageView)frameButtons[0].getGraphic()).setFitWidth(BUTTON_WIDTH + 10);
                       (( ImageView)frameButtons[0].getGraphic()).setFitHeight(BUTTON_HEIGHT + 10);
                       //button.setGraphic(new ImageView(new Image("fullStar.png")));
                   }
              });

              frameButtons[0].setOnMouseExited(new EventHandler<MouseEvent>(){
                   public void handle(MouseEvent e){
                       //System.out.println("asdfads");
                       (( ImageView)frameButtons[0].getGraphic()).setFitWidth(BUTTON_WIDTH);
                       (( ImageView)frameButtons[0].getGraphic()).setFitHeight(BUTTON_HEIGHT);
                       //button.setGraphic(new ImageView(new Image("fullStar.png")));
                   }
              });

                            frameButtons[1].setOnMouseEntered(new EventHandler<MouseEvent>(){
                                 public void handle(MouseEvent e){
                                     //System.out.println("asdfads");
                                     (( ImageView)frameButtons[1].getGraphic()).setFitWidth(BUTTON_WIDTH + 10);
                                     (( ImageView)frameButtons[1].getGraphic()).setFitHeight(BUTTON_HEIGHT + 10);
                                     //button.setGraphic(new ImageView(new Image("fullStar.png")));
                                 }
                            });

                            frameButtons[1].setOnMouseExited(new EventHandler<MouseEvent>(){
                                 public void handle(MouseEvent e){
                                     //System.out.println("asdfads");
                                     (( ImageView)frameButtons[1].getGraphic()).setFitWidth(BUTTON_WIDTH);
                                     (( ImageView)frameButtons[1].getGraphic()).setFitHeight(BUTTON_HEIGHT);
                                     //button.setGraphic(new ImageView(new Image("fullStar.png")));
                                 }
                            });
                                          frameButtons[2].setOnMouseEntered(new EventHandler<MouseEvent>(){
                                               public void handle(MouseEvent e){
                                                   //System.out.println("asdfads");
                                                   (( ImageView)frameButtons[2].getGraphic()).setFitWidth(BUTTON_WIDTH + 10);
                                                   (( ImageView)frameButtons[2].getGraphic()).setFitHeight(BUTTON_HEIGHT + 10);
                                                   //button.setGraphic(new ImageView(new Image("fullStar.png")));
                                               }
                                          });

                                          frameButtons[2].setOnMouseExited(new EventHandler<MouseEvent>(){
                                               public void handle(MouseEvent e){
                                                   //System.out.println("asdfads");
                                                   (( ImageView)frameButtons[2].getGraphic()).setFitWidth(BUTTON_WIDTH);
                                                   (( ImageView)frameButtons[2].getGraphic()).setFitHeight(BUTTON_HEIGHT);
                                                   //button.setGraphic(new ImageView(new Image("fullStar.png")));
                                               }
                                          });
                                                        frameButtons[3].setOnMouseEntered(new EventHandler<MouseEvent>(){
                                                             public void handle(MouseEvent e){
                                                                 //System.out.println("asdfads");
                                                                 (( ImageView)frameButtons[3].getGraphic()).setFitWidth(BUTTON_WIDTH + 10);
                                                                 (( ImageView)frameButtons[3].getGraphic()).setFitHeight(BUTTON_HEIGHT + 10);
                                                                 //button.setGraphic(new ImageView(new Image("fullStar.png")));
                                                             }
                                                        });

                                                        frameButtons[3].setOnMouseExited(new EventHandler<MouseEvent>(){
                                                             public void handle(MouseEvent e){
                                                                 //System.out.println("asdfads");
                                                                 (( ImageView)frameButtons[3].getGraphic()).setFitWidth(BUTTON_WIDTH);
                                                                 (( ImageView)frameButtons[3].getGraphic()).setFitHeight(BUTTON_HEIGHT);
                                                                 //button.setGraphic(new ImageView(new Image("fullStar.png")));
                                                             }
                                                        });
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

        //Adding buttons to middle panel
        this.getChildren().addAll(name, frameButtons[0], frameButtons[1], frameButtons[2], frameButtons[3], soundButton);

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
        frameButtons[0].addEventHandler(MouseEvent.MOUSE_CLICKED, e);
        GameManager.ButtonListener newE = e.clone();
        newE.setIndex(2);
        frameButtons[1].addEventHandler(MouseEvent.MOUSE_CLICKED, newE);
        frameButtons[2].addEventHandler(MouseEvent.MOUSE_CLICKED, newE);
        GameManager.ButtonListener settings = e.clone();
        settings.setIndex(4);
        frameButtons[3].addEventHandler(MouseEvent.MOUSE_CLICKED, settings);

    }

}
