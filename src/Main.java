import java.util.Scanner;

/**
 * Driver program for the game
 * rules are in the readme
 */
public class Main {
    public static void main(String[] args) {
        // who up writing they code
        String player1Name, player2Name;
        Scanner key = new Scanner(System.in);

        System.out.println("Welcome to Choccer!");
        // constant player names for testing purposes
        // player1Name = "P1";
        // player2Name = "P2";
        System.out.print("Player 1, what's your name? "); // figure i may as well ask the players what their names are for a bit more personalization
        player1Name = key.nextLine();
        System.out.printf("Hello, %s! Your pieces are the black ones (which are the solid ones on the *right* side of the board. They'll look white if you're using dark mode on your CLI.)%n", player1Name);

        System.out.print("Player 2, what's your name? ");
        player2Name = key.nextLine();
        System.out.printf("Hello, %s! Your pieces are the white ones (which are the hollow ones on the *left* side of the board. They'll look black if you're using dark mode on your CLI.)%n", player2Name);
        Board gameBoard = new Board(player1Name, player2Name);
        gameBoard.printBoard();
        int fromX, fromY, toX, toY;
        // play for up to the max number of turns
        while (gameBoard.getCurrentTurn() <= Board.BOARD_MAX_TURNS && !gameBoard.getGameState()) {
            System.out.print("Select piece (x y): ");
            fromX = gameBoard.charToInt(key.next());
            fromY = key.nextInt();
            key.nextLine(); // clear buffer
            try {
                if (gameBoard.getSelectedPiece(fromX, fromY).getHoldingGem()) {
                    System.out.print("Throw Gem? y/n: ");
                    char wantToThrowGem = key.nextLine().charAt(0);
                    if (Character.toLowerCase(wantToThrowGem) == 'y') {
                        System.out.print("Throw to? (x y): ");
                        toX = gameBoard.charToInt(key.next());
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
            catch (ArrayIndexOutOfBoundsException aioobe) {
                System.out.println("Error: Attempted to select an out-of-bounds space. Did you swap your row and column values?");
                continue;
            }
            System.out.print("Move to? (x y): ");
            toX = gameBoard.charToInt(key.next());
            toY = key.nextInt();
            key.nextLine(); // clear buffer
            try {
                gameBoard.movePiece(fromX, fromY, toX, toY);
            } catch (ArrayIndexOutOfBoundsException aioobe) {
                System.out.println("Error: Attempted to select an out-of-bounds space. Did you swap your row and column values?");
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