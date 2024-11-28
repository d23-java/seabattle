public class Coordinates {
    private int r,c;
    boolean used, hit;
    //contractor
    public Coordinates(int r, int c){
        this.r = r;
        this.c = c;
        this.used = false;
        this.hit = false;
    }
    public boolean isUsed(){return used;}

    public boolean isHit(){return hit;}

    public String getChar(String who){
        if (isHit() && used) return "[\u001B[31mX\u001B[0m]"; // red X
        else if (isHit() && !used) return "[\u001B[34mO\u001B[0m]"; // blue O
        else if (!isHit() && used && who.equals("owner")) return "[\u001B[30mS\u001B[0m]"; // green S
        else return "[.]";
    }

    public void setUsed(boolean used){this.used = used;}

    public boolean attack(int r, int c){
        if(r==this.r && c==this.c){
            if(hit) return false;
            hit = true;
            return used;
        }
        return false;
    }
}