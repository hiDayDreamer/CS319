//this is an interface that needs to be implemented by each classs that will use
// the timer. The idea is that the timer should run on GameTimer and the executing class
// can simply execute runOnTimer() accordingly to make the changes.

public interface TimerRunnable{
    public void runOnTimer();
} 
