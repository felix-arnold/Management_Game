package General;

public class Tech {

    private final String name;
    private final String[] previousTech;
    private long scienceCost;
    private final int techType;
    private boolean unlocked;
    private boolean researched;
    private boolean discovered;


    //Constructor
    public Tech(String name, String[] previousTech, int scienceCost, int techType) {
        this.name = name;
        this.previousTech = previousTech;
        this.scienceCost = scienceCost;
        this.techType = techType;

        if (name.equals("I.1") || name.equals("ParadoxalGenerator") || name.equals("IASynthetisTank") || name.equals("TemporalCamboose")) {
            unlocked = discovered = true;
        }
        else {
            unlocked = discovered = false;
        }
        researched = false;
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

    public String[] getPreviousTech() {
        return previousTech;
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