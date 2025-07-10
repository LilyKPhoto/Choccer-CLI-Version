/** A Triangle Piece is a type of Piece. It can move diagonally by one space. */

public class TrianglePiece extends Piece
{
    // full constructor:
    public TrianglePiece(boolean isBlack, boolean isHoldingGem, int x, int y)
    {
        setColor(isBlack);
        setHoldingGem(isHoldingGem);
        setPosition(x, y);
        setPointValue(1);
    }

    /**
     * TrianglePieces can move one space diagonally.
     *
     * @param newX the new x position to move to.
     * @param newY the new y position to move to.
     * @return false, always. A TrianglePiece cannot be scored or end the game.
     * @throws MovementException if the attempted move is illegal.
     */
    @Override
    public boolean move(int newX, int newY) throws MovementException
    {
        final String defaultMessage = "Error: cannot move to (" + newX + ", " + newY + "). Triangles can only move 1 space diagonally.";
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
        // next, check if the player attempted to move by more than 1 space in x or y
        if(Math.abs(dx) > 1 || Math.abs(dy) > 1)
        {
            throw new MovementException(defaultMessage);
        }
        // next, check if the move is a valid diagonal move, performing the move if so
        if(Math.abs(dx) == 1 && Math.abs(dy) == 1)
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
     * Returns a triangle in the appropriate color.
     * @return either △ or ▲ depending on the color of the calling TrianglePiece.
     */
    @Override
    public String toString()
    {
        if(this.getColor())
        {
            return "▲";
        }
        else
        {
            return "△";
        }
    }
}
