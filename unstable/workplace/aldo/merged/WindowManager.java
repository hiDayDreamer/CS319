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

public class WindowManager extends Stage{

    //Constants
    private final boolean IS_RESIZABLE = false;
    private final String FRAME_TITLE = "Fun for all ages";
    private final String GAME_ICON_LOG = "/img/java_318-32027.jpg";
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
    private GridPane frame;
    private Pane middlePanel;
    private Pane copyRightPanel;
    private Scene window;
    private Image soundImage;
    private MainPage g = new MainPage();

    public WindowManager() {
        super();
        try {
            Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        initialize();
        this.setTitle(FRAME_TITLE);
        this.setScene(window);
        this.setHeight(HEIGHT);
        this.setWidth(WIDTH);
        this.setResizable(IS_RESIZABLE);

        //Set game icon
        this.getIcons().add(new Image(getClass().getResourceAsStream(GAME_ICON_LOG)));
        this.show();
    }

    public void initialize(){

        //Creating the frame
        frame = new GridPane();

        //Create new scene
        window = new Scene(frame);

        //Creating middle panel
        middlePanel = g;

        //Creating copyright panel
        copyRightPanel = new Pane();
        copyRightPanel.setMinHeight(COPYRIGHT_PANEL_SIZE);
        copyRightPanel.setMinWidth(WIDTH);

        //Copyright label
        copyRightLabel = new Label(COPYRIGHT_LABEL);

        //Version label
        version = new Label("Version b0.1");
        version.setLayoutX(WIDTH - 100);

        //Adding labels to panel
        frame.addRow(1, copyRightPanel);
        copyRightPanel.getChildren().add(copyRightLabel);
        copyRightPanel.getChildren().add(version);

        //Adding panels to frame
        frame.addRow(0, middlePanel);
        this.updateMiddlePanel(g);
        //Default theme
        setCurrentColor(null);
    }

    public boolean updateMiddlePanel(Pane newPane){

        if ( middlePanel == null){
            System.out.println("hope");
            middlePanel = newPane;
            frame.addRow(0, middlePanel);
            return true;
        } else {
            System.out.println("hope");
            frame.getChildren().remove(middlePanel);
            middlePanel = newPane;
            frame.addRow(0, middlePanel);
            return true;
        }
    }

    public void setCurrentColor(String colorCSS){

        if (colorCSS == null){
            middlePanel.setStyle("-fx-background-color: lightblue;");
        }else{
            middlePanel.setStyle(colorCSS);
        }
    }

    public void addHandler( GameManager.ButtonListener e) {
        g.addHandler(e);
        //frameButtons[0].addEventHandler(MouseEvent.MOUSE_CLICKED, e);
    }
}
