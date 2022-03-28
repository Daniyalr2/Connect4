import java.util.ArrayList;

/**
 * Class PLayer:  Player object for the game
 * @author Daniyal Rana
 */
public class Player
{
    private String color; //the color of the player
    private int totalWins; //the total number of wins a player has gotten
    private ArrayList<Integer> tokens = new ArrayList<Integer>(); // the positions of the tokens the player currently has on the grid

    /** Constructor
     * @param color the color of the player (red or yellow)
     */
    public Player(String color)
    {
        this.color = color;
        totalWins = 0;
    }

    /**Getter method for totalWins
     * @return totalWins instance variable
     */
    public int getTotalWins()
    {
        return totalWins;
    }

    /**Getter method for tokens
     * @return the tokens ArrayList
     */
    public ArrayList<Integer> getTokens()
    {
        return tokens;
    }

    /**Getter method for color
     * @return the color instance variable
     */
    public String getColor()
    {
        return color;
    }

    /**Sets the tokens ArrayList to an empty ArrayList
     * @return totalWins instance variable
     */
    public void clearTokens()
    {
        tokens = new ArrayList<Integer>();
    }

    /**Adds 1 to a players total win count*/
    public void addWin()
    {
        totalWins++;
    }

    /**Adds the position of a token to the players tokens ArrayList
     * @param pos the position on the grid of the token being added
     */
    public void addPlayerToken(int pos)
    {
        tokens.add(pos);
    }
}
