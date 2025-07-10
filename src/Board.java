/**
 * Board.java is where the bulk of the gameplay code lies.
 * The core game functions are contained within two core methods: movePiece and passGem.
 * Both methods take in parameters for source and destination coordinates.
 * Any driver program should get user input for those and pass them to a Board object.
 * All input gets checked within the two core methods - whether or not the piece can be moved, whether or not a gem is scored, etc.
 * Full game rules can be found in the README.
 */

// TODO: change either the rows or columns to be letters instead of numbers
public class Board
{
    // board is 11 spaces wide by 7 spaces high (11x7), so max values should be 10 and 6 
    // (since counting starts at 0)
    public static final int BOARD_MIN_X = 0;
    public static final int BOARD_MAX_X = 10;
    public static final int BOARD_MIN_Y = 0;
    public static final int BOARD_MAX_Y = 6;
    public static final int BOARD_MAX_TURNS = 100;

    public static final String DEFAULT_PLAYER_1_NAME = "Player 1";
    public static final String DEFAULT_PLAYER_2_NAME = "Player 2";

    // player variables:
    private String player1Name, player2Name;
    private int currentTurn = 1; // 100 turns total
    private int playerOnePoints = 0; // player 1 point total
    private int playerTwoPoints = 0; // player 2 point total
    private boolean isPlayerOneActive = true; // is it player 1's turn? true = yes, false = no.

    // game board variables:
    private final Piece[][] gameBoard = new Piece[7][11];
    private final GemPiece[][] heldGemPositions = new GemPiece[7][11]; // GemPieces should be moved here if they get held.
    private int numCaptured = 0;
    private final Piece[] capturedPieces = new Piece[22];

    // game state variables
    private boolean isGameOver = false; // moving a RedGem to a goal zone should end the game immediately.

    public Board() {
        initializeGameBoard();
        this.player1Name = DEFAULT_PLAYER_1_NAME;
        this.player2Name = DEFAULT_PLAYER_2_NAME;
    }

    public Board(String p1, String p2)
    {
        initializeGameBoard();
        player1Name = p1;
        player2Name = p2;
    }

    /**
     * sets up the starting game board.
     */
    public void initializeGameBoard() {
        // row 0:
        gameBoard[0][3] = new TrianglePiece(false, false, 3, 0);
        gameBoard[0][5] = new BlueGem(5, 0, 4); // there needs to be a Blue Gem here
        gameBoard[0][7] = new TrianglePiece(true, false, 7, 0);

        // row 1
        gameBoard[1][2] = new StarPiece(false, false, 2, 1);
        gameBoard[1][3] = new TrianglePiece(false, false, 3, 1);
        gameBoard[1][7] = new TrianglePiece(true, false, 7, 1);
        gameBoard[1][8] = new StarPiece(true, false, 8, 1);

        // row 2
        gameBoard[2][2] = new CloverPiece(false, false, 2, 2);
        gameBoard[2][3] = new CirclePiece(false, false, 3, 2);
        gameBoard[2][7] = new CirclePiece(true, false, 7, 2);
        gameBoard[2][8] = new CloverPiece(true, false, 8, 2);

        // row 3
        gameBoard[3][3] = new CirclePiece(false, false, 3, 3);
        gameBoard[3][5] = new RedGem(5, 3, 10); // the one and only red gem
        gameBoard[3][7] = new CirclePiece(true, false, 7, 3);

        // row 4
        gameBoard[4][2] = new CloverPiece(false, false, 2, 4);
        gameBoard[4][3] = new CirclePiece(false, false, 3, 4);
        gameBoard[4][7] = new CirclePiece(true, false, 7, 4);
        gameBoard[4][8] = new CloverPiece(true, false, 8, 4);

        // row 5
        gameBoard[5][2] = new StarPiece(false, false, 2, 5);
        gameBoard[5][3] = new TrianglePiece(false, false, 3, 5);
        gameBoard[5][7] = new TrianglePiece(true, false, 7, 5);
        gameBoard[5][8] = new StarPiece(true, false, 8, 5);

        // row 6:
        gameBoard[6][3] = new TrianglePiece(false, false, 3, 6);
        gameBoard[6][5] = new BlueGem(5, 6, 4);
        gameBoard[6][7] = new TrianglePiece(true, false, 7, 6);
    }

    /**
     * Prints the game board.
     */
    public void printBoard() {
        // print the current turn:
        System.out.printf("Current Turn: %d/%d%n", currentTurn, Board.BOARD_MAX_TURNS);

        System.out.printf("%s has %d points.%n", player1Name, playerOnePoints);
        System.out.printf("%s has %d points.%n", player2Name, playerTwoPoints);
        if (isPlayerOneActive) {
            System.out.printf("It's %s's turn!%n", player1Name);
        } else {
            System.out.printf("It's %s's turn!%n", player2Name);
        }
        // print the top row:
        System.out.println("    ┌───┬───┬───┬───┬───┬───┬───┬───┬───┬───┬───┐");
        System.out.println("    │ A │ B │ C │ D │ E │ F │ G │ H │ I │ J │ K │");
        System.out.println("┌───┼───┼───┼───┼───┼───┼───┼───┼───┼───┼───┼───┼───┐");
        // print the game board:
        for (int i = 0; i < gameBoard.length; i++) {
            System.out.print("│ " + i);
            for (int j = 0; j < gameBoard[i].length; j++) {
                if((i >= 2 && i <= 4) && (j <= 2 || j > 8)) {
                    System.out.print(" ┃");
                }
                else {
                    System.out.print(" │");
                }
                if (gameBoard[i][j] == null) {
                    System.out.print("  ");
                } else {
                    System.out.print(" " + gameBoard[i][j]);
                }
            } // end of inner for loop
            if(i >= 2 && i <= 4) {
                System.out.print(" ┃ " + i + " │");
            }
            else {
                System.out.print(" │ " + i + " │");
            }
            System.out.println();
            if(i == 1)
            {
                System.out.println("├───╆━━━╈━━━╅───┼───┼───┼───┼───┼───┼───┢━━━╈━━━╅───┤");
            }
            else if(i >= 2 && i <= 3)
            {
                System.out.println("├───╊━━━╋━━━╉───┼───┼───┼───┼───┼───┼───╊━━━╋━━━╉───┤");
            }
            else if(i == 4)
            {
                System.out.println("├───╄━━━╇━━━╃───┼───┼───┼───┼───┼───┼───┡━━━╇━━━╃───┤");
            }
            else if(i == 0 || i == 5)
            {
                System.out.println("├───┼───┼───┼───┼───┼───┼───┼───┼───┼───┼───┼───┼───┤");
            }
            else if (i == 6)
            {
                System.out.println("└───┼───┼───┼───┼───┼───┼───┼───┼───┼───┼───┼───┼───┘");
            }
        } // end of outer for loop
        System.out.println("    │ A │ B │ C │ D │ E │ F │ G │ H │ I │ J │ K │");
        System.out.println("    └───┴───┴───┴───┴───┴───┴───┴───┴───┴───┴───┘");
        // now print the captured pieces, only if any pieces have been captured:
        if (capturedPieces[0] != null) {
            System.out.print("Captured Pieces: ");
            for (Piece capturedPiece : capturedPieces) {
                if (capturedPiece == null) {
                    System.out.print("  ");
                } else {
                    System.out.print(capturedPiece + " ");
                }
            }
            System.out.println();
        }
        // finally, print the status of any held gems, including who is holding them:
        for (int i = 0; i < heldGemPositions.length; i++) {
            for (int j = 0; j < heldGemPositions[i].length; j++) {
                if (heldGemPositions[i][j] != null) {
                    if (heldGemPositions[i][j] instanceof BlueGem) {
                        // if player 1 is holding it:
                        if(gameBoard[i][j].getColor()) {
                            System.out.printf("%s is holding a Blue Gem at (%d, %d).%n", player1Name, j, i);
                        }
                        else {
                            System.out.printf("%s is holding a Blue Gem at (%d, %d).%n", player2Name, j, i);
                        }
                    }
                    if (heldGemPositions[i][j] instanceof RedGem) {
                        if(gameBoard[i][j].getColor()) {
                            System.out.printf("%s is holding the Red Gem at (%d, %d).%n", player1Name, j, i);
                        }
                        else {
                            System.out.printf("%s is holding the Red Gem at (%d, %d).%n", player2Name, j, i);
                        }
                    }
                }
            }
        }
    }

    /**
     * Attempts to move a Piece on the gameBoard from (fromX, fromY) to (toX, toY).
     * @param fromX the original x coord
     * @param fromY the original y coord
     * @param toX   the destination x coord
     * @param toY   the destination y coord
     */
    public void movePiece(int fromX, int fromY, int toX, int toY) {
        boolean validMovePerformed = false; // default to no valid move being performed, gets overridden if a valid move gets performed
        // first, check to see if the player is trying to move their own piece, or the opponent's:
        try {
            if (isPlayerOneActive == gameBoard[fromY][fromX].getColor()) {
                // if the player is moving their own piece, check if the target space is empty:
                if (gameBoard[toY][toX] == null) {
                    // next, try moving the specified Piece to the new space.
                    try {
                        // check if all spaces in between the start and end positions are clear, assuming that the piece is trying to move multiple spaces at a time
                        int dx = (toX - fromX);
                        int dy = (toY - fromY);
                        // if the piece is JUST moving in +x
                        if(dx > 0 && dy == 0) {
                            for (int i = fromX + 1; i <= toX; i++) {
                                if(gameBoard[fromY][i] != null) {
                                    System.out.println("Piece cannot move past allied piece");
                                    return;
                                }
                            }
                        }
                        // if the piece is JUST moving in -x
                        else if (dx < 0 && dy == 0) {
                            for (int i = fromX - 1; i >= toX; i--) {
                                if(gameBoard[fromY][i] != null) {
                                    System.out.println("Piece cannot move past allied piece");
                                    return;
                                }
                            }
                        }
                        // if the piece is JUST moving in +y
                        else if(dx == 0 && dy > 0) {
                            for (int i = fromY + 1; i <= toY; i++) {
                                if(gameBoard[i][fromX] != null) {
                                    System.out.println("Piece cannot move past allied piece");
                                    return;
                                }
                            }
                        }
                        // if the piece is JUST moving in -y
                        else if (dx == 0 && dy < 0) {
                            for (int i = fromY - 1; i >= toY; i--) {
                                if(gameBoard[i][fromX] != null) {
                                    System.out.println("Piece cannot move past allied piece");
                                    return;
                                }
                            }
                        }
                        /* handling compound directions now */
                        // +x, +y
                        else if(dx > 0 && dy > 0) {
                            for(int i = fromX + 1, j = fromY + 1; i <= toX && j <= toY; i++, j++) {
                                if(gameBoard[j][i] != null) {
                                    System.out.println("Piece cannot move past allied piece");
                                    return;
                                }
                            }
                        }
                        // +x, -y
                        else if(dx > 0 && dy < 0) {
                            for(int i = fromX + 1, j = fromY - 1; i <= toX && j >= toY; i++, j--) {
                                if(gameBoard[j][i] != null) {
                                    System.out.println("Piece cannot move past allied piece");
                                    return;
                                }
                            }
                        }
                        // -x, +y
                        else if(dx < 0 && dy > 0) {
                            for(int i = fromX - 1, j = fromY + 1; i >= toX && j <= toY; i--, j++) {
                                if(gameBoard[j][i] != null) {
                                    System.out.println("Piece cannot move past allied piece");
                                    return;
                                }
                            }
                        }
                        // -x, -y
                        else if(dx < 0 && dy < 0) {
                            for(int i = fromX - 1, j = fromY - 1; i >= toX && j >= toY; i--, j--) {
                                if(gameBoard[j][i] != null) {
                                    System.out.println("Piece cannot move past allied piece");
                                    return;
                                }
                            }
                        }
                        gameBoard[fromY][fromX].move(toX, toY);
                        // is the current piece holding a Gem? move it if so
                        if (gameBoard[fromY][fromX].getHoldingGem()) {
                            // if this returns true, that means a Gem was scored. handle that appropriately:
                            if(heldGemPositions[fromY][fromX].move(toX, toY)) {
                                // if the held Gem is a Blue Gem, add points and move to captured array
                                if (heldGemPositions[fromY][fromX] instanceof BlueGem) {
                                    if (isPlayerOneActive) {
                                        playerOnePoints += heldGemPositions[fromY][fromX].getPointValue();
                                    }
                                    else {
                                        playerTwoPoints += heldGemPositions[fromY][fromX].getPointValue();
                                    }
                                    gameBoard[fromY][fromX].setHoldingGem(false); // remove the gem from the piece
                                    capturedPieces[numCaptured] = heldGemPositions[fromY][fromX]; // add to captured array
                                    heldGemPositions[fromY][fromX] = null; // remove it from the heldGemPositions array
                                    numCaptured++; // bump numCaptured
                                }
                                // if it's a Red gem, though, add the points and end the game.
                                else if (heldGemPositions[fromY][fromX] instanceof RedGem) {
                                    if (isPlayerOneActive) {
                                        playerOnePoints += heldGemPositions[fromY][fromX].getPointValue();
                                    }
                                    else {
                                        playerTwoPoints += heldGemPositions[fromY][fromX].getPointValue();
                                    }
                                    isGameOver = true; // end the game!
                                }
                            }
                            heldGemPositions[toY][toX] = heldGemPositions[fromY][fromX];
                            heldGemPositions[fromY][fromX] = null;
                        }
                        // once we're done with that, move the piece on the gameBoard:
                        gameBoard[toY][toX] = gameBoard[fromY][fromX]; // move the piece to the new space
                        gameBoard[fromY][fromX] = null; // remove the piece from the old space
                        currentTurn++; // increment the current turn counter only if the move is valid
                        isPlayerOneActive = !isPlayerOneActive; // switch the active player
                        validMovePerformed = true; // signal that a valid move was performed
                    } catch (MovementException me) {
                        // if there was a MovementException thrown from an invalid move, catch it, and display the error message:
                        System.out.println(me.getMessage());
                    }
                }
                // if the target space is NOT empty...
                else {
                    // check if the target piece is a Gem, holding it if true:
                    // procedure:
                    // 1. add the target Gem to the heldGemPositions array
                    // 2. remove it from the main board
                    // 3. move the Piece
                    if (gameBoard[toY][toX] instanceof GemPiece) {
                        try {
                            heldGemPositions[toY][toX] = (GemPiece) gameBoard[toY][toX]; // shallow copy is fine here (1.)
                            try {
                                gameBoard[fromY][fromX].move(toX, toY);
                                gameBoard[fromY][fromX].setHoldingGem(true);
                                gameBoard[toY][toX] = gameBoard[fromY][fromX]; // 3.
                                gameBoard[fromY][fromX] = null; // 2.
                                currentTurn++; // increment the current turn counter only if the move is valid
                                isPlayerOneActive = !isPlayerOneActive; // switch the active player
                                validMovePerformed = true;
                            } catch (MovementException me) {
                                System.out.println(me.getMessage());
                            }
                        } catch (ClassCastException cce) {
                            // if we hit this, then somehow the typecast didn't work
                            System.out.println("There was an error in capturing gem.");
                        }
                    }
                    // if the target piece is NOT a gem, and if the target piece's color does not match the current piece's color,
                    // then capture it, but ONLY IF the current piece isn't holding a gem:
                    else if ((gameBoard[fromY][fromX].getColor() != gameBoard[toY][toX].getColor()) && !gameBoard[fromY][fromX].getHoldingGem()) {
                        try {
                            gameBoard[fromY][fromX].capture(gameBoard[toY][toX]);
                            capturedPieces[numCaptured] = gameBoard[toY][toX]; // store the captured piece in the appropriate array
                            numCaptured++;
                            try {
                                gameBoard[fromY][fromX].move(toX, toY);
                            } catch (MovementException me) {
                                System.out.println(me.getMessage());
                            }
                            gameBoard[toY][toX] = gameBoard[fromY][fromX];
                            gameBoard[fromY][fromX] = null;
                            // add the appropriate number of points to the appropriate player:
                            if (isPlayerOneActive) {
                                playerOnePoints += gameBoard[toY][toX].getPointValue();
                            } else {
                                playerTwoPoints += gameBoard[toY][toX].getPointValue();
                            }
                            currentTurn++; // increment the current turn counter only if the move is valid
                            isPlayerOneActive = !isPlayerOneActive; // switch the active player
                            validMovePerformed = true;
                        } catch (CaptureException ce) {
                            System.out.println(ce.getMessage());
                        }
                    } else if (gameBoard[fromY][fromX].getHoldingGem()) {
                        System.out.println("You can't capture a piece while holding a gem!");
                    }
                    // if colors match, then the player is trying to perform an illegal move.
                    else {
                        System.out.println("ERROR: Cannot move on top of allied piece.");
                    }
                }
            } else {
                System.out.println("That's not your piece!");
            }
        }
        // if the player attempts to move an empty space, a NullPointerException will be thrown.
        // We don't want the program to crash, so we need to catch that:
        catch(NullPointerException npe) {
            System.out.println("Error: Attempted to move empty space!");
        }
        if(validMovePerformed) {
            System.out.println();
            printBoard(); // print the new board state at the very end of the move
        }
    }

    /**
     * Attempts to pass a Gem on the gameBoard from (fromX, fromY) to (toX, toY).
     * @param fromX the original x coord
     * @param fromY the original y coord
     * @param toX   the destination x coord
     * @param toY   the destination y coord
     */
    public void passGem(int fromX, int fromY, int toX, int toY)
    {
        /*
        * General Idea:
        * 0. Check if target piece is a Gem (turns out not including this check results in a bug)
        * 1. Check if target piece is the same color
        * 2. Check if target piece is in range (2 tiles in any direction)
        * 3. Check if target piece is holding a gem already (I forgot about this one initially)
        * 4. Move the Gem in the heldGemPositions array to the new position
        * 5. Remove the Gem from the old position
        * 6. Set the original piece's isHoldingGem flag to false
        * 7. Set the new piece's isHoldingGem flag to true
        * 8. Move to next turn.
        */
        boolean validMovePerformed = false; // default to no valid move being performed, gets overridden if a valid move gets performed
        if(gameBoard[toY][toX] instanceof GemPiece) // 0.
        {
            System.out.println("You can't pass a gem to another gem!");
        }
        else if(gameBoard[fromY][fromX].getColor() == gameBoard[toY][toX].getColor()) // 1.
        {
            // create difference variables for the difference in x/y positions - used for validity checking
            int dx = Math.abs(toX - fromX);
            int dy = Math.abs(toY - fromY);

            if(dx > 2 || dy > 2) // 2.
            {
                System.out.println("Target piece is out of range.");
            }
            else if (gameBoard[toY][toX].getHoldingGem()) // 3.
            {
                System.out.println("Target piece already has a gem!");
            }
            else
            {
                heldGemPositions[toY][toX] = heldGemPositions[fromY][fromX]; // 4.
                heldGemPositions[fromY][fromX] = null; // 5.
                gameBoard[fromY][fromX].setHoldingGem(false); // 6.
                gameBoard[toY][toX].setHoldingGem(true); // 7.
                currentTurn++;                          // 8.
                isPlayerOneActive = !isPlayerOneActive; // 8. (also)
                validMovePerformed = true;
                System.out.printf("Passed gem from (%d, %d) to (%d, %d).%n", fromX, fromY, toX, toY);
            }
        }
        else
        {
            System.out.println("You can't throw a gem to your opponent!");
        }
        if(validMovePerformed) {
            System.out.println();
            printBoard(); // print the new board state at the very end of the move
        }

    }

    public Piece getSelectedPiece(int x, int y)
    {
        return gameBoard[y][x];
    }
    public int getCurrentTurn() {
        return this.currentTurn;
    }

    public int getPlayerOnePoints() {
        return this.playerOnePoints;
    }
    public int getPlayerTwoPoints() {
        return this.playerTwoPoints;
    }
    public boolean getGameState() {
        return this.isGameOver;
    }
}
