
public class Charts {
	private int winRatio;
	private int[] progression;
	private int gameStatus;
	private int stars;
	
	public Charts(int winRatio, int[] progression, int gameStatus, int stars) {
		this.winRatio = winRatio;
		this.progression = progression;
		this.gameStatus = gameStatus;
		this.stars = stars;
	}

	public int getWinRatio() {
		return winRatio;
	}

	public void setWinRatio(int winRatio) {
		this.winRatio = winRatio;
	}

	public int[] getProgression() {
		return progression;
	}

	public void setProgression(int[] progression) {
		this.progression = progression;
	}

	public int getGameStatus() {
		return gameStatus;
	}

	public void setGameStatus(int gameStatus) {
		this.gameStatus = gameStatus;
	}

	public int getStars() {
		return stars;
	}

	public void setStars(int stars) {
		this.stars = stars;
	}
	
}
