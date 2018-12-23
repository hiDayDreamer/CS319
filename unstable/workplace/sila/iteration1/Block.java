
public class Block {
	private boolean isOccupied;
	private boolean isFinishBlock;
	private int x;
	private int y;
	private int endX;
	private int endY;
	private boolean isBlink;
	

	public Block(int x, int y) {
		this.isOccupied = false;
		this.isFinishBlock = false;
		this.x = x;
		this.y = y;
		this.isBlink = false;
	}
	
	public Block(boolean isOccupied, boolean isFinishBlock, int x, int y, boolean isBlink) {
		this.isOccupied = isOccupied;
		this.isFinishBlock = isFinishBlock;
		this.x = x;
		this.y = y;
		this.isBlink = isBlink;
	}
	
	public void updateBlockInfo() {
		isOccupied = !isOccupied;
	}
	public boolean isOccupied() { //previously getBlockInfo()
		return isOccupied;
	}

	public void setOccupied(boolean isOccupied) {
		this.isOccupied = isOccupied;
	}

	public boolean isFinishBlock() { //previously isFinished()
		return isFinishBlock;
	}

	public void setFinishBlock(boolean isFinishBlock) {
		this.isFinishBlock = isFinishBlock;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getHorizontalX() {
		return endX;
	}
	
	public int getY() {
		return y;
	}
	
	public int getVerticalY() {
		return endY;
	}
	
	public void setY(int y) {
		this.y = y;
	}

	public void setHorizontalX(int x, int endX) {
		this.x = x;
		this.endX = endX;
	}
	
	public boolean isBlink() {
		return isBlink;
	}

	public void setBlink(boolean isBlink) {
		this.isBlink = isBlink;
	}
	
}
