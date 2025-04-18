
import java.util.Random;

/**
 * The WhackaMole class handles the logic behind the game.
 * 
 * @author Hayden Hunter
 */
public class WhackaMole {

    /**
     * An integer that stores the number of rows in the grid
     */
    public static final int ROWS = 5;

    /**
     * An integer that stores the number of columsn in the grid
     */
    public static final int COLS = 5;

    /**
     * An integer that stores the boundary for max random number
     */
    public static final int RANDOM_BOUND = 5;

    /**
     * The string names of every symbol in the game
     */
    public static final String[][] SYMBOL_NAMES = { { "cat", "dog", "tiger", "frog", "cat" },
        { "tiger", "lion", "dog", "tiger", "frog" },
        { "lion", "frog", "mole", "dog", "cat" },
        { "frog", "dog", "tiger", "cat", "lion" },
        { "cat", "frog", "lion", "dog", "tiger" } };

    /**
     * The points values of every symbol in the game
     */
    public static final int[][] SYMBOL_POINTS = { { 10, 15, 30, 20, 10 },
        { 30, 40, 15, 30, 20 },
        { 40, 20, 50, 15, 10 },
        { 20, 15, 30, 10, 40 },
        { 10, 20, 40, 15, 30 } };

    /**
     * A boolean variable that keeps track of whether the game is being played in
     * testing mode.
     */
    private boolean testing;

    /**
     * An integer that stores the total score of the player.
     */
    private int totalScore;

    /**
     * An integer that stores the number of misses made by the player.
     */
    private static int numberOfMisses;

    /**
     * A Grid object that represents the way the symbols will be arranged when
     * playing the game.
     */
    private Grid grid = new Grid(ROWS, COLS);

    /**
     * A Random object - this is a random number generator that will be used to
     * choose the next row and column of the symbol to be displayed when not in
     * testing mode.
     */
    private Random rand = new Random(RANDOM_BOUND);

    /**
     * An integer that stores the value of the row of the next symbol to be
     * displayed.
     */
    private int nextRow = 0;

    /**
     * An integer that stores the value of the column of the next symbol to be
     * displayed
     */
    private int nextCol = 0;

    /**
     * This is the constructor of the class. This method must store the value of
     * testing in the instance variable that keeps track of whether the game is in
     * testing mode or not. It must also create a new Grid object. The Grid should
     * be filled with Symbols using the names and point values provided in the
     * SYMBOL_NAMES and SYMBOL_POINTS arrays above. For example, a Symbol with
     * the name cat worth 10 points should be stored in (row 0, col 0) of the
     * Grid, a Symbol with the name dog worth 15 points should be stored
     * in (row 0, col 1) of the Grid, etc. If testing is true, the row and
     * column of the next Symbol to be displayed should each be set to 0.
     * If testing is false, the Random object should be created, for
     * example, rand = new Random(), if the name of the Random object is rand.
     * Then the row and column of the next Symbol to be displayed should each be set
     * to a random integer between 0 and 4.
     * 
     * 
     * @param testing a boolean variable passed through so the unit tests can use
     *                special test rows and cols
     */
    public WhackaMole(boolean testing) {
        totalScore = 0;
        numberOfMisses = 0;
        testing = this.testing;
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                String name = SYMBOL_NAMES[i][j];
                int points = SYMBOL_POINTS[i][j];
                Symbol symbol = new Symbol(name, points);
                grid.setSymbol(i, j, symbol);
            }
        }
    }

    /**
     * This method returns the total score for the player.
     * 
     * @return the total score of the game
     */
    public int getTotalScore() {
        return totalScore;
    }

    /**
     * This method returns the number of misses made by the player.
     * 
     * @return the total number of misses in the game
     */
    public int getNumberOfMisses() {
        return numberOfMisses;
    }

    /**
     * This method returns the row of the next Symbol to be displayed.
     * 
     * @return an integer that stores the value of the next row
     */
    public int getNextRow() {
        return nextRow;
    }

    /**
     * This method returns the column of the next Symbol to be displayed.
     * 
     * @return an integer that stores the value of the next col
     */
    public int getNextCol() {
        return nextCol;
    }

    /**
     * This method returns the name of the Symbol at the given row and col in
     * the Grid, for example, cat. HINT: Use the appropriate Grid and Symbol class
     * methods
     * 
     * @param row passes through the specific row to find the symbol name
     * @param col passes through the specific col to find the symbol name
     * 
     * @return A string of the name of the symbol at the specific row and col in the
     *         grid
     * 
     * @throws IllegalArgumentException with the message Invalid row if row
     *                                  is less than 0 or greater than or equal to
     *                                  the number of rows.
     * @throws IllegalArgumentException with the message Invalid col if col is
     *                                  less than 0 or greater than or equal to the
     *                                  number of columns.
     */
    public String getSymbolName(int row, int col) {

        if ((row < 0) || row >= ROWS) {
            throw new IllegalArgumentException("Invalid row");
        }

        if ((col < 0) || col >= COLS) {
            throw new IllegalArgumentException("Invalid col");
        }

        String symbolName = grid.getSymbol(row, col).getName();
        return symbolName;
    }

    /**
     * This method returns the points for the Symbol at the given row and col in the
     * Grid. HINT: Use the appropriate Grid and Symbol class methods
     * 
     * @param row passes through the specific row to find the symbol name
     * @param col passes through the specific col to find the symbol name
     * 
     * @return an integer of the points of the symbol found at the specific row and
     *         col in the grid
     * 
     * @throws IllegalArgumentException with the message Invalid row if row is
     *                                  less than 0
     *                                  or greater than or equal to the number of
     *                                  rows.
     * @throws IllegalArgumentException with the message Invalid col if col is
     *                                  less than 0 or greater than or equal to the
     *                                  number of columns.
     */
    public int getSymbolPoints(int row, int col) {

        if ((row < 0) || row >= ROWS) {
            throw new IllegalArgumentException("Invalid row");
        }

        if ((col < 0) || col >= COLS) {
            throw new IllegalArgumentException("Invalid col");
        }

        int symbolPoints = grid.getSymbol(row, col).getPoints();
        return symbolPoints;
    }

    /**
     * This method returns true if the Symbol at the given row and col in the Grid
     * has been clicked on, false otherwise. HINT: Use the appropriate Grid and
     * Symbol class methods.
     * 
     * @param row passes through the specific row to find the symbol name
     * @param col passes through the specific col to find the symbol name
     * 
     * @return true or false depending if the symbol has been clicked on at the
     *         specific
     *         row and col in the grid
     * 
     * @throws IllegalArgumentException with the message
     *                                  Invalid row if row is less than 0 or
     *                                  greater than or equal to the number of
     *                                  rows.
     * @throws IllegalArgumentException with the message Invalid col if col
     *                                  is less than 0 or greater than or equal to
     *                                  the number of columns.
     */
    public boolean hasBeenClickedOn(int row, int col) {

        if ((row < 0) || row >= ROWS) {
            throw new IllegalArgumentException("Invalid row");
        }

        if ((col < 0) || col >= COLS) {
            throw new IllegalArgumentException("Invalid col");
        }

        boolean hasBeenClicked = grid.getSymbol(row, col).hasBeenClickedOn();
        return hasBeenClicked;
    }

    /**
     * This method returns true if every Symbol in the grid has been clicked on,
     * false otherwise.
     * 
     * @return true or false
     */
    public boolean allSymbolsClickedOn() {
        boolean allSymbolsClicked = false;
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                if (hasBeenClickedOn(i, j) == false) {
                    allSymbolsClicked = false;
                } else {
                    allSymbolsClicked = true;
                }
            }
        }

        return allSymbolsClicked;
    }

    /**
     * This method sets the row and column of the next Symbol to be displayed.
     * If all the Symbols have already been clicked on and the game is over, the row
     * and column should each be set to -1.
     * In testing mode, the Symbols will be displayed from left to right and top to
     * bottom in the grid.
     * If not in testing mode, the row and column for the next Symbol to be
     * displayed will be selected by randomly generating values between 0 and 4 for
     * each.
     */
    private void updateNextRowAndCol() {
        final int upperbound = 4;
        final int endgame = -1;
        if (allSymbolsClickedOn() == true) {
            this.nextRow = endgame;
            this.nextCol = endgame;

            // game is over
        } // else for allsymbolsclicked
        else if (testing == true) {

            boolean untilTrue = false;
            while (untilTrue == false) {

                if (this.nextCol >= upperbound && this.nextRow >= upperbound) {
                    nextRow = 0;
                    nextCol = 0;
                    break;
                } // if close

                this.nextCol += 1;
                if (this.nextCol > upperbound) {
                    this.nextRow += 1;
                    this.nextCol = 0;
                } // if close

                if (grid.getSymbol(this.nextRow, this.nextCol).hasBeenClickedOn() == true) {
                    this.nextCol += 1;
                    continue;
                } // if close
                else if (grid.getSymbol(this.nextRow, this.nextCol).hasBeenClickedOn() == false) {
                    untilTrue = true;
                } // else if close

            } // while close
        } // if testing close
        else {
            boolean untilTrue2 = false;

            while (untilTrue2 == false) {
                this.nextRow = rand.nextInt(ROWS);
                this.nextCol = rand.nextInt(COLS);

                if (grid.getSymbol(this.nextRow, this.nextCol).hasBeenClickedOn() == true) {
                    continue;
                } // if close
                else {
                    untilTrue2 = true;
                } // else close
            } // while 2 close
        } // else close
    }

    /**
     * If the Symbol in the given row and col has not been clicked on, this method
     * should set the Symbol to has been clicked on, update the total score with
     * the number of points for the Symbol, and call the updateNextRowAndCol()
     * method described above.
     * 
     * @param row passes through the specific row that was clicked
     * @param col passes through the specific col that was clicked
     * 
     * @throws IllegalArgumentException with the message
     *                                  Invalid row if row is less than 0 or
     *                                  greater than or equal to the number of
     *                                  rows.
     * @throws IllegalArgumentException with the message Invalid col if col
     *                                  is less than 0 or greater than or equal to
     *                                  the number of columns.
     */
    public void clickOnSymbol(int row, int col) {
        int updatePoints = 0;
        if ((row < 0) || row >= ROWS) {
            throw new IllegalArgumentException("Invalid row");
        }

        if ((col < 0) || col >= COLS) {
            throw new IllegalArgumentException("Invalid col");
        }

        if (hasBeenClickedOn(row, col) == false) {
            boolean hasBeenClicked = hasBeenClickedOn(row, col);
            hasBeenClicked = true;
            grid.getSymbol(row, col).setHasBeenClickedOn(hasBeenClicked);
            updatePoints = grid.getSymbol(row, col).getPoints();
        }
        totalScore = this.totalScore + updatePoints;
        updateNextRowAndCol();
    }

    /**
     * This method should add one to the number of misses and call the
     * updateNextRowAndCol() method described above.
     */
    public void addMiss() {
        numberOfMisses = numberOfMisses + 1;
        updateNextRowAndCol();
    }

    /**
     * This method returns the Grid object. It will be used for testing/grading.
     * Unit Testing
     * 
     * @return the grid object
     */
    public Grid getGrid() {
        return grid;
    }
}
