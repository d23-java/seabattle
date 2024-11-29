package gameObjects;

public class Cell {
    private String content = "0";
    public Cell() {

    }
    public Cell(String content) {
        this.content = content;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    @Override
    public String toString() {
    if(content.length() < 20 ) return "|"+"_" + content + "_" ;
    else return "|"+"_" + content ;
    }
}
