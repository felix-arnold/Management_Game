package Combat.Unit;

import Combat.ActionCard;

import java.util.ArrayList;

import static General.XMLReader.readXML;

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

    protected ArrayList<ActionCard> actionCardsList = new ArrayList<>();


    public boolean CanBoarding() {
        return canBoarding;
    }
    public boolean CanBombing() {
        return canBombing;
    }

    public ArrayList<ActionCard> getActionCardsList() {
        return actionCardsList;
    }

    //VARIABLE GARDANT LE NAVIRE AUQUEL IL APPARTIENT

}
