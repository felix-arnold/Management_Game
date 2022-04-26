package Management.GeneralManagement;

import java.util.ArrayList;

import static Management.Quarter.QuarterFactory.getQuarter;

/**
 * Tech is the class describing the properties of a technology, which, once researched, unlock a certain quarter or airship.
 */
public class Tech {

    /**
     * Name of the technology.
     */
    private final String name;
    /**
     * List of the index of every technology needed to research this one.
     */
    private final ArrayList<Integer> previousTechIndex;
    /**
     * List of the index of every technology that needs this one to be researched.
     */
    private final ArrayList<Integer> nextTechIndex;
    /**
     * Amount of science the technology need to be fully researched.
     */
    private long scienceCost;
    /**
     * Type of element the technology unlock. It can either be "airship" or either "quarter".
     */
    private final String effect;

    /**
     * Property of a technology which can be researched.
     */
    private boolean unlocked;
    /**
     * Property of the technology being currently researched.
     */
    private boolean researched;
    /**
     * Property of a technology which has already been researched.
     */
    private boolean discovered;


    /**
     * Creates a new technology and initialises its different properties with the given arguments.
     * <br>The properties researched and discovered are set to false, as well as unlocked, except when there is no predecessor, specified by an index "0" in previousTechIndex.
     * @param name the name of the technology
     * @param previousTechIndex the list of the index of the technologies needed to research this one
     * @param nextTechIndex the list of the index of the technologies which need this one to be researched
     * @param scienceCost the amount of science needed to research it
     * @param effect the type of the element the technology unlock
     */
    public Tech(String name, ArrayList<Integer> previousTechIndex, ArrayList<Integer> nextTechIndex, int scienceCost, String effect) {
        this.name = name;
        this.previousTechIndex = previousTechIndex;
        this.nextTechIndex = nextTechIndex;
        this.scienceCost = scienceCost;
        this.effect = effect;

        if (previousTechIndex.get(0) == 0) {
            unlocked = discovered = true;
            applyTech();
        }
        else {
            unlocked = discovered = false;
        }
        researched = false;
    }


    /**
     * Applies the effect of the technology by unlocked the airship or quarter currently describe in their name.
     * <br>By unlocking an element, the method adds either the name of the airship to the unlockedAirships list in the instance of GlobalManager, either the specified quarter to the property unlockedQuarters list in the instance of GlobalManager.
     */
    public void applyTech() {
        if (effect.equals("airship")) {
            GlobalManager.getInstance().getUnlockedAirships().add(name);
        }
        if (effect.equals("quarter")) {
            GlobalManager.getInstance().getUnlockedQuarters().add(getQuarter(name,1));
        }
    }


    /**
     * Returns the value of the property unlocked.
     */
    public boolean isUnlocked() {
        return unlocked;
    }

    /**
     * Sets the value of the property unlocked to true.
     */
    public void setUnlocked() {
        unlocked = true;
    }

    /**
     * Returns the value of the property discovered.
     */
    public boolean isDiscovered() {
        return discovered;
    }

    /**
     * Sets the value of the property discovered to true.
     */
    public void setDiscovered() {
        discovered = true;
    }

    /**
     * Returns the value of the property researched.
     */
    public boolean isResearched() {
        return researched;
    }

    /**
     * Sets the value of the property researched.
     * @param status the new value of the property researched
     */
    public void setResearched(boolean status) {
        researched = status;
    }

    /**
     * Returns the value of the property previousTechIndex.
     */
    public ArrayList<Integer> getPreviousTechIndex() {
        return previousTechIndex;
    }

    /**
     * Returns the value of the property nextTechIndex.
     */
    public ArrayList<Integer> getNextTechIndex() {
        return nextTechIndex;
    }
    /**
     * Returns the value of the property scienceCost.
     */
    public long getScienceCost() {
        return scienceCost;
    }

    /**
     * Decreases the value of the property scienceCost by the value of science being used to research the technology.
     * @param scienceAmount the value sciencCost must be decreased
     */
    public void consumeScience (long scienceAmount) {
        scienceCost -= scienceAmount;
    }

    /**
     * Return the value of the property name.
     */
    public String getName() {
        return name;
    }

}