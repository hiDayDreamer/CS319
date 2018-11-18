import java.awt.image.BufferedImage;

public class Skin {
	private int skinId; //WE NEED SKIN ID TO SEARCH IN THE ARRAY
	private int starsNeeded;
	private BufferedImage carSkin;
	private boolean available;

	public Skin(int skinId, int starsNeeded, BufferedImage carSkin, boolean available)
	{
		this.skinId = skinId;
		this.starsNeeded = starsNeeded;
		this.carSkin = carSkin;
		this.available = available;
	}

	public Skin(int skinId, BufferedImage carSkin, boolean available)
	{
		this.skinId = skinId;
		this.starsNeeded = starsNeeded;
		this.carSkin = carSkin;
		this.available = available;
	}

	public int getSkinId() {
		return skinId;
	}

	public void setSkinId(int skinId) {
		this.skinId = skinId;
	}

	public int getStarsNeeded() {
		return starsNeeded;
	}

	public void setStarsNeeded(int starsNeeded) {
		this.starsNeeded = starsNeeded;
	}

	public BufferedImage getCarSkin() {
		return carSkin;
	}

	public void setCarSkin(BufferedImage carSkin) {
		this.carSkin = carSkin;
	}

	public boolean isAvailable() {
		return available;
	}

	public void setAvailable(boolean available) {
		this.available = available;
	}

}
