import java.util.Random;
import java.util.Scanner;

public class Game {
    private Player user;
    private Player ai;
    /**
     * Constructs a new game object
     */
    public Game(){
        user = new Player("Player");
        ai = new Player("AI");
    }

    /**
     * runs through a full game
     */
    public void gameLoop(){
        Scanner scn = new Scanner(System.in);

        setup();

        while (true) {
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
                    System.out.println("\nYou Won!");
                    System.out.println(printHiddenBoard(ai));
                    break;
                }
            }
            // System.out.println(printBoard(user));

            String aiGuess;
                try {
                    aiGuess = user.aiSelection();
                    user.play(aiGuess);
                    user.addGuess(user.playerSelection(aiGuess));
                } catch (Exception e) {
                    continue;
                }
            result = user.getStatus(user.playerSelection(aiGuess));
            if (result == 'M'){
                System.out.println("The AI Missed!");
            } else if (result == 'H'){
                System.out.println("The AI Hit!");
                user.setAttackingShip(true);
                user.addHits(aiGuess);
            } else {
                if (user.hasWon()){
                    System.out.println("\nYou Lost to an AI!");
                    System.out.println(printPlayerBoard(user));
                    break;
                }
            }
            if(ai.getHits().size() == 0){
                ai.setAttackingShip(false);
            }

        System.out.println("\nYour Radar");
        System.out.println(printHiddenBoard(ai));

        System.out.println("\nYour Ships");
        System.out.println(printPlayerBoard(user));

        }
    }

    /**
     * Prompts user to set up board
     */
    public void setup(){
        Scanner scn = new Scanner(System.in);
        Random rand = new Random();

        for(int i = 0; i < user.getPlayerShips().size(); i++) {
            System.out.println(printPlayerBoard(user));
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
        System.out.println(printPlayerBoard(user));

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

//    public String printBoard(Player player){
//        String output = "  1 2 3 4 5 6 7 8 9 10";
//        for (int i = 0; i < 100; i++){
//            if ((i) % 10 == 0){
//                output += "\n" + (char)('A' + (i / 10)) + " ";
//            }
//            output += player.getStatus(i) + " ";
//
//
//        }
//        return output;
//    }

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

    public String printPlayerBoard(Player player){
        String output = "  1 2 3 4 5 6 7 8 9 10";
        for (int i = 0; i < 100; i++){
            if ((i) % 10 == 0){
                output += "\n" + (char)('A' + (i / 10)) + " ";
            }
            char next = player.getStatus(i);
            if (next == 'O'){
                output += '-' + " ";
            } else {
                output += player.getStatus(i) + " ";
            }

        }
        return output;
    }
}
