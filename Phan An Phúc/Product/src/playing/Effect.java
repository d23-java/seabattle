package playing;

public class Effect {
	public static void isHit() {
		System.out.println(Color.red + " __    __   __  .___________.\r\n" + "|  |  |  | |  | |           |\r\n"
				+ "|  |__|  | |  | `---|  |----`\r\n" + "|   __   | |  |     |  |     \r\n"
				+ "|  |  |  | |  |     |  |     \r\n" + "|__|  |__| |__|     |__|     " + Color.ANSI_Reset);
	}

	public static void isSunk() {
		System.out.println(Color.blue + "     _______. __    __  .__   __.  __  ___\r\n"
				+ "    /       ||  |  |  | |  \\ |  | |  |/  /\r\n" + "   |   (----`|  |  |  | |   \\|  | |  '  / \r\n"
				+ "    \\   \\    |  |  |  | |  . `  | |    <  \r\n"
				+ ".----)   |   |  `--'  | |  |\\   | |  .  \\ \r\n" + "|_______/     \\______/  |__| \\__| |__|\\__\\"
				+ Color.ANSI_Reset);
		System.out.println();
	}

	public static void isVictory() {
		System.out.println(Color.yellow + "____    ____  ______    __    __     ____    __    ____  __  .__   __.\r\n"
				+ "\\   \\  /   / /  __  \\  |  |  |  |    \\   \\  /  \\  /   / |  | |  \\ |  |\r\n"
				+ " \\   \\/   / |  |  |  | |  |  |  |     \\   \\/    \\/   /  |  | |   \\|  |\r\n"
				+ "  \\_    _/  |  |  |  | |  |  |  |      \\            /   |  | |  . `  |\r\n"
				+ "    |  |    |  `--'  | |  `--'  |       \\    /\\    /    |  | |  |\\   |\r\n"
				+ "    |__|     \\______/   \\______/         \\__/  \\__/     |__| |__| \\__|" + Color.ANSI_Reset);
	}

	public static void isMiss() {
		System.out.println(Color.green + ".___  ___.  __       _______.     _______.\r\n"
				+ "|   \\/   | |  |     /       |    /       |\r\n" + "|  \\  /  | |  |    |   (----`   |   (----`\r\n"
				+ "|  |\\/|  | |  |     \\   \\        \\   \\    \r\n"
				+ "|  |  |  | |  | .----)   |   .----)   |   \r\n" + "|__|  |__| |__| |_______/    |_______/    "
				+ Color.ANSI_Reset);
	}

	public static void delay(int seconds) {
		try {
			Thread.sleep(seconds * 1000);
		} catch (InterruptedException e) {
			System.err.println("Đã xảy ra lỗi khi delay: " + e.getMessage());
			Thread.currentThread().interrupt();
		}
	}
}
