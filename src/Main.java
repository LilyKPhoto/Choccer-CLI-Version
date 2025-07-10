import java.util.Scanner;

/**
 * Driver program for the game
 * uh rules go here and stuff idk
 */
public class Main {
    public static void main(String[] args) {
        // who up writing they code
        System.out.println("Welcome to Choccer!");
        Scanner key = new Scanner(System.in);
        Board gameBoard = new Board();
        gameBoard.printBoard();
        int fromX = 0, fromY = 0, toX = 0, toY = 0;
        // play for up to the max number of turns
        while (gameBoard.getCurrentTurn() <= Board.BOARD_MAX_TURNS && !gameBoard.getGameState()) {
            System.out.print("Select piece (x y): ");
            fromX = key.nextInt();
            fromY = key.nextInt();
            key.nextLine(); // clear buffer
            try {
                if (gameBoard.getSelectedPiece(fromX, fromY).getHoldingGem()) {
                    System.out.print("Throw Gem? y/n: ");
                    char wantToThrowGem = key.nextLine().charAt(0);
                    if (Character.toLowerCase(wantToThrowGem) == 'y') {
                        System.out.print("Throw to? (x y): ");
                        toX = key.nextInt();
                        toY = key.nextInt();
                        key.nextLine(); // clear buffer
                        gameBoard.passGem(fromX, fromY, toX, toY);
                        continue;
                    }
                }
            }
            catch (NullPointerException npe) {
                System.out.println("Error: cannot select empty space.");
                continue;
            }
            System.out.print("Move to? (x y): ");
            toX = key.nextInt();
            toY = key.nextInt();
            key.nextLine(); // clear buffer
            try {
                gameBoard.movePiece(fromX, fromY, toX, toY);
            } catch (ArrayIndexOutOfBoundsException aioobe) {
                System.out.println("Did you swap your x and y values?");
            }
        }
        if(gameBoard.getPlayerOnePoints() > gameBoard.getPlayerTwoPoints()) {
            System.out.println("Player 1 won! Congratulations!");
        } else if (gameBoard.getPlayerTwoPoints() > gameBoard.getPlayerOnePoints()) {
            System.out.println("Player 2 won! Congratulations!");
        }
        else {
            System.out.println("The game ended as a tie!");
        }
    }
}