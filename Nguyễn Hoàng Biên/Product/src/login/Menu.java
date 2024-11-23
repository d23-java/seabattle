package login;

import manager.GameBoard;

public class Menu {
    public void showTitle(){
        String art = """
         ███████╗███████╗ █████╗ ██████╗   █████╗ ████████╗██╗     ███████╗
         ██╔════╝██╔════╝██╔══██╗██╔══██╗ ██╔══██╗╚══██╔══╝██║     ██╔════╝
         ███████╗█████╗  ███████║██████╔╝ ███████║   ██║   ██║     █████╗  
         ╚════██║██╔══╝  ██╔══██║██╔═ ██╝ ██╔══██║   ██║   ██║     ██╔══╝  
         ███████║███████╗██║  ██║██████║  ██║  ██║   ██║   ███████╗███████╗
         ╚══════╝╚══════╝╚═╝  ╚═╝╚═════╝  ╚═╝  ╚═╝   ╚═╝   ╚══════╝╚══════╝
        """;
        System.out.println(art);
    }
    public void showMenu(){
        System.out.println("1. start game");
        System.out.println("2. exit");

    }
    public void showBoard(GameBoard board){
        String [][] grid = board.getGrid();
//        System.out.println(board.getSize());
        for(int i = 0; i <= board.getSize(); i++){
            for(int j = 0; j <= board.getSize(); j++){
                System.out.print(grid[i][j] + " ");
            }
            System.out.println();
        }
    }
    public void showBoat(){
        System.out.println("1. patrol boat");
        System.out.println("2. destroyer boat");
        System.out.println("3. submarine");
        System.out.println("4. battle ship");
    }
}
