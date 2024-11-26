package player;

public class CheckShoot {
    public static boolean shoot(ToaDo toado, Player player, Player enemy) {
        int x  = toado.getX();
        int y  = toado.getY();
        player.updateSoODaBan();
        if (enemy.getBoard()[x][y].equals("\uD83C\uDF0A") || player.getEnemyBoard()[x][y].equals("\uD83D\uDD25")||player.getEnemyBoard()[x][y].equals("❌"))
        {
            if (enemy.getBoard()[x][y].equals("\uD83C\uDF0A"))
                player.setEnemyBoard("❌", x, y);
            return false;
        }
        else{
            String symbol = enemy.getBoard()[x][y];
            if (symbol == "\uD83D\uDC24"){
                enemy.decreasePatrolBoat1Point();
                if(enemy.getPatrolBoat1Point() == 0) {
                    System.out.println("Bạn đã hạ gục 1 tàu của đối thủ");
                    player.updateSoTauDaPha();
                    enemy.decreaseSoTauConLai();
                }
            }
            else if (symbol == "\uD83D\uDC25"){
                enemy.decreasePatrolBoat2Point();
                if(enemy.getPatrolBoat2Point() == 0) {
                    System.out.println("Bạn đã hạ gục 1 tàu của đối thủ");
                    player.updateSoTauDaPha();
                    enemy.decreaseSoTauConLai();
                }
            }
            else if (symbol == "\uD83D\uDC26"){
                enemy.decreaseDestroyerBoatPoint();
                if(enemy.getDestroyerBoatPoint() == 0) {
                    System.out.println("Bạn đã hạ gục 1 tàu của đối thủ");
                    player.updateSoTauDaPha();
                    enemy.decreaseSoTauConLai();
                }
            }
            else if (symbol == "\uD83D\uDC27"){
                enemy.decreaseSubmarinePoint();
                if(enemy.getSubmarinePoint() == 0) {
                    System.out.println("Bạn đã hạ gục 1 tàu của đối thủ");
                    player.updateSoTauDaPha();
                    enemy.decreaseSoTauConLai();
                }
            }
            else if (symbol == "\uD83D\uDC14"){
                enemy.decreaseBattleShipPoint();
                if(enemy.getBattleShipPoint() == 0) {
                    System.out.println("Bạn đã hạ gục 1 tàu của đối thủ");
                    player.updateSoTauDaPha();
                    enemy.decreaseSoTauConLai();
                }
            }
            enemy.setBoard("\uD83D\uDD25", x, y);
            player.setEnemyBoard("\uD83D\uDD25", x, y);
            return true;
        }
    }
}
