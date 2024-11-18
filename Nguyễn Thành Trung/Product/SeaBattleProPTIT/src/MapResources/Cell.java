package MapResources;

public class Cell {
    private String[] cell =
            {" ", " ", " ", "|"};

    public Cell() {
        this.cell[0] = "\033[104m\033[30m_\033[0m";
        this.cell[1] = "\033[104m\033[30m~\033[0m";
        this.cell[2] = "\033[104m\033[30m_\033[0m";
    }

    public void setCentreOfCell(String informationOfCell) {
        cell[1] = "\033[104m\033[30m" + informationOfCell + "\033[0m";
    }

    public void setCellColor(String ANSIcode) {
        this.cell[0] = "\033[" + ANSIcode + "m\033[30m_\033[0m";
        this.cell[1] = "\033[" + ANSIcode + "m\033[30m~\033[0m";
        this.cell[2] = "\033[" + ANSIcode + "m\033[30m_\033[0m";
    }

    public void setLargeNumberColumnCell() {

    }

    public void showCell() {
        for (int i = 0; i < 4; ++i) {
            System.out.print(cell[i]);
        }
    }
}
