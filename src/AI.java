import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class AI extends Player {


    /**
     * isAttackingShip: whether the AI will be random or based upon previous attack
     */
    private boolean isAttackingShip;

    private Map<Integer, String> M = new HashMap<>();

    private ArrayList<String> hits;

    /**
     * Constructor, creates AI, sets AI attack to random
     */
    public AI(){
        boolean isAttackingShip = false;

        hits = new ArrayList<>();

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
    }

    /**
     * AI algorithm to determine which space to attack
     * @return int for attack
     */
    public String aiSelection(){
        Integer guess = 0;
        Random rand = new Random();
        if(!isAttackingShip){
            while (true) {
                guess = rand.nextInt(99) + 1;
                if (!guesses.contains(guess)){
                    break;
                }
            }
        } else {
            for(int i = hits.size() -1; i >= 0; i--){
                if(m.get(hits.get(i))+1 == 'O' || m.get(hits.get(i))+1 == 'A'){
                    guess = m.get(hits.get(i)+1);
                }
                else if(m.get(hits.get(i))+10 == '0' || m.get(hits.get(i))+10 == 'A'){
                    guess = m.get(hits.get(i))+10;
                }
                else if(m.get(hits.get(i))-1 == '0' || m.get(hits.get(i))-1 == 'A'){
                    guess = m.get(hits.get(i))-1;
                }
                else{
                    guess = m.get(hits.get(i))-10;
                }

            }

        }

        guesses.add(guess);



        return M.get(guess);
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
