import java.util.Scanner;

public class Game {
    private Player user;
    private Player ai;
    /**
     * Constructs a new game object
     */
    public Game(){
        user = new Player();
        ai = new AI();
    }

    /**
     * runs through a full game
     */
    public void gameLoop(){
        setup();

    }

    /**
     * Prompts user to set up board
     */
    public void setup(){
        System.out.println(printBoard(user));
        Scanner scn = new Scanner(System.in);
        String[] locations =  new String[5];
        boolean[] isVerticals = new boolean[5];
        for(int i = 0; i < playerShips.size(); i++) {
            System.out.println("Location for " + user.getPlayerShips(i) + "?");
            locations[i] = scn.next();
            System.out.println("Is the ship vertical?");
            while(true) {
                String response = scn.next();
                if (response.toUpperCase().charAt(0) == 'Y') {
                    isVerticals[i] = true;
                    break;
                } else if (response.toUpperCase().charAt(0) == 'N') {
                    isVerticals[i] = false;
                    break;
                }
                System.out.println("Y or N");
            }
            setShips(locations, isVerticals);
        }
    }

    public String printBoard(Player player){
        String output = "  1 2 3 4 5 6 7 8 9 10";
        for (int i = 0; i < 100; i++){
            if ((i) % 10 == 0){
                output += "\n" + (char)('A' + (i / 10)) + " ";
            }
            output += player.getStatus(i) + " ";

        }
        return output;
    }
}
