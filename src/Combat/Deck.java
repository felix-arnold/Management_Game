package Combat;

import java.util.ArrayList;
import java.util.Random;

public class Deck {
    private final int MAX_IN_HAND = 2;


    private ArrayList<WeaponActionCard> deck = new ArrayList<>();
    private WeaponActionCard[] hands_card = new WeaponActionCard[MAX_IN_HAND];

    private Random random = new Random();
    private int card_in_hand;

    public Deck(ArrayList<WeaponActionCard> deck) {
        this.deck = deck;
        card_in_hand = 2;
        //XML pour avoir toutes les cartes dans le deck

        //Initialiser deux cartes dans la main
        hands_card[0] = deck.get(random.nextInt(deck.size()));
        hands_card[1] = deck.get(random.nextInt(deck.size()));
    }

    public ArrayList<WeaponActionCard> getDeck () {
        return deck;
    }

    public WeaponActionCard[] getHands_card() {
        return hands_card;
    }

    public void newTurn() {
        while(card_in_hand < MAX_IN_HAND){
            if(hands_card[0]==null){
                hands_card[0] = deck.get(random.nextInt(deck.size()));
                card_in_hand++;
            }
            else {
                hands_card[1] = deck.get(random.nextInt(deck.size()));
                card_in_hand++;
            }
        }
    }

    public void discard(int index) {
        hands_card[index] = null;
        card_in_hand--;
    }

}
