public class DashboardData {
    public DashboardData(){

    }
    private int winPercentage = 80;
    private int losePercentage = 20;
    private int sixToSixData = 8;
    private int eightToEightData = 6;
    private int tenToTenData = 4;
    private int noOfStars = 0;
    private double gameStatus = 25;

    public int getWinPercentage(){
        return winPercentage;
    }

    public void setNoOfStars(int stars){noOfStars = stars;}

    public int getLosePercentage(){
        return losePercentage;
    }

    public int getSixToSixData(){
        return sixToSixData;
    }

    public int getEightToEightData(){
        return eightToEightData;
    }

    public int getTenToTenData(){
        return tenToTenData;
    }

    public int getNoOfStars(){
        return noOfStars;
    }

    public double getGameStatus(){
        return gameStatus / 100;
    }

}
