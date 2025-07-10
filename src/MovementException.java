/**
 * This Exception should be thrown if a {@code Piece} attempts to perform an illegal move. <p>
 * The default message is "Cannot move in the requested direction by that many space(s)."
 */
public class MovementException extends Exception 
{
    public MovementException()
    {
        super("Cannot move in the requested direction by that many space(s).");
    }

    public MovementException(String message)
    {
        super(message);
    }
}
