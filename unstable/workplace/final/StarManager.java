public class StarManager {
	private int[] stars;
	private int totalScore;
	final private int threeStars = 3;
	final private int twoStars = 2;
	final private int oneStar = 1;

	public StarManager() {
		stars = new int[10];
	}

	public void increaseStars(int starAmount) {
		// TODO - implement starManager.increaseStars
		//throw new UnsupportedOperationException();
		if(starAmount == threeStars)
		{
			totalScore += threeStars;
		}
		if(starAmount == twoStars)
		{
			totalScore += twoStars;
		}
		if(starAmount == oneStar)
		{
			totalScore += oneStar;
		}



	}

	public void decreaseStars(int starAmount) {
		// TODO - implement starManager.decreaseStars
		throw new UnsupportedOperationException();
	}

	public int[] getStars() {
		return stars;
	}

	public int getTotalScore() {
		return this.totalScore;
	}

	/**
	 *
	 * @param totalScore
	 */
	public void setTotalScore(int totalScore) {
		this.totalScore = totalScore;
	}

	public int getThreeStars() {
		return threeStars;
	}


	public int getTwoStars() {
		return twoStars;
	}

	public int getOneStar() {
		return oneStar;
	}


}
