public class Main
{
    public static void main(String[] args)
    {
        Connect4 game = new Connect4();
        game.printGrid();
        Player player = new Player("Red");
        game.turn(player);
        game.printGrid();
        Player player2 = new Player("Yellow");
        game.turn(player2);
        game.printGrid();
    }
}
