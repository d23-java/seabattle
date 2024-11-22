public class Player {
    private Board board;
    private String name;

    public Player(String name) {
        this.name = name;
        this.board = new Board();
    }

    public void placeShip() {
        for(Ship ship: board.shipList){
            System.out.println("PLacing " + ship.getName());
            board.placeShip(ship, GameView.getCoordFromPlayer(), GameView.getPlaceShipDirection());
        }
    }

    public void fire(Coord coord) {
        boolean fireRes = board.fire(coord);
        GameView.displayFireResult(fireRes);
    }

    public Board getBoard() {
        return board;
    }
}

