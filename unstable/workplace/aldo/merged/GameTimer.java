import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;


/**
    The following is the implementation of the Gametimer Class.
    It provides for a timer for the game.
    The Engine can set the maximum time of the timer for the timer countdown.
    Otherwise the timer will have running time until it gets stopped.
 */


public class GameTimer extends TimerTask {

     //the attributes of the class
     private int timeInSeconds;
     Timer  countdownTimer;
     TimerRunnable toRunClass;
    
    //default constructor
    public GameTimer(){
        timeInSeconds = 0;
    }

    //provides for the rundown time in seconds
    public GameTimer(int secondsCountDown){
        timeInSeconds = secondsCountDown;
    }

    //sets the countdown time
    public void setCountDown(int seconds){
        timeInSeconds = seconds;
    }

    //returns the countdown time
    public int getCountDown(){
        return timeInSeconds;
    }

    //returns true if the countdown is running
    public boolean isCountDownRunning(){
        return  (timeInSeconds > 0);
    }


    public void startCountDown(TimerRunnable runnable){
        toRunClass = runnable;
        countdownTimer = new Timer();
        countdownTimer.scheduleAtFixedRate(this, 0, 1000);
    }

    public void stopCountDown(){
        countdownTimer.cancel();
    }

    @Override
    public void run() {
        toRunClass.runOnTimer();
        timeInSeconds--;
        if (!isCountDownRunning()){
            countdownTimer.cancel();
        }

    }


    public static void main(String args[]){
        int noSeconds = 10;
        GameTimer gametimer = new GameTimer(noSeconds);
        gametimer.startCountDown(new classHelloPrinter(gametimer));
    }


 }
