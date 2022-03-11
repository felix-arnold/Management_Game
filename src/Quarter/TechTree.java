package Quarter;

import java.util.ArrayList;

public class TechTree {

    private ArrayList<Tech> techTree = new ArrayList<>();

    public TechTree(Tech firstTech) {
        techTree.add(firstTech);
    }

    private void addTech(Tech tech){
        techTree.add(tech);
    }

    public void updateTechTree(int ressource1, int ressource2){
        for(Tech t : techTree) {

            if((t.getRequiredRessource1()>=ressource1)&&(t.getRequiredRessource2()>=ressource2)) {
                t.setCanUnlock(true);
            }
            else {
                t.setCanUnlock(false);
            }

            if(t.getPreviousTech().isUnlocked() && t.canUnlock()){
                //set the previous tech to false if the player unlock the tech
            }
        }
    }

}
