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

    private char[] shipStatus;

    private boolean isVertical;

    private int[] locations;

    /**
     * Constructor for ship
     * @param shipName String, name of ship
     * @param size int, sets the size of shipStatus array
     * isVertical boolean, horizontal[false] vertical[True]
     * locations int array, board locations of ship
     */
    public Ship(String shipName, int size){
        this.shipName = shipName;
        shipStatus = new char[size];
        Arrays.fill(shipStatus, 'A');
        isVertical = false;
        locations = new int[size];
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
    public int[] getLocations() {
        return locations;
    }

    /**
     * @return whether the ship is vertical or not
     */
    public boolean getIsVertical(){
        return isVertical;
    }

    public void setShip (boolean isVertical, int location){
        this.isVertical = isVertical;
        for (int i = 0; i < shipStatus.length; i++){
            if (isVertical){
                locations[i] = location + 10 * i;
            } else {
                locations[i] = location + i;
            }
        }
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
     * Updates array[index] to hit
     * @param index int of which space was hit
     */
    public void hitShipSpace(int index){
        shipStatus[index] = 'H';
    }
}
