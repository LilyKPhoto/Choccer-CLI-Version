import java.util.Scanner;

public class PieceMovementTester
{
    private static final Piece testPiece = new StarPiece(false, false, 5, 3); // update the piece type to test different movement types!

    public static void manualMoveTester()
    {
        Scanner key = new Scanner(System.in);
        int newX = 0, newY = 0;
        System.out.printf("%s @ (%d,%d)%n", testPiece, testPiece.getX(), testPiece.getY());
        do
        {
            System.out.print("Enter your target position as two integers followed by a space: ");
            newX = key.nextInt();
            newY = key.nextInt();
            key.nextLine(); // clear buffer for next iteration
            try
            {
                System.out.printf("Attempting to move %s to (%d,%d).%n", testPiece, newX, newY);
                testPiece.move(newX, newY);
                System.out.printf("%s was successfully moved to (%d,%d).%n", testPiece, testPiece.getX(), testPiece.getY());
            }
            catch(MovementException me)
            {
                System.out.println(me.getMessage());
                System.out.printf("%s @ (%d,%d)%n", testPiece, testPiece.getX(), testPiece.getY());
            }
        } while(newX != -1 || newY != -1);
        key.close();
    }
}
