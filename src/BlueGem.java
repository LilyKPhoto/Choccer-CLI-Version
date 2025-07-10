public class BlueGem extends GemPiece
{
    private final int BLUE_POINT_VALUE = 4;

    public BlueGem()
    {
        super();
        this.setPointValue(BLUE_POINT_VALUE);
    }
    public BlueGem(int x, int y, int val)
    {
        super(x, y, val);
    }

    public String toString()
    {
        return "◇";
    }
    /**
     * Moves the BlueGem to the specified location.
     *
     * @param toX the x position to move to
     * @param toY the x position to move to
     * @return true if the BlueGem was scored.
     * @implNote If this method returns true, that means the BlueGem was scored, and should be treated as captured!
     */
    @Override
    public boolean move(int toX, int toY)
    {
        this.setXPos(toX);
        this.setYPos(toY);
        // if a BlueGem gets scored, return true.
        if(((toX == 0 || toX == 1) || (toX == 9 || toX == 10)) && (toY >= 2 && toY <= 4))
        {
            return true;
        }
        else
        {
            return false;
        }
    }
}
