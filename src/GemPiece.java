/**
 * documentation goes here
 */
public abstract class GemPiece extends Piece
{
    private int xPos, yPos, pointValue;

    public GemPiece()
    {
        this.xPos = 0;
        this.yPos = 0;
        this.pointValue = 0; // blue = 4, red = 10 (red ends game)
    }
    public GemPiece(int x, int y, int val)
    {
        this.setAll(x, y, val);
    }


    /**
     * <p>This abstract move method is responsible for moving a gem.</p>
     * <p>Gems do not have their own movement abilities.</p>
     * <p>The only ways a gem can move are by: </p>
     * <p>1. Being carried by another piece</p>
     * <p>or</p>
     * <p>2. Being thrown from one piece to another allied piece.</p>
     *
     * @param toX the x position to move to
     * @param toY the x position to move to
     * @return true if the move results in a gem being scored, false if not
     */
    @Override
    public abstract boolean move(int toX, int toY);

    public void setXPos(int x)
    {
        this.xPos = x;
    }
    public void setYPos(int y)
    {
        this.yPos = y;
    }

    @Override
    public void setPointValue(int val)
    {
        this.pointValue = val;
    }
    public void setAll(int x, int y, int val)
    {
        this.setXPos(x);
        this.setYPos(y);
        this.setPointValue(val);
    }

    public int getXPos()
    {
        return this.xPos;
    }
    public int getYPos()
    {
        return this.yPos;
    }

    @Override
    public int getPointValue()
    {
        return this.pointValue;
    }
}
