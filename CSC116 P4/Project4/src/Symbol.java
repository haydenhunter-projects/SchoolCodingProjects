
/**
 * The Symbol class represents a single symbol used in the Whack A Mole Game. In
 * our
 * game, the Symbol class will be used to represent animals, but a different
 * game could use the Symbol class to represent another type of object or
 * creature.
 * 
 * @author Hayden Hunter
 */
public class Symbol {

    /**
     * intialize a variable string, "name"
     */
    private String name;

    /**
     * initialze a variable int "points"
     */
    private int points = 0;

    /**
     * initialize a variable boolean "hasBeenClickedOn"
     */
    private boolean hasBeenClickedOn = false;

    /**
     * This is the constructor of the class.
     * It accepts a name and a number of points to assign to the instance fields.
     * 
     * @param name   pass through a name of a symbol to assign it to a symbol
     * @param points pass through the points the symbol is worth
     * 
     * @throws IllegalArgumentException with the message Null name if the name is
     *                                  null
     * @throws IllegalArgumentException with the message Invalid points if points
     *                                  is less than 1.
     * 
     *                                  The field that knows if the symbol has been
     *                                  clicked on must be set to false.
     */
    public Symbol(String name, int points) {

        if (name == null) {
            throw new IllegalArgumentException("Null name");
        }

        if (points < 1) {
            throw new IllegalArgumentException("Invalid points");
        }
        this.name = name;
        this.points = points;
    }

    /**
     * This is the getter method for the instance field that knows the name of the
     * symbol.
     * It simply returns the instance field.
     * 
     * @return A string of the name of the symbol
     */
    public String getName() {
        return name;
    }

    /**
     * This is the getter method for the instance field that knows the points for
     * the symbol.
     * It simply returns the instance field.
     * 
     * @return A int of the point value of the symbol
     */
    public int getPoints() {
        return points;
    }

    /**
     * This is the getter method for the instance field that
     * knows whether the symbol has been clicked on.
     * It simply returns the instance field.
     * 
     * @return A boolean telling rather or not a symbol has been clicked on
     */
    public boolean hasBeenClickedOn() {
        return hasBeenClickedOn;
    }

    /**
     * This is the setter method for the instance field that knows
     * whether the symbol has been clicked on. It simply sets the
     * value of the instance field to the value that was passed as a parameter.
     * 
     * @param hasBeenClickedOn pass through true or false determining if
     *                         the symbol has been clicked on or not
     * 
     */
    public void setHasBeenClickedOn(boolean hasBeenClickedOn) {
        this.hasBeenClickedOn = hasBeenClickedOn;
    }

    /**
     * This method returns whether this Symbol and o are equal. If o is not a
     * Symbol,
     * the method should return false. Symbols are considered equal if they have
     * the same name, points, and have both been clicked on/not clicked on.
     * 
     * @param o pass through an object to compare with the symbol object
     * @return true or false if the objects are equal in aspects of name, points,
     *         and if they have been clicked on or not.
     */
    public boolean equals(Object o) {
        boolean isEquals = false;
        if (o instanceof Symbol) {
            Symbol other = (Symbol) o;

            if ((this.name == other.name && this.points == other.points
                    && this.hasBeenClickedOn == other.hasBeenClickedOn)) {
                isEquals = true;
            } else {
                isEquals = false;
            }
        }
        return isEquals;
    }

    /**
     * This method returns the name followed by a space followed by the points
     * followed by a space followed by true or false depending on whether the
     * symbol has been clicked on or not.
     * 
     * @return a string compiled of the symbol name, points, and a boolean
     *         of if it has been clicked on or not
     */
    public String toString() {
        String output = name + " " + points + " " + hasBeenClickedOn;
        return output;
    }

}
