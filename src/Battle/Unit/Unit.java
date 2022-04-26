package Battle.Unit;

import Battle.GeneralBattle.WeaponActionCard;

import java.util.ArrayList;

/**
 * This abstract class regroups the different methods and properties common to every type of units.
 */
public abstract class Unit {


    /**
     *Creates a new instance of unit.
     * @param name the name of the unit
     * @param level the level of the unit
     */
    public Unit(String name, int level) {
        this.name = name;
        this.level = level;
    }

    /**
     * Name of the unit.
     */
    protected final String name;
    /**
     * Level of the unit.
     */
    protected final int level;

    /**
     * Return the name of the unit.
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the level of the unit.
     */
    public int getLevel() {
        return level;
    }

    /**
     * Property of a unit used in the bombing phase of the combat.
     */
    protected boolean canBombing;
    /**
     * Property of a unit used in the boarding phase of the combat.
     */
    protected boolean canBoarding;

    /**
     * List of the available action cards of the unit.
     */
    protected final ArrayList<WeaponActionCard> actionCardsList = new ArrayList<>();
}
