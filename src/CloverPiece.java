/** A Circle Piece is a type of Piece. It can move orthogonally by up to 3 spaces. */

public class CloverPiece extends Piece
{
    // full constructor:
    public CloverPiece(boolean isBlack, boolean isHoldingGem, int x, int y)
    {
        setColor(isBlack);
        setHoldingGem(isHoldingGem);
        setPosition(x, y);
        setPointValue(2);
    }

    /**
     * CloverPieces can move 1 space horizontally/vertically.
     *
     * @param newX the new x position to move to.
     * @param newY the new y position to move to.
     * @return false, always. A CloverPiece cannot be scored or end the game.
     * @throws MovementException if the attempted move is illegal.
     */
    @Override
    public boolean move(int newX, int newY) throws MovementException
    {
        final String defaultMessage = "Error: cannot move to (" + newX + ", " + newY + "). Clovers can move up to 3 spaces up, down, left, OR right.";

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
        // assuming the above returned false, check if the player tried moving up to 3 spaces in x:
        if(Math.abs(dx) >= 1 && Math.abs(dx) <= 3)
        {
            this.setX(newX);
        }
        // however, if they tried moving more than 3 spaces in x, throw an exception
        else if (Math.abs(dx) > 3)
        {
            throw new MovementException(defaultMessage);
        }
        // assuming the initial error check returned false, check if the player tried moving up to 3 spaces in y:
        if(Math.abs(dy) >= 1 && Math.abs(dy) <= 3)
        {
            this.setY(newY);
        }
        // however, if they tried moving more than 3 spaces in y, throw an exception
        else if (Math.abs(dy) > 3)
        {
            throw new MovementException(defaultMessage);
        }
        return false; // needed to keep compiler happy, doesn't actually do anything useful
    }
    /**
     * Returns a clover in the appropriate color.
     * @return either ♣ or ♧ depending on the color of the calling CirclePiece.
     */
    @Override
    public String toString()
    {
        if(this.getColor())
        {
            return "♣";
        }
        else
        {
            return "♧";
        }
    }
}
