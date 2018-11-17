package sample;

import javafx.application.Application;
import javafx.stage.Stage;

public class GameManager extends Application{

    private WindowManager primaryStage1;
    private Dimensions h = new Dimensions();

    public static void main(String[] args) {
        launch(args);
    }

    public GameManager(){
        primaryStage1 = new WindowManager();
    }

    @Override
    public void start(Stage primaryStage) {

        primaryStage = primaryStage1;
        primaryStage1.updateMiddlePanel(h);
    }

}
