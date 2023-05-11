import java.util.*;

public class Player {
    /**
     * An array representing the state of the board
     */
    private String name;
    protected char[] board = new char[100];

    /**
     * An integer that represents the number of ship spaces that have not yet been sunk
     */
    protected int shipSpacesRemaining = 17;
    private Ship carrier;
    private Ship battleship;
    private Ship cruiser;
    private Ship submarine;
    private Ship destroyer;
    private ArrayList<Ship> playerShips;
    protected ArrayList<String> hits;
    private Map<Integer, String> M = new HashMap<>();
    private boolean isAttackingShip = false;

    /**
     * A set of integers that represents the spaces on the board that have been already guessed
     */
    protected Set<Integer> guesses = new HashSet<>();

    /**
     * A map that translates a String location 'A1' to an integer 1
     */
    protected Map<String, Integer> m = new HashMap<>();

    /**
     * Constructor - creates a player's board
     */
    public Player(String name){
        this.name = name;
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

        for (int i = 0; i < 10; i++){
            for (int j = 0; j < 10; j++){
                String num = "" + (char)('1' + j);
                if (num.equals(":")){
                    num = "10";
                }
                String key =(char)('A' + i) + num;

                M.put( 10 * i + j, key);
            }
        }

        Arrays.fill(board, 'O');
        carrier = new Ship("Carrier", 5);
        battleship = new Ship("Battleship", 4);
        cruiser = new Ship("Cruiser", 3);
        submarine = new Ship("Submarine", 3);
        destroyer = new Ship("Destroyer", 2);

        playerShips = new ArrayList<>();

        playerShips.add(carrier);
        playerShips.add(battleship);
        playerShips.add(cruiser);
        playerShips.add(submarine);
        playerShips.add(destroyer);

        hits = new ArrayList<>();
    }

    /**
     * @return ArrayList of Ships
     */
    public ArrayList<Ship> getPlayerShips(){
        return playerShips;
    }

    /**
     * @return number of ships spaces remaining
     */
    public int getShipSpacesRemaining(){
        return shipSpacesRemaining;
    }

    public void setShips(int index, boolean isVertical, String location) throws Exception {
        int l = playerSelection(location);
        if (isValidPlacement(playerShips.get(index), isVertical, l)) {
            playerShips.get(index).setShip(isVertical, l);
            for (int i = 0; i < playerShips.get(index).getSize(); i++) {
                if (isVertical) {
                    board[l + i * 10] = 'A';
                } else {
                    board[l + i] = 'A';
                }
            }

        } else {
            throw new Exception();
        }

    }

    public boolean isValidPlacement (Ship s, boolean isVertical, int location) {
        if (isVertical) {
            if ((s.getSize()-1) * 10 + location > 99) {
                return false;
            }
        } else {
            if (((s.getSize()-1) + location) % 10 < location % 10) {
                return false;
            }
        }

        for (int i = 0; i < s.getSize(); i++){
            if (isVertical) {
                if (board[location + 10 * i] != 'O') {
                    return false;
                }
            } else {
                if (board[location + i] != 'O') {
                    return false;
                }
            }
        }

        return true;
    }

    /**
     * @param shipSpacesRemaining new amount of ships spaces remaining
     */
    public void setShipSpacesRemaining(int shipSpacesRemaining){
        this.shipSpacesRemaining = shipSpacesRemaining;
    }


    /**
     * Updates the board according to the location entered
     * @param location is a String in the form "A1" representing the desired space on the board
     */
    public void play(String location) throws Exception {
        int l;
        if (isValidGuess(location)) {
            l = playerSelection(location);
        } else {
            throw new Exception();
        }

        if(getStatus(l) == 'O'){
            board[l] = 'M';
        } else {
            board[l] = 'H';
            shipSpacesRemaining--;
            outerloop:
            for (int i = 0; i < playerShips.size(); i++){
                for (int j = 0; j < playerShips.get(i).getSize(); j++){
                    if (l == playerShips.get(i).getLocations()[j]) {
                        playerShips.get(i).hitShipSpace(j);
                        if (playerShips.get(i).hasSunk()){
                            System.out.println(name + " " + playerShips.get(i).getShipName() + " has been sunk!");
                            for (int shipLocation : playerShips.get(i).getLocations()){
                                board[shipLocation] = 'S';
                                hits.remove(M.get(shipLocation));
                            }
                        }
                        break outerloop;
                    }
                }
            }
        }

        guesses.add(l);
    }

    public boolean isValidGuess(String location){
        try {
            int l = playerSelection(location);
        }
        catch (Exception e){
            return false;
        }
        if(guesses.contains(playerSelection(location))){
            return false;
        }
        return true;
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

    public String aiSelection(){
        Integer guess;
        Random rand = new Random();
        while (true) {
            guess = rand.nextInt(99) + 1;
            if (!guesses.contains(guess)){
                break;
            }
        } if(isAttackingShip) {
            for(int i = hits.size() -1; i >= 0; i--){
                if(((m.get(hits.get(i)) + 1) % 10) > (m.get(hits.get(i)) % 10) && (board[m.get(hits.get(i))+1] == 'O' || board[m.get(hits.get(i))+1] == 'A')){
                    guess = m.get(hits.get(i))+1;
                    break;
                }
                else if((m.get(hits.get(i)) + 10 < 100) && (board[m.get(hits.get(i))+10] == '0' || board[m.get(hits.get(i))+10] == 'A')){
                    guess = m.get(hits.get(i))+10;
                    break;
                }
                else if(((m.get(hits.get(i)) - 1) % 10) < (m.get(hits.get(i)) % 10) && (board[m.get(hits.get(i))-1] == 'O' || board[m.get(hits.get(i))-1] == 'A')){
                    guess = m.get(hits.get(i))-1;
                    break;
                }
                else if((m.get(hits.get(i)) - 10 > 0) && (board[m.get(hits.get(i))-10] == '0' || board[m.get(hits.get(i))-10] == 'A')){
                    guess = m.get(hits.get(i))-10;
                    break;
                }

            }

        }


        return M.get(guess);
    }

    /**
     * @return a boolean: true if the player sunk all the ships, otherwise false
     */
    public boolean hasWon(){
        return shipSpacesRemaining == 0;
    }

    public void addGuess(Integer guess) {
        guesses.add(guess);
    }

    public void setAttackingShip(boolean isAttackingShip) {
        this.isAttackingShip = isAttackingShip;
    }

    public ArrayList getHits() {
        return hits;
    }

    public void addHits(String hit){
        hits.add(hit);
    }
}
