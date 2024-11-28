package gameObjects;

import enums.Color;

public class Cell {
    private String content = "0";
    private String backgroundColor;
    private String contentColor;

    public Cell() {
        this.backgroundColor = "";
        this.contentColor = "";
    }
    public static int getRow(String position){
        return position.charAt(0) - 'a';
    }

    public static int getColum(String position){
        int colum = position.charAt(1) - '0';
        if(position.length() == 3) colum = colum * 10 + position.charAt(2) - '0' - 1;
        else colum--;
        return colum;
    }

    public Cell(String content, String backgroundColor) {
        this.content = content;
        this.backgroundColor = backgroundColor;
        this.contentColor = Color.ANSI_WHITE;
    }

    public Cell(String content, String backgroundColor, String contentColor) {
        this.content = content;
        this.backgroundColor = backgroundColor;
        this.contentColor = contentColor;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean getHit() {
        return content.equals("H");
    }

    public void setHit() {
        this.backgroundColor = Color.ANSI_GREEN_BACKGROUND;
        this.content = "H";
    }

    public boolean getMiss() {
        return content.equals("M");
    }

    public void setMiss() {
        this.backgroundColor = Color.ANSI_RED_BACKGROUND;
        this.content = "M";
    }

    public boolean isEmpty() {
        return content.equals("0");
    }

    @Override
    public String toString() {
        String value = "";
        if (content.length() < 2) value = "_" + content + "_";
        else value = "_" + content;
        value = "|" + backgroundColor + contentColor + value + Color.ANSI_RESET + Color.ANSI_RESET;
        return value;
    }

    public String toOpponentBoard() {
        if(!getMiss() && !getHit() ) return "|" + "_" + "0" +"_" ;
        String value = "";
        if (getHit()) value = "|" + Color.ANSI_GREEN_BACKGROUND + "_" + "H" + "_" + Color.ANSI_RESET ;
        else value ="|" + Color.ANSI_RED_BACKGROUND + "_" + "M" + "_" + Color.ANSI_RESET ;
        return value;
    }
}
