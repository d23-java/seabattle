package player;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private String name;
    private char[][] myBoard;
    private char[][] enemyBoard;
    private int soODaBan;
    private int soOTrungDich;
    private int soTauDaPha;
    private int soTauConLai;
    private int PatrolBoat1Point;
    private int PatrolBoat2Point;
    private int DestroyerBoatPoint;
    private int SubmarinePoint;
    private int BattleShipPoint;
    Player(){}
    Player(String name) {
        this.name = name;
        myBoard = new char[21][21];
        enemyBoard = new char[21][21];
        for(int i = 1; i <= 20; i++)
            for(int j = 1; j <= 20; j++) {
                myBoard[i][j] = ' ';
                enemyBoard[i][j] = '?';
            }
        soODaBan = 0;
        soOTrungDich = 0;
        soTauDaPha = 0;
        soTauConLai = 5;
        PatrolBoat1Point = 2;
        PatrolBoat2Point = 2;
        DestroyerBoatPoint = 4;
        SubmarinePoint = 3;
        BattleShipPoint = 5;
    }

    public char[][] getMyBoard() {
        return myBoard;
    }

    public char getMyBoard(int x, int y) {
        return myBoard[x][y];
    }
    public char[][] getEnemyBoard() {
        return enemyBoard;
    }

    public String getName() {
        return name;
    }

    public void setMyBoard(char symbol, int i, int j) {
        this.myBoard[i][j] = symbol;
    }

    public void setEnemyBoard(char symbol, int i, int j) {
        this.enemyBoard[i][j] = symbol;
    }

    public int getSoODaBan() {
        return soODaBan;
    }

    public int getSoOTrungDich() {
        return soOTrungDich;
    }

    public int getSoTauDaPha() {
        return soTauDaPha;
    }

    public void updateSoODaBan() {
       soODaBan++;
    }

    public void updateSoOTrungDich() {
        soOTrungDich++;
    }
    public void updateSoTauDaPha() {
        soTauDaPha++;
    }
    public void decreasePatrolBoat1Point()
    {
        PatrolBoat1Point--;
    }
    public void decreasePatrolBoat2Point()
    {
        PatrolBoat2Point--;
    }
    public void decreaseDestroyerBoatPoint()
    {
        DestroyerBoatPoint--;
    }
    public void decreaseSubmarinePoint()
    {
        SubmarinePoint--;
    }
    public void decreaseBattleShipPoint()
    {
        BattleShipPoint--;
    }

    public int getPatrolBoat1Point() {
        return PatrolBoat1Point;
    }

    public int getDestroyerBoatPoint() {
        return DestroyerBoatPoint;
    }

    public int getPatrolBoat2Point() {
        return PatrolBoat2Point;
    }

    public int getSubmarinePoint() {
        return SubmarinePoint;
    }

    public int getBattleShipPoint() {
        return BattleShipPoint;
    }
}
