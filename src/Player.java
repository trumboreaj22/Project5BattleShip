public class Player {
    /**
     * A 2D array representing the state of the board
     */
    private char[][] board = new char[10][10];
    /**
     * An integer that represents the number of ship spaces that have not yet been sunk
     */
    private int shipSpacesRemaining = 17;
    /**
     * Creates a player's board
     */
    public Player(){

    }

    /**
     * Updates the board according to the location entered
     * @param location is a string in the form of 'A1' representing the
     *                 desired space on the board
     */
    public void play(String location){

    }

    /**
     * Checks
     * @param location is a string in the form of 'A1' representing the
     *                 desired space on the board
     * @return a character: 'o' - open and empty, 'h' - hit,
     *                      'a' - an alive ship, or 'm' -
     */
    public char get(String location){
        return ' ';
    }

}
