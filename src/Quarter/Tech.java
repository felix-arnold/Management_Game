package Quarter;

public class Tech {
    private String techType;
    private Tech previousTech;
    private boolean unlocked;
    private boolean canUnlock;
    private final int requiredRessource1;
    private final int requiredRessource2;

    public Tech(String techType, int ressource1, int ressource2, Tech previousTech) {
        this.techType = techType;
        unlocked = false;
        canUnlock = false;
        requiredRessource1 = ressource1;
        requiredRessource2 = ressource2;
        this.previousTech = previousTech;
    }

    public boolean isUnlocked() {
        return unlocked;
    }

    public void setUnlocked(boolean bool) {
        unlocked = bool;
    }

    public void setCanUnlock(boolean bool) {
        canUnlock = bool;
    }

    public boolean canUnlock() {
        return canUnlock;
    }

    public Tech getPreviousTech() {
        return previousTech;
    }

    public int getRequiredRessource1() {
        return requiredRessource1;
    }

    public int getRequiredRessource2() {
        return requiredRessource2;
    }
}
