import javafx.application.Application;
import javafx.stage.Stage;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.image.ImageView;
import javafx.scene.control.Button;
import javafx.scene.media.*;
import java.io.File;

public class GameManager extends Application{

    private WindowManager primaryStage1;
    private DashboardData data;
    private DataStorage dataStorage;
    private static Engine engine;

    public static void main(String[] args) {
        launch(args);
    }

    double sliderVolume = 40;
    public GameManager(){
        super();
        primaryStage1 = new WindowManager();
        data = new DashboardData();
        dataStorage = new DataStorage();
        engine = new Engine(this);
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
            DashboardPane newPane = new DashboardPane(data);
            newPane.addHandler( new ButtonListener(0));
            primaryStage1.updateMiddlePanel(newPane);
        }
        else if ( index == 4){
            newSettingsPane = new Settings();
            newSettingsPane.addHandler( new ButtonListener(0));
            primaryStage1.updateMiddlePanel(newSettingsPane);
        }
        else if ( index == 6){
            // this goes to the levelsPane from the dimensions panel
            LevelsPane newPane = new LevelsPane("6X6", engine.getStars());
            newPane.addHandler( new ButtonListener(0));
            primaryStage1.updateMiddlePanel(newPane);
        }
        else if ( index == 8){
            LevelsPane newPane = new LevelsPane("8X8", engine.getStars());
            newPane.addHandler( new ButtonListener(0));
            primaryStage1.updateMiddlePanel(newPane);
        }
        else if ( index == 10){
            LevelsPane newPane = new LevelsPane("10X10", engine.getStars());
            newPane.addHandler( new ButtonListener(0));
            primaryStage1.updateMiddlePanel(newPane);
        }
        else if ( index >= 11 && index <= 25) {
            boolean timerMode;
            if (newSettingsPane == null){
                timerMode = false;
            } else {
                timerMode = newSettingsPane.isTimerToogleOn();
            }
            Map selectedMap = dataStorage.getMap().clone();
            engine.setSelectedMap(selectedMap);
            playGame = new PlayGame(selectedMap,timerMode);
            playGame.addHandler( new ButtonListener(6));
            playGame.setSoundVolume(sliderVolume);
            playGame.setStars(engine.getStars());
            primaryStage1.updateMiddlePanel(playGame);

        }
        else if (index == 30){
            System.out.println("toogle");
            newSettingsPane.toogleTimer();
            newSettingsPane.addHandler( new ButtonListener(0));
            //primaryStage1.updateMiddlePanel(newSettingsPane);
        }
        else if (index == 31){
            //this is for start tiemr button
            System.out.println("start timer");
            playGame.startTimer();
            //playGame.addHandler( new ButtonListener(0));
            //primaryStage1.updateMiddlePanel(newSettingsPane);

        }
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

    static class MouseListener implements EventHandler<MouseEvent> {
        ImageView car;
        public MouseListener( ImageView car ) {
            this.car = car;
        }
        public void handle(MouseEvent e) {
            double x = e.getSceneX() - 300;
            double y = e.getSceneY() - 90;
            double afterX = e.getSceneX()-300-(firstX-carX);
            double afterY = e.getSceneY()-90-(firstY-carY);
            boolean empty = true;
            int gridBoxSize = playGame.getBoxSize();
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
                int newX = (int) Math.round(posY / gridBoxSize);
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
                int newY = (int) Math.round(posX / gridBoxSize);
                engine.updateCarY(curr, newY);
            }
            if ( curr.isPlayer() && curr.getVerticalY() == 5 ){

            }
            engine.updateBlockinfo();
        }
    }
}
