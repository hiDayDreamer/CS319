

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

public class WindowManager extends Stage {

    //Constants
    private final boolean IS_RESIZABLE = false;
    private final String FRAME_TITLE = "Fun for all ages";
    private final String GAME_ICON_LOG = "java_318-32027.jpg";
    private final String SOUND_ICON = "/img/soundImage.png";
    private final String COPYRIGHT_LABEL = "Developed by Royal Flush";
    private final String GAME_TITLE = "Rush Hour";
    private final double WIDTH  = 1080;
    private final double HEIGHT = 720;
    private final double COPYRIGHT_PANEL_SIZE = 60;
    private final double BUTTON_X = WIDTH / 2 - 125;
    private final double BUTTON_Y = 125;
    private final double BUTTON_WIDTH = 300;
    private final double BUTTON_HEIGHT = 100;
    private final int ICON_SIZE = 64;

    //Variables
    private Label copyRightLabel;
    private Label name;
    private Label version;
    private Button[] frameButtons;
    private Button soundButton;
    private GridPane frame;
    private Pane middlePanel;
    private Pane copyRightPanel;
    private Scene window;
    private Image soundImage;


    public WindowManager() {
        super();
        /*try {
            Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        initialize();
        this.setTitle(FRAME_TITLE);
        this.setScene(window);
        this.setHeight(HEIGHT);
        this.setWidth(WIDTH);
        this.setResizable(IS_RESIZABLE);

        //Set game icon
       // this.getIcons().add(new Image(getClass().getResourceAsStream(GAME_ICON_LOG)));
        this.show();
    }

    public void initialize(){

        //Creating the frame
        frame = new GridPane();

        //Create new scene
        window = new Scene(frame);

        //Creating middle panel
        middlePanel = new Pane();
        middlePanel.setMinHeight(HEIGHT - COPYRIGHT_PANEL_SIZE);
        middlePanel.setMinWidth(WIDTH);

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
        name.setMaxSize(500,100);
        name.setFont(new Font("Times New Roman", 90));
        name.setLayoutX(WIDTH / 2 - 175 );
        name.setLayoutY(75);

        //Creating buttons
        int space = 0;
        frameButtons = new Button[4];
        for ( int i = 0; i < 4; i++){
            space = space + 150;
            frameButtons[i] = new Button();
            frameButtons[i].setMinSize(BUTTON_WIDTH, BUTTON_HEIGHT);
            frameButtons[i].setLayoutX(BUTTON_X);
            frameButtons[i].setLayoutY(BUTTON_Y + space);
        }
        frameButtons[0].setText("Play");
        frameButtons[1].setText("How To");
        frameButtons[2].setText("Dashboard");
        frameButtons[3].setText("Settings");

        //Sound button
        soundImage = new Image(getClass().getResourceAsStream(SOUND_ICON));
        soundButton = new Button("" , new ImageView(soundImage));
        soundButton.setGraphic(new ImageView(soundImage));
        //soundButton.setStyle("-fx-background-color: transparent");
        soundButton.setMinSize(ICON_SIZE, ICON_SIZE);
        soundButton.setLayoutX(WIDTH - 100);
        soundButton.setLayoutY(25);

        //Adding buttons to middle panel
        middlePanel.getChildren().addAll(name, frameButtons[0], frameButtons[1], frameButtons[2], frameButtons[3], soundButton);

        //Adding labels to panel
        frame.addRow(1, copyRightPanel);
        copyRightPanel.getChildren().add(copyRightLabel);
        copyRightPanel.getChildren().add(version);

        //Adding panels to frame
        frame.addRow(0, middlePanel);

        //Default theme
        setCurrentColor(null);
    }

    public boolean updateMiddlePanel(Pane newPane){

        System.out.println("hop");
        frame.getChildren().remove(middlePanel);
        middlePanel = newPane;
        frame.addRow(0, middlePanel);
        return true;

    }

    public void setCurrentColor(String colorCSS){

        if (colorCSS == null){
            middlePanel.setStyle("-fx-background-color: lightblue;");
        }else{
            middlePanel.setStyle(colorCSS);
        }
    }


}
