package element;

import playing.Color;

public class Cell {
	private boolean isHit;
	private boolean hasBoat;

	public Cell() {
		this.isHit = false;
		this.hasBoat = false;
	}

	public boolean isHit() {
		return isHit;
	}

	public void setHit(boolean hit) {
		isHit = hit;
	}

	public boolean hasBoat() {
		return hasBoat;
	}

	public void setBoat(boolean hasBoat) {
		this.hasBoat = hasBoat;
	}

	@Override
	public String toString() {
		if (isHit) {
			return hasBoat ? Color.red + " X" + Color.ANSI_Reset : Color.purple + " O" + Color.ANSI_Reset;
		} else {
			return "~";
		}
	}

	public String toString(boolean choice) {
		if (hasBoat) {
			return Color.blue + " T" + Color.ANSI_Reset;
		} else {
			return "~";
		}
	}

	public String toString(boolean choice, boolean checkHit) {
		if (isHit) {
			return hasBoat ? Color.red + " X" + Color.ANSI_Reset : Color.purple + " O" + Color.ANSI_Reset;
		} else if (!isHit) {
			return hasBoat ? Color.blue + " T" + Color.ANSI_Reset : "~";
		} else {
			return "~";
		}
	}

}
