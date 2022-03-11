package General;

import Airship.Airship;

import java.util.ArrayList;
import java.util.List;

import static General.CsvFileUser.loadValue;
import static General.CsvFileUser.readCSV;

public class TechTree {

    private final ArrayList<Tech> techTree = new ArrayList<>();
    static final List<String[]> techData = new ArrayList<>();

    //Constructor | Use of a singleton
    private TechTree () {
        readCSV("techData.csv", techData);
        for (String[] techInfo : techData) {
            techTree.add(new Tech(techInfo[0], techInfo[1].split(","), Integer.parseInt(techInfo[2]), Integer.parseInt(techInfo[3])));
        }
    }
    private static TechTree INSTANCE = new TechTree();
    public static TechTree getInstance() {
        return INSTANCE;
    }

    public void researchTech(String name) {
        int i = 0;
        Tech tech = techTree.get(0);
        while (!tech.getName().equals(name)) {
            i++;
            tech = techTree.get(i);
        }
    }

    /*public void updateTechTree(int sciencePrice){
        for(Tech tech : techTree) {

            if(tech.getSciencePrice() >= ressource1) {
                t.setCanUnlock(true);
            }
            else {
                t.setCanUnlock(false);
            }

            if(t.getPreviousTech().isUnlocked() && t.canUnlock()){
                //set the previous tech to false if the player unlock the tech
            }
        }
    }*/
}