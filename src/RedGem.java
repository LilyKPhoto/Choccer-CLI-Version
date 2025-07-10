public class RedGem extends GemPiece
{
    private final int RED_POINT_VALUE = 10;

    public RedGem()
    {
        super();
        this.setPointValue(RED_POINT_VALUE);
    }
    public RedGem(int x, int y, int val)
    {
        super(x, y, val);
    }

    public String toString()
    {
        return "◆";
    }
    /**
     * Moves the RedGem to the specified location.
     * If the red gem gets scored, then we need to return true to indicate that the game is over.
     *
     * @param toX the x position to move to
     * @param toY the x position to move to
     * @return true if the move ends the game, false if not.
     * @implNote IF this method returns true, THE GAME MUST END IMMEDIATELY.
     */
    @Override
    public boolean move(int toX, int toY)
    {
        this.setXPos(toX);
        this.setYPos(toY);
        // if a RedGem enters a capture zone, return true.
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
