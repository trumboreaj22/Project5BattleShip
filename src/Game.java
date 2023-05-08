import java.util.Random;
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
        Scanner scn = new Scanner(System.in);

        setup();

        boolean userIsGuessing = true;
        while (true) {
            if (userIsGuessing) {
                System.out.println("Enter a space to guess:");
                String guess = scn.next();
                guess = guess.toUpperCase();
                try{
                    ai.play(guess);
                }
                catch (Exception e){
                    System.out.println("Make sure the space has not been guessed yet and is in the form \"A1\"");
                    continue;
                }

                char result = ai.getStatus(ai.playerSelection(guess));
                if (result == 'M'){
                    System.out.println("Miss!");
                } else if (result == 'H'){
                    System.out.println("Hit!");
                } else {
                    if (ai.hasWon()){
                        break;
                    }
                }
                System.out.println(printHiddenBoard(ai));
            }
        }

    }

    /**
     * Prompts user to set up board
     */
    public void setup(){
        System.out.println(printBoard(user));
        Scanner scn = new Scanner(System.in);
        Random rand = new Random();

        for(int i = 0; i < user.getPlayerShips().size(); i++) {
            System.out.println("Location for " + user.getPlayerShips().get(i).getShipName() + " (length of " + user.getPlayerShips().get(i).getSize() + ")?");
            String location = scn.next();
            location = location.toUpperCase();

            System.out.println("Is the ship vertical? ('Y' or 'N')");
            while(true) {
                String response = scn.next();
                if (response.toUpperCase().charAt(0) == 'Y') {
                    try {
                        user.setShips(i, true, location);
                        break;
                    }
                    catch(Exception e){
                        System.out.println("Invalid Input, ship overlaps other ship or edge");
                        i--;
                        break;
                    }
                } else if (response.toUpperCase().charAt(0) == 'N') {
                    try {
                        user.setShips(i, false, location);
                        break;
                    }
                    catch(Exception e){
                        System.out.println("Invalid Input, ship overlaps other ship or edge");
                        i--;
                        break;
                    }
                } else if ((response.toUpperCase().charAt(0) != 'N')||(response.toUpperCase().charAt(0) != 'Y')) {
                    System.out.println("Y or N");
                }
            }
        }

        // setting up AI board randomly
        int index = 0;
        while(true) {
            String row = (char)(rand.nextInt(10) + 65) + "";
            String col = (char)(rand.nextInt(10) + 49) + "";
            if (col.equals(":")) {
                col = "10";
            }
            String location = row + col;
            boolean isVertical = rand.nextBoolean();
            try {
                ai.setShips(index, isVertical, location);
                index++;
                if (index == 5) {
                    break;
                }
            }
            catch (Exception e) { }
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

    public String printHiddenBoard(Player player){
        String output = "  1 2 3 4 5 6 7 8 9 10";
        for (int i = 0; i < 100; i++){
            if ((i) % 10 == 0){
                output += "\n" + (char)('A' + (i / 10)) + " ";
            }
            char next = player.getStatus(i);
            if (next == 'O' || next == 'A'){
                output += '-' + " ";
            } else {
                output += player.getStatus(i) + " ";
            }

        }
        return output;
    }
}
