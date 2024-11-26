public class Coordinates {
    private int r,c;
    boolean used, hit;
    //contractor
    public Coordinates(){};
    public Coordinates(int r, int c){
        this.r = r;
        this.c = c;
        this.used = false;
        this.hit = false;
    };
    //getter
    public boolean isUsed(){return used;}
    public boolean isHit(){return hit;}
    public char getOwnerChar(){
        if(hit && used) return 'X';
        else if(hit && !used) return'O';
        else if(!hit && used) return 'S';
        else return ' ';
    }
    public char getOpponentChar(){
        if(hit && used) return 'X';
        else if(hit && !used) return'O';
        else return ' ';
    }

    //setter
    public void setUsed(boolean used){this.used = used;}
    public boolean attack(){
        if(hit) return false;
        hit = true;
        return used;
    }
}