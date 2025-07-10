/** Parent Piece class. all Piece types will extend this parent class.
 * All Pieces have the following common Attributes:
 * - Position (x,y)
 * - Color (either white or black, determines starting side)
 * All Pieces have the following common Abilities:
 * - Can capture another Piece by moving to the same space as the other Piece
 *   - If a Piece captures another Piece while other Piece is holding Gem, then the original Piece takes possession of the Gem.
 * - Can pick up a Gem by moving to the same space as the Gem
 *   - However, Pieces holding Gems CANNOT capture other Pieces.
 * - Can "throw" Gem to other allied Piece within 2 spaces in any direction (5x5 square surrounding Piece)
 * - Cannot move onto space with same colored Piece
 * - No Pieces can move past any other Piece (mostly applies to Clovers and Stars but technically applies to Circles and Triangles too
 * */


public abstract class Piece
{
    // common attributes:
    private boolean isBlack;         // all pieces can either be white or black. white starts on left side, black starts on right side.
    private boolean isHoldingGem;    // all pieces can either be holding a gem or not.
    private boolean hasBeenCaptured; // if a piece gets captured, send to (-1, -1) and set this to true.
    private int xPos, yPos;          // all pieces have a position on the 11x7 board. (-1, -1) indicates captured.
    private int pointValue;          // all pieces have a point value.
    // no argument constructor:
    public Piece()
    {
        setColor(false);
        setHoldingGem(false);
        setHasBeenCaptured(false);
        setPosition(0, 0);
        setPointValue(0);
    }
    // full constructor:
    public Piece(boolean isBlack, boolean isHoldingGem, int x, int y, int pointValue)
    {
        setColor(isBlack);
        setHoldingGem(isHoldingGem);
        setPosition(x, y);
        setPointValue(pointValue);
    }
    // copy constructor:
    public Piece(Piece other)
    {
        this.setAll(other.getColor(), other.getHoldingGem(), other.getX(), other.getY(), other.getPointValue());
    }
    // setter methods for common attributes:
    /** Description: sets the color to white (false) or black (true).*/
    public void setColor(boolean isBlack)
    {
        this.isBlack = isBlack;
    }
    public void setHoldingGem(boolean isHoldingGem)
    {
        this.isHoldingGem = isHoldingGem;
    }

    public void setHasBeenCaptured(boolean hasBeenCaptured)
    {
        this.hasBeenCaptured = hasBeenCaptured;
    }
    /** Description: sets the x position.*/
    public void setX(int x)
    {
        xPos = x;
    }
    /** Description: sets the y position.*/
    public void setY(int y)
    {
        yPos = y;
    }
    /** Description: sets the x and y positions.*/
    public void setPosition(int x, int y)
    {
        xPos = x;
        yPos = y;
    }
    /** Description: sets the point value.*/
    public void setPointValue(int pointValue)
    {
        this.pointValue = pointValue;
    }
    /**
     * Sets all instance variables.
     * @param isBlack boolean telling whether (true) or not (false) the piece is black
     * @param isHoldingGem boolean telling whether or not the piece is holding a Gem
     * @param x int for the x position
     * @param y int for the y position
     * @param pointValue int for the point value
     */
    public void setAll(boolean isBlack, boolean isHoldingGem, int x, int y, int pointValue)
    {
        setColor(isBlack);
        setHoldingGem(isHoldingGem);
        setPosition(x, y);
        setPointValue(pointValue);
    }

    // getter methods for common attributes:
    /** Description: returns a boolean telling whether or not the piece is a black piece.*/
    public boolean getColor()
    {
        return isBlack;
    }
    /** Description: returns the x position.*/
    public int getX()
    {
        return xPos;
    }
    /** Description: returns the y position.*/
    public int getY()
    {
        return yPos;
    }
    /** Description: returns the point value.*/
    public int getPointValue()
    {
        return pointValue;
    }
    /** Description: returns whether or not the Piece is holding a gem.*/
    public boolean getHoldingGem()
    {
        return isHoldingGem;
    }

    // standard helper methods:
    @Override
    public String toString()
    {
        String output = "";
        if(isBlack)
        {
            output = "Black";
        }
        else
        {
            output = "White";
        }
        output += " Piece worth ";
        output += pointValue;
        output += " points, located @ (";
        output += xPos;
        output += ", ";
        output += yPos;
        output += ")";
        return output;
    }
    /**
     * checks if two pieces are equal
     * @param other the other Piece to check
     * @return true if all variables are equal, false if not
     */
    public boolean equals(Piece other)
    {
        return this.isBlack == other.isBlack
                && this.isHoldingGem == other.isHoldingGem
                && this.hasBeenCaptured == other.hasBeenCaptured
                && this.pointValue == other.pointValue
                && this.xPos == other.xPos
                && this.yPos == other.yPos;
    }
    // Common Piece Abilities:

    /**
     * Captures another Piece. First checks if the Piece colors don't match.
     * Then, sets the other Piece's hasBeenCaptured flag to true, moves it to (-1, -1), and if the other Piece is holding a gem, that Gem gets held by the capturing piece.
     * @param other the Piece to be captured
     * @return the point value of the captured piece
     * @throws CaptureException if the calling Piece attempts to capture another Piece of the same color
     */
    public int capture(Piece other) throws CaptureException
    {
        if (this.isBlack != other.isBlack)
        {
            other.hasBeenCaptured = true;
            other.setPosition(-1, -1);
            if (other.isHoldingGem)
            {
                other.isHoldingGem = false;
                this.isHoldingGem = true;
            }

            return other.getPointValue();
        }
        else
        {
            throw new CaptureException(); // if the two pieces share the same color, throw an exception.
        }
    }

    /**
     * Master move method. Override in subclasses to implement movement for each piece type.
     * <p>Illegal moves include hitting the edge, illegal directions, illegal movement amounts, etc.</p>
     * <p>Implementations should show valid movements.</p>
     * <p>Implementations should throw a MovementException if the attempted move is illegal.</p>
     *
     * @param newX the new x position to move to.
     * @param newY the new y position to move to.
     * @return true if the move results in a gem being scored, false if not
     * @throws MovementException if the targeted position is illegal.
     */
    public abstract boolean move(int newX, int newY) throws MovementException;
    
    // public void passGem(GemPiece gp, Piece teammate)
    // {
    //     // code goes here
    // }

}
