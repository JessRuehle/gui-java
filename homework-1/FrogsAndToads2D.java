public class FrogsAndToads2D {
    // variables for all the different integers that will not change
    private static final int FROG = 1;
    private static final int TOAD = 2;
    private static final int EMPTY_SPACE = 0;
    private static final int ROWS = 5;
    private static final int COLS = 5;

    // variables to track the empty space's row and column location
    private static int EMPTY_SPACE_ROW = 0;
    private static int EMPTY_SPACE_COL = 0;

    // two integer arrays, one initialized how the game should start, one in the
    // winning configuration
    private final int[][] grid = {
            {1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1},
            {1, 1, 0, 2, 2},
            {2, 2, 2, 2, 2},
            {2, 2, 2, 2, 2},
    };
    private static final int[][] winningGrid = {
            {2, 2, 2, 2, 2},
            {2, 2, 2, 2, 2},
            {2, 2, 0, 1, 1},
            {1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1},
    };

    /**
     * Sets up the board with all the initial values of the array.
     */
    public FrogsAndToads2D() {
        int[][] playingGrid = grid;
    }

    /**
     * Determines the type of creature at the index the user wants to move and uses the
     * checkFrogMove or checkToadMove methods to check the validity of this move and
     * makes it using the makeMove method if so.
     */
    public void move(int row, int col) {

        // if the creature at the chosen index is a frog, pass the arguments to the
        // checkFrogMove helper method to check validity of move. If there is a valid
        // move, pass row and column location to makeMove method to reassign index values.
        if (grid[row][col] == FROG) {
            if (checkFrogMove(row, col)) {
                makeMove(row, col);
            }
        }
        // if the creature at the chosen index is a toad, pass the arguments to the
        // checkToadMove helper method to check validity of move. If there is a valid
        // move, pass row and column location to makeMove method to reassign index values.
        if (grid[row][col] == TOAD) {
            if (checkToadMove(row, col)) {
                makeMove(row, col);
            }
        }
    }

    /**
     * Checks if there is a valid move a frog could make and, if so, reassigns the
     * values of the EMPTY_SPACE_ROW and EMPTY_SPACE_COL to the row and column of the
     * index of the empty space. If all valid moves are checked and there are none to
     * be made, method returns false. Each check begins with checking if the move is
     * out of bounds.
     *
     * @return true if a valid move can be made.
     */
    private boolean checkFrogMove(int row, int col) {

        // check if empty space is immediately below the frog
        if (row + 1 < ROWS && grid[row + 1][col] == EMPTY_SPACE) {
            EMPTY_SPACE_ROW = row + 1;
            EMPTY_SPACE_COL = col;
            return true;
        }
        // check if empty space is immediately to the right of the frog
        if (col + 1 < COLS && grid[row][col + 1] == EMPTY_SPACE) {
            EMPTY_SPACE_ROW = row;
            EMPTY_SPACE_COL = col + 1;
            return true;
        }
        // check if empty space is two spaces down and if there's a toad in-between
        // them
        if (row + 2 < ROWS && grid[row + 2][col] == EMPTY_SPACE &&
                grid[row + 1][col] != FROG) {
            EMPTY_SPACE_ROW = row + 2;
            EMPTY_SPACE_COL = col;
            return true;
        }
        // check if empty space is two spaces right and if there's a toad in-between
        // them
        if (col + 2 < COLS && grid[row][col + 2] == EMPTY_SPACE &&
                grid[row + 1][col] != FROG) {
            EMPTY_SPACE_ROW = row;
            EMPTY_SPACE_COL = col + 2;
            return true;
        }
        // return false is there are no valid moves to make
        return false;
    }

    /**
     * Checks if there is a valid move a toad could make and, if so, reassigns the
     * values of the EMPTY_SPACE_ROW and EMPTY_SPACE_COL to the row and column of
     * the index of the empty space. If all valid moves are checked and there are none
     * to be made, method returns false. Each check begins with checking if the move is
     * out of bounds.
     *
     * @return true if a valid move can be made.
     */
    private boolean checkToadMove(int row, int col) {

        // check if empty space is immediately above the toad
        if (row - 1 >= 0 && grid[row - 1][col] == EMPTY_SPACE) {
            EMPTY_SPACE_ROW = row - 1;
            EMPTY_SPACE_COL = col;
            return true;
        }
        // check if empty space is immediately to the left of the toad
        if (col - 1 >= 0 && grid[row][col - 1] == EMPTY_SPACE) {
            EMPTY_SPACE_ROW = row;
            EMPTY_SPACE_COL = col - 1;
            return true;
        }
        // check if empty space is two spaces up and if there's a frog in-between them
        if (row - 2 >= 0 && grid[row - 2][col] == EMPTY_SPACE &&
                grid[row - 1][col] == FROG) {
            EMPTY_SPACE_ROW = row - 2;
            EMPTY_SPACE_COL = col;
            return true;
        }
        // check if empty space is two spaces left and if there's a frog in-between them
        if (col - 2 >= 0 && grid[row][col - 2] == EMPTY_SPACE &&
                grid[row][col - 1] == FROG) {
            EMPTY_SPACE_ROW = row;
            EMPTY_SPACE_COL = col - 2;
            return true;
        }
        // return false if there are no valid moves to make
        return false;
    }

    /**
     * Reassigns the values of the empty space grid location and the index the user chose.
     *
     * @param row row number that user chose.
     * @param col column number that user chose.
     */
    private void makeMove(int row, int col) {

        // reassign the value at the empty space location to whatever the value is at
        // the index the user wants to move. Then set the user-picked index to the
        // empty space.
        grid[EMPTY_SPACE_ROW][EMPTY_SPACE_COL] = grid[row][col];
        grid[row][col] = EMPTY_SPACE;
    }

    /**
     * Returns the game state as a string.
     */
    @Override
    public String toString() {

        // declare the output as a string and start by listing the column numbers
        String output = "  0 1 2 3 4 \n";

        // create string objects for the colors of the letters F and T
        String ansiBoldRed = "\u001B[1;31m";
        String ansiBoldYellow = "\u001B[1;33m";
        String ansiReset = "\u001B[0m";
        String frog = ansiBoldRed + "F" + ansiReset;
        String toad = ansiBoldYellow + "T" + ansiReset;

        // for loop that iterates through the whole array. at the beginning of each
        // row, loop adds the number to the beginning of every row
        for (int i = 0; i < ROWS; i++) {
            output += i + " ";
            for (int j = 0; j < COLS; j++) {
                // if the creature at index [i][j] is a frog, add a frog to the string
                if (grid[i][j] == FROG) {
                    output += frog + " ";
                }
                // if the creature at index [i][j] is a toad, add a toad to the string
                if (grid[i][j] == TOAD) {
                    output += toad + " ";
                    // if the empty space is at index [i][j], add the empty space to the
                    // string
                } else if (grid[i][j] == EMPTY_SPACE) {
                    output += "- ";
                }
            }
            // start a new line at the end of every row
            output += "\n";
        }
        // return the completed string
        return output;
    }

    /**
     * Checks if there are still valid moves left to make by user and returns true if so.
     */
    public boolean canMove() {

        // as long as this is true, the game can still be played
        boolean stillMoves = false;

        // for loop to iterate through the whole array
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                // if the grid location at i, j is a frog, test all possible frog
                // moves on that spot. If a valid move can be made, stillMoves will
                // be set to true and the loop will end
                if (grid[i][j] == FROG) {
                    if (checkFrogMove(i, j)) {
                        stillMoves = true;
                        break;
                    }
                }
                // if the grid location at i, j is a toad, test all possible toad
                // moves on that spot. If a valid move can be made, stillMoves will
                // be set to true and the loop will end
                if (grid[i][j] == TOAD) {
                    if (checkToadMove(i, j)) {
                        stillMoves = true;
                        break;
                    }
                }
            }
        }
        return stillMoves;
    }

    /**
     * Checks if the board is in the winning configuration.
     */
    public boolean inWinningConfig() {

        // counter to track the amount of indices that match the indices in the winning
        // configuration of the board. winningConfig returns false if the game board is
        // not in the winning state
        int matchingTiles = 0;
        boolean winningConfig = false;

        // for loop to go through the whole array and compare the value at each index
        // to the value at each index of the winning board. Adds 1 to matchingTiles if
        // it matches.
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                if (grid[i][j] == winningGrid[i][j]) {
                    matchingTiles++;
                }
            }
        }
        // if the amount of matching tiles is equal to the amount of indices in the
        // array, set winningConfig to true
        if (matchingTiles == 25) {
            winningConfig = true;
        }
        // will return false if arrays don't match
        return winningConfig;
    }
}
