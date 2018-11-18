import javafx.application.Application;
import javafx.stage.Stage;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public class GameManager extends Application{

    private WindowManager primaryStage1;
   // private LevelsPane h = new LevelsPane("8x8");

    public static void main(String[] args) {
        launch(args);
    }

    public GameManager(){
        primaryStage1 = new WindowManager();
    }

    @Override
    public void start(Stage primaryStage) {

        primaryStage = primaryStage1;
        //primaryStage1.updateMiddlePanel(h);
        primaryStage1.addHandler( new ButtonListener(1));
    }


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
             else if ( index == 4){
                Settings newPane = new Settings();
                newPane.addHandler( new ButtonListener(0));
                primaryStage1.updateMiddlePanel(newPane);
             }
             else if ( index == 6){
                // this goes to the levelsPane from the dimensions panel
                LevelsPane newPane = new LevelsPane("6x6");
                newPane.addHandler( new ButtonListener(0));
                primaryStage1.updateMiddlePanel(newPane);
             }
             else if ( index == 8){
                LevelsPane newPane = new LevelsPane("8x8");
                newPane.addHandler( new ButtonListener(0));
                primaryStage1.updateMiddlePanel(newPane);
             }
             else if ( index == 10){
               LevelsPane newPane = new LevelsPane("10x10");
               newPane.addHandler( new ButtonListener(0));
               primaryStage1.updateMiddlePanel(newPane);
            }
            else if ( index >= 11 && index <= 25) {
               PlayGame newPane = new PlayGame(new Map(6));
               newPane.addHandler( new ButtonListener(6));
               primaryStage1.updateMiddlePanel(newPane);

            }
          }

          public void setIndex( int index) {
             this.index = index;
          }

          public ButtonListener clone() {
             return new ButtonListener();
          }
       }

}
