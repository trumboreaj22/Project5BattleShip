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
