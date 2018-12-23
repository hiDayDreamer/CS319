import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.control.Label;

public class WindowManager extends Stage{

    //Constants
    private final boolean IS_RESIZABLE = false;
    private final String FRAME_TITLE = "Fun for all ages";
    private final String GAME_ICON_LOG = "/img/cards.png";
    private final String COPYRIGHT_LABEL = "Developed by Royal Flush";
    private final double WIDTH  = 1080;
    private final double HEIGHT = 720;
    private final double COPYRIGHT_PANEL_SIZE = 60;

    //Variables
    private Label copyRightLabel;
    private Label version;
    private GridPane frame;
    private Pane middlePanel;
    private Pane copyRightPanel;
    private Scene window;
    private MainPage mainPane = new MainPage();

    public WindowManager() {
        super();
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
        middlePanel = mainPane;

        //Creating copyright panel
        copyRightPanel = new Pane();
        copyRightPanel.setStyle("-fx-background-color: black");
        copyRightPanel.setMinHeight(COPYRIGHT_PANEL_SIZE);
        copyRightPanel.setMinWidth(WIDTH);

        //Copyright label
        copyRightLabel = new Label(COPYRIGHT_LABEL);
        copyRightLabel.setLayoutX(450);
        copyRightLabel.setStyle("-fx-text-fill: white");

        //Version label
        version = new Label("Version Release");
        version.setStyle("-fx-text-fill: white");
        version.setLayoutX(WIDTH - 115);

        //Adding labels to panel
        frame.addRow(1, copyRightPanel);
        copyRightPanel.getChildren().add(copyRightLabel);
        copyRightPanel.getChildren().add(version);

        //Adding panels to frame
        frame.addRow(0, middlePanel);
        this.updateMiddlePanel(mainPane);

        //Default theme
        setCurrentColor("");
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
        if (colorCSS == ""){
            middlePanel.setStyle("-fx-background-color: rgba(91,106,183,0.69);");
        }else{
            middlePanel.setStyle(colorCSS);
        }
    }

    public void addHandler( GameManager.ButtonListener e) {
        mainPane.addHandler(e);
    }
}
