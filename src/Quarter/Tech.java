package Quarter;

public class Tech {
    private String techType;
    private boolean unlocked;
    private int requiredRessource1;
    private int requiredRessource2;

    public Tech(String techType, int ressource1, int ressource2) {
        this.techType = techType;
        unlocked = false;
        requiredRessource1 = ressource1;
        requiredRessource2 = ressource2;
    }

    public boolean isUnlocked() {
        return unlocked;
    }

    public void techUpdate(int ressource1, int ressource2) {
        if((requiredRessource1==ressource1)&&(requiredRessource2==ressource2)) {
            unlocked = true;
        }
    }
}
