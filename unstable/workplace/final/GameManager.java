import javafx.application.Application;
import javafx.stage.Stage;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.image.ImageView;
import javafx.scene.control.Button;

public class GameManager extends Application{

    private WindowManager primaryStage1;
    private DashboardData data = new DashboardData();

    public static void main(String[] args) {
        launch(args);
    }

    public GameManager(){
        primaryStage1 = new WindowManager();
    }

    @Override
    public void start(Stage primaryStage) {

        primaryStage = primaryStage1;
        primaryStage1.addHandler( new ButtonListener(1));
    }
   Settings newSettingsPane;
   PlayGame playGame;

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
                LevelsPane newPane = new LevelsPane("6X6");
                newPane.addHandler( new ButtonListener(0));
                primaryStage1.updateMiddlePanel(newPane);
             }
             else if ( index == 8){
                LevelsPane newPane = new LevelsPane("8X8");
                newPane.addHandler( new ButtonListener(0));
                primaryStage1.updateMiddlePanel(newPane);
             }
             else if ( index == 10){
               LevelsPane newPane = new LevelsPane("10X10");
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
               playGame = new PlayGame(new Map(6),timerMode);
               playGame.addHandler( new ButtonListener(6));
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

}
