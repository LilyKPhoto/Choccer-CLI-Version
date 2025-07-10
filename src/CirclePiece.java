/** A Circle Piece is a type of Piece. It can move orthogonally by one space. */

public class CirclePiece extends Piece
{
    // full constructor:
    public CirclePiece(boolean isBlack, boolean isHoldingGem, int x, int y)
    {
        setColor(isBlack);
        setHoldingGem(isHoldingGem);
        setPosition(x, y);
        setPointValue(1);
    }
    
    /* movement methods */

    /**
     * CirclePieces can move 1 space horizontally/vertically.
     *
     * @param newX the new x position to move to.
     * @param newY the new y position to move to.
     * @return false, always. A CirclePiece cannot be scored or end the game.
     * @throws MovementException if the attempted move is illegal.
     */
    @Override
    public boolean move(int newX, int newY) throws MovementException
    {
        final String defaultMessage = "Error: cannot move to (" + newX + ", " + newY + "). Circles can only move 1 space up, down, left, or right.";

        int dx = newX - this.getX(); // dx is the difference in target x position vs. current x position
        int dy = newY - this.getY(); // dy is the difference in target y position vs. current y position
        /* movement validity checking: */
        // first, check if the player attempted to move out of bounds:
        if(newX < Board.BOARD_MIN_X || newX > Board.BOARD_MAX_X || newY < Board.BOARD_MIN_Y || newY > Board.BOARD_MAX_Y)
        {
            throw new MovementException("Error: out of bounds move attempted.");
        }
        // next, check if the player attempted to move in *BOTH* x *AND* y:
        if(dx != 0 && dy != 0)
        {
            throw new MovementException(defaultMessage);
        }
        // assuming the above returned false, check if the player tried moving 1 space in x:
        if(Math.abs(dx) == 1)
        {
            this.setX(newX);
        }
        // however, if they tried moving more than 1 space in x, throw an exception
        else if (Math.abs(dx) > 1)
        {
            throw new MovementException(defaultMessage);
        }
        // assuming the initial error check returned false, check if the player tried moving 1 space in y:
        if(Math.abs(dy) == 1)
        {
            this.setY(newY);
        }
        // however, if they tried moving more than 1 space in y, throw an exception
        else if (Math.abs(dy) > 1)
        {
            throw new MovementException(defaultMessage);
        }
        return false; // needed to keep compiler happy, doesn't actually do anything useful
    }

    /**
     * Returns a circle in the appropriate color.
     * @return either ⚪ or ⚫ depending on the color of the calling CirclePiece.
     */
    @Override
    public String toString()
    {
        if(this.getColor())
        {
            return "●";
        }
        else
        {
            return "○";
        }
    }

}
