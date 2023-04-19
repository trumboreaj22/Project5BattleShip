import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Player {
    /**
     * An array representing the state of the board
     */
    private char[] board = new char[10];

    /**
     * An integer that represents the number of ship spaces that have not yet been sunk
     */
    private int shipSpacesRemaining = 17;

    /**
     * A set of integers that represents the spaces on the board that have been already guessed
     */
    private Set<Integer> guesses = new HashSet<>();

    /**
     * A map that translates a String location 'A1' to an integer 1
     */
    private Map<String, Integer> m = new HashMap<>();

    /**
     * Constructor - creates a player's board
     */
    public Player(){
        // TODO
    }

    /**
     * Updates the board according to the location entered
     * @param location is an int from 0-99 representing the desired space on the board
     */
    public void play(int location){
        // TODO
    }

    /**
     * Checks the status of the desired space
     * @param location is an int from 0-99 representing the desired space on the board
     * @return a character: 'O' - open water, 'H' - hit ship,
     *                      'A' - alive ship, 'M' - missed water,
     *                      or 'S' - sunk ship
     */
    public char getStatus(int location){
        // TODO
        return ' ';
    }

    /**
     * Translates the location from a string to an int using a map
     * @param location is a String in the form of 'A1'
     * @return
     */
    public int playerSelection(String location){
        // TODO
        return -1;
    }

    /**
     * @return a boolean: true if the player sunk all the ships, otherwise false
     */
    public boolean hasWon(){
        // TODO
        return false;
    }
}
