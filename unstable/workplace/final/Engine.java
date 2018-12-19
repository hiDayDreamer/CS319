import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.util.LinkedList;
import java.io.File;

public class Engine{

	static class Node{
		int x1Move;
		int y1Move;
		int x2Move;
		int y2Move;
		Node next;
	}

	private Map selectedMap;
	private GameTimer timer;
	private GameSolver hint;
	private StarManager stars;
	private GameManager gameManager;
	private LinkedList<Node> moves;
	private int volume;

	public Engine(GameManager manager) {
		gameManager = manager;
		timer = new GameTimer();
		hint = new GameSolver();
		stars = new StarManager();
		moves = new LinkedList<Node>();
		volume = 50;
		selectedMap = null;
	}


	/**
	 *
	 * @param x1
	 * @param y1
	 * @param x2
	 * @param y2
	 */
	public boolean moveCar(int x1, int y1, int x2, int y2) {
		// TODO - implement Engine.moveCar
		// throw new UnsupportedOperationException();
		Node newNode = new Node();
		newNode.x1Move = x1;
		newNode.y1Move = y1;
		newNode.x2Move = x2;
		newNode.y2Move = y2;
		if(!(moves.getLast().equals(newNode))) {
			moves.add(newNode);
			return true;
		}
		return false;
	}

	/**
	 *
	 * @param clock
	 */
	public boolean gameLost(GameTimer clock) {
		// TODO - implement Engine.gameLost
//		throw new UnsupportedOperationException();
		/*if(clock.getOutOfTime())
		{
			return true;
		}*/
		return false;
	}

	public boolean gameWon() {
		// TODO - implement Engine.gameWon
		// throw new UnsupportedOperationException();
		if(selectedMap.getPlayer().getX() == 5 && selectedMap.getPlayer().getY() == 2)
		{
			return true;
		}
		return false;
	}

	public void updateStars() {
		// TODO - implement Engine.updateStars
		int starsGained = calculateStars();
		stars.increaseStars(starsGained);
		//throw new UnsupportedOperationException();
	}

	public int calculateStars() {
		int starsReturned = 0;
		// TODO - implement Engine.calculateStars
		//throw new UnsupportedOperationException();
		if(timer.getTimerOn() == false)
		{
			if(moves.size() - selectedMap.getMinMoveCount() <= 4)
			{
				starsReturned = stars.getThreeStars();
				//stars.increaseStars(stars.getThreeStars());

			}
			if(moves.size() - selectedMap.getMinMoveCount() > 4 && moves.size() - selectedMap.getMinMoveCount() <= 7)
			{
				starsReturned = stars.getTwoStars();
				//stars.increaseStars(stars.getTwoStars());
			}
			if(moves.size() - selectedMap.getMinMoveCount() > 7)
			{
				starsReturned = stars.getOneStar();
				//stars.increaseStars(stars.getOneStar());
			}
		}
		else if(timer.getTimerOn() == true)
		{
			int difficulty = selectedMap.getDifficulty();
			/*if(timer.getOutOfTime())
			{
				starsReturned =  0;
			}*/
			//if(!timer.getOutOfTime()) {
				if(timer.getTimeSpent() <= difficulty * 120000/4)
				{
					starsReturned = stars.getThreeStars();
				}
				else if(timer.getTimeSpent() > difficulty * 120000/ 4 && timer.getTimeSpent() <= difficulty * 120000 /3) {
					starsReturned = stars.getTwoStars();
				}
				else if(timer.getTimeSpent() > difficulty * 120000/ 3 && timer.getTimeSpent() <= difficulty * 120000) {
					starsReturned = stars.getOneStar();
				}
			//}
		}
		return starsReturned;
	}

	public void playSound() {
		// TODO - implement Engine.playSound
//		throw new UnsupportedOperationException();

		if(gameWon()) {
//			Media gameWonSound = new Media(new File("gameWonSound.mp3").toURI());
//			MediaPlayer mediaPlayer = new MediaPlayer(gameWonSound);                                  IN THE FILE MANAGER
			gameManager.getDataStorage().getSoundEffects()[0].play();
		}
		else if(gameLost(timer)) {
//			Media gameLostSound = new Media(new File("gameLostSound.mp3").toURI());
//			MediaPlayer mediaPlayer = new MediaPlayer(gameLostSound);					    		  IN THE FILE MANAGER
			gameManager.getDataStorage().getSoundEffects()[1].play();
		}
	}

//	public int getHints() {
//		// TODO - implement Engine.getHints
//		throw new UnsupportedOperationException();
//	}

	public boolean undo() {
		// TODO - implement Engine.undo
		// throw new UnsupportedOperationException();
		if(moves.size() != 0)
		{
			moves.removeLast();
			return true;
		}
		return false;
	}

	public Map getSelectedMap() {
		return this.selectedMap;
	}

	/**
	 *
	 * @param selectedMap
	 */
	public void setSelectedMap(Map selectedMap) {
		this.selectedMap = selectedMap;
	}

	public GameTimer getTimer() {
		return this.timer;
	}

	/**
	 *
	 * @param timer
	 */
	public void setTimer(GameTimer timer) {
		this.timer = timer;
	}

	public GameSolver getHint() {
		return this.hint;
	}

	/**
	 *
	 * @param hint
	 */
	public void setHint(GameSolver hint) {
		this.hint = hint;
	}

	public int[] getStars() {
		return this.stars.getStars();
	}

	/**
	 *
	 * @param stars
	 */
	public void setStars(StarManager stars) {
		this.stars = stars;
	}

	public LinkedList getMoves() {
		return this.moves;
	}

	/**
	 *
	 * @param moves
	 */
	public void setMoves(LinkedList moves) {
		this.moves = moves;
	}

	public int getVolume() {
		return this.volume;
	}

	/**
	 *
	 * @param volume
	 */
	public void setVolume(int volume) {
		this.volume = volume;
	}

}
