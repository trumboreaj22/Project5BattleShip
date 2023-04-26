import java.util.*;

public class Player {
    /**
     * An array representing the state of the board
     */
    private char[] board = new char[100];

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
        for (int i = 0; i < 10; i++){
            for (int j = 0; j < 10; j++){
                String num = "" + (char)('1' + j);
                if (num.equals(":")){
                    num = "10";
                }
                String key =(char)('A' + i) + num;

                m.put(key, 10 * i + j);
            }
        }
        Arrays.fill(board, 'O');
    }

    /**
     * @return number of ships spaces remaining
     */
    public int getShipSpacesRemaining(){
        return shipSpacesRemaining;
    }

    /**
     * @param shipSpacesRemaining new amount of ships spaces remaining
     */
    public void setShipSpacesRemaining(int shipSpacesRemaining){
        this.shipSpacesRemaining = shipSpacesRemaining;
    }


    /**
     * Updates the board according to the location entered
     * @param location is an int from 0-99 representing the desired space on the board
     */
    public void play(int location) throws Exception {
        /*

        Integer l = location;
        if(guesses.contains(l)){
            throw new Exception();
        }

        if(getStatus(location) == 'O'){
            board[location] = 'M';
        } else {

        }

        guesses.add(l);

         */
    }

    /**
     * Checks the status of the desired space
     * @param location is an int from 0-99 representing the desired space on the board
     * @return a character: 'O' - open water, 'H' - hit ship,
     *                      'A' - alive ship, 'M' - missed water,
     *                      or 'S' - sunk ship
     */
    public char getStatus(int location){
        return board[location];
    }

    /**
     * Translates the location from a string to an int using a map
     * @param location is a String in the form of "A1"
     * @return
     */
    public int playerSelection(String location){
        return m.get(location);
    }

    /**
     * @return a boolean: true if the player sunk all the ships, otherwise false
     */
    public boolean hasWon(){
        return shipSpacesRemaining == 0;
    }
}
