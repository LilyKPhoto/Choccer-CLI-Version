/**
 * This Exception should be thrown if a Piece attempts to capture a Piece of the same color.
 */

public class CaptureException extends Exception 
{
    public CaptureException()
    {
        super("ERROR: Cannot capture piece of same color.");
    }

    public CaptureException(String message)
    {
        super(message);
    }

}
