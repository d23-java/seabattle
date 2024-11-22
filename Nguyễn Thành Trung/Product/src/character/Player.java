package character;

import java.util.ArrayList;
import mapresources.Board;
import mapresources.ObjectMap;
import ship.Ship;
import system.ComputerSystem;

public class Player {
    private Board playerBoard = new Board();
    private ObjectMap playerObjectMap = new ObjectMap();
    private Board enemyFoggyBoard = new Board();
    private int numberOfPatrolBoat = 2;
    private int numberOfDestroyerBoat = 1;
    private int numberOfSubmarine = 1;
    private int numberOfBattleShip = 1;
    private int totalShipNumber = 5;
    private String ANSIcode;
    private boolean attackMiss = false;
    private ArrayList<Ship> shipArrayList = new ArrayList<>();

    public void insertShip() {
        while (totalShipNumber != 0) {
            System.out.println("Insert type of ship: ");
            System.out.println("1. PatrolBoat");
            System.out.println("2. DestroyerBoat");
            System.out.println("3. Submarine");
            System.out.println("4. BattleShip");

            int shipID = ComputerSystem.scanner.nextInt();
            switch (shipID) {
                case 1:
                    if (numberOfPatrolBoat == 0) {
                        System.out.println("No Patrol Boat left!!!");
                        System.out.println("Press Enter to continue...");
                        ComputerSystem.scanner.next();
                        continue;
                    }
                    numberOfPatrolBoat--;
                    ANSIcode = "100";
                    break;
                case 2:
                    if (numberOfDestroyerBoat == 0) {
                        System.out.println("No Destroyer Boat left!!!");
                        System.out.println("Press Enter to continue...");
                        ComputerSystem.scanner.next();
                        continue;
                    }
                    numberOfDestroyerBoat--;
                    ANSIcode = "105";
                    break;
                case 3:
                    if (numberOfSubmarine == 0) {
                        System.out.println("No Submarine left!!!");
                        System.out.println("Press Enter to continue...");
                        ComputerSystem.scanner.next();
                        continue;
                    }
                    numberOfSubmarine--;
                    ANSIcode = "103";
                    break;
                case 4:
                    if (numberOfBattleShip == 0) {
                        System.out.println("No Battle Ship left!!!");
                        System.out.println("Press Enter to continue...");
                        ComputerSystem.scanner.next();
                        continue;
                    }
                    numberOfBattleShip--;
                    ANSIcode = "107";
                    break;
            }
            totalShipNumber--;

            ComputerSystem.clearScreen();
            showPlayerBoard();

            Ship newShip = new Ship(shipID);
            int input = 1;
            while (input <= newShip.getSize()) {
                ComputerSystem.clearScreen();
                showPlayerBoard();
                System.out.print("Insert " + newShip.getName() + " row: ");
                String xAxisString = ComputerSystem.scanner.next();
                int xAxis = ComputerSystem.charToInt(xAxisString);
                System.out.print("Insert " + newShip.getName() + " column: ");
                int yAxis = ComputerSystem.scanner.nextInt();
                   
                if (!ComputerSystem.checkValidCoordinate(xAxis, yAxis)) {
                    //System.out.println("lỗi 1");
                    ComputerSystem.invalidAnnouncement();
                    continue;
                }

                boolean validCell = true;
                if (input == 2) {
                    //System.out.println("now: " + xAxis + "; " + yAxis);
                    //System.out.println("past: " + newShip.getAxisX().get(0) + "; " + newShip.getAxisY().get(0));
                    if (xAxis != newShip.getAxisX().get(0) && yAxis != newShip.getAxisY().get(0)) {
                        //System.out.println("lỗi 2");
                        ComputerSystem.invalidAnnouncement();
                        continue;
                    } else if (xAxis == newShip.getAxisX().get(0) && yAxis == newShip.getAxisY().get(0)) {
                        //System.out.println("lỗi 3");
                        ComputerSystem.invalidAnnouncement();
                        continue;
                    } else if (playerObjectMap.getObjectMapCell(xAxis, yAxis) != 0) {
                        //System.out.println("lỗi 4");
                        ComputerSystem.invalidAnnouncement();
                        continue;
                    } else if (xAxis == newShip.getAxisX().get(0)) {
                        int direction = yAxis - newShip.getAxisY().get(0);
                        newShip.setxDirection(xAxis);

                        if (direction != 1 && direction != -1) {
                            System.out.println(direction);
                            //System.out.println("lỗi 5");
                            ComputerSystem.invalidAnnouncement();
                            continue;
                        }

                        if (!ComputerSystem.checkValidCoordinate(xAxis, yAxis + direction * newShip.getSize())) {
                            //System.out.println("lỗi 6");
                            ComputerSystem.invalidAnnouncement();
                            continue;
                        }

                        for (int cellMove = 0; cellMove <= newShip.getSize() - 2; cellMove += direction) {
                            if (playerObjectMap.getObjectMapCell(xAxis, yAxis + cellMove) != 0) {
                                //System.out.println("lỗi 7");
                                ComputerSystem.invalidAnnouncement();
                                validCell = false;
                                break;
                            }
                        }

                        if (!validCell) {
                            continue;
                        }
                    } else if (yAxis == newShip.getAxisY().get(0)) {
                        int direction = xAxis - newShip.getAxisX().get(0);
                        newShip.setyDirection(yAxis);

                        if (direction != 1 && direction != -1) {
                            //System.out.println("lỗi 8");
                            ComputerSystem.invalidAnnouncement();
                            continue;
                        }

                        if (!ComputerSystem.checkValidCoordinate(xAxis + direction * newShip.getSize(), yAxis)) {
                            //System.out.println("lỗi 9");
                            ComputerSystem.invalidAnnouncement();
                            continue;
                        }

                        for (int cellMove = 0; cellMove <= newShip.getSize() - 2; cellMove += direction) {
                            if (playerObjectMap.getObjectMapCell(xAxis + cellMove, yAxis) != 0) {
                                //System.out.println("lỗi 10");
                                ComputerSystem.invalidAnnouncement();
                                validCell = false;
                                break;
                            }
                        }

                        if (!validCell) {
                            continue;
                        }
                    }
                }
                
                newShip.addCoordinate(xAxis, yAxis);
                setPlayerBoard(xAxis, yAxis, newShip.getShipID(), ANSIcode);
                playerObjectMap.setObjectMapCell(xAxis, yAxis, newShip.getShipID());
                ComputerSystem.clearScreen();
                showPlayerBoard();
                input++;
            }
            shipArrayList.add(newShip);
        }
    }

    public void setPlayerBoard(int xAxis, int yAxis, int valueOfCell, String ANSIcode) {
        playerBoard.setBoardCell(xAxis, yAxis, "" + valueOfCell, ANSIcode);
        playerObjectMap.setObjectMapCell(xAxis, yAxis, 2);
    }

    public void showEnemyFoggyBoard() {
        enemyFoggyBoard.showBoard();
    }

    public ObjectMap getPlayerObjectMap() {
        return playerObjectMap;
    }

    public void showPlayerBoard() {
        playerBoard.showBoard();
    }

    public void checkHitOrMiss(int xAxis, int yAxis) {
        int valueOfCell = playerObjectMap.getObjectMapCell(xAxis, yAxis);
        boolean checkBreak = false;

        if (valueOfCell > 0) {
            enemyFoggyBoard.setBoardCell(xAxis, yAxis, "" + valueOfCell, "42");
            attackMiss = false;
            for (Ship ship : shipArrayList) {
                if (ship.getShipID() == valueOfCell && !ship.getAxisX().isEmpty()) {
                    for (int x : ship.getAxisX()) {
                        if (x == xAxis) {
                            for (int y : ship.getAxisY()) {
                                if (y == yAxis) {
                                    ship.romveXAxis(xAxis);
                                    ship.romveYAxis(yAxis);
                                    checkBreak = true;
                                    break;
                                }
                            }
                            if (checkBreak) break;
                        }
                    }
                    if (checkBreak) break;
                }
            }
        } else if (valueOfCell == 0) {
            enemyFoggyBoard.setBoardCell(xAxis, yAxis, "X", "41");
            attackMiss = true;
        }
        playerObjectMap.setObjectMapCell(xAxis, yAxis, 3);
    }

    public boolean checkLost() {
        for (Ship ship : shipArrayList) {
            if (!ship.getAxisX().isEmpty()) {
                return false;
            }
        }
        return true;
    }

    public boolean getAttackMiss() {
        return attackMiss;
    }
}
