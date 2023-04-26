import java.util.Arrays;

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
    private final String shipName;

    private final char[] shipStatus;

    private final boolean isVertical;

    private final int location;

    /**
     * Constructor for ship
     * @param shipName String, name of ship
     * @param size int, sets the size of shipStatus array
     * @param isVertical boolean, horizontal[false] vertical[True]
     * @param location int, board location for ship
     */
    public Ship(String shipName, int size, boolean isVertical, int location){
        this.shipName = shipName;
        shipStatus = new char[size];
        Arrays.fill(shipStatus, 'A');
        this.isVertical = isVertical;
        this.location = location;
    }

    /**
     * @return name of ship
     */
    public String getShipName(){
        return shipName;
    }

    /**
     * @return length of shipStatus array
     */
    public int getSize(){
        return shipStatus.length;
    }

    /**
     * @return location of edge of ship
     */
    public int getLocation() {
        return location;
    }

    /**
     * @return whether the ship is vertical or not
     */
    public boolean getIsVertical(){
        return isVertical;
    }

    /**
     * @return boolean if ship has sunk
     */
    public boolean hasSunk(){
        for (char status : shipStatus) {
            if (status != 'H') {
                return false;
                //if at any point there is an 'Alive' spot of ship, return false
            }
        }
        return true;
    }

    /**
     * if hasSunk() is true, sets all shipStatus chars to 'S'
     */
    public void sinkShip(){
        Arrays.fill(shipStatus, 'S');

    }
}
