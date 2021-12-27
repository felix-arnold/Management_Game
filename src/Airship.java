import Quarter.Quarter;

import java.util.ArrayList;

public abstract class Airship {

    protected String name;

    /*protected int armour;
    protected int health;
    protected int speed;
    protected int recoveryRate;
    protected int crewCapacity;
    protected int crew;
    protected int electricity;*/
    static int numberOfShip;

    protected ArrayList <Quarter> quarterList = new ArrayList<Quarter>(10);

    public Airship(String name) {
        this.name = name;
        numberOfShip++;
        }

    public String getName() {
        return name;
    }

    public ArrayList<Quarter> getQuarterList() {
        return quarterList;
    }
}
