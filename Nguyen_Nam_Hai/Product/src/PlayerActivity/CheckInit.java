package PlayerActivity;

public class CheckInit {
    public boolean checkInt(String needCheck, int left, int right){//left là độ dài số nhỏ nhất, right là độ dài số lớn nhất
        int len = needCheck.length();
        if(needCheck.isEmpty()) return false;
        if(len < left || len > right)  return false;
        for(int i = 0; i < len; i++){
            char ch = needCheck.charAt(i);
            if(!Character.isDigit(ch)) return false;
        }
        return true;
    }
    public boolean checkChar(String needCheck){
        if(needCheck.isEmpty()) return false;
        if(needCheck.length() != 1) return false;
        char ch = needCheck.charAt(0);
        return Character.isLetter(ch);
    }
}