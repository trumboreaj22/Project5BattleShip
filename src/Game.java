public class Game {
    /**
     * Constructs a new game object
     */
    public Game(){
        Player user = new Player();
        Player ai = new AI();
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

    }

    public String printBoard(Player player){
        String output = "";
        for (int i = 0; i < 99; i++){
            output += player.getStatus(i) + " ";
            if ((i) % 9 == 0){
                output += "\n" + 'A';
            }
        }
        return output;
    }
}
