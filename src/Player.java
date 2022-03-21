import java.util.ArrayList;

public class Player
{
    private String color;
    private ArrayList<Integer> tokens = new ArrayList<Integer>();

    public Player(String color)
    {
        this.color = color;
    }

    public void addPlayerToken(int pos)
    {
        tokens.add(pos);
    }

    public ArrayList<Integer> getTokens()
    {
        return tokens;
    }

    public String getColor()
    {
        return color;
    }
}
