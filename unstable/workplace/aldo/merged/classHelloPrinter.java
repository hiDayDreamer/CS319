import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;


public class classHelloPrinter implements TimerRunnable{
    GameTimer tester;
    public classHelloPrinter(GameTimer setter){
        tester = setter;
    }
    public void runOnTimer(){
        System.out.println("Aldo");
        System.out.println(tester.getCountDown());
    }
}