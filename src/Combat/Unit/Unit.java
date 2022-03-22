package Combat.Unit;

import Combat.ActionCard;
import Combat.FightAirship;
import Combat.WeaponActionCard;

import java.util.ArrayList;

import static General.XMLReader.readXML;
import static java.lang.Math.*;
import static java.lang.Math.random;

public abstract class Unit {

    //Constructor
    public Unit(String name, int level) {
        this.name=name;
        this.level=level;
    }

    protected final String name;
    protected final int level;
    public String getName() {
        return name;
    }
    public int getLevel() {
        return level;
    }

    protected boolean canBombing;
    protected boolean canBoarding;

    protected ArrayList<WeaponActionCard> actionCardsList = new ArrayList<>();

    public boolean CanBoarding() {
        return canBoarding;
    }
    public boolean CanBombing() {
        return canBombing;
    }

    public ArrayList<WeaponActionCard> getActionCardsList() {
        return actionCardsList;
    }

    //VARIABLE GARDANT LE NAVIRE AUQUEL IL APPARTIENT



    private ArrayList<ActionCard> drawCards = new ArrayList<>();
    public ArrayList<ActionCard> getDrawCards() {
        return drawCards;
    }

    private ArrayList<ActionCard> usedCards = new ArrayList<>();
    public ArrayList<ActionCard> getUsedCards() {
        return usedCards;
    }

    //Draw card among the one available for the selected unit
    public void drawCards(int numberOfCard) {
        while (drawCards.size()<numberOfCard) {
            int r = toIntExact(round(floor(random() * actionCardsList.size())));
            if (!usedCards.contains(getActionCardsList().get(r)))
                drawCards.add(getActionCardsList().get(r));
                usedCards.add(getActionCardsList().get(r));
            if (usedCards.size() <= 1) {
                usedCards.clear();
            }
        }
    }


}
