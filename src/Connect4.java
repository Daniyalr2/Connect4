import java.util.Scanner;

public class Connect4
{
    Scanner scan = new Scanner(System.in);
    private Token[][] grid;
    private boolean goAgain = true;
    private boolean player1Turn = true;
    private boolean playerWon = false;
    private int tokenPos;
    public static final String YELLOW = "\033[0;33m";  // YELLOW
    public static final String RED = "\033[0;31m";     // RED
    public static final String WHITE = "\033[0;37m";   // WHITE
    public static final String BLUE = "\033[0;34m";    // BLUE


    public Connect4()
    {
        grid = new Token[6][7];
    }

    public void play()
    {
        Player player1 = new Player("Red");
        Player player2 = new Player("Yellow");
        while(!playerWon)
        {

            while (goAgain && !playerWon)
            {
                printGrid();
                turn(player1);
            }
            goAgain = true;
            while (goAgain && !playerWon)
            {
                printGrid();
                turn(player2);
            }
            goAgain = true;
            
        }
    }

    public void printGrid()
    {
        System.out.print(WHITE);
        for (int i = 0; i < grid.length; i++)
        {
            printRow(i);

        }
    }

    public void turn(Player player)
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
            if (player1Turn)
            {
                System.out.println("PLAYER 1 WINS!");
            }
            else
            {
                System.out.println("PLAYER 2 WINS!");
            }
        }
        if (!goAgain)
        {
            player1Turn = !player1Turn;
        }
    }

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

    private void printRow(int row)
    {
        for (int col = 0; col < grid[0].length; col++)
        {
            System.out.print(BLUE);
            System.out.print("|");
            printToken(grid[row][col]);
        }
        System.out.print(BLUE);
        System.out.println("|");
    }

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

    private boolean checkWin(Player player, Token token)
    {
        if (checkWinCond1(player, token) || checkWinCond2(player, token) || checkWinCond3(player, token) || checkWinCond4(player, token))
        {
            return  true;
        }
        return false;
    }

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
            return true;
        }
        return false;
    }

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
            return true;
        }
        return false;
    }

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
            return true;
        }
        return false;
    }

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
            return true;
        }
        return false;
    }

}
