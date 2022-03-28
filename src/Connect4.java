import java.util.Scanner;

/**
 * Class Connect4:  Logic for playing the connect 4 game.
 * @author Daniyal Rana
 */
public class Connect4
{
    Scanner scan = new Scanner(System.in);
    private Token[][] grid; //2D array representing the connect 4 grid
    private boolean goAgain = true; //used to determine if a player needs to go again
    private boolean player1Turn = true; //used to determine who's turn it is
    private boolean playerWon = false; //used to determine if a player has won the game
    private Player player1 = new Player("Red"); //first player
    private Player player2 = new Player("Yellow"); //second player
    public static final String YELLOW = "\033[0;33m";  // YELLOW
    public static final String RED = "\033[0;31m";     // RED
    public static final String WHITE = "\033[0;37m";   // WHITE
    public static final String BLUE = "\033[0;34m";    // BLUE

    /** Constructor*/
    public Connect4()
    {
        grid = new Token[6][7];
    }

    /**This method contains the logic for running the game and determines if the players would like to play again.*/
    public void play()
    {
        //This while loop contains the code for running the game itself
        while(!playerWon)
        {
            while (goAgain && !playerWon && player1Turn)
            {
                printGrid();
                turn(player1);
            }
            goAgain = true;
            while (goAgain && !playerWon && !player1Turn)
            {
                printGrid();
                turn(player2);
            }
            goAgain = true;
        }

        //This code determines if the players would like to play again
        System.out.println("Player 1 Wins: " + player1.getTotalWins() + "\nPlayer 2 Wins: " + player2.getTotalWins() + "\n");
        System.out.print("Would you like to play again? (Y) if yes: ");
        scan.nextLine();
        String response = scan.nextLine();
        if (response.equals("Y") || response.equals("y"))
        {
            playerWon = false;
            goAgain = true;
            grid = new Token[6][7];
            String response1 = "";
            while (!(response1.equals("1") || response1.equals("2")))
            {
                System.out.print("Who would you like to go first? Player (1 or 2): ");
                response1 = scan.nextLine();
                if (!(response1.equals("1") || response1.equals("2")))
                {
                    System.out.println("Invalid response!");
                }
            }
            if (response1.equals("1"))
            {
                player1Turn = true;
            }
            else
            {
                player1Turn = false;
            }
            play();
        }
    }

    /**Private helper method that prints the grid containing all player tokens to the console.*/
    private void printGrid()
    {
        System.out.print(WHITE);
        for (int i = 0; i < grid.length; i++)
        {
            for (int col = 0; col < grid[0].length; col++)
            {
                System.out.print(BLUE);
                System.out.print("|");
                printToken(grid[i][col]);
            }
            System.out.print(BLUE);
            System.out.println("|");
        }
    }

    /**Private helper method that allows the player to place a token in the grid
     * @param player the player who's turn it is
     */
    private void turn(Player player)
    {
        int col;
        System.out.print(WHITE);
        if (player1Turn)
        {
            System.out.println("It's Player 1's turn!");
        }
        else
        {
            System.out.println("It's Player 2's turn!");
        }
        goAgain = false;
        System.out.print("Choose a column: ");
        col = scan.nextInt() - 1;
        Token currToken = new Token(player.getColor());
        currToken.setPos(addToken(currToken, col));
        if (currToken.getPos() != 0)
        {
            player.addPlayerToken(currToken.getPos());
        }
        if (checkWin(player, currToken))
        {
            playerWon = true;
            printGrid();
            System.out.println(WHITE);
            if (player1Turn)
            {
                System.out.println("PLAYER 1 WINS!");
            }
            else
            {
                System.out.println("PLAYER 2 WINS!");
            }
            player.addWin();
            player1.clearTokens();
            player2.clearTokens();
        }
        if (!goAgain)
        {
            player1Turn = !player1Turn;
        }
    }

    /**Private helper method that places a token in the grid
     * @param token the token object that will be placed in the grid
     * @param col the column the player chose to place the token in
     * @return the position on the grid the token was placed in
     */
    private int addToken(Token token, int col)
    {
        for (int i = 5; i > -1; i--)
        {
            if (col > 6 || col < 0)
            {
                System.out.println("Column doesn't exist. Choose again!");
                goAgain = true;
                break;
            }
            else if (grid[i][col] == null)
            {
                grid[i][col] = token;
                return i * 7 + col + 1;
            }
            else if (i == 0)
            {
                System.out.println("Row Filled! Choose again!");
                goAgain = true;
            }
        }
        return 0;
    }

    /**Private helper method that prints a token to the console
     * @param token the token object that is getting printed
     */
    private void printToken(Token token)
    {
        if (token == null)
        {
            System.out.print(WHITE);
            System.out.print(" ");
        }
        else
        {
            if (token.getColor().equals("Red"))
            {
                System.out.print(RED);

            }
            else
            {
                System.out.print(YELLOW);
            }
            System.out.print("O");
            System.out.print(WHITE);
        }
    }

    /**Private helper method that determines if a player has won the game
     * @param player the player that's win status is being determined
     * @param token the token that was most recently placed by the player into the grid
     * @return true if the player has won the game, false if they have not
     */
    private boolean checkWin(Player player, Token token)
    {
        if (checkWinCond1(player, token) || checkWinCond2(player, token) || checkWinCond3(player, token) || checkWinCond4(player, token))
        {
            return  true;
        }
        return false;
    }

    /**Private helper method that determines if a player has won the game by connecting 4 tokens diagonally like /
     * @param player the player that's win status is being determined
     * @param token the token that was most recently placed by the player into the grid
     * @return true if the player has won the game, false if they have not
     */
    private boolean checkWinCond1(Player player, Token token)
    {
        int count = 1;
        if (player.getTokens().contains(token.getPos() - 6))
        {
            count++;
            if (player.getTokens().contains(token.getPos() - 12))
            {
                count++;
                if (player.getTokens().contains(token.getPos() - 18))
                {
                    count++;
                }
            }
        }
        if (player.getTokens().contains(token.getPos() + 6))
        {
            count++;
            if (player.getTokens().contains(token.getPos() + 12))
            {
                count++;
                if (player.getTokens().contains(token.getPos() + 18))
                {
                    count++;
                }
            }
        }
        if (count >= 4)
        {
            System.out.println("win con 1");
            return true;
        }
        return false;
    }

    /**Private helper method that determines if a player has won the game by connecting 4 tokens diagonally like \
     * @param player the player that's win status is being determined
     * @param token the token that was most recently placed by the player into the grid
     * @return true if the player has won the game, false if they have not
     */
    private boolean checkWinCond2(Player player, Token token)
    {
        int count = 1;
        if (player.getTokens().contains(token.getPos() - 8))
        {
            count++;
            if (player.getTokens().contains(token.getPos() - 16))
            {
                count++;
                if (player.getTokens().contains(token.getPos() - 24))
                {
                    count++;
                }
            }
        }
        if (player.getTokens().contains(token.getPos() + 8))
        {
            count++;
            if (player.getTokens().contains(token.getPos() + 16))
            {
                count++;
                if (player.getTokens().contains(token.getPos() + 24))
                {
                    count++;
                }
            }
        }
        if (count >= 4)
        {
            System.out.println("win con 2");
            return true;
        }
        return false;
    }

    /**Private helper method that determines if a player has won the game by connecting 4 tokens vertically
     * @param player the player that's win status is being determined
     * @param token the token that was most recently placed by the player into the grid
     * @return true if the player has won the game, false if they have not
     */
    private boolean checkWinCond3(Player player, Token token)
    {
        int count = 1;
        if (player.getTokens().contains(token.getPos() - 7))
        {
            count++;
            if (player.getTokens().contains(token.getPos() - 14))
            {
                count++;
                if (player.getTokens().contains(token.getPos() - 21))
                {
                    count++;
                }
            }
        }
        if (player.getTokens().contains(token.getPos() + 7))
        {
            count++;
            if (player.getTokens().contains(token.getPos() + 14))
            {
                count++;
                if (player.getTokens().contains(token.getPos() + 21))
                {
                    count++;
                }
            }
        }
        if (count >= 4)
        {
            System.out.println("win con 3");
            return true;
        }
        return false;
    }

    /**Private helper method that determines if a player has won the game by connecting 4 tokens horizontally
     * @param player the player that's win status is being determined
     * @param token the token that was most recently placed by the player into the grid
     * @return true if the player has won the game, false if they have not
     */
    private boolean checkWinCond4(Player player, Token token)
    {
        int count = 1;
        if (player.getTokens().contains(token.getPos() - 1))
        {
            count++;
            if (player.getTokens().contains(token.getPos() - 2))
            {
                count++;
                if (player.getTokens().contains(token.getPos() - 3))
                {
                    count++;
                }
            }
        }
        if (player.getTokens().contains(token.getPos() + 1))
        {
            count++;
            if (player.getTokens().contains(token.getPos() + 2))
            {
                count++;
                if (player.getTokens().contains(token.getPos() + 3))
                {
                    count++;
                }
            }
        }
        if (count >= 4)
        {
            System.out.println("win con 4");
            return true;
        }
        return false;
    }

}
