import java.util.ArrayList;
import java.util.Random;

public class AI extends Player {


    /**
     * isAttackingShip: whether the AI will be random or based upon previous attack
     */
    private boolean isAttackingShip;

    private ArrayList<Integer> hits;

    /**
     * Constructor, creates AI, sets AI attack to random
     */
    public AI(){
        boolean isAttackingShip = false;

        hits = new ArrayList<>();
    }

    /**
     * AI algorithm to determine which space to attack
     * @return int for attack
     */
    public int aiSelection(){
        Integer guess;
        /*
        Random rand = new Random();
        if(!isAttackingShip){
            while (true) {
                guess = rand.nextInt(99) + 1;
                if (!guesses.contains(guess)){
                    break;
                }
            }
        } else {

        }

        guesses.add(guess);

         */
        return guess;
    }
}
