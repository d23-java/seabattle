package element;

import java.util.ArrayList;

public class Boat {
	private int numOfShip = 5;
	private BoatType type;
	private ArrayList<int[]> coordinates;
	private int size;
	private boolean hadSunk;

	public Boat(BoatType type) {
		this.type = type;
		this.coordinates = new ArrayList<>();
		this.size = type.getSize();
	}

	public void addCoordinate(int x, int y) {
		coordinates.add(new int[] { x, y });
	}

	public boolean isDestroyed(Cell[][] map) {
		for (int[] coord : coordinates) {
			int x = coord[0];
			int y = coord[1];
			if (!map[x][y].isHit()) {
				return false;
			}
		}
		return true;
	}

	public boolean isSunk(int x, int y) {
		if (hadSunk) {
			return false;
		}
		for (int[] coord : coordinates) {
			if (coord[0] == x && coord[1] == y) {
				--size;
			}
		}
		if (size == 0) {
			hadSunk = true;
			numOfShip--;
			return true;
		} else
			return false;
	}

	public String getName() {
		return type.getName();
	}

	public int getSize() {
		return type.getSize();
	}

	public int getSoLuong() {
		return type.getSoLuong();
	}

	public ArrayList<int[]> getCoordinates() {
		return coordinates;
	}

	public int getNumOfShip() {
		return numOfShip;
	}

}
