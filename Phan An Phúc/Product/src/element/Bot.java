package element;

import java.util.ArrayList;
import java.util.Random;

public class Bot extends Player {
	private ArrayList<int[]> targetHit;
	private Random random;

	public ArrayList<int[]> getTargetHit() {
		return targetHit;
	}

	public Bot(String namePlayer, ArrayList<Boat> boats, Cell[][] mapPlayer) {
		super(namePlayer, boats, mapPlayer);
		this.targetHit = new ArrayList<>();
		this.random = new Random();
	}

	public boolean inTargetShip() {
		return !targetHit.isEmpty();
	}

	public void detechTargetShip(int x, int y, Cell[][] map) {
		// Kiểm tra row xem vị trí x-1 và x+1 bị bắn chưa
		boolean hitLeft = (y > 0 && map[x][y - 1].isHit() && map[x][y - 1].hasBoat());
		boolean hitRight = (y < map[0].length - 1 && map[x][y + 1].isHit() && map[x][y + 1].hasBoat());

		if (hitLeft || hitRight) {
			if (y > 0 && !map[x][y - 1].isHit()) {
				targetHit.add(new int[] { x, y - 1 });
			}
			if (y < map[0].length - 1 && !map[x][y + 1].isHit()) {
				targetHit.add(new int[] { x, y + 1 });
			}
			return;
		}

		// Kiểm tra column xem vị trí y-1 và y+1 bị bắn chưa
		boolean hitUp = (x > 0 && map[x - 1][y].isHit() && map[x - 1][y].hasBoat());
		boolean hitDown = (x < map.length - 1 && map[x + 1][y].isHit() && map[x + 1][y].hasBoat());

		if (hitUp || hitDown) {
			if (x > 0 && !map[x - 1][y].isHit()) {
				targetHit.add(new int[] { x - 1, y });
			}
			if (x < map.length - 1 && !map[x + 1][y].isHit()) {
				targetHit.add(new int[] { x + 1, y });
			}
			return;
		}

		if (x > 0 && !map[x - 1][y].isHit())
			targetHit.add(new int[] { x - 1, y });
		if (x < map.length - 1 && !map[x + 1][y].isHit())
			targetHit.add(new int[] { x + 1, y });
		if (y > 0 && !map[x][y - 1].isHit())
			targetHit.add(new int[] { x, y - 1 });
		if (y < map[0].length - 1 && !map[x][y + 1].isHit())
			targetHit.add(new int[] { x, y + 1 });
	}

	public int[] getNextCoordinates(Cell[][] map) {
		while (!targetHit.isEmpty()) {
			int[] nextTarget = targetHit.remove(0);
			int x = nextTarget[0];
			int y = nextTarget[1];

			if (!map[x][y].isHit()) {
				return nextTarget;
			}
		}
		return getRandomCoordinates(map);

	}

	public int[] getRandomCoordinates(Cell[][] map) {
		int x = random.nextInt(map.length);
		int y = random.nextInt(map[0].length);
		while (map[x][y].isHit()) {
			x = random.nextInt(map.length);
			y = random.nextInt(map[0].length);
		}

		return new int[] { x, y };
	}

}
