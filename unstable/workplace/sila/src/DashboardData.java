import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

public class DashboardData {
    public DashboardData(){

    }
    private int winPercentage = 80;
    private int losePercentage = 20;
    private int sixToSixData = 8;
    private int eightToEightData = 6;
    private int tenToTenData = 4;
    public int setStars(){
    	int stars = 0;
    	try{
            BufferedReader br = new BufferedReader(new FileReader("src/MyFile.txt")); 
            String s = br.readLine();
            stars = Integer.parseInt(s);
            br.close();
        }
        catch(IOException e) {
        	e.printStackTrace();
        }
    	return stars;
    }
    
    
    private int noOfStars = setStars();
    private double gameStatus = 25;

    public int getWinPercentage(){
        return winPercentage;
    }

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
