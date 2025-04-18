
/**
 * The Grid class represents a two-dimensional layout of symbols.
 * We will use a 2D array of Symbol objects to manage the actual symbols in the
 * grid.
 * 
 * @author Hayden Hunter
 */
public class Grid {

    /**
     * An integer that represents the number of rows in the grid (2D array).
     */
    private int rows;

    /**
     * An integer that represents the number of columns in the grid (2D array).
     */
    private int cols;

    /**
     * A 2D array of Symbol objects. Each element in the array is a single Symbol
     * object.
     */
    private Symbol[][] symbols;

    /**
     * This is the constructor of the Grid class. The values of the rows and cols
     * parameters
     * should be stored in the appropriate instance variables. You must create a 2D
     * array of Symbol
     * objects with the given number of rows and cols. These array elements will be
     * null.
     * 
     * @param rows a variable that passes in the number of rows for the grid
     * @param cols a variable that passes in the number of columns for the grid
     * 
     * @throws IllegalArgumentException with the message Invalid rows/cols, if
     *                                  rows or cols is less than 1.
     */
    public Grid(int rows, int cols) {
        if ((rows < 1) || (cols < 1)) {
            throw new IllegalArgumentException("Invalid rows/cols");
        }

        this.rows = rows;
        this.cols = cols;
        symbols = new Symbol[rows][cols];

    }

    /**
     * This method returns the number of rows in the grid.
     * 
     * @return an integer of the number of rows
     */
    public int getRows() {
        return rows;
    }

    /**
     * This method returns the number of columns in the grid.
     * 
     * @return an integer of the number of columns
     */
    public int getCols() {
        return cols;
    }

    /**
     * This method stores the symbol in the 2D array at the given row and column.
     * 
     * @param row    the row passed in where the symbol should be stored in on the
     *               grid
     * @param col    the col passed in where the symbol should be stored in on the
     *               grid
     * @param symbol the symbol passed in to be stored in the grid
     * 
     * @throws IllegalArgumentException with the message Null symbol, if the
     *                                  symbol is null.
     * @throws IllegalArgumentException with the message Invalid row if row is
     *                                  less than 0 or
     *                                  greater than or equal to the number of rows.
     * @throws IllegalArgumentException with the message Invalid col if col is
     *                                  less than 0 or greater than or equal to the
     *                                  number of columns.
     */
    public void setSymbol(int row, int col, Symbol symbol) {
        if (symbol == null) {
            throw new IllegalArgumentException("Null symbol");
        }

        if ((row < 0) || row >= rows) {
            throw new IllegalArgumentException("Invalid row");
        }

        if ((col < 0) || col >= cols) {
            throw new IllegalArgumentException("Invalid col");
        }

        symbols[row][col] = symbol;
    }

    /**
     * This method returns the symbol in the 2D array at the given row and column.
     * 
     * @param row the row we wish to pull the symbol from
     * @param col the col we wish to pull the symbol from
     * 
     * @return the symbol found in the grid at (row, col)
     * 
     * @throws IllegalArgumentException with the message Invalid row
     *                                  if row is less than 0 or greater than or
     *                                  equal to the number of rows.
     * @throws IllegalArgumentException with the message Invalid col
     *                                  if col is less than 0 or greater than or
     *                                  equal to the number of columns.
     */
    public Symbol getSymbol(int row, int col) {

        if ((row < 0) || row >= rows) {
            throw new IllegalArgumentException("Invalid row");
        }

        if ((col < 0) || col >= cols) {
            throw new IllegalArgumentException("Invalid col");
        }

        return symbols[row][col];
    }

    /**
     * This method creates a String representation of the grid
     * that contains the name of each symbol in the grid.
     * The names in each row are separated by a single
     * space but there is no space after the last symbol in the row.
     * 
     * @return A string representation of the grid
     */
    public String toString() {

        String update = "";
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.cols; j++) {
                if (j == cols - 1) {
                    update = update + symbols[i][j].getName() + "\n";
                } else if (i == rows - 1 && j == cols - 1) {
                    update = update + symbols[i][j].getName();
                } else {
                    update = update + symbols[i][j].getName() + " ";
                }
            }
        }
        return update;
    }

}
