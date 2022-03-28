/**
 * Class Token:  A token object
 * @author Daniyal Rana
 */
public class Token
{
    private String color; //color of the token
    private int pos; //position of the token on the grid

    /** Constructor
     * @param color the color of the token (red or yellow)
     */
    public Token(String color)
    {
        this.color = color;
    }

    /**Getter method for pos
     * @return pos instance variable
     */
    public int getPos()
    {
        return pos;
    }

    /**Getter method for color
     * @return color instance variable
     */
    public String getColor()
    {
        return color;
    }

    /**Setter method for pos
     * @param pos the position of the token on the grid
     */
    public void setPos(int pos)
    {
        this.pos = pos;
    }
}
