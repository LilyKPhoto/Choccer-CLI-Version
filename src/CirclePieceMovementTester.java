import java.util.Scanner;

public class CirclePieceMovementTester
{
    public static void autoMoveTester()
    {
        Piece testCircle = new CirclePiece(false, false, 5, 3);

        /* attempting legal moves: */
        // up:
        try
        {
            testCircle.move(5, 4);
            System.out.printf("%s was successfully moved to (%d,%d).%n", testCircle, testCircle.getX(), testCircle.getY());
        }
        catch(MovementException me)
        {
            System.out.println(me.getMessage());
        }
        // down:
        try
        {
            testCircle.move(5, 3);
            System.out.printf("%s was successfully moved to (%d,%d).%n", testCircle, testCircle.getX(), testCircle.getY());
        }
        catch(MovementException me)
        {
            System.out.println(me.getMessage());
        }
        // left:
        try
        {
            testCircle.move(4, 3);
            System.out.printf("%s was successfully moved to (%d,%d).%n", testCircle, testCircle.getX(), testCircle.getY());
        }
        catch(MovementException me)
        {
            System.out.println(me.getMessage());
        }
        // right:
        try
        {
            testCircle.move(5, 3);
            System.out.printf("%s was successfully moved to (%d,%d).%n%n", testCircle, testCircle.getX(), testCircle.getY());
        }
        catch(MovementException me)
        {
            System.out.println(me.getMessage());
        }

        /* attempting to move by too many spaces: */
        // attempting to move 2 spaces right in x:
        try
        {
            System.out.println("Attempting to move 2 spaces right in x:");
            testCircle.move(7, 3);
            System.out.printf("%s was successfully moved to (%d,%d).%n", testCircle, testCircle.getX(), testCircle.getY());
        }
        catch(MovementException me)
        {
            System.out.println(me.getMessage());
            System.out.printf("%s @ (%d,%d)%n", testCircle, testCircle.getX(), testCircle.getY());
        }
        // attempting to move 2 spaces left in x:
        try
        {
            System.out.println("Attempting to move 2 spaces left in x:");
            testCircle.move(3, 3);
            System.out.printf("%s was successfully moved to (%d,%d).%n", testCircle, testCircle.getX(), testCircle.getY());
        }
        catch(MovementException me)
        {
            System.out.println(me.getMessage());
            System.out.printf("%s @ (%d,%d)%n", testCircle, testCircle.getX(), testCircle.getY());
        }
        // attempting to move 2 spaces up in y:
        try
        {
            System.out.println("Attempting to move 2 spaces up in y:");
            testCircle.move(5, 5);
            System.out.printf("%s was successfully moved to (%d,%d).%n", testCircle, testCircle.getX(), testCircle.getY());
        }
        catch(MovementException me)
        {
            System.out.println(me.getMessage());
            System.out.printf("%s @ (%d,%d)%n", testCircle, testCircle.getX(), testCircle.getY());
        }
        // attempting to move 2 spaces down in y:
        try
        {
            System.out.println("Attempting to move 2 spaces down in y:");
            testCircle.move(5, 1);
            System.out.printf("%s was successfully moved to (%d,%d).%n", testCircle, testCircle.getX(), testCircle.getY());
        }
        catch(MovementException me)
        {
            System.out.println(me.getMessage());
            System.out.printf("%s @ (%d,%d)%n%n", testCircle, testCircle.getX(), testCircle.getY());
        }

        // manually moving piece to (0,0) to test boundary checking:
        testCircle.setPosition(0, 0);
        System.out.println("Manually moving piece to (0,0).");
        System.out.printf("%s @ (%d,%d)%n", testCircle, testCircle.getX(), testCircle.getY());
        // down:
        try
        {
            testCircle.move(0, -1);
            System.out.printf("%s was successfully moved to (%d,%d).%n", testCircle, testCircle.getX(), testCircle.getY());
        }
        catch(MovementException me)
        {
            System.out.println(me.getMessage());
            System.out.printf("%s @ (%d,%d)%n%n", testCircle, testCircle.getX(), testCircle.getY());
        }
        // left:
        try
        {
            testCircle.move(-1, 0);
            System.out.printf("%s was successfully moved to (%d,%d).%n", testCircle, testCircle.getX(), testCircle.getY());
        }
        catch(MovementException me)
        {
            System.out.println(me.getMessage());
            System.out.printf("%s @ (%d,%d)%n%n", testCircle, testCircle.getX(), testCircle.getY());
        }
    }

    public static void manualMoveTester()
    {
        Scanner key = new Scanner(System.in);
        Piece testCircle = new CirclePiece(false, false, 5, 3);
        int newX = 0, newY = 0;
        System.out.printf("%s @ (%d,%d)%n", testCircle, testCircle.getX(), testCircle.getY());
        do
        {
            System.out.print("Enter your target position as two integers followed by a space: ");
            newX = key.nextInt();
            newY = key.nextInt();
            key.nextLine(); // clear buffer for next iteration
            try
            {
                System.out.printf("Attempting to move %s to (%d,%d).%n", testCircle, newX, newY);
                testCircle.move(newX, newY);
                System.out.printf("%s was successfully moved to (%d,%d).%n", testCircle, testCircle.getX(), testCircle.getY());
            }
            catch(MovementException me)
            {
                System.out.println(me.getMessage());
                System.out.printf("%s @ (%d,%d)%n", testCircle, testCircle.getX(), testCircle.getY());
            }
        } while(newX != -1 || newY != -1);
        key.close();
    }
}
