public class Ship {

    /**
     * shipName: ship type/name
     * shipStatus: each char is status of part of sip,
     *      size is also determined through size of array
     *      'A' - alive ship
     *      'H' - hit ship
     *      'S' - sunk ship
     * direction: whether ship is positioned vertical or horizontal
     * location: space where top of ship is located
     */
    private String shipName;

    private char[] shipStatus;

    private boolean isVertical;

    private int location;

    /**
     * Contractor for ship
     * @param shipName String, name of ship
     * @param size int, sets the size of shipStatus array
     * @param Direction boolean, horizontal[false] vertical[True]
     * @param location int, board location for ship
     */
    public Ship(String shipName, int size, boolean Direction, int location){
        //TODO
    }

    /**
     *
     * @return boolean if ship has sunk
     */
    public boolean hasSunk(){
        //TODO
        return false;
    }

    /**
     * if hasSunk() is true, sets all shipStatus chars to 'S'
     */
    public void sinkShip(){
        //TODO
    }
}
