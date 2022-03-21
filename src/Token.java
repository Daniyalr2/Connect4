public class Token
{
    private String color;
    private int pos;

    public Token(String color)
    {
        this.color = color;
    }

    public void setPos(int pos)
    {
        this.pos = pos;
    }

    public int getPos()
    {
        return pos;
    }

    public String getColor()
    {
        return color;
    }
}
