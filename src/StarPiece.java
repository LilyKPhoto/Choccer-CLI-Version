/** A Star Piece is a type of Piece. It can move diagonally by up to 3 spaces. */

public class StarPiece extends Piece
{
    // full constructor:
    public StarPiece(boolean isBlack, boolean isHoldingGem, int x, int y)
    {
        setColor(isBlack);
        setHoldingGem(isHoldingGem);
        setPosition(x, y);
        setPointValue(2);
    }
    /**
     * StarPieces can move up to three spaces in ONE diagonal at once. No compound moves.
     *
     * @param newX the new x position to move to.
     * @param newY the new y position to move to.
     * @return false, always. A StarPiece cannot be scored or end the game.
     * @throws MovementException if the attempted move is illegal.
     */
    @Override
    public boolean move(int newX, int newY) throws MovementException
    {
        final String defaultMessage = "Error: cannot move to (" + newX + ", " + newY + "). Stars can move up to 3 spaces along one diagonal at a time.";
        int dx = newX - this.getX(); // dx is the difference in target x position vs. current x position
        int dy = newY - this.getY(); // dy is the difference in target y position vs. current y position
        // abs(dx) should equal abs(dy) for any valid move
        /* movement validity checking: */
        // first, check if the player attempted to move out of bounds:
        if(newX < Board.BOARD_MIN_X || newX > Board.BOARD_MAX_X || newY < Board.BOARD_MIN_Y || newY > Board.BOARD_MAX_Y)
        {
            throw new MovementException("Error: out of bounds move attempted.");
        }
        // next, check if the player attempted to move ONLY in x *OR* y:
        if(dx == 0 || dy == 0)
        {
            throw new MovementException(defaultMessage);
        }
        // next, check if the player attempted to move by more than 3 spaces in x or y
        if(Math.abs(dx) > 3 || Math.abs(dy) > 3)
        {
            throw new MovementException(defaultMessage);
        }
        // next, check if the move is a valid diagonal move, performing the move if so
        if(Math.abs(dx) == Math.abs(dy))
        {
            this.setPosition(newX, newY);
        }
        else
        {
            throw new MovementException(defaultMessage); // throw an exception if something goes wrong here
        }
        return false; // needed to keep compiler happy, doesn't actually do anything useful
    }
    /**
     * Returns a Star in the appropriate color.
     * @return either ☆ or ★ depending on the color of the calling TrianglePiece.
     */
    @Override
    public String toString()
    {
        if(this.getColor())
        {
            return "★";
        }
        else
        {
            return "☆";
        }
    }
}
