import java.io.File;

public class SoundEffect {
	private boolean effectStatus;
	private String url;
	private File gameWon; //specified the file format
	private File gameLost;
	private int defaultVolume;

	public SoundEffect(boolean effectStatus, String url, File gameWon, File gameLost, int defaultVolume) {
		this.effectStatus = effectStatus;
		this.url = url;
		this.gameWon = gameWon;
		this.gameLost = gameLost;
		this.defaultVolume = defaultVolume;
	}

	public SoundEffect(String url, int defaultVolume) {
		this.url = url;
		this.defaultVolume = defaultVolume;
	}

	public boolean isEffectStatus() {
		return effectStatus;
	}

	public void setEffectStatus(boolean effectStatus) {
		this.effectStatus = effectStatus;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public File getGameWon() {
		return gameWon;
	}

	public void setGameWon(File gameWon) {
		this.gameWon = gameWon;
	}

	public File getGameLost() {
		return gameLost;
	}

	public void setGameLost(File gameLost) {
		this.gameLost = gameLost;
	}

	public int getDefaultVolume() {
		return defaultVolume;
	}

	public void setDefaultVolume(int defaultVolume) {
		this.defaultVolume = defaultVolume;
	}

}
