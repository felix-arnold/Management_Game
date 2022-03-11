package General;

public class Tech {

    private final String name;
    private final String[] previousTech;
    private final int sciencePrice;
    private final int techType;
    private boolean unlocked;
    private boolean researched;


    //Constructor
    public Tech(String name, String[] previousTech, int sciencePrice, int techType) {
        this.name = name;
        this.previousTech = previousTech;
        this.sciencePrice = sciencePrice;
        this.techType = techType;

        if (name.equals("I.1") || name.equals("ParadoxalGenerator") || name.equals("IASynthetisTank") || name.equals("TemporalCamboose")) {
            unlocked = researched = true;
        }
        else {
            unlocked = researched = false;
        }
    }

    //Getter and Setter
    public boolean isUnlocked() {
        return unlocked;
    }
    public void setUnlocked() {
        unlocked = true;
    }
    public boolean isResearched() {
        return researched;
    }
    public void setResearched() {
        researched = true;
    }
    public String[] getPreviousTech() {
        return previousTech;
    }
    public int getSciencePrice() {
        return sciencePrice;
    }
    public String getName() {
        return name;
    }
}