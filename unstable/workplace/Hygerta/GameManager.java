import javafx.application.Application;
import javafx.stage.Stage;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.image.ImageView;
import javafx.scene.control.Button;
import javafx.scene.media.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javafx.scene.layout.Pane;
public class GameManager extends Application{

    private WindowManager primaryStage1;
    private DashboardData data;
    private DataStorage dataStorage;
    private static Engine engine;
    private String backgroundColor = "";
    private boolean timerMode;
    private int currentThemeIndex;
    private int dimension;
    private DashboardPane newDashboardPane;
    public static void main(String[] args) {
        launch(args);
        String fileContent = "Some moves.";
    }

    double sliderVolume = 40;
    public GameManager(){
        super();
        primaryStage1 = new WindowManager();
        data = new DashboardData();
        dataStorage = new DataStorage();
        engine = new Engine(this);
        currentThemeIndex = 4;
        dimension = 6;
        //playBackgroundSound(sliderVolume,"./sound/backgroundSound.mp3");
    }

    public DataStorage getDataStorage() {
        return dataStorage;
    }
    @Override
    public void start(Stage primaryStage) {

        primaryStage = primaryStage1;
        primaryStage1.addHandler( new ButtonListener(1));
    }
   Settings newSettingsPane;
   static PlayGame playGame;


   class ButtonListener implements EventHandler<MouseEvent>{
      private int index;


      public ButtonListener() {
         super();
         index = 0;
      }
      public ButtonListener( int index) {
         super();
         this.index = index;
      }

      public void handle(MouseEvent e) {
         if ( index == 0 ) {
            MainPage newPane = new MainPage();
            newPane.addHandler( new ButtonListener(1));
            primaryStage1.updateMiddlePanel(newPane);
        }
        if ( index == 1 ) {
            // this goes to dimensionsPane from the mainPanel
            Dimensions newPane = new Dimensions();
            newPane.addHandler( new ButtonListener(0));
            primaryStage1.updateMiddlePanel(newPane);
        }

        else if ( index == 2 ){
            // this goes to howto pane from the mainPanel
            How_To newPane = new How_To();
            newPane.addHandler( new ButtonListener(0));
            primaryStage1.updateMiddlePanel(newPane);
        }
        else if ( index == 3 ){
            newDashboardPane = new DashboardPane(data);
            newDashboardPane.addHandler( new ButtonListener(0));
            primaryStage1.updateMiddlePanel(newDashboardPane);
        }
        else if ( index == 4){
            if (newSettingsPane != null){
                newSettingsPane.initialize(newSettingsPane.isTimerToogleOn());
            } else {
                newSettingsPane = new Settings();
            }
            newSettingsPane.setSelectedTheme(currentThemeIndex);
            newSettingsPane.addHandler( new ButtonListener(0));
            primaryStage1.updateMiddlePanel(newSettingsPane);

        }

        else if ( index == 5 ) {
            // this goes to credits pane from the mainPanel
            CreditsPane newPane = new CreditsPane();
            newPane.addHandler(new ButtonListener(0));
            primaryStage1.updateMiddlePanel(newPane);
        }

        else if ( index == 6){
            // this goes to the levelsPane from the dimensions panel
            LevelsPane newPane = new LevelsPane("6X6", engine.getStars());
            newPane.addHandler( new ButtonListener(0));
            primaryStage1.updateMiddlePanel(newPane);
            dimension = 6;
        } else if ( index == 7){
          newDashboardPane.updatePlayerSkin(1);
        }


        else if ( index == 8){
            LevelsPane newPane = new LevelsPane("8X8", engine.getStars());
            newPane.addHandler( new ButtonListener(0));
            primaryStage1.updateMiddlePanel(newPane);
            dimension = 8;
        }
        else if ( index == 9){
             System.out.println("Update prev skin");
              newDashboardPane.updatePlayerSkin(-1);
        }
        else if ( index == 10){
            LevelsPane newPane = new LevelsPane("10X10", engine.getStars());
            newPane.addHandler( new ButtonListener(0));
            primaryStage1.updateMiddlePanel(newPane);
            dimension = 10;
        }
        else if ( index >= 11 && index <= 25) {
            if (newSettingsPane == null){
                timerMode = false;
            } else {
                timerMode = newSettingsPane.isTimerToogleOn();
            }
            Map selectedMap = dataStorage.getMap(dimension).clone();
            selectedMap.printDim();
            engine.setSelectedMap(selectedMap);
            playGame = new PlayGame(selectedMap,timerMode,dimension);
            playGame.addHandler( new ButtonListener(6));
            playGame.setSoundVolume(sliderVolume);
            playGame.setStars(engine.getStars());
            primaryStage1.updateMiddlePanel(playGame);

        }
        else if (index == 30){
            System.out.println("toogle " +  newSettingsPane.isTimerToogleOn());
            newSettingsPane.toogleTimer();
            newSettingsPane.addHandler( new ButtonListener(0));
        }
        else if (index == 31){
            //this is for start timer button
            System.out.println("start timer");
            playGame.startTimer();

        }
        else if (index == 32){
            //this is for start tiemr button
            System.out.println(newSettingsPane.getSliderVolume());

        } 
        else if (index == 33){
            backgroundColor = "-fx-background-color: #ffd151;";
            currentThemeIndex = index -33;
            newSettingsPane.setSelectedTheme(currentThemeIndex);
            boolean myTimer = newSettingsPane.isTimerToogleOn();
            newSettingsPane.initialize(myTimer);
            newSettingsPane.addHandler( new ButtonListener(0));
            
        }
        else if (index == 34 ){
            backgroundColor = "-fx-background-color: #719fe6;";
            currentThemeIndex = index -33;
            newSettingsPane.setSelectedTheme(currentThemeIndex);
            boolean myTimer = newSettingsPane.isTimerToogleOn();
            newSettingsPane.initialize(myTimer);
            newSettingsPane.addHandler( new ButtonListener(0));

        }
        else if (index == 35){
            backgroundColor = "-fx-background-color: rgba(74,148,74,0.6)";
            currentThemeIndex = index -33;
            newSettingsPane.setSelectedTheme(currentThemeIndex);
            boolean myTimer = newSettingsPane.isTimerToogleOn();
            newSettingsPane.initialize(myTimer);
            newSettingsPane.addHandler( new ButtonListener(0));

        }
        else if (index == 36){
            backgroundColor = "-fx-background-color: rgba(153,50,147,0.7);";
            currentThemeIndex = index -33;
            newSettingsPane.setSelectedTheme(currentThemeIndex);
            boolean myTimer = newSettingsPane.isTimerToogleOn();
            newSettingsPane.initialize(myTimer);
            newSettingsPane.addHandler( new ButtonListener(0));

        }
        else if (index == 37){
            backgroundColor = "-fx-background-color: rgba(224,133,179,0.94);";
            currentThemeIndex = index -33;
            newSettingsPane.setSelectedTheme(currentThemeIndex);
            boolean myTimer = newSettingsPane.isTimerToogleOn();
            newSettingsPane.initialize(myTimer);
            newSettingsPane.addHandler( new ButtonListener(0));

        }

        primaryStage1.setCurrentColor(backgroundColor);
    }

        public void setIndex( int index) {
            this.index = index;
        }

        public ButtonListener clone() {
            return new ButtonListener();
        }
    }


    static class Animation implements EventHandler<MouseEvent>{
        private Button b;
        private double x;
        private double y;
        private double width;
        private double height;
        private int factor;
        private boolean enter;
        public Animation() {
            super();
        }
        public Animation(Button button, int factor, boolean enter) {
            super();
            b = button;
            x = b.getLayoutX();
            y = b.getLayoutY();
            width = (( ImageView)b.getGraphic()).getFitWidth();
            height = (( ImageView)b.getGraphic()).getFitHeight();
            this.factor = factor;
            this.enter = enter;
        }
        public void handle(MouseEvent e){
            if (enter) {
                (( ImageView)b.getGraphic()).setFitWidth(width + factor);
                (( ImageView)b.getGraphic()).setFitHeight(height + factor);
                b.setLayoutX(x-factor/2);
                b.setLayoutY(y-factor/2);
            } else {
                (( ImageView)b.getGraphic()).setFitWidth(width);
                (( ImageView)b.getGraphic()).setFitHeight(height);
                b.setLayoutX(x);
                b.setLayoutY(y);
            }
        }
    }

    static double firstX, firstY;
    static int firstIntX, firstIntY;
    static double carX, carY;
    static Car curr;
    static int moveForward;
    static int moveBackward;
    static class MoveCar implements EventHandler<MouseEvent>{
        private ImageView possibleCar;
        public MoveCar(ImageView car) {
            possibleCar = car;
        }
        public void handle(MouseEvent e) {
            int gridBoxSize = playGame.getBoxSize();
            int mouseY = (int)((e.getSceneX() - 300)/gridBoxSize);
            int mouseX = (int)((e.getSceneY() - 90)/gridBoxSize);
            double x = e.getSceneX() - 300;
            double y = e.getSceneY() - 90;
            curr = playGame.findCar(mouseX, mouseY);
            firstIntX = curr.getX();
            firstIntY = curr.getY();
            carX = possibleCar.getLayoutX();
            carY = possibleCar.getLayoutY();
            firstX = x;
            firstY = y;
            moveForward = 0;
            moveBackward = 0;
            int[] res = engine.findMax(curr);
            moveForward = res[0];
            moveBackward = res[1];
        }
    }

    public static void updateCar() {
        if ( curr.getCarDirection() == 1 || curr.getCarDirection() == 3 ) {
            //if ( firstIntX != newX)
                engine.updateCarX( curr, newX);
        } else  {
            //if (firstIntY != newY)
                engine.updateCarY( curr, newY);
        }
    }

    static class BlowUp implements EventHandler<MouseEvent>{
        Pane carsPane;
        public BlowUp(Pane pane) {
            carsPane = pane;
        }
        public void handle(MouseEvent e) {
            System.out.println("BlowUp");
            int length = engine.getCarNo();
            if ( length > 1 ) {
                int toGo = (int)(Math.random()*(length-1)) + 1;
                engine.blowUpCar(toGo);
                carsPane.getChildren().remove(toGo);
                engine.updateBlockinfo();
            }
        }
    }

    static int shrinkCount;
    static class Shrink implements EventHandler<MouseEvent>{
        Pane carsPane;
        public Shrink(Pane pane) {
            carsPane = pane;
            shrinkCount = 0;
        }

        public void handle(MouseEvent e) {
            System.out.println("Shrink");
            int index = (int)(Math.random()*(engine.getCarNo()-1)) + 1;
            if ( shrinkCount < 3 ) {
                while ( engine.getCar(index).getLength() < 2 ) {
                    index = (int)(Math.random()*(engine.getCarNo()-1)) + 1;
                }
                //if ( temp.getLength() > 1 )
                //engine.getCar(index).setLength( engine.getCar(index).getLength() - 1);
                //ImageView tempView = (ImageView) carsPane.getChildren().get(index);
                //engine.getCar(index).setHorizontalX(engine.getCar(index).getX(), engine.getCar(index).getHorizontalX() - 1);
                engine.shrinkCar(index);
                playGame.shrinkCar(index);
                //tempView.setFitHeight(tempView.getFitHeight()-gridBoxSize);
                //engine.getCar(index).setVerticalY(engine.getCar(index).getY(), engine.getCar(index).getVerticalY() - 1);
                //engine.shrinkCar(car);
                //tempView.setFitWidth(tempView.getFitWidth()-gridBoxSize);
                shrinkCount++;
                //}
                engine.updateBlockinfo();
            }
        }
    }

    static class Release implements EventHandler<MouseEvent>{
        ImageView possibleCar;
        public Release( ImageView car ) {
            possibleCar = car;
        }
        public void handle(MouseEvent e){
            gridBoxSize = playGame.getBoxSize();
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
            //updateCar();
            //engine.updateBlockinfo();
            if ( engine.gameWon() ) {
                playGame.gameWon();
            }
        }
    }

    static int newX;
    static int newY;
    static int gridBoxSize;
    static class MouseListener implements EventHandler<MouseEvent> {
        ImageView car;
        public MouseListener( ImageView car ) {
            this.car = car;
        }
        public void handle(MouseEvent e) {
            gridBoxSize  = playGame.getBoxSize();
            double x = e.getSceneX() - 300;
            double y = e.getSceneY() - 90;
            double afterX = e.getSceneX()-300-(firstX-carX);
            double afterY = e.getSceneY()-90-(firstY-carY);
            boolean empty = true;
            if ( curr.getCarDirection() == 1 || curr.getCarDirection() == 3 ) {
                boolean neg = y < firstY;
                double posY;
                if ( neg ) {
                    posY = Math.max(afterY, carY-gridBoxSize*moveForward);
                }
                else {
                    posY = Math.min(afterY, carY+gridBoxSize*moveBackward);
                }
                car.setLayoutY(posY);
                newX = (int) Math.round(posY / gridBoxSize);
                engine.updateCarX(curr, newX);
            } else {
                boolean neg = x < firstX;
                double posX;
                if ( neg ) {
                    posX = Math.max(afterX, carX-gridBoxSize*moveForward);
                }
                else {
                    posX = Math.min(afterX, carX+gridBoxSize*moveBackward);
                }
                car.setLayoutX(posX);
                newY = (int) Math.round(posX / gridBoxSize);
                engine.updateCarY(curr, newY);
            }
            //if ( curr.isPlayer() && curr.getVerticalY() == 5 ){

            //}
            engine.updateBlockinfo();
        }
    }
}
