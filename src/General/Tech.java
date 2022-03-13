package General;

import java.util.ArrayList;
import java.util.Objects;

import Quarter.QuarterFactory;

public class Tech {

    private final String name;
    private final ArrayList<Integer> previousTechIndex;
    private final ArrayList<Integer> nextTechIndex;
    private long scienceCost;
    private final boolean unlockTechType;
    private final String effect;

    private boolean unlocked;
    private boolean researched;
    private boolean discovered;


    //Constructor
    public Tech(String name, ArrayList<Integer> previousTechIndex, ArrayList<Integer> nextTechIndex, int scienceCost, boolean techType, String effect) {
        this.name = name;
        this.previousTechIndex = previousTechIndex;
        this.nextTechIndex = nextTechIndex;
        this.scienceCost = scienceCost;
        this.unlockTechType = techType;
        this.effect = effect;

        if (name.equals("I.1") || name.equals("ParadoxalGenerator") || name.equals("IASynthetisTank") || name.equals("TemporalCamboose")) {
            unlocked = discovered = true;
        }
        else {
            unlocked = discovered = false;
        }
        researched = false;
    }


    //To apply the tech effect
    public void applyTech() {
        if (unlockTechType) {
            Objects.requireNonNull(QuarterFactory.getQuarter(effect)).unlock();
        }
        else {
            //ECRIRE UN IF PAR TECH DE CE TYPE !!!!!!
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