package General;

import java.util.ArrayList;
import java.util.Objects;

import Quarter.ProductionQuarter.ParadoxalGenerator;
import Quarter.Quarter;
import Quarter.QuarterFactory;

import static Quarter.QuarterFactory.getQuarter;

public class Tech {

    private final String name;
    private final ArrayList<Integer> previousTechIndex;
    private final ArrayList<Integer> nextTechIndex;
    private long scienceCost;
    private final String effect;

    private boolean unlocked;
    private boolean researched;
    private boolean discovered;


    //Constructor
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


    //To apply the tech effect
    public void applyTech() {
        if (effect.equals("airship")) {
            GlobalManager.getInstance().getUnlockedAirships().add(name);
        }
        if (effect.equals("quarter")) {
            GlobalManager.getInstance().getUnlockedQuarters().add(getQuarter(name,1));
        }
    }


    //Getter and Setter
    //if it is possible to research it
    public boolean isUnlocked() {
        return unlocked;
    }
    public void setUnlocked() {
        unlocked = true;
    }

    //if it is already researched
    public boolean isDiscovered() {
        return discovered;
    }
    public void setDiscovered() {
        discovered = true;
    }

    //if it is being researched
    public boolean isResearched() {
        return researched;
    }
    public void setResearched(boolean status) {
        researched = status;
    }

    public ArrayList<Integer> getPreviousTechIndex() {
        return previousTechIndex;
    }
    public ArrayList<Integer> getNextTechIndex() {
        return nextTechIndex;
    }
    public long getScienceCost() {
        return scienceCost;
    }
    public void consumeScience (long scienceAmount) {
        scienceCost -= scienceAmount;
    }
    public String getName() {
        return name;
    }

}