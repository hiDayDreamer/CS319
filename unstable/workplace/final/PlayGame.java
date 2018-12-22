import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.layout.GridPane;
import javafx.scene.control.Label;
import javafx.geometry.Insets;
import javafx.scene.text.*;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.*;

public class PlayGame extends Pane implements TimerRunnable {
    //Constants
    private final String SOUND_ICON = "/img/soundIcon.png";
    private final String SETTINGS_ICON = "/img/Settings-icon.png";
    private final String BACK_ICON = "/img/backIcon.png";
    private final String UNDO_ICON = "/img/undo.png";
    private final String RESET_ICON = "/img/reset.png";
    private final String HINT_ICON = "/img/hint.png";
    private final String COPYRIGHT_LABEL = "Developed by Royal Flush";
    private final String BU_ICON = "/img/bomb.png";
    private final String SHRINK_ICON = "/img/shrinkCarIcon.png";
    private final String ROTATE_ICON = "/img/rotateCarIcon.png";

    private final double WIDTH  = 1080;
    private final double HEIGHT = 720;
    private final double COPYRIGHT_PANEL_SIZE = 60;
    private final int ICON_SIZE = 64;
    private final int GRIDBOX = 90;

    //Variables
    //Labels
    private Label copyRightLabel;
    private Pane copyRightPanel;
    private GridPane playGameSubpanel;
    private Label version;
    private Pane box;

    //Buttons
    private Button soundButton;
    private Button settingsButton;
    private Button backButton;
    private Button undoButton;
    private Button resetButton;
    private Button howToButton;
    private Button startButton;
    private Button blowUpButton;
    private Button shrinkButton;
    private Button rotate;
    private Button changeExit;
    private Button goBackMenuButton;

    //Images
    //private Image soundImage;
    //private Image settingsImage;
    //private Image backImage;
    //private Image undoImage;
    //private Image resetImage;
    //private Image howToImage;

    private Map gameMap;
    private Car[] cars;
    private boolean timerMode;
    private Block[][] blocks;
    private Pane carsPane;
    private int[] stars;
    private double volume;
    private int dimension;
    private int gridBoxSize;
    private Label timerCountdown;
    int noSeconds = 5;
    //private int shrinkCount;
    public PlayGame(Map map,boolean mode,int dim) {
        super();
        gameMap = map;
        timerMode = mode;
        volume = 0;
        dimension = dim;
        initialize();
    }

    public void initialize(){
        //Creating middle panel
        this.setMinHeight(HEIGHT - COPYRIGHT_PANEL_SIZE);
        this.setMinWidth(WIDTH);
        //shrinkCount = 0;
        blocks = gameMap.getBlocks();
        //Creating copyright panel
        copyRightPanel = new Pane();

        //Copyright label
        copyRightLabel = new Label(COPYRIGHT_LABEL);

        //Version label
        version = new Label("Version b0.1");

        //How_To Panel
        ImageView soundImage = new ImageView(new Image(SOUND_ICON));
        soundImage.setFitWidth(ICON_SIZE);
        soundImage.setFitHeight(ICON_SIZE);
        soundButton = new Button();
        soundButton.setStyle("-fx-background-color: transparent");
        soundButton.setMinSize(ICON_SIZE,ICON_SIZE);
        soundButton.setLayoutX(WIDTH - 100);
        soundButton.setLayoutY(25);

        soundButton.setGraphic(soundImage);

        settingsButton = new Button();
        settingsButton.setStyle("-fx-background-color: transparent");
        settingsButton.setMinSize(ICON_SIZE, ICON_SIZE);
        settingsButton.setLayoutX(WIDTH - 200);
        settingsButton.setLayoutY(25);
        settingsButton.setGraphic(new ImageView(SETTINGS_ICON));

        backButton = new Button();
        backButton.setStyle("-fx-background-color: transparent");
        backButton.setMinSize(ICON_SIZE, ICON_SIZE);
        backButton.setLayoutX(25);
        backButton.setLayoutY(25);
        backButton.setGraphic(new ImageView(BACK_ICON));

        undoButton = new Button();
        undoButton.setStyle("-fx-background-color: transparent");
        undoButton.setMinSize(ICON_SIZE, ICON_SIZE);
        undoButton.setLayoutX(150);
        undoButton.setLayoutY(300);
        undoButton.setGraphic(new ImageView(UNDO_ICON));

        resetButton = new Button();
        resetButton.setGraphic(new ImageView(RESET_ICON));
        resetButton.setStyle("-fx-background-color: transparent");
        resetButton.setMinSize(ICON_SIZE, ICON_SIZE);
        resetButton.setLayoutX(150);
        resetButton.setLayoutY(500);
        //reset
        Label resetLabel = new Label("Reset");
        resetLabel.setLayoutX(160);
        resetLabel.setLayoutY(580);
        resetLabel.setFont(new Font(20));

        //undo
        Label undoLabel = new Label("Undo");
        undoLabel.setLayoutX(160);
        undoLabel.setLayoutY(380);
        undoLabel.setFont(new Font(20));

        //hint
        Label hintLabel = new Label("Hint");
        hintLabel.setLayoutX(WIDTH-190);
        hintLabel.setLayoutY(580);
        hintLabel.setFont(new Font(20));

        howToButton = new Button();
        howToButton.setGraphic(new ImageView(HINT_ICON));
        howToButton.setStyle("-fx-background-color: transparent");
        howToButton.setMinSize(ICON_SIZE, ICON_SIZE);
        howToButton.setLayoutX(WIDTH-200);
        howToButton.setLayoutY(500);
        howToButton.setAlignment(Pos.CENTER);

        blowUpButton = new Button();
        blowUpButton.setGraphic(new ImageView(BU_ICON));
        blowUpButton.setStyle("-fx-background-color: transparent");
        blowUpButton.setMinSize(ICON_SIZE, ICON_SIZE);
        Label blowUpLabel = new Label("Blow up");
        blowUpLabel.setLayoutX(WIDTH-190);
        blowUpLabel.setLayoutY(380);
        blowUpLabel.setFont(new Font(20));

        shrinkButton = new Button();
        shrinkButton.setGraphic(new ImageView(SHRINK_ICON));
        shrinkButton.setStyle("-fx-background-color: transparent");
        shrinkButton.setMinSize(ICON_SIZE, ICON_SIZE);
        Label shrinkLabel = new Label("Shrink");
        shrinkLabel.setLayoutX(WIDTH-190);
        shrinkLabel.setLayoutY(260);
        shrinkLabel.setFont(new Font(20));

        rotate = new Button();
        rotate.setGraphic(new ImageView(ROTATE_ICON));
        rotate.setStyle("-fx-background-color: transparent");
        rotate.setMinSize(ICON_SIZE, ICON_SIZE);

        HBox shrinkRotate = new HBox(25);
        shrinkRotate.getChildren().addAll(shrinkButton, rotate);



        Label rotateLabel = new Label("Rotate Car");
        rotateLabel.setLayoutX(WIDTH-90);
        rotateLabel.setLayoutY(340);
        rotateLabel.setFont(new Font(20));
/*
        changeExit = new Button();
        ImageView exit = new ImageView("/img/emergency-sign.png");
        exit.setFitWidth(ICON_SIZE);
        exit.setFitHeight(ICON_SIZE);
        changeExit.setGraphic(exit);
        changeExit.setStyle("-fx-background-color: transparent");
        changeExit.setMinSize(ICON_SIZE, ICON_SIZE);*/

        Label changeExitLabel = new Label("Change Exit");
        changeExitLabel.setLayoutX(WIDTH-90);
        changeExitLabel.setLayoutY(470);
        changeExitLabel.setFont(new Font(20));

        HBox blowChange = new HBox(25);
        blowChange.getChildren().addAll(blowUpButton, howToButton);

        VBox shrinkBlow = new VBox(30);
        shrinkBlow.setLayoutX(50);
        shrinkBlow.setLayoutY(250);
        //shrinkBlow.setMinSize(500,500);
        shrinkBlow.setStyle("-fx-border-color: black; -fx-border-width: 1px; -fx-border-radius: 50px 50 50px 50px; -fx-padding: 10px");
        shrinkBlow.getChildren().addAll(shrinkRotate, blowChange );

        VBox undoReset = new VBox(30);
        undoReset.setLayoutX(880);
        undoReset.setLayoutY(250);
        undoReset.setStyle("-fx-border-color: black; -fx-border-width: 1px; -fx-border-radius: 50px 50 50px 50px; -fx-padding: 10px");
        undoReset.getChildren().addAll(undoButton, resetButton);


        BorderPane title = new BorderPane();
        Label royalFlush = new Label("Welcome to the Royal Flush experience!");
        royalFlush.setLayoutX(250);
        royalFlush.setLayoutY(10);
        royalFlush.setFont(new Font(30));

        timerCountdown = new Label("" + noSeconds);
        timerCountdown.setLayoutX(100);
        timerCountdown.setLayoutY(200);
        timerCountdown.setFont(new Font(30));

        startButton = new Button();
        //startButton.setStyle("-fx-background-color: transparent");


        playGameSubpanel = buildGrid(new Insets(90,0,0,300));
         startButton.setMinSize(gridBoxSize*getCurrentDimensionSize(),gridBoxSize*getCurrentDimensionSize());
        startButton.setLayoutX(300);
        startButton.setLayoutY(90);
        if (timerMode){
            getChildren().addAll( royalFlush, soundButton, settingsButton, playGameSubpanel, backButton, timerCountdown,startButton, shrinkBlow, undoReset);
        } else {
            getChildren().addAll( royalFlush, soundButton, settingsButton, playGameSubpanel, backButton, shrinkBlow, undoReset);
        }


        //Adding labels to panel
        copyRightPanel.getChildren().add(copyRightLabel);
        copyRightPanel.getChildren().add(version);

        //Default theme
        //setCurrentColor(null);
    }

    public void setStars(int[] stars) {
        this.stars = stars;
    }

    private GridPane buildGrid(Insets constraints){
        playGameSubpanel = new GridPane();
        playGameSubpanel.setPadding(constraints);

        Block[][] mapBlocks = gameMap.getBlocks();
        cars = gameMap.getCars();

        String loc = "/img/grass.jpg";
        setCurrentDimensionSize(dimension);

        box = new Pane();
        
        box.setStyle("-fx-background-color: black, -fx-control-inner-background; -fx-background-insets: 0, 2; -fx-padding:2;");

        for (int rowIndex = 0; rowIndex < getCurrentDimensionSize(); rowIndex++){
            for (int columnIndex = 0; columnIndex < getCurrentDimensionSize(); columnIndex++){
                Image img = new Image("/img/brick.jpg");
                ImageView possibleCar = new ImageView(img);
                possibleCar.setFitWidth(gridBoxSize-1);
                possibleCar.setFitHeight(gridBoxSize-1);
                possibleCar.setLayoutX(gridBoxSize * columnIndex);
                possibleCar.setLayoutY(gridBoxSize * rowIndex);
                box.getChildren().add(possibleCar);
            }
        }

        Image immg = new Image("/img/exit.png");
        ImageView exit = new ImageView(immg);
        exit.setFitWidth(gridBoxSize-1);
        exit.setFitHeight(gridBoxSize-1);
        exit.relocate(5*gridBoxSize, 3*gridBoxSize);
        box.getChildren().add(exit);

        box.setStyle("-fx-background-color: black");
        carsPane = new Pane();
        int direction = -1;
        for (int carIndex = 0 ; carIndex < cars.length; carIndex++){
            direction = cars[carIndex].getCarDirection();
            loc = cars[carIndex].getImageLocation();
            Image img = new Image(loc+"-"+(direction%2)+".png");
            ImageView possibleCar = new ImageView(img);

            if (direction == 3 || direction == 2) {
                possibleCar.setRotate(180);
            }
            possibleCar.setOnMousePressed(new GameManager.MoveCar(possibleCar));

            possibleCar.addEventHandler(MouseEvent.MOUSE_DRAGGED, new GameManager.MouseListener(possibleCar));
            possibleCar.setOnMouseReleased( new GameManager.Release(possibleCar));

            if ( direction == 1 || direction == 3){
                possibleCar.setFitWidth(gridBoxSize);
                possibleCar.setFitHeight(gridBoxSize*cars[carIndex].getLength());
                possibleCar.setLayoutX(gridBoxSize * cars[carIndex].getY());
                possibleCar.setLayoutY(gridBoxSize * cars[carIndex].getX());
                carsPane.getChildren().add(possibleCar);
            } else {
                possibleCar.setFitWidth(cars[carIndex].getLength()* gridBoxSize);
                possibleCar.setFitHeight(gridBoxSize);
                possibleCar.setLayoutX(gridBoxSize * cars[carIndex].getY());
                possibleCar.setLayoutY(gridBoxSize * cars[carIndex].getX());
                carsPane.getChildren().add(possibleCar);
            }
        }
        box.getChildren().add(carsPane);
        playGameSubpanel.getChildren().add(box);
        return playGameSubpanel;
    }


    public void setCurrentDimensionSize(int dimension){
        gridBoxSize = 540/dimension;
    }
    private int getCurrentDimensionSize(){
        return dimension;
    }

    public int getBoxSize() {
        return gridBoxSize;
    }

    public void addHandler( GameManager.ButtonListener e) {
        // add the button listener to the back button
        backButton.addEventHandler(MouseEvent.MOUSE_CLICKED, e);

        GameManager.ButtonListener settings = e.clone();
        settings.setIndex(4);
        settingsButton.addEventHandler(MouseEvent.MOUSE_CLICKED, settings);

        GameManager.ButtonListener startTime = e.clone();
        startTime.setIndex(31);
        howToButton.addEventHandler(MouseEvent.MOUSE_CLICKED, new GameManager.Hint());
        startButton.addEventHandler(MouseEvent.MOUSE_CLICKED, startTime);
        blowUpButton.addEventHandler(MouseEvent.MOUSE_CLICKED, new GameManager.BlowUp(carsPane));
        shrinkButton.addEventHandler(MouseEvent.MOUSE_CLICKED,new GameManager.Shrink(carsPane));
        rotate.addEventHandler(MouseEvent.MOUSE_CLICKED,new GameManager.RotateaCar(carsPane));
        resetButton.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>(){
            public void handle(MouseEvent e) {
                System.out.println("Reset");
            }
        });
    }


    public Car findCar(int x, int y) {
        for ( int i = 0; i < cars.length; i++ ) {
            if ( x >= cars[i].getX() && x <= cars[i].getHorizontalX() ) {
                if ( y >= cars[i].getY() && y <= cars[i].getVerticalY() )
                return cars[i];
            }
        }
        return null;
    }


    GameTimer timer;
    public void startTimer(){
        getChildren().remove(startButton);
        if (timer == null){
            timer = new GameTimer(noSeconds);
        }
        System.out.println("I am in start tiemr play game");
        timer.startCountDown(this);
    }

    public void shrinkCar(int index) {
        ImageView tempView = (ImageView) carsPane.getChildren().get(index);
        if ( tempView.getFitWidth() < tempView.getFitHeight() )
            tempView.setFitHeight(tempView.getFitHeight()-gridBoxSize);
        else
            tempView.setFitWidth(tempView.getFitWidth()-gridBoxSize);
    }
    public void rotateCar(int index,int carDirection) {
        
        ImageView tempView = (ImageView) carsPane.getChildren().get(index);
        tempView.setImage(new Image("/img/"+ index+"-" + carDirection%2+ ".png"));
        if (carDirection == 3 || carDirection == 2) {
            tempView.setRotate(180);
        }
        double temp = tempView.getFitWidth();
        double tmp2 = tempView.getFitHeight();
        tempView.setFitHeight(temp);
        tempView.setFitWidth(tmp2);
    }

    public void runOnTimer(){
        System.out.println("" + timer.getCountDown());
        noSeconds--;
        //initialize();
        Platform.runLater(() -> timerCountdown.setText("" + timer.getCountDown()));
        if (noSeconds == 0){
            Platform.runLater(() -> gameLoss());
        }
    }

    public boolean gameWon() {
        Stage popupwindow=new Stage();
        popupwindow.initModality(Modality.APPLICATION_MODAL);
        popupwindow.setTitle("Game Won");
        popupwindow.setResizable(false);
        popupwindow.setHeight(500);
        popupwindow.setWidth(700);
        popupwindow.getIcons().add(new Image(getClass().getResourceAsStream("/img/java_318-32027.png")));
        Label label1= new Label("");
        ImageView labelImage = new ImageView("/img/win.png");
        labelImage.setFitWidth(400);
        labelImage.setFitHeight(60);
        label1.setGraphic(labelImage);
        Image im = new Image("/img/win.gif");
        ImageView view = new ImageView(im);
        view.setFitWidth(400);
        view.setFitHeight(200);
        Label label2= new Label("Number of moves: ");
        goBackMenuButton= new Button("Go Back to Menu");
        goBackMenuButton.setOnAction(e -> popupwindow.close());
        VBox layout= new VBox(20);
        layout.getChildren().addAll(view, label1, label2, goBackMenuButton);
        layout.setAlignment(Pos.CENTER);
        Scene scene1= new Scene(layout, 300, 250);
        popupwindow.setScene(scene1);
        popupwindow.showAndWait();
        stars[0] = 3;
        return true;
    }


    public boolean gameLoss(){
        /*getChildren().remove(playGameSubpanel);
        Image im = new Image("/img/win.gif");
        ImageView view = new ImageView(im);
        view.setFitWidth(WIDTH - 40);
        view.setFitHeight(90*6);
        view.setLayoutX(20);
        view.setLayoutY(100);
        getChildren().add(view);*/
        Stage popupwindow=new Stage();
        popupwindow.initModality(Modality.APPLICATION_MODAL);
        popupwindow.setTitle("Game Won");
        popupwindow.setResizable(false);
        popupwindow.setHeight(500);
        popupwindow.setWidth(700);
        popupwindow.getIcons().add(new Image(getClass().getResourceAsStream("/img/java_318-32027.png")));
        Label label1= new Label("");
        ImageView labelImage = new ImageView("/img/game_over.png");
        labelImage.setFitWidth(400);
        labelImage.setFitHeight(60);
        label1.setGraphic(labelImage);
        Image im = new Image("/img/win.gif");
        ImageView view = new ImageView(im);
        view.setFitWidth(400);
        view.setFitHeight(200);
        Label label2= new Label("Number of moves: ");
        Label label3= new Label("Time: ");
        goBackMenuButton= new Button("Go Back to Menu");
        goBackMenuButton.setOnAction(e -> popupwindow.close());
        VBox layout= new VBox(20);
        layout.getChildren().addAll(view, label1, label2, label3, goBackMenuButton);
        layout.setAlignment(Pos.CENTER);
        Scene scene1= new Scene(layout, 300, 250);
        popupwindow.setScene(scene1);
        popupwindow.showAndWait();
        return true;
    }

    public void setSoundVolume(double percentage){
        volume = percentage;
    }

    int moveForward;
    int moveBackward;
    public void setMaxMoves(int moveForward, int moveBackward) {
        this.moveForward = moveForward;
        this.moveBackward = moveBackward;
    }

    public void rebuildGrid() {
        for (int rowIndex = 0; rowIndex < getCurrentDimensionSize(); rowIndex++){
            for (int columnIndex = 0; columnIndex < getCurrentDimensionSize(); columnIndex++){
                ((ImageView) box.getChildren().get(dimension * rowIndex + columnIndex)).setImage(new Image("img/brick.jpg"));
            }
        }
    }

    public void showHint( int dir, int length, int x, int y) {
        // Timeline flasher = new Timeline(
        //     new KeyFrame(Duration.seconds(0.5), e -> {
        //         // use "flash" color
        //     }),
        //
        //     new KeyFrame(Duration.seconds(1.0), e -> {
        //         // revert to regular color
        //     })
        // );
        if ( dir == 1 || dir == 3 ) {
            for ( int i = x; i < x + length; i++ ) {
                ((ImageView) box.getChildren().get(dimension * i + y)).setImage(new Image("img/grass.jpg"));
            }
        } else {
            for ( int j = y; j < y + length; j++ ) {

                ((ImageView) box.getChildren().get(dimension * x + j)).setImage(new Image("img/grass.jpg"));
            }
        }
    }

}
