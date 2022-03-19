package Combat;

import Combat.Unit.FlyingUnit;
import General.Airship;
import Combat.Unit.Unit;
import com.sun.javafx.print.Units;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class FightAirship {

    //Constructor
    public FightAirship(Airship airship) {
        this.maxHullIntegrity = airship.getMaxHullIntegrity();
        this.maxShield = airship.getMaxShield();
        this.armorRating = airship.getArmorRating();
        this.speed = airship.getSpeed();
        this.unitList = airship.getUnitList();

        this.hullIntegrity = airship.getHullIntegrity();
        this.shield = maxShield;

    }


    private final int maxHullIntegrity;
    private final int maxShield;
    private final int armorRating;
    private final int speed;
    private final ArrayList<Unit> unitList;

    private int hullIntegrity;
    private int shield;
    private int field = -1;
    private int position;

    private boolean canMove = true;
    private int turnPreviousMove;

    private final ArrayList<Units> bombingUnitsList= new ArrayList<>();
    private final ArrayList<FlyingUnit> flyingUnitsList= new ArrayList<>();


    private boolean destroyed=false;


    public void udpateCanMove(int turn) {
        if (turn - turnPreviousMove >= speed) {
            canMove = true;
            turnPreviousMove = turn;
        }
    }


    public int getField() {
        return field;
    }
    public int getPosition() {
        return position;
    }
    public int getMaxShield() {
        return maxShield;
    }
    public int getMaxHullIntegrity() {
        return maxHullIntegrity;
    }
    public int getShield() {
        return shield;
    }
    public int getArmorRating() {
        return armorRating;
    }
    public int getHullIntegrity() {
        return hullIntegrity;
    }
    public ArrayList<Unit> getUnitList() {
        return unitList;
    }
    public int getSpeed() {
        return speed;
    }
    public int getTurnPreviousMove() {
        return turnPreviousMove;
    }
    public boolean canMove() {
        return canMove;
    }

    public void hasMove() {
        canMove = false;
    }
    public void setHullIntegrity(int hullIntegrity) {
        this.hullIntegrity = hullIntegrity;
    }
    public void setShield(int shield) {
        this.shield = shield;
    }
    public void setField(int field) {
        this.field = field;
    }
    public void setPosition(int position) {
        this.position = position;
    }

    public void setDestroyed() {
        destroyed = true;
    }
    public boolean isDestroyed() {
        return destroyed;
    }

    public ArrayList<FlyingUnit> getFlyingUnitsList() {
        return flyingUnitsList;
    }
    public ArrayList<Units> getBombingUnitsList() {
        return bombingUnitsList;
    }

}
