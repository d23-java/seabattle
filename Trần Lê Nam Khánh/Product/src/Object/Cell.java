package Object;
import Color.ConsoleColors;
import java.lang.String;
import java.util.Objects;

public class Cell {
    private String state;

    public Cell(){
        this.state = ConsoleColors.BLUE_BACKGROUND_BRIGHT + "~" + ConsoleColors.RESET;
    }

    public String getState(){
        return state;
    }
    public void setState(String state){
        this.state = state;
    }
    public boolean isSunk(){
        return Objects.equals(state, ConsoleColors.GREEN_BACKGROUND + "S" + ConsoleColors.RESET);
    }
    public boolean isHit(){
        return Objects.equals(state, ConsoleColors.GREEN_BACKGROUND + "X" + ConsoleColors.RESET);
    }
    public boolean isMiss(){
        return Objects.equals(state, ConsoleColors.RED_BACKGROUND + "O" + ConsoleColors.RESET);
    }
}
