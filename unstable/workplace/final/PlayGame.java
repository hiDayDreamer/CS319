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
import javafx.animation.*;
import javafx.util.*;
import javafx.beans.property.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class PlayGame extends Pane implements TimerRunnable {
    //Constants
    private final String SOUND_ICON = "/img/soundIcon.png";
    private final String SETTINGS_ICON = "/img/Settings-icon.png";
    private final String BACK_ICON = "/img/backIcon.png";
    private final String UNDO_ICON = "/img/undo.png";
    private final String RESET_ICON = "/img/reset.png";
    private final String HINT_ICON = "/img/hintIcon.png";
    private final String COPYRIGHT_LABEL = "Developed by Royal Flush";
    private final String BU_ICON = "/img/bomb.png";
    private final String SHRINK_ICON = "/img/shrinkCarIcon.png";
    private final String ROTATE_ICON = "/img/rotateCarIcon.png";
    private final String START_ICON = "/img/startTime.png";

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
    private ImageView backImage;
    //private Image soundImage;
    //private Image settingsImage;
    //private Image backImage;
    //private Image undoImage;
    //private Image resetImage;
    //private Image howToImage;

    private GameTimer timer;
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
    private int noSeconds;
    private int noMinutes;
    private boolean timerOff = false;

    //private int shrinkCount;
    public PlayGame(Map map,boolean mode,int dim) {
        super();
        gameMap = map;
        timerMode = mode;
        volume = 0;
        dimension = dim;
        noSeconds = 65;
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

        ImageView settingsImage = new ImageView(new Image(SETTINGS_ICON));
        settingsImage.setFitWidth(ICON_SIZE);
        settingsImage.setFitHeight(ICON_SIZE);
        settingsButton = new Button();
        settingsButton.setStyle("-fx-background-color: transparent");
        settingsButton.setMinSize(ICON_SIZE, ICON_SIZE);
        settingsButton.setLayoutX(WIDTH - 200);
        settingsButton.setLayoutY(25);
        settingsButton.setGraphic(settingsImage);

        backImage = new ImageView(BACK_ICON);
        backImage.setFitWidth(ICON_SIZE);
        backImage.setFitHeight(ICON_SIZE);
        backButton = new Button();
        backButton.setGraphic(backImage);
        backButton.setStyle("-fx-background-color: transparent");
        backButton.setMinSize(ICON_SIZE, ICON_SIZE);
        backButton.setLayoutX(25);
        backButton.setLayoutY(25);

        Label hover = new Label();
        hover.setLayoutY(465);
        hover.setLayoutX(135);
        hover.setFont(new Font("American Typewriter", 20));

        Label hover1 = new Label();
        hover1.setLayoutY(465);
        hover1.setLayoutX(910);
        hover1.setFont(new Font("American Typewriter", 20));

        undoButton = new Button();
        undoButton.setStyle("-fx-background-color: transparent");
        undoButton.setMinSize(ICON_SIZE, ICON_SIZE);
        undoButton.setLayoutX(150);
        undoButton.setLayoutY(300);
        undoButton.setGraphic(new ImageView(UNDO_ICON));
        undoButton.addEventHandler(MouseEvent.MOUSE_ENTERED,
                e -> {
                    hover1.setText("Undo");
                });
        undoButton.addEventHandler(MouseEvent.MOUSE_EXITED,
                e -> {
                    hover1.setText("");
                });


        resetButton = new Button();
        resetButton.setGraphic(new ImageView(RESET_ICON));
        resetButton.setStyle("-fx-background-color: transparent");
        resetButton.setMinSize(ICON_SIZE, ICON_SIZE);
        resetButton.setLayoutX(150);
        resetButton.setLayoutY(500);
        resetButton.addEventHandler(MouseEvent.MOUSE_ENTERED,
                e -> {
                    hover1.setText("Reset");
                });
        resetButton.addEventHandler(MouseEvent.MOUSE_EXITED,
                e -> {
                    hover1.setText("");
                });

        howToButton = new Button();
        howToButton.setGraphic(new ImageView(HINT_ICON));
        howToButton.setStyle("-fx-background-color: transparent");
        howToButton.setMinSize(ICON_SIZE, ICON_SIZE);
        howToButton.setLayoutX(WIDTH-200);
        howToButton.setLayoutY(500);
        howToButton.setAlignment(Pos.CENTER);
        howToButton.addEventHandler(MouseEvent.MOUSE_ENTERED,
                e -> {
                    hover.setText("Hint");
                });
        howToButton.addEventHandler(MouseEvent.MOUSE_EXITED,
                e -> {
                    hover.setText("");
                });

        blowUpButton = new Button();
        blowUpButton.setGraphic(new ImageView(BU_ICON));
        blowUpButton.setStyle("-fx-background-color: transparent");
        blowUpButton.setMinSize(ICON_SIZE, ICON_SIZE);
        Label blowUpLabel = new Label("Blow up");
        blowUpLabel.setLayoutX(WIDTH-190);
        blowUpLabel.setLayoutY(380);
        blowUpLabel.setFont(new Font(20));
        blowUpButton.addEventHandler(MouseEvent.MOUSE_ENTERED,
                e -> {
                    hover.setText("Blow Up");
                });
        blowUpButton.addEventHandler(MouseEvent.MOUSE_EXITED,
                e -> {
                    hover.setText("");
                });

        shrinkButton = new Button();
        shrinkButton.setGraphic(new ImageView(SHRINK_ICON));
        shrinkButton.setStyle("-fx-background-color: transparent");
        shrinkButton.setMinSize(ICON_SIZE, ICON_SIZE);
        Label shrinkLabel = new Label("Shrink");
        shrinkLabel.setLayoutX(WIDTH-190);
        shrinkLabel.setLayoutY(260);
        shrinkLabel.setFont(new Font(20));
        shrinkButton.addEventHandler(MouseEvent.MOUSE_ENTERED,
                e -> {
                    hover.setText("Shrink");
                });
        shrinkButton.addEventHandler(MouseEvent.MOUSE_EXITED,
                e -> {
                    hover.setText("");
                });

        rotate = new Button();
        rotate.setGraphic(new ImageView(ROTATE_ICON));
        rotate.setStyle("-fx-background-color: transparent");
        rotate.setMinSize(ICON_SIZE, ICON_SIZE);
        rotate.addEventHandler(MouseEvent.MOUSE_ENTERED,
                e -> {
                    hover.setText("Rotate");
                });
        rotate.addEventHandler(MouseEvent.MOUSE_EXITED,
                e -> {
                    hover.setText("");
                });

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
        undoReset.setLayoutX(930);
        undoReset.setLayoutY(250);
        undoReset.setStyle("-fx-border-color: black; -fx-border-width: 1px; -fx-border-radius: 50px 50 50px 50px; -fx-padding: 10px");
        undoReset.getChildren().addAll(undoButton, resetButton);


        BorderPane title = new BorderPane();
        Label royalFlush = new Label("Welcome to the Royal Flush experience!");
        royalFlush.setLayoutX(250);
        royalFlush.setLayoutY(10);
        royalFlush.setFont(new Font(30));

        
        addAnimation(10);
        startButton = new Button();

        //startButton.setStyle("-fx-background-color: transparent");


        playGameSubpanel = buildGrid(new Insets(90,0,0,300));
        startButton.setMinSize(gridBoxSize*getCurrentDimensionSize(),gridBoxSize*getCurrentDimensionSize());
        ImageView timerStartImage = new ImageView(START_ICON);
        timerStartImage.setFitWidth(gridBoxSize*getCurrentDimensionSize());
        timerStartImage.setFitHeight(gridBoxSize*getCurrentDimensionSize());
        startButton.setGraphic(timerStartImage);
        startButton.setStyle("-fx-background-color: transparent");

        
        startButton.setLayoutX(290);
        startButton.setLayoutY(85);


        if (timerMode){
            getChildren().addAll( royalFlush, soundButton, settingsButton, playGameSubpanel, backButton,startButton, shrinkBlow, undoReset, hover, hover1);
        } else {
            getChildren().addAll( royalFlush, soundButton, settingsButton, playGameSubpanel, backButton, shrinkBlow, undoReset, hover, hover1);
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
    public void setExit(int y, int x){
    
        Image immg = new Image("/img/exit.png");
        ImageView exit = new ImageView(immg);
        exit.setFitWidth(gridBoxSize);
        exit.setFitHeight(gridBoxSize);
        exit.setLayoutX(300 +(y)*gridBoxSize + gridBoxSize/2);
        exit.setLayoutY((x)*gridBoxSize + 90 );
        exit.setRotate(90);
 
        getChildren().add(exit);
    }
    private GridPane buildGrid(Insets constraints){
        playGameSubpanel = new GridPane();
        playGameSubpanel.setPadding(constraints);
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
        GameManager.ButtonListener reset = e.clone();
        reset.setIndex(55);
        resetButton.addEventHandler(MouseEvent.MOUSE_CLICKED, reset);
        GameManager.ButtonListener undo = e.clone();
        undo.setIndex(38);
        undoButton.addEventHandler(MouseEvent.MOUSE_CLICKED, undo);
        blowUpButton.addEventHandler(MouseEvent.MOUSE_CLICKED, new GameManager.BlowUp(carsPane));
        shrinkButton.addEventHandler(MouseEvent.MOUSE_CLICKED,new GameManager.Shrink(carsPane));
        rotate.addEventHandler(MouseEvent.MOUSE_CLICKED,new GameManager.RotateaCar(carsPane));
        resetButton.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>(){
            public void handle(MouseEvent e) {
                System.out.println("Reset");
            }
        });
    }

    public void undo(Car car, int index, int type) {
        if ( type == 0 ) {
            carsPane.getChildren().get(index).setLayoutX(gridBoxSize*car.getY());
            carsPane.getChildren().get(index).setLayoutY(gridBoxSize*car.getX());
        } else if (type == 1 || type == 2) {
            int direction = car.getCarDirection();
            ImageView tempView = (ImageView) carsPane.getChildren().get(index);
            if ( direction == 1 || direction == 3 )
                tempView.setFitHeight(tempView.getFitHeight()+gridBoxSize);
            else
                tempView.setFitWidth(tempView.getFitWidth()+gridBoxSize);
        }else if (type == 3){
                ImageView tempView = (ImageView) carsPane.getChildren().get(index);
                tempView.setImage(new Image("/img/"+ index+"-" + car.getCarDirection()%2+ ".png"));
                if (car.getCarDirection() == 3 || car.getCarDirection() == 2) {
                    tempView.setRotate(180);
                }
                tempView.setLayoutX(gridBoxSize * car.getY());
                tempView.setLayoutY(gridBoxSize * car.getX());

                double temp = tempView.getFitWidth();
                double tmp2 = tempView.getFitHeight();
                tempView.setFitHeight(temp);
                tempView.setFitWidth(tmp2);
        } 
    }

    public void unBlowUp( Car[] arr) {
        carsPane.getChildren().removeAll(carsPane.getChildren());
        int direction = -1;
        for (int carIndex = 0 ; carIndex < arr.length; carIndex++){
            direction = arr[carIndex].getCarDirection();
            String loc = arr[carIndex].getImageLocation();
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
                possibleCar.setFitHeight(gridBoxSize*arr[carIndex].getLength());
                possibleCar.setLayoutX(gridBoxSize * arr[carIndex].getY());
                possibleCar.setLayoutY(gridBoxSize * arr[carIndex].getX());
                carsPane.getChildren().add(possibleCar);
            } else {
                possibleCar.setFitWidth(arr[carIndex].getLength()* gridBoxSize);
                possibleCar.setFitHeight(gridBoxSize);
                possibleCar.setLayoutX(gridBoxSize * arr[carIndex].getY());
                possibleCar.setLayoutY(gridBoxSize * arr[carIndex].getX());
                carsPane.getChildren().add(possibleCar);
            }
        }
    }

   
    public void startTimer( GameTimer timer){
        getChildren().remove(startButton);
        if (timer == null){
            timer = new GameTimer(noSeconds);
        }
        this.timer = timer;
        getChildren().remove(timerCountdown);
        noMinutes = timer.getCountDown()/60;
        noSeconds = timer.getCountDown() - noMinutes*60;
        String tmp  = String.format("%02d", noMinutes)+ " : " + String.format("%02d", noSeconds);
        timerCountdown = new Label( tmp );
        timerCountdown.setLayoutX(100);
        timerCountdown.setLayoutY(200);
        timerCountdown.setFont(new Font(30));
        getChildren().add(timerCountdown);
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
    public void rotateCar(int index,Car car) {
        
        ImageView tempView = (ImageView) carsPane.getChildren().get(index);
        System.out.println("The car name location " + "/img/"+ index+"-" + car.getCarDirection()%2+ ".png");
        tempView.setImage(new Image("/img/"+ index+"-" + car.getCarDirection()%2+ ".png"));
        if (car.getCarDirection() == 3 || car.getCarDirection() == 2) {
            tempView.setRotate(360);
        }
        tempView.setLayoutX(gridBoxSize * car.getY());
        tempView.setLayoutY(gridBoxSize * car.getX());

        double temp = tempView.getFitWidth();
        double tmp2 = tempView.getFitHeight();
        tempView.setFitHeight(temp);
        tempView.setFitWidth(tmp2);
    }

    public void runOnTimer(){
        int cuurentSec = timer.getCountDown();
        noMinutes = cuurentSec/60;
        noSeconds = cuurentSec- noMinutes*60;
        String tmp  = String.format("%02d", noMinutes)+ " : " + String.format("%02d", noSeconds);
        System.out.println("" + timer.getCountDown());
        cuurentSec--;
        //initialize();
        Platform.runLater(() -> timerCountdown.setText(tmp));
        if (cuurentSec == 0){
              System.out.println("GameWon") ;
            Platform.runLater(() -> gameLoss());
        }
    }

    public boolean gameWon() {
        Stage popupwindow=new Stage();
        popupwindow.initModality(Modality.APPLICATION_MODAL);
        popupwindow.setTitle("Game Won");
        popupwindow.setResizable(false);
        popupwindow.setHeight(600);
        popupwindow.setWidth(600);
        popupwindow.getIcons().add(new Image(getClass().getResourceAsStream("/img/cards.png")));
        ImageView starsImage = new ImageView("/img/fullStar.png");
        ImageView starsImage1 = new ImageView("/img/fullStar.png");
        ImageView starsImage2 = new ImageView("/img/fullStar.png");

        //starsImage.setFitWidth(400);
        //starsImage.setFitHeight(60);
        Label label1= new Label("");
        Image labelImage = new Image(getClass().getResourceAsStream("/img/win.png"), 400,100,true, false);
        //stars[0] = 3;
         //stars[0] = 3;
        try{
            int star = 3;
            //int stars = Integer.parseInt(str);
            BufferedReader br = new BufferedReader(new FileReader("./storage/starsFile.txt")); 
            String s = br.readLine();
            int stars = Integer.parseInt(s);
            stars = stars + star;
            String str = "" + stars;
        	FileOutputStream outputStream = new FileOutputStream("./storage/starsFile.txt");
            byte[] strToBytes = str.getBytes();
            outputStream.write(strToBytes);
            br.close();
            outputStream.close();
        }
        catch(IOException e) {
        	e.printStackTrace();
        }
        /*labelImage.setFitWidth(400);
        labelImage.setFitHeight(60);*/
        //labelImage.preserveRatioProperty();
        label1.setGraphic(new ImageView(labelImage));
        Image im = new Image("/img/win.gif");
        ImageView view = new ImageView(im);
        view.setFitWidth(400);
        view.setFitHeight(200);
        Label label2= new Label("Number of moves: ");
        goBackMenuButton= new Button("Go Back to Menu");
        goBackMenuButton.setOnAction(e -> popupwindow.close());
        VBox layout= new VBox(20);
        HBox layout1 = new HBox(10);
        layout.getChildren().addAll(view, label1);
        if(stars[0] == 1) {
            layout1.getChildren().addAll(starsImage);
        }else if (stars[0] == 2){
            layout1.getChildren().addAll(starsImage, starsImage1);
        }else if(stars[0] == 3){
            layout1.getChildren().addAll(starsImage, starsImage1, starsImage2);
        }
        layout1.setAlignment(Pos.CENTER);
        layout.getChildren().addAll(layout1, label2, goBackMenuButton);

        layout.setAlignment(Pos.CENTER);
        Scene scene1= new Scene(layout, 300, 250);
        popupwindow.setScene(scene1);
        popupwindow.showAndWait();
        return true;
    }

    public void setTimerOf(boolean val){
        timerOff = val;
    }

    public boolean gameLoss(){
        Stage popupwindow=new Stage();
        popupwindow.initModality(Modality.APPLICATION_MODAL);
        popupwindow.setTitle("Game Won");
        popupwindow.setResizable(false);
        popupwindow.setHeight(500);
        popupwindow.setWidth(700);
        popupwindow.getIcons().add(new Image(getClass().getResourceAsStream("/img/cards.png")));
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

    public void showHint( int index, int dir, int length, int x, int y) {
        Transition[] ft = new Transition[length+1];// = new FadeTransition(Duration.millis(3000), box.getChildren().get(0));
        ft[0] = new Transition () {
            {
                setCycleDuration(Duration.seconds(4.0));
            }
            @Override
            protected void interpolate(double frac)
            {
                carsPane.getChildren().get(index).setStyle("-fx-effect: dropshadow(gaussian, black, 127, 0, 0.5, 0)");
                if ( frac == 1 ) {
                    carsPane.getChildren().get(index).setStyle("");
                }
            }
        };
        if ( dir == 1 || dir == 3 ) {
            for ( int i = x; i < x + length; i++ ) {
                //((ImageView) box.getChildren().get(dimension * i + y)).setImage(new Image("img/grass.jpg"));
                ft[i - x+1] = new FadeTransition(Duration.seconds(1.0), box.getChildren().get(dimension * i + y));
                ((FadeTransition) ft[i-x+1]).setFromValue(4.0);
                ((FadeTransition) ft[i-x+1]).setToValue(0.3);
                //ft[i-x+1].setToValue(0.1);
            }
        } else {
            for ( int j = y; j < y + length; j++ ) {
                ft[j-y+1] = new FadeTransition(Duration.seconds(1.0), box.getChildren().get(dimension * x + j));
                ((FadeTransition) ft[j-y+1]).setFromValue(1.0);
                ((FadeTransition) ft[j-y+1]).setToValue(0.3);
                //((ImageView) box.getChildren().get(dimension * x + j)).setImage(new Image("img/grass.jpg"));
            }
        }
        ft[0].setInterpolator(Interpolator.LINEAR);
        ft[0].setAutoReverse(true);
        ft[0].setCycleCount(1);
        ft[0].play();
        for ( int i = 1; i < length+1; i++ ) {
            ft[i].setAutoReverse(true);
            ft[i].setCycleCount(4);
            ft[i].play();
        }
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
