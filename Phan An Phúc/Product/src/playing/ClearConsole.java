package playing;

public class ClearConsole {
	public void clearConsole() {
		try {
			System.out.print("\033[H\033[2J");
			System.out.flush();
		} catch (Exception e) {
			System.out.println("Lỗi khi xóa màn hình.");
		}
	}

}
