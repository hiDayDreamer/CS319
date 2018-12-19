import javafx.event.EventHandler;
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

public class PlayGame extends Pane implements TimerRunnable {
    //Constants
    private final String SOUND_ICON = "/img/soundIcon.png";
    private final String SETTINGS_ICON = "/img/Settings-icon.png";
    private final String BACK_ICON = "/img/backIcon.png";
    private final String UNDO_ICON = "/img/undo.png";
    private final String RESET_ICON = "/img/reset.png";
    private final String HINT_ICON = "/img/hintImage.png";
    private final String COPYRIGHT_LABEL = "Developed by Royal Flush";
    private final String BU_ICON = "/img/hintImage.png";
    private final String SHRINK_ICON = "/img/hintImage.png";
    private final double WIDTH  = 1080;
    private final double HEIGHT = 720;
    private final double COPYRIGHT_PANEL_SIZE = 60;
    private final int ICON_SIZE = 64;
    private final int GRIDBOX = 90;
    private final PlayGame mySelf = this;


    //Variables
    //Labels
    private Label copyRightLabel;
    private Pane copyRightPanel;
    private GridPane playGameSubpanel;
    private Label version;

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

    private Label timerCountdown;
    int noSeconds = 5;
    private int shrinkCount;
    public PlayGame(Map map,boolean mode) {
        super();
        gameMap = map;
        timerMode = mode;
        initialize();
    }

    public void initialize(){
        //Creating middle panel
        this.setMinHeight(HEIGHT - COPYRIGHT_PANEL_SIZE);
        this.setMinWidth(WIDTH);
        shrinkCount = 0;
        blocks = gameMap.getBlocks();
        //Creating copyright panel
        copyRightPanel = new Pane();

        //Copyright label
        copyRightLabel = new Label(COPYRIGHT_LABEL);

        //Version label
        version = new Label("Version b0.1");

        //How_To Panel
        //Sound button
        //soundImage = new Image(getClass().getResourceAsStream(SOUND_ICON),ICON_SIZE,ICON_SIZE,false,false);
        ImageView soundImage = new ImageView(new Image(SOUND_ICON));
        soundImage.setFitWidth(ICON_SIZE);
        soundImage.setFitHeight(ICON_SIZE);
        soundButton = new Button();
        soundButton.setStyle("-fx-background-color: transparent");
        soundButton.setMinSize(ICON_SIZE,ICON_SIZE);
        soundButton.setLayoutX(WIDTH - 100);
        soundButton.setLayoutY(25);

        //ImageView s = new ImageView(soundImage);

        //s.fitWidthProperty().bind(soundButton.widthProperty());
        //s.fitWidthProperty().bind(soundButton.heightProperty());
        soundButton.setGraphic(soundImage);

        //Settings button
        //settingsImage = new Image(getClass().getResourceAsStream(SETTINGS_ICON),ICON_SIZE,ICON_SIZE,false,false);
        settingsButton = new Button();
        settingsButton.setStyle("-fx-background-color: transparent");
        settingsButton.setMinSize(ICON_SIZE, ICON_SIZE);
        settingsButton.setLayoutX(WIDTH - 200);
        settingsButton.setLayoutY(25);
        //ImageView b = new ImageView(settingsImage);
        //b.fitWidthProperty().bind(settingsButton.widthProperty());
        //b.fitWidthProperty().bind(settingsButton.heightProperty());
        settingsButton.setGraphic(new ImageView(SETTINGS_ICON));

        //Back button
        //backImage = new Image(getClass().getResourceAsStream(BACK_ICON),ICON_SIZE,ICON_SIZE,false,false);
        backButton = new Button();
        backButton.setStyle("-fx-background-color: transparent");
        backButton.setMinSize(ICON_SIZE, ICON_SIZE);
        backButton.setLayoutX(25);
        backButton.setLayoutY(25);

        //ImageView b1 = new ImageView(backImage);
        //b1.fitWidthProperty().bind(backButton.widthProperty());
        //b1.fitWidthProperty().bind(backButton.heightProperty());
        //backButton.setGraphic(b1);
        backButton.setGraphic(new ImageView(BACK_ICON));

        //undoImage = new Image(getClass().getResourceAsStream(UNDO_ICON),ICON_SIZE,ICON_SIZE,false,false);
        undoButton = new Button();
        undoButton.setStyle("-fx-background-color: transparent");
        undoButton.setMinSize(ICON_SIZE, ICON_SIZE);
        undoButton.setLayoutX(150);
        undoButton.setLayoutY(300);
        undoButton.setGraphic(new ImageView(UNDO_ICON));

        //ImageView b2 = new ImageView(undoImage);
        //b2.fitWidthProperty().bind(undoButton.widthProperty());
        //b2.fitWidthProperty().bind(undoButton.heightProperty());
        //undoButton.setGraphic(b2);

        //resetImage = new Image(getClass().getResourceAsStream(RESET_ICON),ICON_SIZE,ICON_SIZE,false,false);
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
        Label hintLabel = new Label("Hint?");
        hintLabel.setLayoutX(WIDTH-190);
        hintLabel.setLayoutY(580);
        hintLabel.setFont(new Font(20));

        //ImageView b3 = new ImageView(resetImage);
        //b3.fitWidthProperty().bind(resetButton.widthProperty());
        //b3.fitWidthProperty().bind(resetButton.heightProperty());
        //resetButton.setGraphic(b3);

        //howToImage = new Image(getClass().getResourceAsStream(HINT_ICON),ICON_SIZE,ICON_SIZE,false,false);
        howToButton = new Button();
        howToButton.setGraphic(new ImageView(HINT_ICON));
        howToButton.setStyle("-fx-background-color: transparent");
        howToButton.setMinSize(ICON_SIZE, ICON_SIZE);
        howToButton.setLayoutX(WIDTH-200);
        howToButton.setLayoutY(500);
        //ImageView b5 = new ImageView(howToImage);
        //b5.fitWidthProperty().bind(howToButton.widthProperty());
        //b5.fitWidthProperty().bind(howToButton.heightProperty());
        //howToButton.setGraphic(b5);

        blowUpButton = new Button();
        blowUpButton.setGraphic(new ImageView(BU_ICON));
        blowUpButton.setStyle("-fx-background-color: transparent");
        blowUpButton.setMinSize(ICON_SIZE, ICON_SIZE);
        blowUpButton.setLayoutX(WIDTH-200);
        blowUpButton.setLayoutY(300);
        Label blowUpLabel = new Label("Blow up!!!");
        blowUpLabel.setLayoutX(WIDTH-190);
        blowUpLabel.setLayoutY(380);
        blowUpLabel.setFont(new Font(20));

        shrinkButton = new Button();
        shrinkButton.setGraphic(new ImageView(SHRINK_ICON));
        shrinkButton.setStyle("-fx-background-color: transparent");
        shrinkButton.setMinSize(ICON_SIZE, ICON_SIZE);
        shrinkButton.setLayoutX(WIDTH-200);
        shrinkButton.setLayoutY(180);
        Label shrinkLabel = new Label("Shrink!!!");
        shrinkLabel.setLayoutX(WIDTH-190);
        shrinkLabel.setLayoutY(260);
        shrinkLabel.setFont(new Font(20));


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
        startButton.setMinSize(ICON_SIZE,ICON_SIZE);
        startButton.setLayoutX(100);
        startButton.setLayoutY(75);

        playGameSubpanel = buildGrid(new Insets(90,0,0,300));
        this.getChildren().addAll( royalFlush, soundButton, settingsButton, howToButton, playGameSubpanel, backButton, undoButton, resetButton,
        resetLabel, hintLabel, undoLabel,timerCountdown,startButton, blowUpLabel, blowUpButton, shrinkLabel, shrinkButton);

        //this.getChildren().addAll(soundButton);

        //Adding labels to panel
        copyRightPanel.getChildren().add(copyRightLabel);
        copyRightPanel.getChildren().add(version);

        //Default theme
        setCurrentColor(null);
    }

    int gridBoxSize;
    private GridPane buildGrid(Insets constraints){
        playGameSubpanel = new GridPane();
        //playGameSubpanel.setPadding(new Insets(90, 0, 0,300));
        playGameSubpanel.setPadding(constraints);

        Block[][] mapBlocks = gameMap.getBlocks();
        cars = gameMap.getCars();

        String loc = "/img/grass.jpg";
        gridBoxSize = 90;

        Pane box = new Pane();
		  carsPane = new Pane();
        //box.setGridLinesVisible(true);
        box.setStyle("-fx-background-color: black, -fx-control-inner-background; -fx-background-insets: 0, 2; -fx-padding:2;");

        //RowConstraints rowHeight = new RowConstraints(gridBoxSize);
        //ColumnConstraints columnWidth = new ColumnConstraints(gridBoxSize);
        for (int rowIndex = 0; rowIndex < getCurrentDimensionSize(); rowIndex++){
            //rowHeight = new RowConstraints(gridBoxSize);
            //columnWidth = new ColumnConstraints(gridBoxSize);
            //box.getRowConstraints().add(rowHeight);
            //box.getColumnConstraints().add(columnWidth);
            for (int columnIndex = 0; columnIndex < getCurrentDimensionSize(); columnIndex++){
                //GridPane.setConstraints(box, columnIndex, rowIndex);
                Image img = new Image("/img/brick.jpg");
                ImageView possibleCar = new ImageView(img);
                possibleCar.setFitWidth(gridBoxSize-1);
                possibleCar.setFitHeight(gridBoxSize-1);
                //box.add(possibleCar,columnIndex,rowIndex);
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
        //box.add(exit,5,3);

        box.setStyle("-fx-background-color: black");

        int direction = -1;
        //loc = "/img/grass.png";
        for (int carIndex = 0 ; carIndex < cars.length; carIndex++){
            direction = cars[carIndex].getCarDirection();
            loc = cars[carIndex].getImageLocation();
            Image img = new Image(loc+"-"+(direction%2)+".png");
            ImageView possibleCar = new ImageView(img);

            if (direction == 3 || direction == 2) {
                possibleCar.setRotate(180);
            }
            possibleCar.setOnMousePressed(new EventHandler<MouseEvent>(){
                public void handle(MouseEvent e) {
                    int mouseY = (int)((e.getSceneX() - 300)/GRIDBOX);
                    int mouseX = (int)((e.getSceneY() - 90)/GRIDBOX);
                    double x = e.getSceneX() - 300;
                    double y = e.getSceneY() - 90;
                    curr = findCar(mouseX, mouseY);
                    carX = possibleCar.getLayoutX();
                    carY = possibleCar.getLayoutY();
                    firstX = x;
                    firstY = y;
                    moveForward = 0;
                    moveBackward = 0;
                    if ( curr != null ) {
                        if ( curr.getCarDirection() == 1 || curr.getCarDirection() == 3 ) {
                            int i = curr.getX() - 1;
                            while ( i > -1 && !blocks[i][curr.getY()].isOccupied() ) {
                                moveForward++;
                                i--;
                            }
                            int end = curr.getHorizontalX() + 1;
                            while ( end < 6 && !blocks[end][curr.getY()].isOccupied()) {
                                moveBackward++;
                                end++;
                            }
                        } else {
                            int i = curr.getY() - 1;
                            while ( i > -1 && !blocks[curr.getX()][i].isOccupied()) {
                                moveForward++;
                                i--;
                            }
                            int end = curr.getVerticalY() + 1;
                            while ( end < 6 && !blocks[curr.getX()][end].isOccupied()) {
                                moveBackward++;
                                end++;
                            }
                        }
                    }
                }
            });
            possibleCar.addEventHandler(MouseEvent.MOUSE_DRAGGED, new MouseListener(possibleCar));
            possibleCar.setOnMouseReleased(new EventHandler<MouseEvent>(){
                public void handle(MouseEvent e){
                    double carX = possibleCar.getLayoutX();
                    double carY = possibleCar.getLayoutY();
                    double x = carX % gridBoxSize;
                    double y = carY % gridBoxSize;
                    if ( x < gridBoxSize / 2 )
                        possibleCar.setLayoutX( carX - x);
                    else
                        possibleCar.setLayoutX( carX + gridBoxSize - x);

                    if ( y < gridBoxSize / 2)
                        possibleCar.setLayoutY( carY - y);
                    else
                        possibleCar.setLayoutY(carY + gridBoxSize - y);
                }
            });

            if ( direction == 1 || direction == 3){
                possibleCar.setFitWidth(gridBoxSize);
                possibleCar.setFitHeight(gridBoxSize*cars[carIndex].getLength());
                //possibleCar.setON
                possibleCar.setLayoutX(gridBoxSize * cars[carIndex].getY());
                possibleCar.setLayoutY(gridBoxSize * cars[carIndex].getX());
                /*
                GridPane.setRowSpan(possibleCar,cars[carIndex].getLength());
                GridPane.setRowIndex(possibleCar,cars[carIndex].getX());
                GridPane.setColumnIndex(possibleCar,cars[carIndex].getY());
                */
                carsPane.getChildren().add(possibleCar);
            } else {
                possibleCar.setFitWidth(cars[carIndex].getLength()* gridBoxSize);
                possibleCar.setFitHeight(gridBoxSize);
                possibleCar.setLayoutX(gridBoxSize * cars[carIndex].getY());
                possibleCar.setLayoutY(gridBoxSize * cars[carIndex].getX());
                /*
                GridPane.setRowIndex(possibleCar,cars[carIndex].getX());
                GridPane.setColumnIndex(possibleCar,cars[carIndex].getY());
                */
                carsPane.getChildren().add(possibleCar);
            }
        }
        box.getChildren().add(carsPane);
        playGameSubpanel.getChildren().add(box);
        return playGameSubpanel;
    }

    private String chooseMapImage(int number){
        switch(number) {
            case 0: return  "/img/gertasila.png";
            case 1: return  "/img/gertasila.png";
            case 2: return  "/img/gertasila.png";
            default: return  "/img/gertasila.png";
        }
    }

    public void setCurrentColor(String colorCSS){

        if (colorCSS == null){
            this.setStyle("-fx-background-color: #81aae6;");
        }else{
            this.setStyle(colorCSS);
        }
    }


    public void setCurrentDimensionSize(int dimension){

    }
    private int getCurrentDimensionSize(){
        return 6;
    }

    public void addHandler( GameManager.ButtonListener e) {
        // add the button listener to the back button
        backButton.addEventHandler(MouseEvent.MOUSE_CLICKED, e);

        GameManager.ButtonListener settings = e.clone();
        settings.setIndex(4);
        settingsButton.addEventHandler(MouseEvent.MOUSE_CLICKED, settings);

        GameManager.ButtonListener startTime = e.clone();
        startTime.setIndex(31);
        startButton.addEventHandler(MouseEvent.MOUSE_CLICKED, startTime);
        blowUpButton.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>(){
            public void handle(MouseEvent e) {
                System.out.println("BlowUp");
                if ( cars.length > 1 ) {
                    int toGo = (int)(Math.random()*(cars.length-1)) + 1;
                    Car[] newCars = new Car[cars.length - 1];
                    for ( int i = 0; i < toGo; i++ )
                        newCars[i] = cars[i];
                    for ( int i = toGo+1; i < cars.length; i++)
                        newCars[i-1] = cars[i];
                    gameMap.setCars(newCars);
                    cars = gameMap.getCars();
                    carsPane.getChildren().remove(toGo);
                    updateBlockinfo();
                }
            }
        });
        shrinkButton.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>(){
            public void handle(MouseEvent e) {
                System.out.println("Shrink");
                int index = (int)(Math.random()*(cars.length-1)) + 1;
                Car temp = cars[index];
                while ( shrinkCount < cars.length && temp.getLength() < 2 ) {
                    index = (int)(Math.random()*(cars.length-1)) + 1;
                    temp = cars[index];
                }
                if ( temp.getLength() > 1 ) {
                    temp.setLength( temp.getLength() - 1);
                    ImageView tempView = (ImageView) carsPane.getChildren().get(index);
                    if ( temp.getCarDirection() == 1 || temp.getCarDirection() == 3 ) {
                        temp.setHorizontalX(temp.getX(), temp.getHorizontalX() - 1);
                        tempView.setFitHeight(tempView.getFitHeight()-gridBoxSize);
                    } else {
                        temp.setVerticalY(temp.getY(), temp.getVerticalY() - 1);
                        tempView.setFitWidth(tempView.getFitWidth()-gridBoxSize);
                    }
                    shrinkCount++;
                }
                updateBlockinfo();
            }
        });
    }

    private void updateBlockinfo(){
        Block[][] arr = blocks;
        for ( int i = 0; i < gameMap.getDimension(); i++) {
            for ( int j = 0; j < gameMap.getDimension(); j++) {
                arr[i][j].setOccupied(false);
            }
        }
        for ( int i = 0; i < cars.length; i++) {
            if ( cars[i].getCarDirection() == 0 ) {
                for ( int j = 0; j < cars[i].getLength(); j++) {
                    arr[cars[i].getX()][cars[i].getY()+j].setOccupied(true);
                }
            } else if ( cars[i].getCarDirection() == 1 ) {
                for ( int j = 0; j < cars[i].getLength(); j++) {
                    arr[cars[i].getX()+j][cars[i].getY()].setOccupied(true);
                }
            }else if ( cars[i].getCarDirection() == 2 ) {
                for ( int j = 0; j < cars[i].getLength(); j++) {
                    arr[cars[i].getX()][cars[i].getY()+j].setOccupied(true);
                }
            }else {
                for ( int j = 0; j < cars[i].getLength(); j++) {
                    arr[cars[i].getX()+j][cars[i].getY()].setOccupied(true);
                }
            }
        }
        //Print the blocks in console
        /*for ( int i = 0; i < arr.length; i++ ) {
            for ( int j = 0; j < arr[i].length; j++ ) {
                int temp = arr[i][j].isOccupied() ? 1 : 0;
                System.out.print(temp + " ");
            }
            System.out.println();
        }
        System.out.println();
        */
    }

    double firstX, firstY;
    double carX, carY;
    Car curr;
    int moveForward;
    int moveBackward;
    class MouseListener implements EventHandler<MouseEvent> {
        ImageView car;
        public MouseListener( ImageView car) {
            this.car = car;
        }
        public void handle(MouseEvent e) {
            double x = e.getSceneX() - 300;
            double y = e.getSceneY() - 90;
            double afterX = e.getSceneX()-300-(firstX-carX);
            double afterY = e.getSceneY()-90-(firstY-carY);
            boolean empty = true;

            if ( curr.getCarDirection() == 1 || curr.getCarDirection() == 3 ) {
                //if ( afterY > -1 && afterY + gridBoxSize * curr.getLength() < gridBoxSize*6 ) {
                    boolean neg = y < firstY;
                    double posY;
                    if ( neg ) {
                        posY = Math.max(afterY, carY-gridBoxSize*moveForward);
                    }
                    else {
                        posY = Math.min(afterY, carY+gridBoxSize*moveBackward);
                    }
                    car.setLayoutY(posY);
                    int newX = (int) Math.round(posY / gridBoxSize);
                    updateCarX(curr, newX);

                //}
            } else {
                //if ( afterX > -1 && afterX + gridBoxSize * curr.getLength() < gridBoxSize*6 ) {
                    boolean neg = x < firstX;
                    double posX;
                    if ( neg ) {
                        posX = Math.max(afterX, carX-gridBoxSize*moveForward);
                    }
                    else {
                        posX = Math.min(afterX, carX+gridBoxSize*moveBackward);
                    }
                    car.setLayoutX(posX);
                    int newY = (int) Math.round(posX / gridBoxSize);
                    updateCarY(curr, newY);
                //}
            }
            if ( curr.isPlayer() && curr.getVerticalY() == 5 ){
                mySelf.getChildren().remove(playGameSubpanel);
                Image im = new Image("/img/win.gif");
                ImageView view = new ImageView(im);
                view.setFitWidth(WIDTH - 40);
                view.setFitHeight(90*6);
                view.setLayoutX(20);
                view.setLayoutY(100);
                mySelf.getChildren().add(view);

            }
            updateBlockinfo();
        }
    }

    public boolean updateCarX(Car car, int x) {
        car.setHorizontalX( x, x + car.getLength() - 1);
        return true;
    }

    public boolean updateCarY(Car car, int y) {
        car.setVerticalY( y, y + car.getLength() - 1);
        return true;
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
        timer = new GameTimer(noSeconds);
        System.out.println("I am in start tiemr play game");
        timer.startCountDown(this);
    }


    public void runOnTimer(){
        System.out.println("" + timer.getCountDown());
        noSeconds--;
        //initialize();
        Platform.runLater(() -> timerCountdown.setText("" + timer.getCountDown()));
        if (noSeconds == 0){
            Platform.runLater(() -> gameloss());
        }
    }

    public void gameloss(){
        mySelf.getChildren().remove(playGameSubpanel);
        Image im = new Image("/img/win.gif");
        ImageView view = new ImageView(im);
        view.setFitWidth(WIDTH - 40);
        view.setFitHeight(90*6);
        view.setLayoutX(20);
        view.setLayoutY(100);
        mySelf.getChildren().add(view);
    }
}
