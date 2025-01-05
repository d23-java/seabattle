package game;

public class CheckShoot {
    public static boolean shoot(ToaDo toado, Player player, Player enemy) {
        int x  = toado.getX();
        int y  = toado.getY();
        player.updateSoODaBan();
        if (enemy.getBoard()[x][y].equals(".") || player.getEnemyBoard()[x][y].equals("V")||player.getEnemyBoard()[x][y].equals("X"))
        {
            if (enemy.getBoard()[x][y].equals("."))
                player.setEnemyBoard("X", x, y);
            return false;
        }
        else{
            String symbol = enemy.getBoard()[x][y];
            if (symbol == "P"){
                enemy.decreasePatrolBoat1Point();
                if(enemy.getPatrolBoat1Point() == 0) {
                    System.out.println("Bạn đã hạ gục 1 tàu của đối thủ");
                    player.updateSoTauDaPha();
                    enemy.decreaseSoTauConLai();
                }
            }
            else if (symbol == "p"){
                enemy.decreasePatrolBoat2Point();
                if(enemy.getPatrolBoat2Point() == 0) {
                    System.out.println("Bạn đã hạ gục 1 tàu của đối thủ");
                    player.updateSoTauDaPha();
                    enemy.decreaseSoTauConLai();
                }
            }
            else if (symbol == "D"){
                enemy.decreaseDestroyerBoatPoint();
                if(enemy.getDestroyerBoatPoint() == 0) {
                    System.out.println("Bạn đã hạ gục 1 tàu của đối thủ");
                    player.updateSoTauDaPha();
                    enemy.decreaseSoTauConLai();
                }
            }
            else if (symbol == "S"){
                enemy.decreaseSubmarinePoint();
                if(enemy.getSubmarinePoint() == 0) {
                    System.out.println("Bạn đã hạ gục 1 tàu của đối thủ");
                    player.updateSoTauDaPha();
                    enemy.decreaseSoTauConLai();
                }
            }
            else if (symbol == "B"){
                enemy.decreaseBattleShipPoint();
                if(enemy.getBattleShipPoint() == 0) {
                    System.out.println("Bạn đã hạ gục 1 tàu của đối thủ");
                    player.updateSoTauDaPha();
                    enemy.decreaseSoTauConLai();
                }
            }
            enemy.setBoard("X", x, y);
            player.setEnemyBoard("V", x, y);
            return true;
        }
    }
}
