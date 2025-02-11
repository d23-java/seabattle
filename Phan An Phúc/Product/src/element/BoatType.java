package element;

public enum BoatType {
	PATROL_BOAT("Tàu tuần tra", 2, 2), SUBMARINE("Tàu ngầm", 3, 1), DESTROYER_BOAT("Tàu khu trục", 4, 1),
	BATTLE_SHIP("Tàu chiến hạm", 5, 1);

	private final String name;
	private final int size;
	private final int soLuong;

	BoatType(String name, int size, int quantity) {
		this.name = name;
		this.size = size;
		this.soLuong = quantity;
	}

	public String getName() {
		return name;
	}

	public int getSize() {
		return size;
	}

	public int getSoLuong() {
		return soLuong;
	}
}
