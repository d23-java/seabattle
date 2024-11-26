package playing;

import java.util.Random;
import java.util.Scanner;

import Main.Input;
import element.Boat;
import element.BoatType;
import element.Bot;
import element.Cell;
import element.Player;
import element.PlayerOpponent;

public class BeforePlaying {
	static Scanner sc = Input.getScanner();
	private static Random random = new Random();

	public void setUpBoatsForPlayer(Player player, boolean checkTypeOfSetUp) {
		for (BoatType type : BoatType.values()) {
			for (int i = 0; i < type.getSoLuong(); i++) {
				System.out.println("Đặt tàu: " + type.getName() + " (Kích thước: " + type.getSize() + ")");
				boolean placed = false;
				if (checkTypeOfSetUp) {
					while (!placed) {
						System.out.print("Nhập tọa độ đầu (x, y): ");
						int x = Integer.parseInt(sc.nextLine());
						int y = Integer.parseInt(sc.nextLine());
						--x;
						--y;
						System.out.print("Chọn hướng (1. Ngang, 2. Dọc): ");
						int direction = Integer.parseInt(sc.nextLine());

						if (isValidPlacement(player.getMapPlayer(), x, y, type.getSize(), direction)) {
							placeBoat(player, new Boat(type), x, y, direction);
							player.mapSetUp();
							placed = true;
						} else {
							System.out.println("Vị trí không hợp lệ, thử lại!");
						}
					}
				} else {
					while (!placed) {
						int x = random.nextInt(player.getMapPlayer().length);
						int y = random.nextInt(player.getMapPlayer()[0].length);
						int direction = random.nextInt(2) + 1;

						if (isValidPlacement(player.getMapPlayer(), x, y, type.getSize(), direction)) {
							placeBoat(player, new Boat(type), x, y, direction);
							placed = true;
						}
					}
				}
			}
		}
		if (checkTypeOfSetUp == false)
			player.mapSetUp();
	}

	private boolean isValidPlacement(Cell[][] map, int x, int y, int size, int direction) {
		if (direction == 1 && y + size > map[0].length)
			return false;
		if (direction == 2 && x + size > map.length)
			return false;

		for (int i = 0; i < size; i++) {
			if ((direction == 1 && map[x][y + i].hasBoat()) || (direction == 2 && map[x + i][y].hasBoat())) {
				return false;
			}
		}
		return true;
	}

	public void setUpBoatsForOpponent(PlayerOpponent player, boolean checkTypeOfSetUp) {
		for (BoatType type : BoatType.values()) {
			for (int i = 0; i < type.getSoLuong(); i++) {
				System.out.println("Đặt tàu: " + type.getName() + " (Kích thước: " + type.getSize() + ")");
				boolean placed = false;
				if (checkTypeOfSetUp) {
					while (!placed) {
						System.out.print("Nhập tọa độ đầu (x, y): ");
						int x = Integer.parseInt(sc.nextLine());
						int y = Integer.parseInt(sc.nextLine());
						--x;
						--y;
						System.out.print("Chọn hướng (1. Ngang, 2. Dọc): ");
						int direction = Integer.parseInt(sc.nextLine());

						if (isValidPlacement(player.getMapPlayer(), x, y, type.getSize(), direction)) {
							placeBoat(player, new Boat(type), x, y, direction);
							player.mapSetUp();
							placed = true;
						} else {
							System.out.println("Vị trí không hợp lệ, thử lại!");
						}
					}
				} else {
					while (!placed) {
						int x = random.nextInt(player.getMapPlayer().length);
						int y = random.nextInt(player.getMapPlayer()[0].length);
						int direction = random.nextInt(2) + 1;

						if (isValidPlacement(player.getMapPlayer(), x, y, type.getSize(), direction)) {
							placeBoat(player, new Boat(type), x, y, direction);
							placed = true;
						}
					}
				}
			}
		}
		if (checkTypeOfSetUp == false)
			player.mapSetUp();
	}

	public void setUpBoatsForBot(Bot player2) {
		for (BoatType type : BoatType.values()) {
			for (int i = 0; i < type.getSoLuong(); i++) {
				boolean placed = false;
				while (!placed) {
					int x = random.nextInt(player2.getMapPlayer().length);
					int y = random.nextInt(player2.getMapPlayer()[0].length);
					int direction = random.nextInt(2) + 1;

					if (isValidPlacement(player2.getMapPlayer(), x, y, type.getSize(), direction)) {
						placeBoat(player2, new Boat(type), x, y, direction);
						placed = true;
					}
				}
			}
		}
	}

	private void placeBoat(Player player, Boat boat, int x, int y, int direction) {
		for (int i = 0; i < boat.getSize(); i++) {
			if (direction == 1) {
				player.getMapPlayer()[x][y + i].setBoat(true);
				boat.addCoordinate(x, y + i);
			} else {
				player.getMapPlayer()[x + i][y].setBoat(true);
				boat.addCoordinate(x + i, y);
			}
		}
		player.getBoats().add(boat);
	}

}
