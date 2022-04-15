package Combat;

import Combat.Unit.FlyingUnit;
import Combat.Unit.LandUnit;
import Combat.Unit.Weapon;
import General.Airship;
import Combat.Unit.Unit;
import com.sun.javafx.print.Units;

import java.util.ArrayList;

public class FightAirship {


    //Constructor
    public FightAirship(Airship airship) {
        this.maxHullIntegrity = airship.getMaxHullIntegrity();
        this.maxShield = airship.getMaxShield();
        this.hullArmorRating = airship.getHullArmorRating();
        this.shieldArmorRating = airship.getShieldArmorRating();
        this.speed = airship.getSpeed();
        this.unitList = airship.getUnitList();

        this.hullIntegrity = airship.getHullIntegrity();
        this.shield = maxShield;

    }


    private final int maxHullIntegrity;
    private final int maxShield;
    private final int hullArmorRating;
    private final int shieldArmorRating;
    private double speed=0;
    private final ArrayList<Unit> unitList;

    private int hullIntegrity;
    private int shield;
    private int field = -1;
    private int position;
    private double crewHealth=100;

    private boolean canMove = true;
    private int turnPreviousMove=0;

    private final ArrayList<Weapon> weaponsList= new ArrayList<>();
    private final ArrayList<FlyingUnit> flyingUnitsList= new ArrayList<>();
    private final ArrayList<LandUnit> hatsList= new ArrayList<>();


    private boolean destroyed=false;


    public void udpateCanMove(int turn) {
        if (turn - turnPreviousMove >= speed) {
            canMove = true;
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
    public int getHullArmorRating() {
        return hullArmorRating;
    }
    public int getShieldArmorRating() {
        return shieldArmorRating;
    }
    public int getHullIntegrity() {
        return hullIntegrity;
    }
    public ArrayList<Unit> getUnitList() {
        return unitList;
    }
    public double getSpeed() {
        return speed;
    }
    public int getTurnPreviousMove() {
        return turnPreviousMove;
    }
    public boolean canMove() {
        return canMove;
    }
    public double getCrewHealth() {
        return crewHealth;
    }

    public void hasMove() {
        canMove = false;
    }
    public void adjustHullIntegrity(double hullIntegrity) {
        this.hullIntegrity += hullIntegrity;
    }
    public void adjustShield(double shield) {
        this.shield += shield;
    }
    public void setField(int field) {
        this.field = field;
    }
    public void setPosition(int position) {
        this.position = position;
    }
    public void adjustCrewHealth(double crewHealth) {
        this.crewHealth+=crewHealth;
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
    public ArrayList<Weapon> getWeaponsList() {
        return weaponsList;
    }
    public ArrayList<LandUnit> getHatsList() {
        return hatsList;
    }


    //If the airship is ignited
    private int numberOfFireStart = 0;
    public int getNumberOfFireStart() {
        return numberOfFireStart;
    }
    public void ignite(int newFireStarts) {
        numberOfFireStart+=newFireStarts;
    }
    
    
    private int vulnerabilities=0;
    public int getVulnerabilities() {
        return vulnerabilities;
    }
    public void changeVulnerability(int amount) {
        vulnerabilities+=amount;
    }
    
    
    public void slow(double slowAmount) {
        speed-=slowAmount;
    }
    
    public ArrayList<Weapon> getWeaponList() {
        return weaponsList;
    }
}
