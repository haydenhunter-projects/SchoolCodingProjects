import java.util.*;

/**
 * Guessing Game a minigame based off the game Mastermind
 * where a player has 10 chances to guess a 4 digit code
 * 
 * @author Hayden Hunter
 */
public class GuessingGame {
    /**
     * Replaces 0 where 0 needed to be replaced
     */
    static final int INT_ZERO = 0;

    /**
     * Replaces row one in the code
     */
    static final int INT_ONE = 1;

    /**
     * Replaces row two in the code
     */
    static final int INT_TWO = 2;

    /**
     * Replaces row three in the code
     */
    static final int INT_THREE = 3;

    /**
     * Replaces row four in the code
     */
    static final int INT_FOUR = 4;

    /**
     * Replaces row five in the code
     */
    static final int INT_FIVE = 5;

    /**
     * Replaces row six in the code
     */
    static final int INT_SIX = 6;

    /**
     * Replaces row seven in the code
     */
    static final int INT_SEVEN = 7;

    /**
     * Replaces row eight in the code
     */
    static final int INT_EIGHT = 8;

    /**
     * Replaces row nine in the code
     */
    static final int INT_NINE = 9;

    /**
     * Replaces row 10 in the code
     */
    static final int INT_TEN = 10;

    /**
     * Declares amount of rows for 2d store guess array
     */
    static final int INT_ROWS = 10;

    /**
     * Declares amount of columns for 2d store guess array
     */
    static final int INT_COLUMNS = 6;

    /**
     * Declares boundary value 9 for max accepted digits
     */
    static final int INT_BOUND_DIGIT = 9;

    /**
     * This method creates and returns an integer array of the given length.
     * The rand instance must be used to generate digits between 0 and 9
     * to fill the array starting with element 0
     * 
     * @param rand   a 1d array that stores the secret code for the game
     * 
     * @param length a 1d array that stores the user input/guess for the game
     * 
     * @return Returns a randomly generated number array of whatever length is
     *         specified
     * @throws IllegalArgumentException if the rand is null and if the length is
     *                                  less than one
     */
    public static int[] getSecretCode(Random rand, int length) {

        if (rand == null) {
            throw new IllegalArgumentException("Null rand");
        }
        if (length < INT_ONE) {
            throw new IllegalArgumentException("Invalid length");
        }

        int i;
        int[] array = new int[length];

        for (i = 0; i < array.length; i++) {
            array[i] = rand.nextInt(INT_TEN);
        }

        return array;
    }

    /**
     * Method compares two 1d arrays to find the amount of elements equivalent in
     * the arrays
     * 
     * @param code  a 1d array that stores the secret code for the game
     * 
     * @param guess a 1d array that stores the user input/guess for the game
     * 
     * @return Returns the number of digits in the guess array that are also in
     *         the code array.
     * 
     * @throws IllegalArgumentException if the code or guess array is equal to null,
     *                                  or the length of the code and guess arrays
     *                                  are not equal, or any digit in the
     *                                  code or guess arrays are smaller than 0 or
     *                                  larger than 9
     */
    public static int getCorrectDigits(int[] code, int[] guess) throws IllegalArgumentException {

        int i;
        int j;
        // throws IllegalArgumentException if the code is null
        if (code == null) {
            throw new IllegalArgumentException("Null code");
        }

        // throws IllegalArgumentException if the guess is null
        if (guess == null) {
            throw new IllegalArgumentException("Null guess");
        }

        // throws IllegalArgumentException if the code length does not equal the guess
        // length
        if (code.length != guess.length) {
            throw new IllegalArgumentException("Different lengths");
        }

        // throws IllegalArgumentException if any element in code or guess is not
        // between 0 and 9
        for (i = 0; i < code.length;) {
            if ((code[i] >= 0) && (code[i] <= INT_BOUND_DIGIT)) {
                i++;
            } else {
                throw new IllegalArgumentException("Invalid digit");
            }
        }
        for (i = 0; i < guess.length;) {
            if ((guess[i] >= 0) && (guess[i] <= INT_BOUND_DIGIT)) {
                i++;
            } else {
                throw new IllegalArgumentException("Invalid digit");
            }
        }

        int correctDigits = 0;
        if (code.length == INT_FOUR) {
            for (i = 0; i < guess.length; i++) {
                if ((code[i] == guess[0]) || (code[i] == guess[INT_ONE]) ||
                        (code[i] == guess[INT_TWO])
                        || (code[i] == guess[INT_THREE])) {
                    correctDigits = correctDigits + INT_ONE;
                }
            }
        }
        if (code.length == INT_TWO) {
            for (i = 0; i < guess.length; i++) {
                if ((code[i] == guess[0]) || (code[i] == guess[INT_ONE])) {
                    correctDigits = correctDigits + INT_ONE;
                }
            }
        }
        if (code.length == INT_EIGHT) {
            for (i = 0; i < guess.length; i++) {
                if ((code[i] == guess[0]) || (code[i] == guess[INT_ONE]) ||
                        (code[i] == guess[INT_TWO])
                        || (code[i] == guess[INT_THREE]) || (code[i] == guess[INT_FOUR]) ||
                        (code[i] == guess[INT_FIVE])
                        || (code[i] == guess[INT_SIX])
                        || (code[i] == guess[INT_SEVEN])) {
                    correctDigits = correctDigits + INT_ONE;
                }
            }
        }
        return correctDigits;

    }

    /**
     * Method compares two 1d arrays to find how many elements are
     * in the same location in the other array
     * 
     * 
     * @param code  a 1d array that stores the secret code for the game
     * 
     * @param guess a 1d array that stores the user input/guess for the game
     * 
     * @return Returns the number of digits in the guess array that are in the same
     *         position in the code array.
     * 
     * @throws IllegalArgumentException if the code or guess array is equal to null,
     *                                  or the length of the code and guess arrays
     *                                  are not equal, or any digit in the
     *                                  code or guess arrays are smaller than 0 or
     *                                  larger than 9
     */
    public static int getCorrectDigitsInCorrectPlace(int[] code, int[] guess) {

        int i;
        int correctPlace = 0;

        // throws IllegalArgumentException if the code is null
        if (code == null) {
            throw new IllegalArgumentException("Null code");
        }

        // throws IllegalArgumentException if the guess is null
        if (guess == null) {
            throw new IllegalArgumentException("Null guess");
        }

        // throws IllegalArgumentException if the code length does not equal the guess
        // length
        if (code.length != guess.length) {
            throw new IllegalArgumentException("Different lengths");
        }

        // throws IllegalArgumentException if any element in code or guess is not
        // between 0 and 9
        for (i = 0; i < code.length; i++) {
            if ((code[i] >= 0) && (code[i] <= INT_BOUND_DIGIT)) {
                i++;
            } else {
                throw new IllegalArgumentException("Invalid digit");
            }
        }
        for (i = 0; i < guess.length; i++) {
            if ((guess[i] >= 0) && (guess[i] <= INT_BOUND_DIGIT)) {
                i++;
            } else {
                throw new IllegalArgumentException("Invalid digit");
            }
        }

        for (i = 0; i < guess.length; i++) {
            if (code[i] == guess[i]) {
                ++correctPlace;
            }

        }

        return correctPlace;

    }

    /**
     * the main method initializes the game interface
     * as well as create a seed for the game, and runs the game to completion.
     * 
     * @param args The command line arguments.
     * @throws InputMismatchException when anything other
     *                                than an integer is inputted into the scanner
     */
    public static void main(String args[]) {
        int i;
        // initialze scanner
        Scanner sc = new Scanner(System.in);

        if (args.length > INT_ONE) {
            System.out.println("Usage: java -cp bin GuessingGame <seed>");
            System.exit(INT_ONE);
        }

        // get random seed
        Random rand = new Random();
        if (args.length == INT_ONE) {
            try {
                int seed = Integer.parseInt(args[0]);
                rand.setSeed(seed);
            } catch (NumberFormatException e) {
                System.out.println("Usage: java -cp bin GuessingGame <seed>");
                System.exit(INT_ONE);
            }
        }

        // game interface
        System.out.print("              Welcome to the Guessing Game!\n" +
                "You must try to guess a secret code consisting of 4 digits.\n" +
                "After each guess,  the total number of correct digits (CD)\n" +
                "and the number of correct digits in the correct place (CP)\n" +
                "for the guess and all preceding guesses will be output. You\n" +
                "will have 10 chances to guess the secret code, which will\n" +
                "be revealed, if you do not guess it!\n" + "\n");

        int[] guess = new int[INT_FOUR];
        int numGuess = 0;
        int[] secretCode = getSecretCode(rand, INT_FOUR);
        String secretCodeStr = Arrays.toString(secretCode);
        int[][] storeGuess = new int[INT_ROWS][INT_COLUMNS];
        int temp = 0;
        // user guess interface
        while (numGuess != INT_TEN) {
            System.out.print("\nGuess 4 digits (e.g., 2 8 5 8): ");
            for (i = 0; i < guess.length; i++) {

                while (true) {
                    try {
                        String input = sc.next();
                        temp = Integer.parseInt(input);
                        if (temp >= 0 && temp <= INT_BOUND_DIGIT) {
                            guess[i] = temp;
                            break;
                        } else {
                            System.out.println("Invalid guess");
                            System.out.print("\nGuess 4 digits (e.g., 2 8 5 8): ");
                        }
                    } catch (NumberFormatException ne) {
                        System.out.println("Invalid guess");
                        System.out.print("\nGuess 4 digits (e.g., 2 8 5 8): ");
                    }

                }
            }
            // while close
            // for i close

            int correctDigits = getCorrectDigits(secretCode, guess);
            int correctPlace = getCorrectDigitsInCorrectPlace(secretCode, guess);

            // intialize guess storing interface
            System.out.println("\n  Guess " + " | " + "CD " + "CP ");
            for (int k = numGuess; k <= numGuess; ++k) {
                for (int j = 0; j < guess.length; j++) {
                    storeGuess[k][j] = guess[j];
                    storeGuess[k][INT_FOUR] = correctDigits;
                    storeGuess[k][INT_FIVE] = correctPlace;

                }
                if (k == 0) {
                    for (int j = 0; j < guess.length; ++j) {
                        System.out.printf(" %s", storeGuess[k][j]);
                    }
                    System.out.printf(" | %s", storeGuess[k][INT_FOUR]);
                    System.out.printf("  %s \n", storeGuess[k][INT_FIVE]);
                }
                // if statements to repeat guess after every guess
                if (k == INT_ONE) {
                    // k=0
                    for (int j = 0; j < guess.length; ++j) {
                        System.out.printf(" %s", storeGuess[k - INT_ONE][j]);
                    }
                    System.out.printf(" | %s", storeGuess[k - INT_ONE][INT_FOUR]);
                    System.out.printf("  %s \n", storeGuess[k - INT_ONE][INT_FIVE]);
                    // k==1_ONE
                    for (int j = 0; j < guess.length; ++j) {
                        System.out.printf(" %s", storeGuess[k][j]);
                    }
                    System.out.printf(" | %s", storeGuess[k][INT_FOUR]);
                    System.out.printf("  %s \n", storeGuess[k][INT_FIVE]);
                }
                if (k == INT_TWO) {
                    // k=0
                    for (int j = 0; j < guess.length; ++j) {
                        System.out.printf(" %s", storeGuess[k - k][j]);
                    }
                    System.out.printf(" | %s", storeGuess[k - k][INT_FOUR]);
                    System.out.printf("  %s \n", storeGuess[k - k][INT_FIVE]);
                    // k==1
                    for (int j = 0; j < guess.length; ++j) {
                        System.out.printf(" %s", storeGuess[k - INT_ONE][j]);
                    }
                    System.out.printf(" | %s", storeGuess[k - INT_ONE][INT_FOUR]);
                    System.out.printf("  %s \n", storeGuess[k - INT_ONE][INT_FIVE]);
                    // k==2
                    for (int j = 0; j < guess.length; ++j) {
                        System.out.printf(" %s", storeGuess[k][j]);
                    }
                    System.out.printf(" | %s", storeGuess[k][INT_FOUR]);
                    System.out.printf("  %s \n", storeGuess[k][INT_FIVE]);
                }
                if (k == INT_THREE) {
                    // k=0
                    for (int j = 0; j < guess.length; ++j) {
                        System.out.printf(" %s", storeGuess[k - k][j]);
                    }
                    System.out.printf(" | %s", storeGuess[k - k][INT_FOUR]);
                    System.out.printf("  %s \n", storeGuess[k - k][INT_FIVE]);
                    // k==1
                    for (int j = 0; j < guess.length; ++j) {
                        System.out.printf(" %s", storeGuess[k - k + INT_ONE][j]);
                    }
                    System.out.printf(" | %s", storeGuess[k - k + INT_ONE][INT_FOUR]);
                    System.out.printf("  %s \n", storeGuess[k - k + INT_ONE][INT_FIVE]);
                    // k==2
                    for (int j = 0; j < guess.length; ++j) {
                        System.out.printf(" %s", storeGuess[k - k + INT_TWO][j]);
                    }
                    System.out.printf(" | %s", storeGuess[k - k + INT_TWO][INT_FOUR]);
                    System.out.printf("  %s \n", storeGuess[k - k + INT_TWO][INT_FIVE]);
                    // k==3
                    for (int j = 0; j < guess.length; ++j) {
                        System.out.printf(" %s", storeGuess[k - k + INT_THREE][j]);
                    }
                    System.out.printf(" | %s", storeGuess[k - k + INT_THREE][INT_FOUR]);
                    System.out.printf("  %s \n", storeGuess[k - k + INT_THREE][INT_FIVE]);
                }
                if (k == INT_FOUR) {
                    // k=0
                    for (int j = 0; j < guess.length; ++j) {
                        System.out.printf(" %s", storeGuess[k - k][j]);
                    }
                    System.out.printf(" | %s", storeGuess[k - k][INT_FOUR]);
                    System.out.printf("  %s \n", storeGuess[k - k][INT_FIVE]);
                    // k==1
                    for (int j = 0; j < guess.length; ++j) {
                        System.out.printf(" %s", storeGuess[k - k + INT_ONE][j]);
                    }
                    System.out.printf(" | %s", storeGuess[k - k + INT_ONE][INT_FOUR]);
                    System.out.printf("  %s \n", storeGuess[k - k + INT_ONE][INT_FIVE]);
                    // k==2
                    for (int j = 0; j < guess.length; ++j) {
                        System.out.printf(" %s", storeGuess[k - k + INT_TWO][j]);
                    }
                    System.out.printf(" | %s", storeGuess[k - k + INT_TWO][INT_FOUR]);
                    System.out.printf("  %s \n", storeGuess[k - k + INT_TWO][INT_FIVE]);
                    // k==3
                    for (int j = 0; j < guess.length; ++j) {
                        System.out.printf(" %s", storeGuess[k - k + INT_THREE][j]);
                    }
                    System.out.printf(" | %s", storeGuess[k - k + INT_THREE][INT_FOUR]);
                    System.out.printf("  %s \n", storeGuess[k - k + INT_THREE][INT_FIVE]);
                    // k==4
                    for (int j = 0; j < guess.length; ++j) {
                        System.out.printf(" %s", storeGuess[k - k + INT_FOUR][j]);
                    }
                    System.out.printf(" | %s", storeGuess[k - k + INT_FOUR][INT_FOUR]);
                    System.out.printf("  %s \n", storeGuess[k - k + INT_FOUR][INT_FIVE]);
                }
                if (k == INT_FIVE) {
                    // k=0
                    for (int j = 0; j < guess.length; ++j) {
                        System.out.printf(" %s", storeGuess[k - k][j]);
                    }
                    System.out.printf(" | %s", storeGuess[k - k][INT_FOUR]);
                    System.out.printf("  %s \n", storeGuess[k - k][INT_FIVE]);
                    // k==1
                    for (int j = 0; j < guess.length; ++j) {
                        System.out.printf(" %s", storeGuess[k - k + INT_ONE][j]);
                    }
                    System.out.printf(" | %s", storeGuess[k - k + INT_ONE][INT_FOUR]);
                    System.out.printf("  %s \n", storeGuess[k - k + INT_ONE][INT_FIVE]);
                    // k==2
                    for (int j = 0; j < guess.length; ++j) {
                        System.out.printf(" %s", storeGuess[k - k + INT_TWO][j]);
                    }
                    System.out.printf(" | %s", storeGuess[k - k + INT_TWO][INT_FOUR]);
                    System.out.printf("  %s \n", storeGuess[k - k + INT_TWO][INT_FIVE]);
                    // k==3
                    for (int j = 0; j < guess.length; ++j) {
                        System.out.printf(" %s", storeGuess[k - k + INT_THREE][j]);
                    }
                    System.out.printf(" | %s", storeGuess[k - k + INT_THREE][INT_FOUR]);
                    System.out.printf("  %s \n", storeGuess[k - k + INT_THREE][INT_FIVE]);
                    // k==4
                    for (int j = 0; j < guess.length; ++j) {
                        System.out.printf(" %s", storeGuess[k - k + INT_FOUR][j]);
                    }
                    System.out.printf(" | %s", storeGuess[k - k + INT_FOUR][INT_FOUR]);
                    System.out.printf("  %s \n", storeGuess[k - k + INT_FOUR][INT_FIVE]);
                    // k==5
                    for (int j = 0; j < guess.length; ++j) {
                        System.out.printf(" %s", storeGuess[k - k + INT_FIVE][j]);
                    }
                    System.out.printf(" | %s", storeGuess[k - k + INT_FIVE][INT_FOUR]);
                    System.out.printf("  %s \n", storeGuess[k - k + INT_FIVE][INT_FIVE]);
                }
                if (k == INT_SIX) {
                    // k=0
                    for (int j = 0; j < guess.length; ++j) {
                        System.out.printf(" %s", storeGuess[k - k][j]);
                    }
                    System.out.printf(" | %s", storeGuess[k - k][INT_FOUR]);
                    System.out.printf("  %s \n", storeGuess[k - k][INT_FIVE]);
                    // k==1
                    for (int j = 0; j < guess.length; ++j) {
                        System.out.printf(" %s", storeGuess[k - k + INT_ONE][j]);
                    }
                    System.out.printf(" | %s", storeGuess[k - k + INT_ONE][INT_FOUR]);
                    System.out.printf("  %s \n", storeGuess[k - k + INT_ONE][INT_FIVE]);
                    // k==2
                    for (int j = 0; j < guess.length; ++j) {
                        System.out.printf(" %s", storeGuess[k - k + INT_TWO][j]);
                    }
                    System.out.printf(" | %s", storeGuess[k - k + INT_TWO][INT_FOUR]);
                    System.out.printf("  %s \n", storeGuess[k - k + INT_TWO][INT_FIVE]);
                    // k==3
                    for (int j = 0; j < guess.length; ++j) {
                        System.out.printf(" %s", storeGuess[k - k + INT_THREE][j]);
                    }
                    System.out.printf(" | %s", storeGuess[k - k + INT_THREE][INT_FOUR]);
                    System.out.printf("  %s \n", storeGuess[k - k + INT_THREE][INT_FIVE]);
                    // k==4
                    for (int j = 0; j < guess.length; ++j) {
                        System.out.printf(" %s", storeGuess[k - k + INT_FOUR][j]);
                    }
                    System.out.printf(" | %s", storeGuess[k - k + INT_FOUR][INT_FOUR]);
                    System.out.printf("  %s \n", storeGuess[k - k + INT_FOUR][INT_FIVE]);
                    // k==5
                    for (int j = 0; j < guess.length; ++j) {
                        System.out.printf(" %s", storeGuess[k - k + INT_FIVE][j]);
                    }
                    System.out.printf(" | %s", storeGuess[k - k + INT_FIVE][INT_FOUR]);
                    System.out.printf("  %s \n", storeGuess[k - k + INT_FIVE][INT_FIVE]);
                    // k==6
                    for (int j = 0; j < guess.length; ++j) {
                        System.out.printf(" %s", storeGuess[k - k + INT_SIX][j]);
                    }
                    System.out.printf(" | %s", storeGuess[k - k + INT_SIX][INT_FOUR]);
                    System.out.printf("  %s \n", storeGuess[k - k + INT_SIX][INT_FIVE]);
                }
                if (k == INT_SEVEN) {
                    // k=0
                    for (int j = 0; j < guess.length; ++j) {
                        System.out.printf(" %s", storeGuess[k - k][j]);
                    }
                    System.out.printf(" | %s", storeGuess[k - k][INT_FOUR]);
                    System.out.printf("  %s \n", storeGuess[k - k][INT_FIVE]);
                    // k==1
                    for (int j = 0; j < guess.length; ++j) {
                        System.out.printf(" %s", storeGuess[k - k + INT_ONE][j]);
                    }
                    System.out.printf(" | %s", storeGuess[k - k + INT_ONE][INT_FOUR]);
                    System.out.printf("  %s \n", storeGuess[k - k + INT_ONE][INT_FIVE]);
                    // k==2
                    for (int j = 0; j < guess.length; ++j) {
                        System.out.printf(" %s", storeGuess[k - k + INT_TWO][j]);
                    }
                    System.out.printf(" | %s", storeGuess[k - k + INT_TWO][INT_FOUR]);
                    System.out.printf("  %s \n", storeGuess[k - k + INT_TWO][INT_FIVE]);
                    // k==3
                    for (int j = 0; j < guess.length; ++j) {
                        System.out.printf(" %s", storeGuess[k - k + INT_THREE][j]);
                    }
                    System.out.printf(" | %s", storeGuess[k - k + INT_THREE][INT_FOUR]);
                    System.out.printf("  %s \n", storeGuess[k - k + INT_THREE][INT_FIVE]);
                    // k==4
                    for (int j = 0; j < guess.length; ++j) {
                        System.out.printf(" %s", storeGuess[k - k + INT_FOUR][j]);
                    }
                    System.out.printf(" | %s", storeGuess[k - k + INT_FOUR][INT_FOUR]);
                    System.out.printf("  %s \n", storeGuess[k - k + INT_FOUR][INT_FIVE]);
                    // k==5
                    for (int j = 0; j < guess.length; ++j) {
                        System.out.printf(" %s", storeGuess[k - k + INT_FIVE][j]);
                    }
                    System.out.printf(" | %s", storeGuess[k - k + INT_FIVE][INT_FOUR]);
                    System.out.printf("  %s \n", storeGuess[k - k + INT_FIVE][INT_FIVE]);
                    // k==6
                    for (int j = 0; j < guess.length; ++j) {
                        System.out.printf(" %s", storeGuess[k - k + INT_SIX][j]);
                    }
                    System.out.printf(" | %s", storeGuess[k - k + INT_SIX][INT_FOUR]);
                    System.out.printf("  %s \n", storeGuess[k - k + INT_SIX][INT_FIVE]);
                    // k==7
                    for (int j = 0; j < guess.length; ++j) {
                        System.out.printf(" %s", storeGuess[k - k + INT_SEVEN][j]);
                    }
                    System.out.printf(" | %s", storeGuess[k - k + INT_SEVEN][INT_FOUR]);
                    System.out.printf("  %s \n", storeGuess[k - k + INT_SEVEN][INT_FIVE]);
                }
                if (k == INT_EIGHT) {
                    // k=0
                    for (int j = 0; j < guess.length; ++j) {
                        System.out.printf(" %s", storeGuess[k - k][j]);
                    }
                    System.out.printf(" | %s", storeGuess[k - k][INT_FOUR]);
                    System.out.printf("  %s \n", storeGuess[k - k][INT_FIVE]);
                    // k==1
                    for (int j = 0; j < guess.length; ++j) {
                        System.out.printf(" %s", storeGuess[k - k + INT_ONE][j]);
                    }
                    System.out.printf(" | %s", storeGuess[k - k + INT_ONE][INT_FOUR]);
                    System.out.printf("  %s \n", storeGuess[k - k + INT_ONE][INT_FIVE]);
                    // k==2
                    for (int j = 0; j < guess.length; ++j) {
                        System.out.printf(" %s", storeGuess[k - k + INT_TWO][j]);
                    }
                    System.out.printf(" | %s", storeGuess[k - k + INT_TWO][INT_FOUR]);
                    System.out.printf("  %s \n", storeGuess[k - k + INT_TWO][INT_FIVE]);
                    // k==3
                    for (int j = 0; j < guess.length; ++j) {
                        System.out.printf(" %s", storeGuess[k - k + INT_THREE][j]);
                    }
                    System.out.printf(" | %s", storeGuess[k - k + INT_THREE][INT_FOUR]);
                    System.out.printf("  %s \n", storeGuess[k - k + INT_THREE][INT_FIVE]);
                    // k==4
                    for (int j = 0; j < guess.length; ++j) {
                        System.out.printf(" %s", storeGuess[k - k + INT_FOUR][j]);
                    }
                    System.out.printf(" | %s", storeGuess[k - k + INT_FOUR][INT_FOUR]);
                    System.out.printf("  %s \n", storeGuess[k - k + INT_FOUR][INT_FIVE]);
                    // k==5
                    for (int j = 0; j < guess.length; ++j) {
                        System.out.printf(" %s", storeGuess[k - k + INT_FIVE][j]);
                    }
                    System.out.printf(" | %s", storeGuess[k - k + INT_FIVE][INT_FOUR]);
                    System.out.printf("  %s \n", storeGuess[k - k + INT_FIVE][INT_FIVE]);
                    // k==6
                    for (int j = 0; j < guess.length; ++j) {
                        System.out.printf(" %s", storeGuess[k - k + INT_SIX][j]);
                    }
                    System.out.printf(" | %s", storeGuess[k - k + INT_SIX][INT_FOUR]);
                    System.out.printf("  %s \n", storeGuess[k - k + INT_SIX][INT_FIVE]);
                    // k==7
                    for (int j = 0; j < guess.length; ++j) {
                        System.out.printf(" %s", storeGuess[k - k + INT_SEVEN][j]);
                    }
                    System.out.printf(" | %s", storeGuess[k - k + INT_SEVEN][INT_FOUR]);
                    System.out.printf("  %s \n", storeGuess[k - k + INT_SEVEN][INT_FIVE]);
                    // k==8
                    for (int j = 0; j < guess.length; ++j) {
                        System.out.printf(" %s", storeGuess[k - k + INT_EIGHT][j]);
                    }
                    System.out.printf(" | %s", storeGuess[k - k + INT_EIGHT][INT_FOUR]);
                    System.out.printf("  %s \n", storeGuess[k - k + INT_EIGHT][INT_FIVE]);
                }
                if (k == INT_NINE) {
                    // k=0
                    for (int j = 0; j < guess.length; ++j) {
                        System.out.printf(" %s", storeGuess[k - k][j]);
                    }
                    System.out.printf(" | %s", storeGuess[k - k][INT_FOUR]);
                    System.out.printf("  %s \n", storeGuess[k - k][INT_FIVE]);
                    // k==1
                    for (int j = 0; j < guess.length; ++j) {
                        System.out.printf(" %s", storeGuess[k - k + INT_ONE][j]);
                    }
                    System.out.printf(" | %s", storeGuess[k - k + INT_ONE][INT_FOUR]);
                    System.out.printf("  %s \n", storeGuess[k - k + INT_ONE][INT_FIVE]);
                    // k==2
                    for (int j = 0; j < guess.length; ++j) {
                        System.out.printf(" %s", storeGuess[k - k + INT_TWO][j]);
                    }
                    System.out.printf(" | %s", storeGuess[k - k + INT_TWO][INT_FOUR]);
                    System.out.printf("  %s \n", storeGuess[k - k + INT_TWO][INT_FIVE]);
                    // k==3
                    for (int j = 0; j < guess.length; ++j) {
                        System.out.printf(" %s", storeGuess[k - k + INT_THREE][j]);
                    }
                    System.out.printf(" | %s", storeGuess[k - k + INT_THREE][INT_FOUR]);
                    System.out.printf("  %s \n", storeGuess[k - k + INT_THREE][INT_FIVE]);
                    // k==4
                    for (int j = 0; j < guess.length; ++j) {
                        System.out.printf(" %s", storeGuess[k - k + INT_FOUR][j]);
                    }
                    System.out.printf(" | %s", storeGuess[k - k + INT_FOUR][INT_FOUR]);
                    System.out.printf("  %s \n", storeGuess[k - k + INT_FOUR][INT_FIVE]);
                    // k==5
                    for (int j = 0; j < guess.length; ++j) {
                        System.out.printf(" %s", storeGuess[k - k + INT_FIVE][j]);
                    }
                    System.out.printf(" | %s", storeGuess[k - k + INT_FIVE][INT_FOUR]);
                    System.out.printf("  %s \n", storeGuess[k - k + INT_FIVE][INT_FIVE]);
                    // k==6
                    for (int j = 0; j < guess.length; ++j) {
                        System.out.printf(" %s", storeGuess[k - k + INT_SIX][j]);
                    }
                    System.out.printf(" | %s", storeGuess[k - k + INT_SIX][INT_FOUR]);
                    System.out.printf("  %s \n", storeGuess[k - k + INT_SIX][INT_FIVE]);
                    // k==7
                    for (int j = 0; j < guess.length; ++j) {
                        System.out.printf(" %s", storeGuess[k - k + INT_SEVEN][j]);
                    }
                    System.out.printf(" | %s", storeGuess[k - k + INT_SEVEN][INT_FOUR]);
                    System.out.printf("  %s \n", storeGuess[k - k + INT_SEVEN][INT_FIVE]);
                    // k==8
                    for (int j = 0; j < guess.length; ++j) {
                        System.out.printf(" %s", storeGuess[k - k + INT_EIGHT][j]);
                    }
                    System.out.printf(" | %s", storeGuess[k - k + INT_EIGHT][INT_FOUR]);
                    System.out.printf("  %s \n", storeGuess[k - k + INT_EIGHT][INT_FIVE]);
                    // k==9
                    for (int j = 0; j < guess.length; ++j) {
                        System.out.printf(" %s", storeGuess[k - k + INT_NINE][j]);
                    }
                    System.out.printf(" | %s", storeGuess[k - k + INT_NINE][INT_FOUR]);
                    System.out.printf("  %s \n", storeGuess[k - k + INT_NINE][INT_FIVE]);
                }
                if (k == INT_TEN) {
                    // k=0
                    for (int j = 0; j < guess.length; ++j) {
                        System.out.printf(" %s", storeGuess[k - k][j]);
                    }
                    System.out.printf(" | %s", storeGuess[k - k][INT_FOUR]);
                    System.out.printf("  %s \n", storeGuess[k - k][INT_FIVE]);
                    // k==1
                    for (int j = 0; j < guess.length; ++j) {
                        System.out.printf(" %s", storeGuess[k - k + INT_ONE][j]);
                    }
                    System.out.printf(" | %s", storeGuess[k - k + INT_ONE][INT_FOUR]);
                    System.out.printf("  %s \n", storeGuess[k - k + INT_ONE][INT_FIVE]);
                    // k==2
                    for (int j = 0; j < guess.length; ++j) {
                        System.out.printf(" %s", storeGuess[k - k + INT_TWO][j]);
                    }
                    System.out.printf(" | %s", storeGuess[k - k + INT_TWO][INT_FOUR]);
                    System.out.printf("  %s \n", storeGuess[k - k + INT_TWO][INT_FIVE]);
                    // k==3
                    for (int j = 0; j < guess.length; ++j) {
                        System.out.printf(" %s", storeGuess[k - k + INT_THREE][j]);
                    }
                    System.out.printf(" | %s", storeGuess[k - k + INT_THREE][INT_FOUR]);
                    System.out.printf("  %s \n", storeGuess[k - k + INT_THREE][INT_FIVE]);
                    // k==4
                    for (int j = 0; j < guess.length; ++j) {
                        System.out.printf(" %s", storeGuess[k - k + INT_FOUR][j]);
                    }
                    System.out.printf(" | %s", storeGuess[k - k + INT_FOUR][INT_FOUR]);
                    System.out.printf("  %s \n", storeGuess[k - k + INT_FOUR][INT_FIVE]);
                    // k==5
                    for (int j = 0; j < guess.length; ++j) {
                        System.out.printf(" %s", storeGuess[k - k + INT_FIVE][j]);
                    }
                    System.out.printf(" | %s", storeGuess[k - k + INT_FIVE][INT_FOUR]);
                    System.out.printf("  %s \n", storeGuess[k - k + INT_FIVE][INT_FIVE]);
                    // k==6
                    for (int j = 0; j < guess.length; ++j) {
                        System.out.printf(" %s", storeGuess[k - k + INT_SIX][j]);
                    }
                    System.out.printf(" | %s", storeGuess[k - k + INT_SIX][INT_FOUR]);
                    System.out.printf("  %s \n", storeGuess[k - k + INT_SIX][INT_FIVE]);
                    // k==7
                    for (int j = 0; j < guess.length; ++j) {
                        System.out.printf(" %s", storeGuess[k - k + INT_SEVEN][j]);
                    }
                    System.out.printf(" | %s", storeGuess[k - k + INT_SEVEN][INT_FOUR]);
                    System.out.printf("  %s \n", storeGuess[k - k + INT_SEVEN][INT_FIVE]);
                    // k==8
                    for (int j = 0; j < guess.length; ++j) {
                        System.out.printf(" %s", storeGuess[k - k + INT_EIGHT][j]);
                    }
                    System.out.printf(" | %s", storeGuess[k - k + INT_EIGHT][INT_FOUR]);
                    System.out.printf("  %s \n", storeGuess[k - k + INT_EIGHT][INT_FIVE]);
                    // k==9
                    for (int j = 0; j < guess.length; ++j) {
                        System.out.printf(" %s", storeGuess[k - k + INT_NINE][j]);
                    }
                    System.out.printf(" | %s", storeGuess[k - k + INT_NINE][INT_FOUR]);
                    System.out.printf("  %s \n", storeGuess[k - k + INT_NINE][INT_FIVE]);
                    // k==INT_TEN
                    for (int j = 0; j < guess.length; ++j) {
                        System.out.printf(" %s", storeGuess[k - k + INT_TEN][j]);
                    }
                    System.out.printf(" | %s", storeGuess[k - k + INT_TEN][INT_FOUR]);
                    System.out.printf("  %s \n", storeGuess[k - k + INT_TEN][INT_FIVE]);

                }

            }
            // tick up numGuess after each guess
            ++numGuess;

            // win condition
            if (correctPlace == INT_FOUR) {
                System.out.print("\nYou guessed correctly after " + numGuess + " guess(es)!");
                System.exit(INT_ONE);
            }

        }
        // lose condition
        System.out.print("\nSorry out of guesses the secret code is: ");
        sc.close();
        System.out.printf(" %s", secretCodeStr);
        System.exit(INT_ONE);

    }
}
